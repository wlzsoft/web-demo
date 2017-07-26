package com.demo.entity;

import java.io.Serializable;
import java.util.Date;

public class LorePointEntity implements Serializable{

	private static final long serialVersionUID = 563723650984037521L;
	
	 public Integer  id          ;
	 
	 public String  lorePointName    ;
	 
	 public Integer  number      ;
	 
	 public Date  createTime ;
	 
	 public Integer  createId   ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLorePointName() {
		return lorePointName;
	}

	public void setLorePointName(String lorePointName) {
		this.lorePointName = lorePointName;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateId() {
		return createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}


	
}
