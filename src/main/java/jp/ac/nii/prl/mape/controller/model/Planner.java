package jp.ac.nii.prl.mape.controller.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Planner {
	
	@GeneratedValue
	@Id
	@JsonIgnore
	private Long id;
	
	private String baseUrl;
	
	private String executionUrl;

	public String getBaseUrl() {
		return baseUrl;
	}

	public String getExecutionUrl() {
		return executionUrl;
	}

	public Long getId() {
		return id;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public void setExecutionUrl(String executionUrl) {
		this.executionUrl = executionUrl;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
