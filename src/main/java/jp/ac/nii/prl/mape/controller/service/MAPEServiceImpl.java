package jp.ac.nii.prl.mape.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.nii.prl.mape.controller.configuration.ControllerConfigurationProperties;
import jp.ac.nii.prl.mape.controller.model.APConcern;
import jp.ac.nii.prl.mape.controller.model.MAPE;

@Service("mapeService")
public class MAPEServiceImpl implements MAPEService {

	private final MonitorService monitorService;
	private final ExecuterService executerService;
	private final APService apService;
	
	@Autowired
	private ControllerConfigurationProperties properties;
	
	@Autowired
	public MAPEServiceImpl(MonitorService monitorService,
			ExecuterService executerService,
			APService apService) {
		this.monitorService = monitorService;
		this.executerService = executerService;
		this.apService = apService;
	}
	
	/* (non-Javadoc)
	 * @see jp.ac.nii.prl.mape.controller.service.MAPEService#loop()
	 */
	@Override
	public void loop(MAPE mape) {
		monitorService.monitor(mape.getMonitor(), mape.getKb());
		for (APConcern ap:mape.getAps())
			apService.analyseAndPlan(ap, mape.getKb());
		executerService.execute(mape.getExecuter(), mape.getKb());
	}
}
