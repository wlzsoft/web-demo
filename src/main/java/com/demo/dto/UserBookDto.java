package com.demo.dto;

import java.util.Date;

public class UserBookDto {

	public Integer bookId;
	
	public String bookName;
	
	public Date lastTime;//最后练习时间
	
	public Integer dailyGoal;//练习本 每日练习目标
	
	public Integer hidden;//是否隐藏
	
	public String remark;//备注
	
	public Integer notification ;// 是否开启提醒 0 不开启，1  开启
	
	public String notificationTime;// 提醒时间，使用 , 号分隔，每条记
	
	public Integer pointNumRemain;//知识点未练习数量

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public Integer getDailyGoal() {
		return dailyGoal;
	}

	public void setDailyGoal(Integer dailyGoal) {
		this.dailyGoal = dailyGoal;
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

	public Integer getNotification() {
		return notification;
	}

	public void setNotification(Integer notification) {
		this.notification = notification;
	}

	public String getNotificationTime() {
		return notificationTime;
	}

	public void setNotificationTime(String notificationTime) {
		this.notificationTime = notificationTime;
	}

	public Integer getPointNumRemain() {
		return pointNumRemain;
	}

	public void setPointNumRemain(Integer pointNumRemain) {
		this.pointNumRemain = pointNumRemain;
	}
	

}
