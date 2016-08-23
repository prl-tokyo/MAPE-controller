package jp.ac.nii.prl.mape.controller.configuration;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app.controller")
@ComponentScan(basePackages="jp.ac.nii.prl.mape.controller.tree")
public class ControllerConfigurationProperties {

	private ComponentProperty monitor;
	
	private List<APProperties> aps;

	private ComponentProperty executer;

	public List<APProperties> getAps() {
		return aps;
	}
	
	public ComponentProperty getExecuter() {
		return executer;
	}

	public ComponentProperty getMonitor() {
		return monitor;
	}

	public void setAps(List<APProperties> aps) {
		this.aps = aps;
	}

	public void setExecuter(ComponentProperty executer) {
		this.executer = executer;
	}
	
	public void setMonitor(ComponentProperty monitor) {
		this.monitor = monitor;
	}
	
	@Override
	public String toString() {
		return String.format("Controller configuration with %s AP services", aps.size());
	}
}
