package jp.ac.nii.prl.mape.controller.tree;

import java.net.URI;

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
public class Intrusion extends Thread {
	
	private static final Logger logger = LoggerFactory.getLogger(Intrusion.class);
	
	private TimeData timer;

	@Override
	public void run() {
		logger.debug("Starting intrusion branch");
		logger.debug("Getting view");
		timer.setFirewall_get_start(System.currentTimeMillis());
		String firewall = CloudTree.get(BX.FIREWALL);
		timer.setFirewall_get_stop(System.currentTimeMillis());
		
		logger.debug("Sending view to AP service");
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(firewall, headers);
        timer.setFirewall_ap_start(System.currentTimeMillis());
        URI response = restTemplate.postForLocation("http://localhost:8082/firewall", entity);
		firewall = restTemplate.getForObject(response, String.class);
		timer.setFirewall_ap_stop(System.currentTimeMillis());
		logger.debug("Received updated view");
		
		logger.debug("Putting view");
		timer.setFirewall_put_start(System.currentTimeMillis());
		CloudTree.put(BX.FIREWALL, firewall);
		timer.setFirewall_put_stop(System.currentTimeMillis());
		logger.debug("Ending intrusion branch");
	}
	
	public void setTimer(TimeData timer) {
		this.timer = timer;
	}

}
