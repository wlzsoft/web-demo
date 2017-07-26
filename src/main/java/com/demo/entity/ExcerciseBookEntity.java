package com.demo.entity;

import java.io.Serializable;
import java.util.Date;

public class ExcerciseBookEntity implements Serializable{

	private static final long serialVersionUID = 4031129668715480206L;
	
	public Integer id ;
	
	public String  excerciseBookName ;
	
	public Integer  parentId ;
	
	public Integer  sharedType ;
	
	public Boolean  isLeaves ;
	
	public Date  createTime ;
	
	public Integer  createID ;
	
	public String  remark   ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getExcerciseBookName() {
		return excerciseBookName;
	}

	public void setExcerciseBookName(String excerciseBookName) {
		this.excerciseBookName = excerciseBookName;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getSharedType() {
		return sharedType;
	}

	public void setSharedType(Integer sharedType) {
		this.sharedType = sharedType;
	}

	public Boolean getIsLeaves() {
		return isLeaves;
	}

	public void setIsLeaves(Boolean isLeaves) {
		this.isLeaves = isLeaves;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateID() {
		return createID;
	}

	public void setCreateID(Integer createID) {
		this.createID = createID;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}