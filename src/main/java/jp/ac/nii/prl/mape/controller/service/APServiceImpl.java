package jp.ac.nii.prl.mape.controller.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jp.ac.nii.prl.mape.controller.model.AP;
import jp.ac.nii.prl.mape.controller.model.Analyser;
import jp.ac.nii.prl.mape.controller.model.CombinedAP;
import jp.ac.nii.prl.mape.controller.model.KnowledgeBase;
import jp.ac.nii.prl.mape.controller.model.Planner;

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
	public void analyseAndPlan(AP ap, KnowledgeBase kb) {
		if (ap.hasCombinedAP())
			combinedAP(ap.getName(), ap.getCombinedAP(), kb);
		else {
			String view = analyse(ap.getName(), ap.getAnalyser(), kb);
			plan(ap.getName(), view, ap.getPlanner(), kb);
		}
	}
	
	private String analyse(String bx, Analyser analyser, KnowledgeBase kb) {
		String view = kbService.get(kb, bx);
		RestTemplate template = new RestTemplate();
		URI location = template.postForLocation(analyser.getAnalysisUrl(), view);
		view = template.getForObject(location, String.class);
		return view;
	}
	
	private void plan(String bx, String view, Planner planner, KnowledgeBase kb) {
		RestTemplate template = new RestTemplate();
		URI location = template.postForLocation(planner.getExecutionUrl(), view);
		view = template.getForObject(location, String.class);
		kbService.put(kb, bx, view);
	}
	
	private void combinedAP (String bx, CombinedAP combined, KnowledgeBase kb) {
		String view = kbService.get(kb, bx);
		RestTemplate template = new RestTemplate();
		URI location = template.postForLocation(combined.getExecutionUrl(), view);
		view = template.getForObject(location, String.class);
		kbService.put(kb, bx, view);
	}
}
