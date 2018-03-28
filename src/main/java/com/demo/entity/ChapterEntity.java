package com.demo.entity;

import java.io.Serializable;
import java.util.Date;

public class ChapterEntity implements Serializable {

	private static final long serialVersionUID = 9059527466326065039L;
	public Integer	id	;//主键ID
	public Integer bookId;//所属练习本ID
	public String name;//章节名称
	public Integer level; //章节层级
	public Integer parentId ;//父级章节id
	public Integer sort;//排序
	public Integer isOpen;//是否公开试读  1:是  0：否
	public Integer isDel;//是否删除  1:是  0：否
	public Date createTime	;//创建时间				
	public Integer createId	;//创建人ID				
	public Date updateTime	;//修改时间				
	public Integer updateId	;//修改人ID	
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
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
	public Integer getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	
}
