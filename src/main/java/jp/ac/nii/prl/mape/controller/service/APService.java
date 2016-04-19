package jp.ac.nii.prl.mape.controller.service;

import jp.ac.nii.prl.mape.controller.model.AP;
import jp.ac.nii.prl.mape.controller.model.KnowledgeBase;

public interface APService {

	void analyseAndPlan(AP ap, KnowledgeBase bk);

}