package com.demo.entity;

import java.io.Serializable;
import java.util.Date;

public class UserExerciseDetailEntity implements Serializable {

	private static final long serialVersionUID = -311568145338751599L;
	
	  public Integer id  ;
	  public Integer  userId               ;
	  public Integer  loreCardId           ;//卡片ID'
	  public Integer lorePointId; //知识点ID
	  public Date  exerciseDate    ;//练习日期
	  public Date  startExerciseTime     ;//练习开始时间
	  public Date  endExerciseTime      ;//练习结束时间
	  public Integer  exerciseUpshot          ;//练习结果(1：正确:  2：错误)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getLoreCardId() {
		return loreCardId;
	}
	public void setLoreCardId(Integer loreCardId) {
		this.loreCardId = loreCardId;
	}
	public Integer getLorePointId() {
		return lorePointId;
	}
	public void setLorePointId(Integer lorePointId) {
		this.lorePointId = lorePointId;
	}
	public Date getExerciseDate() {
		return exerciseDate;
	}
	public void setExerciseDate(Date exerciseDate) {
		this.exerciseDate = exerciseDate;
	}
	public Date getStartExerciseTime() {
		return startExerciseTime;
	}
	public void setStartExerciseTime(Date startExerciseTime) {
		this.startExerciseTime = startExerciseTime;
	}

	public Date getEndExerciseTime() {
		return endExerciseTime;
	}
	public void setEndExerciseTime(Date endExerciseTime) {
		this.endExerciseTime = endExerciseTime;
	}
	public Integer getExerciseUpshot() {
		return exerciseUpshot;
	}
	public void setExerciseUpshot(Integer exerciseUpshot) {
		this.exerciseUpshot = exerciseUpshot;
	}
	  
}
