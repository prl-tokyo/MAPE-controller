package jp.ac.nii.prl.mape.controller.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.ac.nii.prl.mape.controller.model.Timing;

public interface TimingRepository extends JpaRepository<Timing, Long> {

}
