package jp.ac.nii.prl.mape.controller.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Timing {

	@GeneratedValue
	@Id
	@JsonIgnore
	private Long id;
	
	private String name;
	
	private Date start;

	private Date end;

	private List<Timing> children = new ArrayList<>();

	public void addchild(Timing child) {
		children.add(child);
	}

	public List<Timing> getChildren() {
		return children;
	}

	public Date getEnd() {
		return end;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Date getStart() {
		return start;
	}
	
	public void setChildren(List<Timing> children) {
		this.children = children;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setStart(Date start) {
		this.start = start;
	}
}
