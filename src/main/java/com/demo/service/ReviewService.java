package com.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.ExcerciseBookDao;
import com.demo.dao.LorePointDao;
import com.demo.dao.ReviewDao;
import com.demo.dto.BookDto;
import com.demo.dto.CardDto;
import com.demo.dto.PonitDto;
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
	
	@Autowired
	private ExcerciseBookDao excerciseBookDao;
	
	@Transactional
	public void reviewCrad(String lorePointId,String cradId,Integer right){
		
		//保存用户练习流水
		UserExerciseDetailEntity entity = new UserExerciseDetailEntity();
			entity.setEndExerciseTime(new Date());
			entity.setExerciseDate(new Date());
			entity.setStartExerciseTime(new Date());
			entity.setExerciseUpshot(right);
			entity.setCardId(Integer.parseInt(cradId));
			entity.setPointId(Integer.parseInt(lorePointId));
			entity.setUserId(1);
			reviewDao.savaUserExcerciseDetail(entity);
		//更新用户知识点联系详情
		LorePointExerciseDetailEntity  detailEntity = lorePointDao.findPointIdByDetail(Integer.parseInt(lorePointId));
		int exerciseNumber =detailEntity.getExerciseNumber();
		 if(right==1){//正确
			 int cc =  DateUtil.compareDate(new Date(),detailEntity.getNextExerciseTime());
			 if(cc!=-1){
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
						 
						 detailEntity.setExerciseCycle(detailEntity.getExerciseCycle()+2);
						 
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
						 detailEntity.setExerciseCycle(detailEntity.getExerciseCycle()+1);
					 }
					 
					 detailEntity.setExerciseNumber(detailEntity.getExerciseNumber()+1); 
					 detailEntity.setConCorrectNumber(detailEntity.getConCorrectNumber()+1);
					 detailEntity.setConErrorNumber(0);
					 detailEntity.setLastExerciseDate(new Date());
					 detailEntity.setCorrectNumber(detailEntity.getCorrectNumber()+1);
				}
		 }else{//错误
			 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.FIRST_TIME.timesanmp)); 
			 detailEntity.setExerciseNumber(detailEntity.getExerciseNumber()+1); 
			 detailEntity.setExerciseCycle(1);
			 detailEntity.setConCorrectNumber(0);
			 detailEntity.setConErrorNumber(detailEntity.getConErrorNumber()+1);
			 detailEntity.setErrorNumber(detailEntity.getErrorNumber()+1);
			 detailEntity.setLastExerciseDate(new Date()); 
		 }	
		 reviewDao.updateLorePointExerciseDetail(detailEntity);
	}
	
	
	public List<PonitDto> excercise(Integer userId){
		//根据练习本ID排序 是叶子节点
		 List<BookDto> bookList = reviewDao.bookList(userId);
		 List<PonitDto> listAll = new ArrayList<PonitDto>();
		 int con = 0;//用于记录查询知识点的个数
		      for(int j=0;j<bookList.size();j++){
					 BookDto entity = bookList.get(j);
					 List<PonitDto> pointList = reviewDao.reviewPoint(entity.getId(),userId); //查询小于或等于 当前时间的知识点 (下次练习时间不为null的数据)
					 if(pointList.size()>0){
						 if(listAll.size()==0){
							 if(pointList.size()<=20){
								 listAll.addAll(pointList); 
								 con=con+pointList.size();
							 }else{
								 for(int i=0;i<20;i++){
									 listAll.add(pointList.get(i)) ;
								 }
								 con=20;
							 }
						 }else{
							 if(pointList.size()<=(20-con)){
								 listAll.addAll(pointList); 
								 con=con+pointList.size();
							 }else{
								 for(int i=0;i<(20-con);i++){
									 listAll.add(pointList.get(i)) ;
								 }
								 con=20; 
							 }
						 } 
					 }
					  if(con>=20){
						  System.out.println("条件以满足无需再继续查找...........................");
						 break; 
					  }
			   }
		 
		 if(listAll.size()<20){
		     for(int j=0;j<bookList.size();j++){
					 BookDto entity = bookList.get(j);
					 List<PonitDto> pointList = reviewDao.reviewPointNull(entity.getId(),userId); //查询下次练习时间为null的数据 按 id升序排
					 if(pointList.size()>0){
						 if(listAll.size()==0){
							 if(pointList.size()<=20){
								 listAll.addAll(pointList); 
								 con=con+pointList.size();
							 }else{
								 for(int i=0;i<20;i++){
									 listAll.add(pointList.get(i)) ;
								 }
								 con=20;
							 }
						 }else{
							 if(pointList.size()<=(20-con)){
								 listAll.addAll(pointList); 
								 con=con+pointList.size();
							 }else{
								 for(int i=0;i<(20-con);i++){
									 listAll.add(pointList.get(i)) ;
								 }
								 con=20; 
							 }
						 } 
					 }
				 
				  if(con>=20){
					  System.out.println("条件以满足无需再继续查找...........................");
					 break; 
				  }
			 }
		 }
		 //当所有知识点都练习完了的时候，进行随即抽取
		 if(listAll.size()<20){
			 List<PonitDto> ponitList = lorePointDao.roundPoint(userId);
			 java.util.Random random = new java.util.Random();
			 for(int j=0;j<20-listAll.size();j++){
				 int randomPos = random.nextInt(ponitList.size());
				 listAll.add(ponitList.get(randomPos));
				 ponitList.remove(randomPos);
			 }
		 }
		 
		 System.out.println("数据大小为--------------："+listAll.size());
		return listAll ;
	}
	
	
	public CardDto roundCard(Integer pointId){
		  List<CardDto> cardList = reviewDao.roundCard(pointId);
	      java.util.Random random = new java.util.Random();
	      int randomPos = random.nextInt(cardList.size());
	      return cardList.get(randomPos);
	}
	
	
	public static void main(String[] args){
		Date date = new Date(); 
		long unixTimestamp = date.getTime()/1000;  
		System.out.println(unixTimestamp);  
		
	}
	// 1503364197

}
