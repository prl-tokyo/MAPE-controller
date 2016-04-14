package jp.ac.nii.prl.mape.controller.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class KnowledgeBase {

	@GeneratedValue
	@Id
	@JsonIgnore
	private Long id;
	
	private String baseUrl;

	public String getBaseUrl() {
		return baseUrl;
	}

	public Long getId() {
		return id;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
