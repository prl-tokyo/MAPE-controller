package jp.ac.nii.prl.mape.controller.service;

import java.util.List;

import jp.ac.nii.prl.mape.controller.model.Timing;

public interface TimingService {

	void save(Timing timing);

	List<Timing> findAll();

}