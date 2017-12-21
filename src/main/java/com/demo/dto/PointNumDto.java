package com.demo.dto;

public class PointNumDto {
	
	public Integer bookId;
	
	public Integer exErrorNum;//错题数
	
	public Integer exNewNum;//新题数
	
	public Integer exStrengthenNum;//巩固数量
	
	public Integer intensify; //强化练习数

	public Integer getExErrorNum() {
		return exErrorNum;
	}

	public void setExErrorNum(Integer exErrorNum) {
		this.exErrorNum = exErrorNum;
	}

	public Integer getExNewNum() {
		return exNewNum;
	}

	public void setExNewNum(Integer exNewNum) {
		this.exNewNum = exNewNum;
	}

	public Integer getExStrengthenNum() {
		return exStrengthenNum;
	}

	public void setExStrengthenNum(Integer exStrengthenNum) {
		this.exStrengthenNum = exStrengthenNum;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Integer getIntensify() {
		return intensify;
	}

	public void setIntensify(Integer intensify) {
		this.intensify = intensify;
	}
	
}
