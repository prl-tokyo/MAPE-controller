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
public class AutoscalingFault extends Thread {
	
	private static final Logger logger = LoggerFactory.getLogger(Intrusion.class);

	@Override
	public void run() {
		System.out.println("ICI");
		logger.debug("Starting autoscaling and failure branch");
		
		// Failure
		logger.debug("Getting failure view");
		String failure = CloudTree.get(BX.FAILURE);
		logger.debug("Sending failure view to AP service");
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(failure, headers);
//		failure = restTemplate.postForObject("http://localhost:8082/failure", entity, String.class);
		logger.debug("Received updated failure view");
		
		logger.debug("Putting failure view");
		CloudTree.put(BX.FAILURE, failure);
		
		// Autoscaling
		logger.debug("Getting autoscaling view");
		String autoscaling = CloudTree.get(BX.AUTOSCALING);
		logger.debug("Sending autoscaling view to AP service");
		restTemplate = new RestTemplate();
		headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        entity = new HttpEntity<String>(autoscaling, headers);
//		autoscaling = restTemplate.postForObject("http://localhost:8082/autoscaling", entity, String.class);
		logger.debug("Received updated autoscaling view");
		
		logger.debug("Putting autoscaling view");
		CloudTree.put(BX.AUTOSCALING, autoscaling);
		logger.debug("Ending autoscaling and fault branch");
	}

}
