package jp.ac.nii.prl.mape.controller.service;

import jp.ac.nii.prl.mape.controller.model.APConcern;
import jp.ac.nii.prl.mape.controller.model.MAPEKComponent;
import jp.ac.nii.prl.mape.controller.model.Timing;

public interface APService {

	void analyseAndPlan(APConcern ap, MAPEKComponent bk, Timing timing);

}