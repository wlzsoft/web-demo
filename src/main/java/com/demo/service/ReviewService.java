package com.demo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.LorePointDao;
import com.demo.dao.ReviewDao;
import com.demo.entity.LorePointExerciseDetailEntity;
import com.demo.entity.UserExerciseDetailEntity;
import com.demo.util.enums.LearningCycle;
import com.smartframe.basics.util.DateUtil;

@Service("reviewService")
public class ReviewService {
	
	@Autowired
	private ReviewDao reviewDao;
	
	@Autowired
	private LorePointDao lorePointDao;
	
	@Transactional
	public void reviewCrad(String lorePointId,String cradId,Integer right){
		
		//保存用户练习流水
		UserExerciseDetailEntity entity = new UserExerciseDetailEntity();
			entity.setEndExerciseTime(new Date());
			entity.setExerciseDate(new Date());
			entity.setStartExerciseTime(new Date());
			entity.setExerciseUpshot(right);
			entity.setLoreCardId(Integer.parseInt(cradId));
			entity.setLorePointId(Integer.parseInt(lorePointId));
			entity.setUserId(1);
			reviewDao.savaUserExcerciseDetail(entity);
		//更新用户知识点联系详情
		LorePointExerciseDetailEntity  detailEntity = lorePointDao.findPointIdByDetail(Integer.parseInt(lorePointId));
		int exerciseNumber =detailEntity.getExerciseNumber(); 
		 if(right==1){//正确
			 if(detailEntity.getConCorrectNumber()>=2){
				 if(exerciseNumber==2){
					 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.FIFTH_TIME.timesanmp));
				 }else if(exerciseNumber==3){
					 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.SIXTH_TIME.timesanmp));
				 }else if(exerciseNumber==4){
					 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.SEVENTH_TIME.timesanmp));
				 }else if(exerciseNumber==5){
					 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.EIGHTH_TIME.timesanmp));
				 }else if(exerciseNumber==6){
					 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.NINTH_TIME.timesanmp));
				 }else if(exerciseNumber==7){
					 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.TENTH_TIME.timesanmp));
				 }else if(exerciseNumber==8){
					 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.ELEVENTH_TIME.timesanmp));
				 }else if(exerciseNumber==9){
					 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.TWELFTH_TIME.timesanmp));
				 }else if(exerciseNumber==10){
					 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.THIRTEENTH_TIME.timesanmp));
				 }else if(exerciseNumber==11){
					 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.FOURTEENTH_TIME.timesanmp));
				 } 
			 }else{
				 if(exerciseNumber==0){
					 detailEntity.setFirstExerciseDate(new Date());
					 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.FIRST_TIME.timesanmp));//下次练习时间
				 }else if(exerciseNumber==1){
					 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.SECOND_TIME.timesanmp));
				 }else if(exerciseNumber==2){
					 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.Third_TIME.timesanmp));
				 }else if(exerciseNumber==3){
					 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.FOURTH_TIME.timesanmp));
				 }else if(exerciseNumber==4){
					 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.FIFTH_TIME.timesanmp));
				 }else if(exerciseNumber==5){
					 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.SIXTH_TIME.timesanmp));
				 }else if(exerciseNumber==6){
					 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.SEVENTH_TIME.timesanmp));
				 }else if(exerciseNumber==7){
					 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.EIGHTH_TIME.timesanmp));
				 }else if(exerciseNumber==8){
					 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.NINTH_TIME.timesanmp));
				 }else if(exerciseNumber==9){
					 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.TENTH_TIME.timesanmp));
				 }else if(exerciseNumber==10){
					 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.ELEVENTH_TIME.timesanmp));
				 }else if(exerciseNumber==11){
					 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.TWELFTH_TIME.timesanmp));
				 }else if(exerciseNumber==12){
					 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.THIRTEENTH_TIME.timesanmp));
				 }else if(exerciseNumber==13){
					 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.FOURTEENTH_TIME.timesanmp));
				 } 
			 }
			 
			 detailEntity.setExerciseNumber(detailEntity.getExerciseNumber()+1); 
			 detailEntity.setExerciseCycle(detailEntity.getExerciseNumber()+1);
			 detailEntity.setConCorrectNumber(detailEntity.getConCorrectNumber()+1);
			 detailEntity.setConErrorNumber(0);
			 detailEntity.setLastExerciseDate(new Date());
		 }else{//错误
			 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.FIFTH_TIME.timesanmp)); 
			 detailEntity.setExerciseNumber(detailEntity.getExerciseNumber()+1); 
			 detailEntity.setExerciseCycle(detailEntity.getExerciseNumber()+1);
			 detailEntity.setConCorrectNumber(0);
			 detailEntity.setConErrorNumber(detailEntity.getConErrorNumber()+1);
			 detailEntity.setLastExerciseDate(new Date()); 
		 }	
		 reviewDao.updateLorePointExerciseDetail(detailEntity);
	}

}
