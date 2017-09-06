package com.demo.dto;

public class ChapterDto {
	
	public Integer	 bookId;//所属练习本ID
	
	public String chapterJson;//章节Json
	
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public String getChapterJson() {
		return chapterJson;
	}
	public void setChapterJson(String chapterJson) {
		this.chapterJson = chapterJson;
	}
	
	

}
