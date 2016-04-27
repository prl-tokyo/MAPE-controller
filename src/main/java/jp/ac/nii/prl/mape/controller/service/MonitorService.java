package jp.ac.nii.prl.mape.controller.service;

import jp.ac.nii.prl.mape.controller.model.MAPEKComponent;
import jp.ac.nii.prl.mape.controller.model.Timing;

public interface MonitorService {

	void update(String model);
	
	void monitor(MAPEKComponent monitor, MAPEKComponent kb, Timing timing);

}