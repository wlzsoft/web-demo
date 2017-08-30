package com.demo.entity;

import java.io.Serializable;
import java.util.Date;

public class ChapterEntity implements Serializable {

	private static final long serialVersionUID = 9059527466326065039L;
	public Integer	id	;//主键ID
    public Integer	 parentId;//父级ID
    public String name ;//章节名称
	public Integer	 bookId;//所属练习本ID
	public Integer updateId;
	public Date updateTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
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
