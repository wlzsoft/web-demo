package com.demo.entity;

import java.io.Serializable;
import java.util.Date;

public class LoreCardExerciseDetailEntity implements Serializable{

	private static final long serialVersionUID = -4597855193912517426L;
	
   public Integer id            ;
   public Integer userId        ;//用户ID
   public Integer cardId        ;//卡片ID
   public Date firstExerciseDate  ;//首次练习日期
   public Date lastExerciseDate   ;//上一次练习日期
   public Integer exerciseNumber;//练习次数
   public Integer conCorrectNumber;//连续回答正确次数	
   public Integer conErrorNumber;//连续回答错误次数		
   public float  correctRate   ;//正确率
   public Integer  correctNumber ;//正确数
   public Integer errorNumber   ;//错误数
   public Date avgConsumingTime   ;//平均耗时
   public float weights       ;//练习权重
   public float  grasp         ;//掌握值
	  
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
	
	public Integer getCardId() {
		return cardId;
	}
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
	public Integer getConCorrectNumber() {
		return conCorrectNumber;
	}
	public void setConCorrectNumber(Integer conCorrectNumber) {
		this.conCorrectNumber = conCorrectNumber;
	}
	public Integer getConErrorNumber() {
		return conErrorNumber;
	}
	public void setConErrorNumber(Integer conErrorNumber) {
		this.conErrorNumber = conErrorNumber;
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
