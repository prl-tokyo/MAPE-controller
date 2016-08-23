package jp.ac.nii.prl.mape.controller.tree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import jp.ac.nii.prl.mape.controller.tree.CloudTree.BX;

@Component
@Scope("prototype")
public class Intrusion extends Thread {
	
	private static final Logger logger = LoggerFactory.getLogger(Intrusion.class);

	@Override
	public void run() {
		logger.debug("Starting intrusion branch");
		logger.debug("Getting view");
		String firewall = CloudTree.get(BX.FIREWALL);
		
		logger.debug("Sending view to AP service");
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(firewall, headers);
//		firewall = restTemplate.postForObject("http://localhost:8082/firewall", entity, String.class);
		logger.debug("Received updated view");
		
		logger.debug("Putting view");
		CloudTree.put(BX.FIREWALL, firewall);
		logger.debug("Ending intrusion branch");
	}

}
