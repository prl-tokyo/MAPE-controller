package jp.ac.nii.prl.mape.controller.service;

import org.springframework.stereotype.Service;

@Service("knowledgeBaseService")
public class KnowledgeBaseServiceImpl implements KnowledgeBaseService {

	/* (non-Javadoc)
	 * @see jp.ac.nii.prl.mape.controller.service.KnowledgeBaseService#put(java.lang.String, java.lang.String)
	 */
	@Override
	public void put(String bx, String view) {
		
	}
	
	/* (non-Javadoc)
	 * @see jp.ac.nii.prl.mape.controller.service.KnowledgeBaseService#get(java.lang.String)
	 */
	@Override
	public String get(String bx) {
		return null;
	}
}
