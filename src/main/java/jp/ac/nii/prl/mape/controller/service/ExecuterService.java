package jp.ac.nii.prl.mape.controller.service;

import jp.ac.nii.prl.mape.controller.model.MAPEKComponent;

public interface ExecuterService {

	void execute(MAPEKComponent executer, MAPEKComponent kb);

}