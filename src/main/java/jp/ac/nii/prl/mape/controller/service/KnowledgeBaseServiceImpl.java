package jp.ac.nii.prl.mape.controller.service;

import java.net.URI;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jp.ac.nii.prl.mape.controller.model.KnowledgeBase;

@Service("knowledgeBaseService")
public class KnowledgeBaseServiceImpl implements KnowledgeBaseService {

	/* (non-Javadoc)
	 * @see jp.ac.nii.prl.mape.controller.service.KnowledgeBaseService#put(java.lang.String, java.lang.String)
	 */
	@Override
	public void put(KnowledgeBase kb, String bx, String view) {
		RestTemplate template = new RestTemplate();
		URI location = template.postForLocation(kb.getBaseUrl() + "/bx", view);
		view = template.getForObject(location, String.class);
	}
	
	/* (non-Javadoc)
	 * @see jp.ac.nii.prl.mape.controller.service.KnowledgeBaseService#get(java.lang.String)
	 */
	@Override
	public String get(KnowledgeBase kb, String bx) {
		RestTemplate template = new RestTemplate();
		String view = template.getForObject(kb.getBaseUrl() + "/bx", String.class);
		return view;
	}
}
