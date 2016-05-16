package jp.ac.nii.prl.mape.controller.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.nii.prl.mape.controller.model.APConcern;
import jp.ac.nii.prl.mape.controller.model.MAPE;
import jp.ac.nii.prl.mape.controller.model.Timing;

@Service("mapeService")
public class MAPEServiceImpl implements MAPEService {

	private final MonitorService monitorService;
	private final ExecuterService executerService;
	private final APService apService;
	private final TimingService timingService;
	
	@Autowired
	public MAPEServiceImpl(MonitorService monitorService,
			ExecuterService executerService,
			APService apService,
			TimingService timingService) {
		this.monitorService = monitorService;
		this.executerService = executerService;
		this.apService = apService;
		this.timingService = timingService;
	}
	
	/* (non-Javadoc)
	 * @see jp.ac.nii.prl.mape.controller.service.MAPEService#loop()
	 */
	@Override
	public void loop(MAPE mape) {
		Timing timing = new Timing();
		timing.setName("MAPE");
		timing.setStart(new Date());
		
		monitorService.monitor(mape.getMonitor(), mape.getKb(), timing);
		
		for (APConcern ap:mape.getAps())
			apService.analyseAndPlan(ap, mape.getKb(), timing);
		
		executerService.execute(mape.getExecuter(), mape.getKb(), timing);
		
		timing.setEnd(new Date());
		timingService.save(timing);
	}
}
