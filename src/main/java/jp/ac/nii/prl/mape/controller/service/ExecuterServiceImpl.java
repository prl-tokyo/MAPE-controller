package jp.ac.nii.prl.mape.controller.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jp.ac.nii.prl.mape.controller.model.MAPEKComponent;
import jp.ac.nii.prl.mape.controller.model.Timing;

@Service("executerService")
public class ExecuterServiceImpl implements ExecuterService {

	private final KnowledgeBaseService kbService;
	
	@Autowired
	public ExecuterServiceImpl(KnowledgeBaseService kbService) {
		this.kbService = kbService;
	}
	
	/* (non-Javadoc)
	 * @see jp.ac.nii.prl.mape.controller.service.ExecuterService#execute()
	 */
	@Override
	public void execute(MAPEKComponent executer, MAPEKComponent kb, Timing timing) {
		Timing getTiming = new Timing();
		getTiming.setName("Executer GET");
		getTiming.setStart(new Date());
		
		RestTemplate template = new RestTemplate();
		String view = kbService.get(kb, "execution", "");
		
		getTiming.setEnd(new Date());
		timing.addchild(getTiming);
		
		Timing executionTiming = new Timing();
		executionTiming.setName("Executer");
		executionTiming.setStart(new Date());
		
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(view, headers);
        System.out.println("Execute: " + view);
		template.postForLocation(executer.getUrl(), entity);
		
		executionTiming.setEnd(new Date());

		System.out.println("Execution completed");
		
		timing.addchild(executionTiming);
	}
}
