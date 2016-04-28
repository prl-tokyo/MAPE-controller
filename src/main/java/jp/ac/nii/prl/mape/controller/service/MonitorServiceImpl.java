package jp.ac.nii.prl.mape.controller.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jp.ac.nii.prl.mape.controller.model.MAPEKComponent;
import jp.ac.nii.prl.mape.controller.model.Timing;

@Service("monitorService")
public class MonitorServiceImpl implements MonitorService {

	/* (non-Javadoc)
	 * @see jp.ac.nii.prl.mape.controller.service.MonitorService#update(java.lang.String)
	 */
	@Override
	public void update(String model) {
		
	}

	@Override
	public void monitor(MAPEKComponent monitor, MAPEKComponent kb, Timing timing) {
		RestTemplate restTemplate = new RestTemplate();
		
		// get the source from the monitor
		String source = restTemplate.getForObject(monitor.getUrl(), String.class);
		
		System.out.println("Source fetched from monitor");
		
		// send source to the knowledge base
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<String> entity = new HttpEntity<String>(source, headers);
        restTemplate.postForEntity(kb.getUrl() + "/source", entity, String.class);
        
        System.out.println("Source sent to knowledge base");
	}
}
