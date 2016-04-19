package jp.ac.nii.prl.mape.controller.service;

import jp.ac.nii.prl.mape.controller.model.Executer;
import jp.ac.nii.prl.mape.controller.model.KnowledgeBase;

public interface ExecuterService {

	void execute(Executer executer, KnowledgeBase kb);

}