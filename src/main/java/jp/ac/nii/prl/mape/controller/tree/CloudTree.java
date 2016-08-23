package jp.ac.nii.prl.mape.controller.tree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import jp.ac.nii.prl.mape.controller.configuration.ControllerConfigurationProperties;
import jp.ac.nii.prl.mape.controller.model.TimeData;

public class CloudTree {
	
	private static final Logger logger = LoggerFactory.getLogger(CloudTree.class);
	
	public enum BX {
		AUTOSCALING("autoScaling"),
		FAILURE("failure"),
		FIREWALL("firewall"),
		ASF("autoscalingFailure");
		
		private final String bxName;
		
		BX(String bxName) {
			this.bxName = bxName;
		}
		
		public String getBXName() {
			return bxName;
		}
	}
	
	private static CloudTree singleton;
	
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ControllerConfigurationProperties.class);
	
	private CloudTree() {
	}
	
	public static synchronized CloudTree getInstance() {
		if (singleton == null)
			singleton = new CloudTree();
		return singleton;
	}
	
	public boolean analyse(TimeData time) {
		System.out.println("TEST TREE");
		//context.refresh();
		Intrusion intrusion = (Intrusion) context.getBean("intrusion");
		AutoscalingFault asFault = (AutoscalingFault) context.getBean("autoscalingFault");
		intrusion.setTimer(time);
		asFault.setTimer(time);
		intrusion.start();
		asFault.start();
		try {
			intrusion.join();
			asFault.join();
		} catch (InterruptedException e) {
			logger.error("Transformation error");
			e.printStackTrace();
			return false;
		}
		
		System.out.println("TEST TREE DONE");
		return true;
	}
	
	public static String get(BX bx) {
		System.out.println("Get " + bx.getBXName());
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(bx.getBXName(), headers);
        System.out.println(String.format("http://localhost:8083/kb/get/%s", bx.getBXName()));
		return restTemplate.getForObject(String.format("http://localhost:8083/kb/get/%s", bx.getBXName()), String.class);
	}
	
	public static void put(BX bx, String view) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(view, headers);
		restTemplate.postForObject(String.format("http://localhost:8083/kb/put/%s", bx.getBXName()), entity, String.class);
	}
	
}
