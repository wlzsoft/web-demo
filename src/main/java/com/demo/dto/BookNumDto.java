package com.demo.dto;

import java.util.Date;

/**
 * 练习本 练习信息对象
 * @author Administrator
 *
 */
public class BookNumDto{
	
	public Integer bookId;
	
	public String bookName;
	
	public Integer dailyGoal;//每日目标
	
	public Integer completeNum;//今日完成数
	
	public Integer continueNum; //连续天数
	
	public Integer pointNum; //总共知识点数量
	
	public Integer pointNumY; //已经练习
	
	public Integer state;//状态  0：新增   1：上次答错    2：巩固    
	
	public Integer exNum;//需练习的数量
	
	public Date nextExerciseTime;//练习本下最近需要练习的知识点的下次练习时间

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}


	public Integer getContinueNum() {
		return continueNum;
	}


	public Integer getCompleteNum() {
		return completeNum;
	}

	public void setCompleteNum(Integer completeNum) {
		this.completeNum = completeNum;
	}



	public void setContinueNum(Integer continueNum) {
		this.continueNum = continueNum;
	}

	public Integer getPointNum() {
		return pointNum;
	}

	public void setPointNum(Integer pointNum) {
		this.pointNum = pointNum;
	}

	public Integer getPointNumY() {
		return pointNumY;
	}

	public void setPointNumY(Integer pointNumY) {
		this.pointNumY = pointNumY;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Integer getDailyGoal() {
		return dailyGoal;
	}

	public void setDailyGoal(Integer dailyGoal) {
		this.dailyGoal = dailyGoal;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getExNum() {
		return exNum;
	}

	public void setExNum(Integer exNum) {
		this.exNum = exNum;
	}

	public Date getNextExerciseTime() {
		return nextExerciseTime;
	}

	public void setNextExerciseTime(Date nextExerciseTime) {
		this.nextExerciseTime = nextExerciseTime;
	}
	
}
