package com.demo.dto;


public class BookDto {

	public Integer id ;
	
	public String  excerciseBookName ;//练习本名称
	
	public Integer  parentId ;//父级ID
	
	public Integer  sharedType ;//共享类型 (0私有、1只读共享)
	
	public Boolean  isLeaves ;//是否叶子节点
	
	public Integer  createId ;

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

	public Integer getCreateId() {
		return createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}
	
}
