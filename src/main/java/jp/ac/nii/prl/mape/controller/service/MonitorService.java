package jp.ac.nii.prl.mape.controller.service;

import jp.ac.nii.prl.mape.controller.model.MAPEKComponent;

public interface MonitorService {

	void update(String model);
	
	void monitor(MAPEKComponent monitor, MAPEKComponent kb);

}