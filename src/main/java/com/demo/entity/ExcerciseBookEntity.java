package com.demo.entity;

import java.io.Serializable;
import java.util.Date;

public class ExcerciseBookEntity implements Serializable{

	private static final long serialVersionUID = 4031129668715480206L;
	
	public Integer id ;
	
	public String  excerciseBookName ;//练习本名称
	
	public Integer  parentId ;//父级ID
	
	public Integer language	;//	卡片使用的语言	
	
	public Integer area	;//	卡片所属领域
	
	public Integer  sharedType ;//共享类型 (0私有、1只读共享)
	
	public Boolean  isLeaves ;//是否叶子节点
	
	public Date  createTime ;
	
	public Integer  createId ;
	
	public Date  updateTime ;
	
	public Integer  updateId ;
	
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}