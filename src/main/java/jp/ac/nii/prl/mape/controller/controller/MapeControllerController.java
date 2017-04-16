package jp.ac.nii.prl.mape.controller.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import jp.ac.nii.prl.mape.controller.configuration.ControllerConfigurationProperties;
import jp.ac.nii.prl.mape.controller.model.APConcern;
import jp.ac.nii.prl.mape.controller.model.MAPE;
import jp.ac.nii.prl.mape.controller.model.MAPEKComponent;
import jp.ac.nii.prl.mape.controller.model.TimeData;
import jp.ac.nii.prl.mape.controller.model.Timing;
import jp.ac.nii.prl.mape.controller.service.MAPEService;
import jp.ac.nii.prl.mape.controller.service.TimingService;
import jp.ac.nii.prl.mape.controller.tree.CloudTree;

@RestController
@Component
@RequestMapping("/mape")
public class MapeControllerController {
	
	private final ControllerConfigurationProperties properties;
	
	private final MAPEService mapeService;
	private final TimingService timingService;
	
	@Autowired
	public MapeControllerController(ControllerConfigurationProperties properties,
			TimingService timingService,
			MAPEService mapeService) {
		this.properties = properties;
		this.timingService = timingService;
		this.mapeService = mapeService;
	}
	
//	@Scheduled(fixedRate=900000) // every 15 minutes
	public void mape() {
		System.out.println("MAPE");
		MAPE mape = createMAPELoop();
		mapeService.loop(mape);
	}
	
//	@Scheduled(fixedRate=50000)
//	public void testTree() {
//		CloudTree tree = CloudTree.getInstance();
//		tree.analyse();
//	}
	
	@RequestMapping(value="/icse", method=RequestMethod.GET)
	public TimeData icse() {
		TimeData time = new TimeData();
		RestTemplate restTemplate = new RestTemplate();
		time.setMape_start(System.currentTimeMillis());
		
		// get data from monitor
		time.setMonitoring_start(System.currentTimeMillis());
		String source = restTemplate.getForObject("http://localhost:8080/monitor", String.class);
		time.setMonitoring_stop(System.currentTimeMillis());
		
		// update KB
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(source, headers);
		time.setUpdate_start(System.currentTimeMillis());
		restTemplate.postForEntity("http://localhost:8083/kb/source", entity, Object.class);
		time.setUpdate_stop(System.currentTimeMillis());
		
		// analyse and plan
		CloudTree tree = CloudTree.getInstance();
		tree.analyse(time);
		
		// get execution
		time.setExecution_get_start(System.currentTimeMillis());
		String execute = restTemplate.getForObject("http://localhost:8083/kb/get/execution", String.class);
		time.setExecution_get_stop(System.currentTimeMillis());
		
		//finish
		time.setMape_stop(System.currentTimeMillis());
		System.out.println(execute);
		return time;
	}
	
	@RequestMapping(value="/mape", method=RequestMethod.GET)
	public ResponseEntity<?> doMape() {
		mape();
		// create response
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
	}
	
	@RequestMapping(value="/ape", method=RequestMethod.POST)
	public ResponseEntity<?> ape(@RequestBody String monitor) {
		
		RestTemplate restTemplate = new RestTemplate();
        String view = restTemplate.getForObject("http://localhost:8080/kb/get/autoScaling", String.class);
        System.out.println("Got AS view from KB");
        System.out.println(view);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(view, headers);
        URI location = restTemplate.postForLocation("http://localhost:8081/deployment/", entity);
        System.out.println("AS analysis called");
        view = restTemplate.getForObject(location, String.class);
        System.out.println("AS analysis retrieved");
        System.out.println(view);
        entity = new HttpEntity<String>(view, headers);
        location = restTemplate.postForLocation("http://localhost:8082/deployment/", entity);
        System.out.println("AS plan called");
        view = restTemplate.getForObject(location, String.class);
        System.out.println("AS plan retrieved");
        System.out.println(view);
        entity = new HttpEntity<String>(view, headers);
        restTemplate.postForEntity("http://localhost:8080/kb/put/autoScaling", entity, String.class);
        System.out.println("AS put completed");
        
		
		// create response
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
	}
	
	@RequestMapping(value="/timings", method=RequestMethod.GET)
	public List<Timing> getTimings() {
		return timingService.findAll();
	}
	
	private MAPE createMAPELoop() {
		MAPE mape = new MAPE();
		MAPEKComponent kb = new MAPEKComponent();
		kb.setBaseUrl("http://localhost:8080");
		kb.setEndpoint("/kb");
		
		mape.setKb(kb);
		
		MAPEKComponent monitor = new MAPEKComponent();
		monitor.setBaseUrl("http://localhost:8081");
		monitor.setEndpoint("/monitor");
		
		mape.setMonitor(monitor);
		
		APConcern autoScaling = new APConcern();
		autoScaling.setName("autoScaling");
		
		MAPEKComponent asAnalyser = new MAPEKComponent();
		asAnalyser.setBaseUrl("http://localhost:8082");
		asAnalyser.setEndpoint("/deployment");
		asAnalyser.setParameter("sg-77d40013");
		
		autoScaling.setAnalyser(asAnalyser);
		
		MAPEKComponent asPlanner = new MAPEKComponent();
		asPlanner.setBaseUrl("http://localhost:8090");
		asPlanner.setEndpoint("/deployment");
		asPlanner.setParameter("sg-77d40013");
		
		autoScaling.setPlanner(asPlanner);;
		
		mape.addAP(autoScaling);
		
		APConcern redundancy = new APConcern();
		redundancy.setName("redundancy");
		
		MAPEKComponent redAP = new MAPEKComponent();
		redAP.setBaseUrl("http://localhost:8099");
		redAP.setEndpoint("/redundancy");
		
		redundancy.setCombined(redAP);
		
		mape.addAP(redundancy);
		
		APConcern firewall = new APConcern();
		firewall.setName("firewall");
		
		MAPEKComponent firewallService = new MAPEKComponent();
		firewallService.setBaseUrl("http://localhost:8084");
		firewallService.setEndpoint("/firewall");
		
		firewall.setCombined(firewallService);
		
		mape.addAP(firewall);
		
		MAPEKComponent executer = new MAPEKComponent();
		executer.setBaseUrl("http://localhost:8087");
		executer.setEndpoint("/execute");
		
		mape.setExecuter(executer);
		
		return mape;
	}
	
}
