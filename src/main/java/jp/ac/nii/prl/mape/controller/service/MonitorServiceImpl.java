package jp.ac.nii.prl.mape.controller.service;

import org.springframework.stereotype.Service;

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
		// TODO Auto-generated method stub
		
	}
}
