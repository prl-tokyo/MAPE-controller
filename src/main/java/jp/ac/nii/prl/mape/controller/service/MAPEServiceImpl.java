package jp.ac.nii.prl.mape.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.nii.prl.mape.controller.model.AP;
import jp.ac.nii.prl.mape.controller.model.MAPE;

@Service("mapeService")
public class MAPEServiceImpl implements MAPEService {

	private final ExecuterService executerService;
	private final APService apService;
	
	@Autowired
	public MAPEServiceImpl(ExecuterService executerService,
			APService apService) {
		this.executerService = executerService;
		this.apService = apService;
	}
	
	/* (non-Javadoc)
	 * @see jp.ac.nii.prl.mape.controller.service.MAPEService#loop()
	 */
	@Override
	public void loop(MAPE mape) {
		for (AP ap:mape.getAps())
			apService.analyseAndPlan(ap, mape.getKb());
		executerService.execute(mape.getExecuter(), mape.getKb());
	}
}
