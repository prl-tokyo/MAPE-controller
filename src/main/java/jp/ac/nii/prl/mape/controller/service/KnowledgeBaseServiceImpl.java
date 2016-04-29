package jp.ac.nii.prl.mape.controller.service;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jp.ac.nii.prl.mape.controller.model.MAPEKComponent;

@Service("knowledgeBaseService")
public class KnowledgeBaseServiceImpl implements KnowledgeBaseService {

	/* (non-Javadoc)
	 * @see jp.ac.nii.prl.mape.controller.service.KnowledgeBaseService#put(java.lang.String, java.lang.String)
	 */
	@Override
	public void put(MAPEKComponent kb, String bx, String view, String param) {
		RestTemplate template = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(view, headers);
        System.out.println(kb.getUrl() + "/put/" + bx + "/" + param);
		template.postForLocation(kb.getUrl() + "/put/" + bx + "/" + param, entity);
	}
	
	/* (non-Javadoc)
	 * @see jp.ac.nii.prl.mape.controller.service.KnowledgeBaseService#get(java.lang.String)
	 */
	@Override
	public String get(MAPEKComponent kb, String bx, String param) {
		if (param == null)
			param = "nothing";
		if (param.equals(""))
			param = "nothing";
		RestTemplate template = new RestTemplate();
		System.out.println("GET: " + kb.getUrl() + "/get/" + bx + "/" + param);
		String view = template.getForObject(kb.getUrl() + "/get/" + bx + "/" + param, String.class);
		return view;
	}
}
