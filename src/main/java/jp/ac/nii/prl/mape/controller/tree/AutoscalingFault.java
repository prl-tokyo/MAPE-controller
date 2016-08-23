package jp.ac.nii.prl.mape.controller.tree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import jp.ac.nii.prl.mape.controller.model.TimeData;
import jp.ac.nii.prl.mape.controller.tree.CloudTree.BX;

@Component
@Scope("prototype")
public class AutoscalingFault extends Thread {
	
	private static final Logger logger = LoggerFactory.getLogger(Intrusion.class);
	
	private TimeData timer;

	@Override
	public void run() {
		System.out.println("ICI");
		logger.debug("Starting autoscaling and failure branch");
		
		// Failure
		logger.debug("Getting failure view");
		timer.setFailure_get_start(System.currentTimeMillis());
		String failure = CloudTree.get(BX.FAILURE);
		timer.setFailure_get_stop(System.currentTimeMillis());
		logger.debug("Sending failure view to AP service");
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(failure, headers);
        timer.setFailure_ap_start(System.currentTimeMillis());
		failure = restTemplate.postForObject("http://localhost:8082/failure", entity, String.class);
		timer.setFailure_ap_stop(System.currentTimeMillis());
		logger.debug("Received updated failure view");
		
		logger.debug("Putting failure view");
		timer.setFailure_put_start(System.currentTimeMillis());
		CloudTree.put(BX.FAILURE, failure);
		timer.setFailure_put_stop(System.currentTimeMillis());
		
		// Autoscaling
		logger.debug("Getting autoscaling view");
		timer.setAutoscaling_get_start(System.currentTimeMillis());
		String autoscaling = CloudTree.get(BX.AUTOSCALING);
		timer.setAutoscaling_get_stop(System.currentTimeMillis());
		
		logger.debug("Sending autoscaling view to AP service");
		restTemplate = new RestTemplate();
		headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        entity = new HttpEntity<String>(autoscaling, headers);
        timer.setAutoscaling_ap_start(System.currentTimeMillis());
        autoscaling = restTemplate.postForObject("http://localhost:8082/autoscaling", entity, String.class);
        timer.setAutoscaling_ap_stop(System.currentTimeMillis());
        logger.debug("Received updated autoscaling view");
		
		logger.debug("Putting autoscaling view");
		timer.setAutoscaling_put_start(System.currentTimeMillis());
		CloudTree.put(BX.AUTOSCALING, autoscaling);
		timer.setAutoscaling_put_stop(System.currentTimeMillis());
		logger.debug("Ending autoscaling and fault branch");
	}
	
	public void setTimer(TimeData timer) {
		this.timer = timer;
	}

}
