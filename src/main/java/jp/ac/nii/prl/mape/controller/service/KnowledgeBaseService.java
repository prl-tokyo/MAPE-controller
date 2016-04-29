package jp.ac.nii.prl.mape.controller.service;

import jp.ac.nii.prl.mape.controller.model.MAPEKComponent;

public interface KnowledgeBaseService {

	void put(MAPEKComponent kb, String bx, String view, String param);

	String get(MAPEKComponent kb, String bx, String param);

}