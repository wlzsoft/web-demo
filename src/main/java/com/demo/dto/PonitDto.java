package com.demo.dto;

public class PonitDto {
	
	public Integer id ;//知识点主键ID
	
	public String lorePointName ;//知识点名称
	
	public String  excerciseBookId ;//练习本ID
	
	public  Integer number; //知识点卡片数量

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

	public String getExcerciseBookId() {
		return excerciseBookId;
	}

	public void setExcerciseBookId(String excerciseBookId) {
		this.excerciseBookId = excerciseBookId;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	
	

}
