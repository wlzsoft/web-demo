package com.demo.entity;

import java.io.Serializable;
import java.util.Date;

public class LorePointEntity implements Serializable{

	private static final long serialVersionUID = 563723650984037521L;
	
	 public Integer  id          ;
	 
	 public Integer excerciseBookId ;//练习本主键ID
	 
	 public String  lorePointName    ;//知识点名称
	 
	 public Integer  number      ;//知识点卡片数量
	 
	 public Date  createTime ;
	 
	 public Integer  createId   ;
	 
	 public Date  updateTime ;
	 
	 public Integer  updateId   ; 

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getExcerciseBookId() {
		return excerciseBookId;
	}

	public void setExcerciseBookId(Integer excerciseBookId) {
		this.excerciseBookId = excerciseBookId;
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getUpdateId() {
		return updateId;
	}

	public void setUpdateId(Integer updateId) {
		this.updateId = updateId;
	}

    
	
}
