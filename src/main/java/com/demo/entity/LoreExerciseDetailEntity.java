package com.demo.entity;

import java.io.Serializable;
import java.util.Date;

public class LoreExerciseDetailEntity implements Serializable {

	private static final long serialVersionUID = 8598685062349072088L;
	
	  public Integer id            ;
	  public Integer userId        ;
	  public Integer lorePointId        ;
	  public Date firstExerciseDate  ;
	  public Date lastExerciseDate   ;
	  public Integer exerciseNumber;
	  public float  correctRate   ;
	  public Integer  correctNumber ;
	  public Integer errorNumber   ;
	  public Date avgConsumingTime   ;
	  public float weights       ;
	  public float  grasp         ;
	  
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
	public Integer getLorePointId() {
		return lorePointId;
	}
	public void setLorePointId(Integer lorePointId) {
		this.lorePointId = lorePointId;
	}
	public Date getFirstExerciseDate() {
		return firstExerciseDate;
	}
	public void setFirstExerciseDate(Date firstExerciseDate) {
		this.firstExerciseDate = firstExerciseDate;
	}
	public Date getLastExerciseDate() {
		return lastExerciseDate;
	}
	public void setLastExerciseDate(Date lastExerciseDate) {
		this.lastExerciseDate = lastExerciseDate;
	}
	public Integer getExerciseNumber() {
		return exerciseNumber;
	}
	public void setExerciseNumber(Integer exerciseNumber) {
		this.exerciseNumber = exerciseNumber;
	}
	public float getCorrectRate() {
		return correctRate;
	}
	public void setCorrectRate(float correctRate) {
		this.correctRate = correctRate;
	}
	public Integer getCorrectNumber() {
		return correctNumber;
	}
	public void setCorrectNumber(Integer correctNumber) {
		this.correctNumber = correctNumber;
	}
	public Integer getErrorNumber() {
		return errorNumber;
	}
	public void setErrorNumber(Integer errorNumber) {
		this.errorNumber = errorNumber;
	}
	public Date getAvgConsumingTime() {
		return avgConsumingTime;
	}
	public void setAvgConsumingTime(Date avgConsumingTime) {
		this.avgConsumingTime = avgConsumingTime;
	}
	public float getWeights() {
		return weights;
	}
	public void setWeights(float weights) {
		this.weights = weights;
	}
	public float getGrasp() {
		return grasp;
	}
	public void setGrasp(float grasp) {
		this.grasp = grasp;
	}
	  
}
