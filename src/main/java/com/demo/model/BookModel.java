package com.demo.model;

import java.math.BigDecimal;
import java.util.Date;

public class BookModel {
	
	public Integer id ;
	
    public String bookName;//练习本名称
    
    public BigDecimal price;//价格
    
    public Date startTime ;//价格开始时间
    
    public Date endTime;//价格结束时间
    
    public Integer status;//状态
	
	public String language	;//	卡片使用的语言	
	
	public String area	;//	卡片所属领域
	
	public Integer  sharedType ;//共享类型 (0私有、1只读共享)
	
	public Date  createTime ;
	
	public Integer  createId ;
	
	public Date  updateTime ;
	
	public Integer  updateId ;
	
	public Date  updateDetailTime ;
	
	public Integer  updateDetailId ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Integer getSharedType() {
		return sharedType;
	}

	public void setSharedType(Integer sharedType) {
		this.sharedType = sharedType;
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

	public Date getUpdateDetailTime() {
		return updateDetailTime;
	}

	public void setUpdateDetailTime(Date updateDetailTime) {
		this.updateDetailTime = updateDetailTime;
	}

	public Integer getUpdateDetailId() {
		return updateDetailId;
	}

	public void setUpdateDetailId(Integer updateDetailId) {
		this.updateDetailId = updateDetailId;
	}


}
