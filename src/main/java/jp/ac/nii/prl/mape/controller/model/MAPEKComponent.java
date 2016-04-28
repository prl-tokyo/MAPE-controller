package jp.ac.nii.prl.mape.controller.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class MAPEKComponent {

	@GeneratedValue
	@Id
	@JsonIgnore
	private Long id;
	
	private String baseUrl;
	
	private String endpoint;
	
	private String parameter;

	public String getBaseUrl() {
		return baseUrl;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public Long getId() {
		return id;
	}
	
	public String getParameter() {
		return parameter;
	}

	/**
	 * Returns a String representation of the URL used to call the service, in the form
	 * baseUrl/endPoint
	 * @return
	 */
	public String getUrl() {
		return baseUrl + "/" + endpoint;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

}
