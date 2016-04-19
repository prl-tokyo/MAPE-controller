package jp.ac.nii.prl.mape.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jp.ac.nii.prl.mape.controller.model.Executer;
import jp.ac.nii.prl.mape.controller.model.KnowledgeBase;

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
	public void execute(Executer executer, KnowledgeBase kb) {
		RestTemplate template = new RestTemplate();
		String view = kbService.get(kb, "execution");
		template.postForLocation(executer.getExecutionUrl(), view);
	}
}
