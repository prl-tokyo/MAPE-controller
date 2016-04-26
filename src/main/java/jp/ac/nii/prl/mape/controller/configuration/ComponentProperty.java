package jp.ac.nii.prl.mape.controller.configuration;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

public class ComponentProperty {

	@NotEmpty
	@Valid
	private String name;
	
	@NotEmpty
	@Valid
	private String baseUrl;
	
	@NotEmpty
	@Valid
	private String endPoint;

	public String getBaseUrl() {
		return baseUrl;
	}

	public String getEndPoint() {
		return endPoint;
	}

	public String getName() {
		return name;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public void setName(String name) {
		this.name = name;
	}
}
