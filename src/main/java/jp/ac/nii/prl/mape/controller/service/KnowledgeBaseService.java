package jp.ac.nii.prl.mape.controller.service;

public interface KnowledgeBaseService {

	void put(String bx, String view);

	String get(String bx);

}