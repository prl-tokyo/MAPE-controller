package jp.ac.nii.prl.mape.controller.service;

import java.net.URI;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jp.ac.nii.prl.mape.controller.model.APConcern;
import jp.ac.nii.prl.mape.controller.model.MAPEKComponent;
import jp.ac.nii.prl.mape.controller.model.Timing;

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
	public void analyseAndPlan(APConcern ap, MAPEKComponent kb, Timing timing) {
		Timing apTiming = new Timing();
		apTiming.setName(ap.getName() + "AP");
		apTiming.setStart(new Date());
		
		if (ap.isCombined())
			combinedAP(ap.getName(), ap.getCombined(), kb, timing);
		else {
			String view = analyse(ap.getName(), ap.getAnalyser(), kb, timing);
			plan(ap.getName(), view, ap.getPlanner(), kb, timing);
		}
		
		apTiming.setEnd(new Date());
		timing.addchild(apTiming);
	}
	
	private String analyse(String bx, MAPEKComponent analyser, MAPEKComponent kb, Timing timing) {
		Timing getTiming = new Timing();
		getTiming.setName(bx + " analysis GET");
		getTiming.setStart(new Date());
		
		String view = kbService.get(kb, bx);
		
		getTiming.setEnd(new Date());
		timing.addchild(getTiming);
		
		Timing aTiming = new Timing();
		aTiming.setName(bx + " analysis");
		aTiming.setStart(new Date());
		
		RestTemplate template = new RestTemplate();
		URI location = template.postForLocation(analyser.getUrl(), view);
		view = template.getForObject(location, String.class);
		
		aTiming.setEnd(new Date());
		timing.addchild(aTiming);
		
		return view;
	}
	
	private void plan(String bx, 
			String view, 
			MAPEKComponent planner, 
			MAPEKComponent kb, 
			Timing timing) {
		Timing pTiming = new Timing();
		pTiming.setName(bx + " planning");
		pTiming.setStart(new Date());
		
		RestTemplate template = new RestTemplate();
		URI location = template.postForLocation(planner.getUrl(), view);
		view = template.getForObject(location, String.class);
		
		pTiming.setEnd(new Date());
		timing.addchild(pTiming);
		
		Timing putTiming = new Timing();
		putTiming.setName(bx + " planning PUT");
		putTiming.setStart(new Date());
		
		kbService.put(kb, bx, view);
		
		putTiming.setEnd(new Date());
		timing.addchild(putTiming);
	}
	
	private void combinedAP (String bx, MAPEKComponent combined, MAPEKComponent kb, Timing timing) {
		Timing getTiming = new Timing();
		getTiming.setName(bx + "combined GET");
		getTiming.setStart(new Date());
		
		String view = kbService.get(kb, bx);
		
		getTiming.setEnd(new Date());
		timing.addchild(getTiming);
		
		Timing combTiming = new Timing();
		combTiming.setName(bx + "combined");
		combTiming.setStart(new Date());
		
		RestTemplate template = new RestTemplate();
		URI location = template.postForLocation(combined.getUrl(), view);
		view = template.getForObject(location, String.class);
		
		combTiming.setEnd(new Date());
		timing.addchild(combTiming);
		
		Timing putTiming = new Timing();
		putTiming.setName(bx + "combined PUT");
		putTiming.setStart(new Date());
		
		kbService.put(kb, bx, view);
		
		putTiming.setEnd(new Date());
		timing.addchild(putTiming);
	}
}
