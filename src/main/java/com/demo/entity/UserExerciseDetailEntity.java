package com.demo.entity;

import java.io.Serializable;
import java.util.Date;

public class UserExerciseDetailEntity implements Serializable {

	private static final long serialVersionUID = -311568145338751599L;
	
	  public Integer id  ;
	  public Integer  userId               ;
	  public Integer  loreCardId           ;
	  public Integer lorePointId;
	  public Date  exerciseDate    ;
	  public Date  startExerciseTime     ;
	  public Integer  endExerciseTime      ;
	  public Integer  exerciseUpshot          ;
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
	public Integer getEndExerciseTime() {
		return endExerciseTime;
	}
	public void setEndExerciseTime(Integer endExerciseTime) {
		this.endExerciseTime = endExerciseTime;
	}
	public Integer getExerciseUpshot() {
		return exerciseUpshot;
	}
	public void setExerciseUpshot(Integer exerciseUpshot) {
		this.exerciseUpshot = exerciseUpshot;
	}
	  
}
