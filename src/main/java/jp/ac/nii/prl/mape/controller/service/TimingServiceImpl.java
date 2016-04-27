package jp.ac.nii.prl.mape.controller.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jp.ac.nii.prl.mape.controller.model.Timing;
import jp.ac.nii.prl.mape.controller.repository.TimingRepository;

@Service("timingService")
public class TimingServiceImpl {
	
	private final TimingRepository timingRepository;
	
	public TimingServiceImpl(TimingRepository timingRepository) {
		this.timingRepository = timingRepository;
	}
	
	public void save(Timing timing) {
		timingRepository.save(timing);
	}
	
	public List<Timing> findAll() {
		return timingRepository.findAll();
	}

}
