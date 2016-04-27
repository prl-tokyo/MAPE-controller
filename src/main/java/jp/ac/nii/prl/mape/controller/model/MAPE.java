package jp.ac.nii.prl.mape.controller.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class MAPE {
	
	@GeneratedValue
	@Id
	@JsonIgnore
	private Long id;
	
	@OneToOne
	private MAPEKComponent monitor;
	
	@OneToMany
	private List<APConcern> aps;

	@OneToOne
	private MAPEKComponent executer;

	@OneToOne
	private MAPEKComponent kb;
	
	public List<APConcern> getAps() {
		return aps;
	}

	public MAPEKComponent getExecuter() {
		return executer;
	}

	public Long getId() {
		return id;
	}
	
	public MAPEKComponent getKb() {
		return kb;
	}
	
	public MAPEKComponent getMonitor() {
		return monitor;
	}
	
	public void setAps(List<APConcern> aps) {
		this.aps = aps;
	}
	
	public void setExecuter(MAPEKComponent executer) {
		this.executer = executer;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setKb(MAPEKComponent kb) {
		this.kb = kb;
	}
	
	public void setMonitor(MAPEKComponent monitor) {
		this.monitor = monitor;
	}
}
