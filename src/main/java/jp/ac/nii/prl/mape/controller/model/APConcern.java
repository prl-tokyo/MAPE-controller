package jp.ac.nii.prl.mape.controller.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class APConcern {

	@GeneratedValue
	@Id
	@JsonIgnore
	private Long id;
	
	@NotEmpty
	@Valid
	private String name;
	
	@OneToOne
	private MAPEKComponent combined;
	
	@OneToOne
	private MAPEKComponent analyser;
	
	@OneToOne
	private MAPEKComponent planner;
	
	public MAPEKComponent getAnalyser() {
		return analyser;
	}

	public MAPEKComponent getCombined() {
		return combined;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public MAPEKComponent getPlanner() {
		return planner;
	}

	public boolean isCombined() {
		return combined != null;
	}

	public void setAnalyser(MAPEKComponent analyser) {
		this.analyser = analyser;
	}

	public void setCombined(MAPEKComponent combined) {
		this.combined = combined;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPlanner(MAPEKComponent planner) {
		this.planner = planner;
	}

}
