package jp.ac.nii.prl.mape.controller.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jp.ac.nii.prl.mape.controller.model.APConcern;
import jp.ac.nii.prl.mape.controller.model.MAPEKComponent;

@Service("apService")
public class APServiceImpl implements APService {
	
	private final KnowledgeBaseService kbService;

	@Autowired
	public APServiceImpl(KnowledgeBaseService kbService) {
		this.kbService = kbService;
	}
	
	/* (non-Javadoc)
	 * @see jp.ac.nii.prl.mape.controller.service.APService#analyseAndPlan(jp.ac.nii.prl.mape.controller.model.AP, jp.ac.nii.prl.mape.controller.model.KnowledgeBase)
	 */
	@Override
	public void analyseAndPlan(APConcern ap, MAPEKComponent kb) {
		if (ap.isCombined())
			combinedAP(ap.getName(), ap.getCombined(), kb);
		else {
			String view = analyse(ap.getName(), ap.getAnalyser(), kb);
			plan(ap.getName(), view, ap.getPlanner(), kb);
		}
	}
	
	private String analyse(String bx, MAPEKComponent analyser, MAPEKComponent kb) {
		String view = kbService.get(kb, bx);
		RestTemplate template = new RestTemplate();
		URI location = template.postForLocation(analyser.getUrl(), view);
		view = template.getForObject(location, String.class);
		return view;
	}
	
	private void plan(String bx, String view, MAPEKComponent planner, MAPEKComponent kb) {
		RestTemplate template = new RestTemplate();
		URI location = template.postForLocation(planner.getUrl(), view);
		view = template.getForObject(location, String.class);
		kbService.put(kb, bx, view);
	}
	
	private void combinedAP (String bx, MAPEKComponent combined, MAPEKComponent kb) {
		String view = kbService.get(kb, bx);
		RestTemplate template = new RestTemplate();
		URI location = template.postForLocation(combined.getUrl(), view);
		view = template.getForObject(location, String.class);
		kbService.put(kb, bx, view);
	}
}
