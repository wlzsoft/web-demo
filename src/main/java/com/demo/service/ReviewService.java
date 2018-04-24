package com.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.PointDao;
import com.demo.dao.ReviewDao;
import com.demo.dto.BookDto;
import com.demo.dto.CardDto;
import com.demo.dto.PointExerciseDetailDto;
import com.demo.dto.PonitDto;
import com.demo.util.enums.LearningCycle;
import com.pmp.entity.UserExerciseDetailEntity;
import com.smartframe.basics.util.DateFormatEnum;
import com.smartframe.basics.util.DateUtil;

@Service("reviewService")
public class ReviewService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReviewService.class);
	
	@Autowired
	private UtilService utilService;
	
	@Autowired
	private ReviewDao reviewDao;
	
	@Autowired
	private PointDao lorePointDao;
	
	@Autowired
	private SystemService systemService ;
	
	private final int COUNT=20;
	
	/**
	 * 复习保存
	 * @param lorePointId 知识点ID
	 * @param cradId 卡片ID
	 * @param right 回答是否正确 1：正确   0：错误
	 * @param duration 本次答题耗时(毫秒)
	 * @return
	 */
	
/*	
	@Transactional
	public void reviewCrad(String lorePointId,String cradId,Integer right,Integer duration){
		Integer userId =systemService.getCurrentUser().getId();
		//保存用户练习流水
		UserExerciseDetailEntity entity = new UserExerciseDetailEntity();
			entity.setEndExerciseTime(new Date());
			entity.setExerciseDate(new Date());
			entity.setStartExerciseTime(new Date());
			entity.setExerciseUpshot(right);
			entity.setCardId(Integer.parseInt(cradId));
			entity.setPointId(Integer.parseInt(lorePointId));
			entity.setUserId(userId);
			entity.setDuration(duration);
			reviewDao.savaUserExcerciseDetail(entity);
		//更新用户知识点联系详情
		
		PointExerciseDetailDto  detailEntity = lorePointDao.pointIdByDetail(Integer.parseInt(lorePointId),userId);
		int exerciseCycle =detailEntity.getExerciseCycle();//获取练习周期
		
		if(detailEntity.getExerciseNumber()==0){
			detailEntity.setFirstExerciseDate(new Date());// 设置第一次练习时间
		}
		
		 if(right==1){//正确
			  *//**
			   * 如果答题错误 练习周期往前升一级，熟练度为 +1 ，熟练度最高为3 最低为0 
			   * 
			   * **//*
			  int cc =  DateUtil.compareDate(new Date(),detailEntity.getNextExerciseTime());//比较时间    -1:小于 ;  1: 大于; 0:相等
			  if(cc>-1){//***当前时间 大于 或 等于 下次练习时间，即 在这个周期之内
					 
				     if(exerciseCycle==0){
						 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.FIRST_TIME.timesanmp));//计划下次练习时间
					 }else if(exerciseCycle==1){
						 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.SECOND_TIME.timesanmp));//计划下次练习时间
					 }else if(exerciseCycle==2){
						 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.Third_TIME.timesanmp));
					 }else if(exerciseCycle==3){
						 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.FOURTH_TIME.timesanmp));
					 }else if(exerciseCycle==4){
						 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.FIFTH_TIME.timesanmp));
					 }else if(exerciseCycle==5){
						 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.SIXTH_TIME.timesanmp));
					 }else if(exerciseCycle==6){
						 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.SEVENTH_TIME.timesanmp));
					 }else if(exerciseCycle==7){
						 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.EIGHTH_TIME.timesanmp));
					 }else if(exerciseCycle==8){
						 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.NINTH_TIME.timesanmp));
					 }
					 detailEntity.setExerciseCycle((exerciseCycle<9)?exerciseCycle+1:exerciseCycle);//练习周期 【周期最高为 9 】
					 
					 //熟练度（0，1，2，3，4） 
                     if(exerciseCycle<9){
                    	  detailEntity.setSkilled(1); 
				     }else{
				    	 detailEntity.setSkilled(detailEntity.getSkilled()>3?detailEntity.getSkilled()+1:4);
				     }
			  }else{
				  detailEntity.setSkilled((detailEntity.getSkilled()<=3)?detailEntity.getSkilled()+1:detailEntity.getSkilled()); ////熟练度（0，1，2，3，4）  
			  }
			 detailEntity.setExerciseNumber(detailEntity.getExerciseNumber()+1); //练习次数
			 detailEntity.setConCorrectNumber(detailEntity.getConCorrectNumber()+1);//连续回答正确次数
			 detailEntity.setConErrorNumber(0);//连续回答错误次数
			 detailEntity.setLastExerciseDate(new Date());//上一次练习的日期
			 detailEntity.setCorrectNumber(detailEntity.getCorrectNumber()+1);//正确数
			 
			 if(detailEntity.getSkilled()>1){
				 detailEntity.setState(3);//正确需要强化[熟练度大于1时 就不需要巩固 ]
			 }else {
				 detailEntity.setState(2);//正确需要巩固
			 }
			 
		
		 }else{
			     *//** 错误
			      * 如果答题错误 练习周期往后降一级，熟练度为 0 
			      * 
			      * **//*
				 if(exerciseCycle==0){//因为周期最少为0，所以从1开始
					 detailEntity.setNextExerciseTime(new Date());//计划下次练习时间
				 }else if(exerciseCycle==1){//因为周期最少为0，所以从1开始
					 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.FIRST_TIME.timesanmp));//计划下次练习时间
				 }else if(exerciseCycle==2){
					 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.SECOND_TIME.timesanmp));//计划下次练习时间
				 }else if(exerciseCycle==3){
					 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.Third_TIME.timesanmp));
				 }else if(exerciseCycle==4){
					 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.FOURTH_TIME.timesanmp));
				 }else if(exerciseCycle==5){
					 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.FIFTH_TIME.timesanmp));
				 }else if(exerciseCycle==6){
					 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.SIXTH_TIME.timesanmp));
				 }else if(exerciseCycle==7){
					 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.SEVENTH_TIME.timesanmp));
				 }else if(exerciseCycle==8){
					 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.EIGHTH_TIME.timesanmp));
				 }else if(exerciseCycle==9){
					 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.NINTH_TIME.timesanmp));
				 }
			 
				 detailEntity.setExerciseCycle((detailEntity.getExerciseCycle()>1)?detailEntity.getExerciseCycle()-1:detailEntity.getExerciseCycle());//练习周期
				 detailEntity.setExerciseNumber(detailEntity.getExerciseNumber()+1); //练习次数
				 detailEntity.setSkilled(0);//熟练度（0，1，2，3）
				 detailEntity.setConCorrectNumber(0);//连续回答正确次数
				 detailEntity.setConErrorNumber(detailEntity.getConErrorNumber()+1);//连续回答错误次数
				 detailEntity.setErrorNumber(detailEntity.getErrorNumber()+1);//错误数
				 detailEntity.setLastExerciseDate(new Date()); //上一次练习的日期
				 detailEntity.setState(1);//答错后 提示上次答错
		 }	
		 reviewDao.updateLorePointExerciseDetail(detailEntity);
		 LOGGER.info("更新练习明细成功！");
		 utilService.checkErrorCard(detailEntity.getBookId(),detailEntity.getPointId(),Integer.parseInt(cradId),userId,right);
		 LOGGER.info("处理错题库信息成功！");
		 
		 utilService.bookProgress(userId, detailEntity.getBookId());
		 LOGGER.info("练习本进度计算统计完成");
	}
	
	**/
	
	/**
	 * 复习用户下全部练习本
	 * @param userId
	 * @return
	 */
	public List<PonitDto> excercise(Integer userId){
		//根据练习本ID排序 是叶子节点
		 List<BookDto> bookList = reviewDao.bookList(userId);
		 List<PonitDto> listAll = new ArrayList<PonitDto>();
		 int con = 0;//用于记录查询知识点的个数
		      for(int j=0;j<bookList.size();j++){
					 BookDto entity = bookList.get(j);
					 List<PonitDto> pointList = reviewDao.reviewPoint(entity.getId(),null,userId); //查询小于或等于 当前时间的知识点 (下次练习时间不为null的数据)
					 if(pointList.size()>0){
						 if(listAll.size()==0){
							 if(pointList.size()<=COUNT){
								 listAll.addAll(pointList); 
								 con=con+pointList.size();
							 }else{
								 for(int i=0;i<COUNT;i++){
									 listAll.add(pointList.get(i)) ;
								 }
								 con=COUNT;
							 }
						 }else{
							 if(pointList.size()<=(COUNT-con)){
								 listAll.addAll(pointList); 
								 con=con+pointList.size();
							 }else{
								 for(int i=0;i<(COUNT-con);i++){
									 listAll.add(pointList.get(i)) ;
								 }
								 con=COUNT; 
							 }
						 } 
					 }
					  if(con>=COUNT){
						  System.out.println("条件以满足无需再继续查找...........................");
						 break; 
					  }
			   }
		 
		 if(listAll.size()<COUNT){
		     for(int j=0;j<bookList.size();j++){
					 BookDto entity = bookList.get(j);
					 List<PonitDto> pointList = reviewDao.reviewPointNull(entity.getId(),null,userId); //查询下次练习时间为null的数据 按 id升序排
					 if(pointList.size()>0){
						 if(listAll.size()==0){
							 if(pointList.size()<=COUNT){
								 listAll.addAll(pointList); 
								 con=con+pointList.size();
							 }else{
								 for(int i=0;i<COUNT;i++){
									 listAll.add(pointList.get(i)) ;
								 }
								 con=COUNT;
							 }
						 }else{
							 if(pointList.size()<=(COUNT-con)){
								 listAll.addAll(pointList); 
								 con=con+pointList.size();
							 }else{
								 for(int i=0;i<(COUNT-con);i++){
									 listAll.add(pointList.get(i)) ;
								 }
								 con=COUNT; 
							 }
						 } 
					 }
				 
				  if(con>=COUNT){
					  System.out.println("条件以满足无需再继续查找...........................");
					 break; 
				  }
			 }
		 }
		 
		 //当所有知识点都练习完了的时候，进行随即抽取
		 if(listAll.size()<COUNT){
			 Integer[] pointIdArray = new Integer[listAll.size()];
			 for(int i=0;i<listAll.size();i++){
				 Integer pointId = listAll.get(i).getId();
				 pointIdArray[i]=pointId;
			 }
			 List<PonitDto> ponitList = null;
			 if(pointIdArray.length>0){
				 ponitList= reviewDao.roundPoint(userId,pointIdArray); 
			 }else{
				 ponitList= reviewDao.roundPointByUserId(userId); 
			 }
			
			 
			 if(ponitList.size()<=COUNT-listAll.size()){
				 listAll.addAll(ponitList);
			 }else{
				for(int i=0;i<=COUNT-listAll.size();i++){
					listAll.add(ponitList.get(i));
				} 
			 }
		 }
		 
		 System.out.println("数据大小为--------------："+listAll.size());
		return listAll ;
	}
	
	/**根据练习本ID 来计算获取知识点
	 * @param userId
	 * @param bookId
	 * @return
	 */
	public List<CardDto> excerciseCard(Integer userId,Integer bookId){
		 List<CardDto> cardListAll = new ArrayList<>();
		 List<PonitDto> pointList = new ArrayList<>();
		 int con = 0;//用于记录查询练习知识点卡片的个数
		
		//01: 查询小于或等于 当前时间的知识点 (下次练习时间不为null的数据)
	    pointList = reviewDao.reviewPoint(bookId,null,userId); //查询下次练习时间小于或等于 当前时间的知识点 (下次练习时间不为null的数据)
			if(pointList.size()>0){
				List<CardDto> cardList_01 = new ArrayList<>();
				for(PonitDto dto:pointList){
					cardList_01 = reviewDao.roundCard(dto.getId());//根据知识点ID 查询知识点下面所有的卡片
					if(cardListAll.size()==0){
						 if(cardList_01.size()<=COUNT){
							 cardListAll.addAll(cardList_01); 
							 con=con+cardList_01.size();
						 }else{
							 cardListAll.addAll(cardList_01.subList(0, COUNT-1));
							 con=COUNT;
						 }
					 }else{
						 if(cardList_01.size()<=(COUNT-con)){
							 cardListAll.addAll(cardList_01); 
							 con=con+cardList_01.size();
						 }else{
							 cardListAll.addAll(cardList_01.subList(0, COUNT-con-1));//取List的前几条数据
							 con=COUNT; 
						 }
					 } 
					  if(con>=COUNT){
						  System.out.println("条件以满足无需再继续查找...........................");
						 break; 
					  }
				}
			}
			//02:查询下次练习时间为null的数据
			if(cardListAll.size()<COUNT){
				  pointList = reviewDao.reviewPointNull(bookId,null,userId); //查询下次练习时间为null的数据 
				  if(pointList.size()>0){
					  List<CardDto> cardList_02 = new ArrayList<>();
					  for(PonitDto dto:pointList){
						  cardList_02 = reviewDao.roundCard(dto.getId());//根据知识点ID 查询知识点下面所有的卡片
						  if(cardListAll.size()==0){
							 if(cardList_02.size()<=COUNT){
								 cardListAll.addAll(cardList_02); 
								 con=con+cardList_02.size();
							 }else{
								 cardListAll.addAll(cardList_02.subList(0, COUNT-1));
								 con=COUNT;
							 } 
						  }else{
								 if(cardList_02.size()<=(COUNT-con)){
									 cardListAll.addAll(cardList_02); 
									 con=con+cardList_02.size();
								 }else{
									 cardListAll.addAll(cardList_02.subList(0, COUNT-con-1));//取List的前几条数据
									 con=COUNT; 
								 } 
						  }
						  if(con>=COUNT){
							  System.out.println("条件以满足无需再继续查找...........................");
							 break; 
						  }
					  }
				  }
			}
		
		  //03:当所有条件查询还没有满【COUNT】的练习数量时，就查询练习本下所有的知识点 进行随机抽取	 
		  if(cardListAll.size()==0){
			   pointList = reviewDao.reviewPointBefore(bookId, null, userId); //查询所有知识点
			   if(pointList.size()>0){
				   List<CardDto> cardList_03 = new ArrayList<>();
				   for(PonitDto dto:pointList){
					   cardList_03 = reviewDao.roundCard(dto.getId());//根据知识点ID 查询知识点下面所有的卡片
					   if(cardListAll.size()==0){
							 if(cardList_03.size()<=COUNT){
								 cardListAll.addAll(cardList_03); 
								 con=con+cardList_03.size();
							 }else{
								 cardListAll.addAll(cardList_03.subList(0, COUNT-1));
								 con=COUNT;
							 } 
					   }else{
							 if(cardList_03.size()<=(COUNT-con)){
								 cardListAll.addAll(cardList_03); 
								 con=con+cardList_03.size();
							 }else{
								 cardListAll.addAll(cardList_03.subList(0, COUNT-con-1));//取List的前几条数据
								 con=COUNT; 
							 } 
					  }
						  if(con>=COUNT){
							  System.out.println("条件以满足无需再继续查找...........................");
							 break; 
						  }
				   }
			   }
		  }
		return cardListAll ;
	}
	
	
	
	/**
	 * 根据指定练习本的章节进行练习
	 * @param userId 用户ID
	 * @param bookId 练习本ID
	 * @param chapterId 章节ID数组
	 * @return
	 */
	public List<CardDto> excerciseCard(Integer userId,Integer bookId,Integer ... chapterId){
		 List<CardDto> cardListAll = new ArrayList<>();
		 List<PonitDto> pointList = new ArrayList<>();
		 int con = 0;//用于记录查询练习知识点卡片的个数
		
		//01: 查询小于或等于 当前时间的知识点 (下次练习时间不为null的数据)
	    pointList = reviewDao.reviewPoint(bookId,chapterId,userId); //查询下次练习时间小于或等于 当前时间的知识点 (下次练习时间不为null的数据)
			if(pointList.size()>0){
				List<CardDto> cardList_01 = new ArrayList<>();
				for(PonitDto dto:pointList){
					cardList_01 = reviewDao.roundCard(dto.getId());//根据知识点ID 查询知识点下面所有的卡片
					if(cardListAll.size()==0){
						 if(cardList_01.size()<=COUNT){
							 cardListAll.addAll(cardList_01); 
							 con=con+cardList_01.size();
						 }else{
							 cardListAll.addAll(cardList_01.subList(0, COUNT-1));
							 con=COUNT;
						 }
					 }else{
						 if(cardList_01.size()<=(COUNT-con)){
							 cardListAll.addAll(cardList_01); 
							 con=con+cardList_01.size();
						 }else{
							 cardListAll.addAll(cardList_01.subList(0, COUNT-con-1));//取List的前几条数据
							 con=COUNT; 
						 }
					 } 
					  if(con>=COUNT){
						  System.out.println("条件以满足无需再继续查找...........................");
						 break; 
					  }
				}
			}
			//02:查询下次练习时间为null的数据
			if(cardListAll.size()<COUNT){
				  pointList = reviewDao.reviewPointNull(bookId,chapterId,userId); //查询下次练习时间为null的数据 
				  if(pointList.size()>0){
					  List<CardDto> cardList_02 = new ArrayList<>();
					  for(PonitDto dto:pointList){
						  cardList_02 = reviewDao.roundCard(dto.getId());//根据知识点ID 查询知识点下面所有的卡片
						  if(cardListAll.size()==0){
							 if(cardList_02.size()<=COUNT){
								 cardListAll.addAll(cardList_02); 
								 con=con+cardList_02.size();
							 }else{
								 cardListAll.addAll(cardList_02.subList(0, COUNT-1));
								 con=COUNT;
							 } 
						  }else{
								 if(cardList_02.size()<=(COUNT-con)){
									 cardListAll.addAll(cardList_02); 
									 con=con+cardList_02.size();
								 }else{
									 cardListAll.addAll(cardList_02.subList(0, COUNT-con-1));//取List的前几条数据
									 con=COUNT; 
								 } 
						  }
						  if(con>=COUNT){
							  System.out.println("条件以满足无需再继续查找...........................");
							 break; 
						  }
					  }
				  }
			}
		
		  //03:当所有条件查询还没有满【COUNT】的练习数量时，就查询练习本下所有的知识点 进行随机抽取	 
		  if(cardListAll.size()==0){
			   pointList = reviewDao.reviewPointBefore(bookId, chapterId, userId); //查询所有知识点
			   if(pointList.size()>0){
				   List<CardDto> cardList_03 = new ArrayList<>();
				   for(PonitDto dto:pointList){
					   cardList_03 = reviewDao.roundCard(dto.getId());//根据知识点ID 查询知识点下面所有的卡片
					   if(cardListAll.size()==0){
							 if(cardList_03.size()<=COUNT){
								 cardListAll.addAll(cardList_03); 
								 con=con+cardList_03.size();
							 }else{
								 cardListAll.addAll(cardList_03.subList(0, COUNT-1));
								 con=COUNT;
							 } 
					   }else{
							 if(cardList_03.size()<=(COUNT-con)){
								 cardListAll.addAll(cardList_03); 
								 con=con+cardList_03.size();
							 }else{
								 cardListAll.addAll(cardList_03.subList(0, COUNT-con-1));//取List的前几条数据
								 con=COUNT; 
							 } 
					  }
					  if(con>=COUNT){
						  System.out.println("条件以满足无需再继续查找...........................");
						 break; 
					  }
				   }
			   }
		  }
		return cardListAll ;
	}
	
	/**
	 * 查询用户指定日期内 练习新题的数量
	 * @param toDate
	 * @param bookId
	 * @param ueserId
	 * @return
	 */
	public List<PointExerciseDetailDto> getDailyGoals(Date toDate,Integer bookId){
		Integer userId =systemService.getCurrentUser().getId();
		List<PointExerciseDetailDto>  list = reviewDao.getDailyGoals(DateUtil.format(toDate, DateFormatEnum.YEAR_TO_DAY), bookId, userId);
		return list;
	}
	
	public static void main(String[] args){
		Date date = new Date(); 
		long unixTimestamp = date.getTime()/1000;  
		System.out.println(unixTimestamp);  
		
	}


}
