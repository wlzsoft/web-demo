package com.demo.entity;

import java.io.Serializable;
import java.util.Date;

public class ChapterEntity implements Serializable {

	private static final long serialVersionUID = 9059527466326065039L;
	public Integer	id	;//主键ID
	public Integer	 bookId;//所属练习本ID
	public String chapterJson;//章节Json
	public Integer updateId;
	public Date updateTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public Integer getUpdateId() {
		return updateId;
	}
	public void setUpdateId(Integer updateId) {
		this.updateId = updateId;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
