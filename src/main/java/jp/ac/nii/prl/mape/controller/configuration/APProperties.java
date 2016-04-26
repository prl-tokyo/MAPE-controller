package jp.ac.nii.prl.mape.controller.configuration;

public class APProperties {
	
	private String name;
	
	private ComponentProperty analyser = null;
	
	private ComponentProperty planner = null;
	
	private ComponentProperty combinedAP = null;

	public ComponentProperty getAnalyser() {
		return analyser;
	}

	public ComponentProperty getCombinedAP() {
		return combinedAP;
	}

	public String getName() {
		return name;
	}

	public ComponentProperty getPlanner() {
		return planner;
	}

	public void setAnalyser(ComponentProperty analyser) {
		this.analyser = analyser;
	}

	public void setCombinedAP(ComponentProperty combinedAP) {
		this.combinedAP = combinedAP;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPlanner(ComponentProperty planner) {
		this.planner = planner;
	}
}
