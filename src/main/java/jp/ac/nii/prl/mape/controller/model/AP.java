package jp.ac.nii.prl.mape.controller.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class AP {
	
	@GeneratedValue
	@Id
	@JsonIgnore
	private Long id;
	
	private String name;

	@ElementCollection
	private List<String> predecessors;

	@OneToOne
	private Analyser analyser;
	
	@OneToOne
	private Planner planner;
	
	@OneToOne
	private CombinedAP combinedAP;
	
	public Analyser getAnalyser() {
		return analyser;
	}
	
	public CombinedAP getCombinedAP() {
		return combinedAP;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Planner getPlanner() {
		return planner;
	}

	public Collection<String> getPredecessors() {
		return predecessors;
	}

	public void setAnalyser(Analyser analyser) {
		this.analyser = analyser;
	}

	public void setCombinedAP(CombinedAP combinedAP) {
		this.combinedAP = combinedAP;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPlanner(Planner planner) {
		this.planner = planner;
	}

	public void setPredecessors(List<String> predecessors) {
		this.predecessors = predecessors;
	}

	public boolean hasCombinedAP() {
		return combinedAP != null;
	}

}
