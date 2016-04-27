package jp.ac.nii.prl.mape.controller.service;

import jp.ac.nii.prl.mape.controller.model.MAPEKComponent;
import jp.ac.nii.prl.mape.controller.model.Timing;

public interface ExecuterService {

	void execute(MAPEKComponent executer, MAPEKComponent kb, Timing timing);

}