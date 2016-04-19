package jp.ac.nii.prl.mape.controller.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class MAPE {
	
	@GeneratedValue
	@Id
	@JsonIgnore
	private Long id;
	
	private List<AP> aps;
	
	private Executer executer;

	private KnowledgeBase kb;

	public List<AP> getAps() {
		return aps;
	}
	
	public Executer getExecuter() {
		return executer;
	}
	
	public Long getId() {
		return id;
	}
	
	public KnowledgeBase getKb() {
		return kb;
	}
	
	public void setAps(List<AP> aps) {
		this.aps = aps;
	}
	
	public void setExecuter(Executer executer) {
		this.executer = executer;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setKb(KnowledgeBase kb) {
		this.kb = kb;
	}
}
