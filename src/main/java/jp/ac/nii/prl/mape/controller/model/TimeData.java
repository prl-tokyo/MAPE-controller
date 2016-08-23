package jp.ac.nii.prl.mape.controller.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TimeData {

	@GeneratedValue
	@Id
	private Long id;
	
	private long mape_start;
	
	private long monitoring_start;

	private long monitoring_stop;

	private long update_start;

	private long update_stop;

	private long failure_get_start;

	private long failure_get_stop;

	private long failure_ap_start;

	private long failure_ap_stop;

	private long failure_put_start;

	private long failure_put_stop;

	private long autoscaling_get_start;

	private long autoscaling_get_stop;

	private long autoscaling_ap_start;

	private long autoscaling_ap_stop;

	private long autoscaling_put_start;

	private long autoscaling_put_stop;

	private long firewall_get_start;

	private long firewall_get_stop;

	private long firewall_ap_start;

	private long firewall_ap_stop;

	private long firewall_put_start;

	private long firewall_put_stop;

	private long execution_get_start;

	private long execution_get_stop;

	private long mape_stop;

	public long getAutoscaling_ap_start() {
		return autoscaling_ap_start;
	}

	public long getAutoscaling_ap_stop() {
		return autoscaling_ap_stop;
	}

	public long getAutoscaling_get_start() {
		return autoscaling_get_start;
	}

	public long getAutoscaling_get_stop() {
		return autoscaling_get_stop;
	}

	public long getAutoscaling_put_start() {
		return autoscaling_put_start;
	}

	public long getAutoscaling_put_stop() {
		return autoscaling_put_stop;
	}

	public long getExecution_get_start() {
		return execution_get_start;
	}

	public long getExecution_get_stop() {
		return execution_get_stop;
	}

	public long getFailure_ap_start() {
		return failure_ap_start;
	}

	public long getFailure_ap_stop() {
		return failure_ap_stop;
	}

	public long getFailure_get_start() {
		return failure_get_start;
	}

	public long getFailure_get_stop() {
		return failure_get_stop;
	}

	public long getFailure_put_start() {
		return failure_put_start;
	}

	public long getFailure_put_stop() {
		return failure_put_stop;
	}

	public long getFirewall_ap_start() {
		return firewall_ap_start;
	}

	public long getFirewall_ap_stop() {
		return firewall_ap_stop;
	}

	public long getFirewall_get_start() {
		return firewall_get_start;
	}

	public long getFirewall_get_stop() {
		return firewall_get_stop;
	}

	public long getFirewall_put_start() {
		return firewall_put_start;
	}

	public long getFirewall_put_stop() {
		return firewall_put_stop;
	}

	public Long getId() {
		return id;
	}

	public long getMape_start() {
		return mape_start;
	}

	public long getMape_stop() {
		return mape_stop;
	}

	public long getMonitoring_start() {
		return monitoring_start;
	}

	public long getMonitoring_stop() {
		return monitoring_stop;
	}

	public long getUpdate_start() {
		return update_start;
	}

	public long getUpdate_stop() {
		return update_stop;
	}

	public void setAutoscaling_ap_start(long autoscaling_ap_start) {
		this.autoscaling_ap_start = autoscaling_ap_start;
	}

	public void setAutoscaling_ap_stop(long autoscaling_ap_stop) {
		this.autoscaling_ap_stop = autoscaling_ap_stop;
	}

	public void setAutoscaling_get_start(long autoscaling_get_start) {
		this.autoscaling_get_start = autoscaling_get_start;
	}
	
	public void setAutoscaling_get_stop(long autoscaling_get_stop) {
		this.autoscaling_get_stop = autoscaling_get_stop;
	}
	
	public void setAutoscaling_put_start(long autoscaling_put_start) {
		this.autoscaling_put_start = autoscaling_put_start;
	}
	
	public void setAutoscaling_put_stop(long autoscaling_put_stop) {
		this.autoscaling_put_stop = autoscaling_put_stop;
	}
	
	public void setExecution_get_start(long execution_get_start) {
		this.execution_get_start = execution_get_start;
	}
	
	public void setExecution_get_stop(long execution_get_stop) {
		this.execution_get_stop = execution_get_stop;
	}
	
	public void setFailure_ap_start(long failure_ap_start) {
		this.failure_ap_start = failure_ap_start;
	}
	
	public void setFailure_ap_stop(long failure_ap_stop) {
		this.failure_ap_stop = failure_ap_stop;
	}
	
	public void setFailure_get_start(long failure_get_start) {
		this.failure_get_start = failure_get_start;
	}
	
	public void setFailure_get_stop(long failure_get_stop) {
		this.failure_get_stop = failure_get_stop;
	}
	
	public void setFailure_put_start(long failure_put_start) {
		this.failure_put_start = failure_put_start;
	}
	
	public void setFailure_put_stop(long failure_put_stop) {
		this.failure_put_stop = failure_put_stop;
	}
	
	public void setFirewall_ap_start(long firewall_ap_start) {
		this.firewall_ap_start = firewall_ap_start;
	}
	
	public void setFirewall_ap_stop(long firewall_ap_stop) {
		this.firewall_ap_stop = firewall_ap_stop;
	}
	
	public void setFirewall_get_start(long firewall_get_start) {
		this.firewall_get_start = firewall_get_start;
	}
	
	public void setFirewall_get_stop(long firewall_get_stop) {
		this.firewall_get_stop = firewall_get_stop;
	}
	
	public void setFirewall_put_start(long firewall_put_start) {
		this.firewall_put_start = firewall_put_start;
	}
	
	public void setFirewall_put_stop(long firewall_put_stop) {
		this.firewall_put_stop = firewall_put_stop;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setMape_start(long mape_start) {
		this.mape_start = mape_start;
	}
	
	public void setMape_stop(long mape_stop) {
		this.mape_stop = mape_stop;
	}
	
	public void setMonitoring_start(long monitoring_start) {
		this.monitoring_start = monitoring_start;
	}
	
	public void setMonitoring_stop(long monitoring_stop) {
		this.monitoring_stop = monitoring_stop;
	}
	
	public void setUpdate_start(long update_start) {
		this.update_start = update_start;
	}
	
	public void setUpdate_stop(long update_stop) {
		this.update_stop = update_stop;
	}
}
