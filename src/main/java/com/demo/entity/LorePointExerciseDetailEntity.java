package com.demo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 知识点练习详情
 * @author Administrator
 *
 */
public class LorePointExerciseDetailEntity implements Serializable {

	private static final long serialVersionUID = 8598685062349072088L;
	
	  public Integer id            ;
	  public Integer userId        ;
	  public Integer lorePointId        ; //知识点ID
	  public Date nextExerciseTime ;//'计划下次练习时间',
	  public Integer exerciseCycle ;// '知识点当前练习周期',
	  public Integer conCorrectNumber ;// '连续回答正确次数',
	  public Integer conErrorNumber ;// '连续回答错误次数',
	  public Date firstExerciseDate  ;//首次练习日期
	  public Date lastExerciseDate   ;//上一次练习日期
	  public Integer exerciseNumber;//练习总次数
	  public float  correctRate   ;//总正确数
	  public Integer  correctNumber ;//总正确数
	  public Integer errorNumber   ;//总错误数
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
	
	public Date getNextExerciseTime() {
		return nextExerciseTime;
	}
	public void setNextExerciseTime(Date nextExerciseTime) {
		this.nextExerciseTime = nextExerciseTime;
	}
	public Integer getExerciseCycle() {
		return exerciseCycle;
	}
	public void setExerciseCycle(Integer exerciseCycle) {
		this.exerciseCycle = exerciseCycle;
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
