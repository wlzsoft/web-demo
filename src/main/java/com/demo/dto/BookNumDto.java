package com.demo.dto;

/**
 * 练习本 练习信息对象
 * @author Administrator
 *
 */
public class BookNumDto extends PointNumDto {
	
	public Integer dailyGoals;//每日目标
	
	public Integer completeNumber;//每日完成数
	
	public Integer continueNum; //连续天数
	
	public Integer pointNum; //总共知识点数量
	
	public Integer pointNumY; //已经练习

	public Integer getDailyGoals() {
		return dailyGoals;
	}

	public void setDailyGoals(Integer dailyGoals) {
		this.dailyGoals = dailyGoals;
	}

	public Integer getContinueNum() {
		return continueNum;
	}
	
	public Integer getCompleteNumber() {
		return completeNumber;
	}

	public void setCompleteNumber(Integer completeNumber) {
		this.completeNumber = completeNumber;
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
	
}
