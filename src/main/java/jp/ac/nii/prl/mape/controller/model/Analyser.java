package jp.ac.nii.prl.mape.controller.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Analyser {
	
	@GeneratedValue
	@Id
	@JsonIgnore
	private Long id;
	
	private String baseUrl;

	private String analysisUrl;

	public String getAnalysisUrl() {
		return analysisUrl;
	}
	
	public String getBaseUrl() {
		return baseUrl;
	}

	public Long getId() {
		return id;
	}

	public void setAnalysisUrl(String analysisUrl) {
		this.analysisUrl = analysisUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
