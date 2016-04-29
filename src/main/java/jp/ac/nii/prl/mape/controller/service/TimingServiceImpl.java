package jp.ac.nii.prl.mape.controller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.nii.prl.mape.controller.model.Timing;
import jp.ac.nii.prl.mape.controller.repository.TimingRepository;

@Service("timingService")
public class TimingServiceImpl implements TimingService {
	
	private final TimingRepository timingRepository;
	
	@Autowired
	public TimingServiceImpl(TimingRepository timingRepository) {
		this.timingRepository = timingRepository;
	}
	
	/* (non-Javadoc)
	 * @see jp.ac.nii.prl.mape.controller.service.TimingService#save(jp.ac.nii.prl.mape.controller.model.Timing)
	 */
	@Override
	public void save(Timing timing) {
		for (Timing child:timing.getChildren())
			save(child);
		timingRepository.save(timing);
	}
	
	/* (non-Javadoc)
	 * @see jp.ac.nii.prl.mape.controller.service.TimingService#findAll()
	 */
	@Override
	public List<Timing> findAll() {
		return timingRepository.findAll();
	}

}
