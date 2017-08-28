package com.demo.dto;

public class BookProgressDto {
	
	public Integer bookId; //练习本Id
	
	public Double progress;//练习进度

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Double getProgress() {
		return progress;
	}

	public void setProgress(Double progress) {
		this.progress = progress;
	}
	

}
