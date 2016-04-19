package jp.ac.nii.prl.mape.controller.service;

import jp.ac.nii.prl.mape.controller.model.KnowledgeBase;

public interface KnowledgeBaseService {

	void put(KnowledgeBase kb, String bx, String view);

	String get(KnowledgeBase kb, String bx);

}