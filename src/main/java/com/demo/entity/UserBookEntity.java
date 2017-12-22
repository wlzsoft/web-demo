package com.demo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017-09-19.
 */
public class UserBookEntity implements Serializable {
	
	private static final long serialVersionUID = 4599543678350818379L;

	public Integer id;
	
	public Integer bookId;
	
	public Integer userId;
	
	public Date lastTime;
	
	public Integer dailyGoals;//练习本 每日联系目标
	
	public Integer hidden;//是否隐藏
	
	public String remark;//备注
	
	public Integer createId ;
	
	public Date createTime;
	
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public Integer getCreateId() {
		return createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public Integer getDailyGoals() {
		return dailyGoals;
	}

	public void setDailyGoals(Integer dailyGoals) {
		this.dailyGoals = dailyGoals;
	}

	public Integer getHidden() {
		return hidden;
	}

	public void setHidden(Integer hidden) {
		this.hidden = hidden;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}
