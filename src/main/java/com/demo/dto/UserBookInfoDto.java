package com.demo.dto;

public class UserBookInfoDto {
	
	public Integer userId;
	
	public Integer bookNum;//练习本的总是
	
	public Integer pointNum;//所有知识点的总是

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getBookNum() {
		return bookNum;
	}

	public void setBookNum(Integer bookNum) {
		this.bookNum = bookNum;
	}

	public Integer getPointNum() {
		return pointNum;
	}

	public void setPointNum(Integer pointNum) {
		this.pointNum = pointNum;
	}

}
