package com.demo.entity;

import java.io.Serializable;

public class LoreCardEntity implements Serializable{

	private static final long serialVersionUID = 461079389536773396L;
	
	public Integer id ;
	
	public Integer lorePointId;
	
	public String positve;
	
	public String rear ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLorePointId() {
		return lorePointId;
	}

	public void setLorePointId(Integer lorePointId) {
		this.lorePointId = lorePointId;
	}

	public String getPositve() {
		return positve;
	}

	public void setPositve(String positve) {
		this.positve = positve;
	}

	public String getRear() {
		return rear;
	}

	public void setRear(String rear) {
		this.rear = rear;
	}
	
}
