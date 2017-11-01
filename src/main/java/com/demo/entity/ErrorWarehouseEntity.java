package com.demo.entity;

import java.io.Serializable;
import java.util.Date;

public class ErrorWarehouseEntity implements Serializable{
	
	private static final long serialVersionUID = 6993298852804510112L;
	
  	  public Integer id ;
  	  
	  public Integer bookId ;
	  
	  public Integer pointId;
	  
	  public Integer cardId;
	  
	  public Integer isRight;
	  
	  public Integer userId;
	  
	  public Date updateTime;
	  
	  public Date createTime;
	  
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
	public Integer getPointId() {
		return pointId;
	}
	public void setPointId(Integer pointId) {
		this.pointId = pointId;
	}
	public Integer getCardId() {
		return cardId;
	}
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
	public Integer getIsRight() {
		return isRight;
	}
	public void setIsRight(Integer isRight) {
		this.isRight = isRight;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
