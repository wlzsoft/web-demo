package com.demo.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.PointDao;
import com.demo.dao.Point_branchDao;
import com.demo.dao.ReviewDao;
import com.demo.dto.PointExerciseDetailDto;
import com.demo.util.enums.LearningCycle;
import com.demo.util.enums.LengthDate;
import com.pmp.entity.PointBranchEntity;
import com.pmp.entity.PointExerciseDetailEntity;
import com.pmp.entity.UserExerciseDetailEntity;
import com.smartframe.basics.util.DateUtil;

@Service("answerService")
public class AnswerService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AnswerService.class);
	
	@Autowired
	private SystemService systemService ;
	
	@Autowired
	private ReviewDao reviewDao;
	
	@Autowired
	private PointDao pointDao;
	
	@Autowired
	private Point_branchDao point_branchDao;
	
	@Autowired
	private UtilService utilService;
	
	/**
	 * 答题提交
	 * @param pointId 知识点ID
	 * @param cradId 卡片ID
	 * @param right 回答是否正确 1：正确   0：错误
	 * @param duration 本次答题耗时(毫秒)
	 * @return
	 */
	@Transactional
	public void cardAnswer(String pointId,String cradId,Integer right,Integer duration){
		Integer userId =systemService.getCurrentUser().getId();
		//保存用户练习流水
		UserExerciseDetailEntity entity = new UserExerciseDetailEntity();
			entity.setEndExerciseTime(new Date());
			entity.setExerciseDate(new Date());
			entity.setStartExerciseTime(new Date());
			entity.setExerciseUpshot(right);
			entity.setCardId(Integer.parseInt(cradId));
			entity.setPointId(Integer.parseInt(pointId));
			entity.setUserId(userId);
			entity.setDuration(duration);
			reviewDao.savaUserExcerciseDetail(entity);
		
		//更新用户知识点联系详情
		PointExerciseDetailDto  detailEntity = pointDao.pointIdByDetail(Integer.parseInt(pointId),userId);
		//如果是null说明改练习题是 新的 需要往知识点明细库里面插入数据
		if(null==detailEntity){
			PointBranchEntity pointBranchEntity =point_branchDao.findPointBranchById(Integer.valueOf(pointId));
			//插入知识点练习明细
		    PointExerciseDetailEntity pointDetail = new PointExerciseDetailEntity();
				pointDetail.setUserId(userId);
				pointDetail.setPointId(Integer.valueOf(pointId));
				pointDetail.setBookId(pointBranchEntity.getBookId());
				pointDetail.setNextExerciseTime(new Date());
				pointDetail.setNextUpdateTime(new Date());
				pointDetail.setExerciseCycle(0);
				pointDetail.setState(0);
				pointDao.addPointDetail(pointDetail);
				
				//进行等同赋值
				detailEntity.setUserId(userId);
				detailEntity.setPointId(Integer.valueOf(pointId));
				detailEntity.setBookId(pointBranchEntity.getBookId());
				detailEntity.setNextExerciseTime(new Date());
				detailEntity.setNextUpdateTime(new Date());
				detailEntity.setExerciseCycle(0);
				detailEntity.setState(0);
				
				if(right==1){//答对
					detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.FIRST_TIME.timesanmp));
					detailEntity.setNextUpdateTime(DateUtil.addOrSubHour(new Date(), LengthDate.ONE_DATE.timesanmp));
					detailEntity.setExerciseCycle(1);//记忆等级 +1 
					detailEntity.setLastUpdateTime(new Date());//上次记忆等级更新时间
					detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.TWELVE_TIME.timesanmp));
					
					detailEntity.setLastExerciseTime(new Date());//上次练习时间
					detailEntity.setExerciseNumber(detailEntity.getExerciseNumber()+1); //练习次数
					detailEntity.setConCorrectNumber(detailEntity.getConCorrectNumber()+1);//连续回答正确次数
					detailEntity.setConErrorNumber(0);//连续回答错误次数
					detailEntity.setCorrectNumber(detailEntity.getCorrectNumber()+1);//正确数
					detailEntity.setState(2);//巩固
				}else{//答错
					detailEntity.setExerciseCycle(0);//记忆等级 +1 
					detailEntity.setLastExerciseTime(new Date());//上次练习时间
					detailEntity.setNextExerciseTime(new Date());//下次联系时间
					detailEntity.setLastUpdateTime(null);//上次记忆等级更新时间
					detailEntity.setNextUpdateTime(null);
					 
					detailEntity.setExerciseNumber(1); //练习次数
					detailEntity.setConCorrectNumber(0);//连续回答正确次数
					detailEntity.setConErrorNumber(1);//连续回答错误次数
					detailEntity.setErrorNumber(1);//错误数
					detailEntity.setState(1);//答错后 提示上次答错
				}
		}else{
			int exerciseCycle =detailEntity.getExerciseCycle();//记忆等级
			
			//如果ExerciseNumber=0 是第首次练习
			if(exerciseCycle==0){
				detailEntity.setFirstExerciseTime(new Date());// 设置首次练习时间
			}
			
			 if(right==1){//-------答对
				 int cc =  DateUtil.compareDate(new Date(),detailEntity.getNextUpdateTime());//比较时间    -1:小于 ;  1: 大于; 0:相等
				 if(cc>-1){//***当前时间 大于 或 等于 下次练习时间，即 在这个周期之内
					 if(exerciseCycle==0){
						 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.FIRST_TIME.timesanmp));
						 detailEntity.setNextUpdateTime(DateUtil.addOrSubHour(new Date(), LengthDate.ONE_DATE.timesanmp));
					 }else if(exerciseCycle==1){
						 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.SECOND_TIME.timesanmp));
						 detailEntity.setNextUpdateTime(DateUtil.addOrSubHour(new Date(), LengthDate.TWO_DATE.timesanmp));
					 }else if(exerciseCycle==2){
						 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.Third_TIME.timesanmp));
						 detailEntity.setNextUpdateTime(DateUtil.addOrSubHour(new Date(), LengthDate.Third_DATE.timesanmp));
					 }else if(exerciseCycle==3){
						 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.FOURTH_TIME.timesanmp));
						 detailEntity.setNextUpdateTime(DateUtil.addOrSubHour(new Date(), LengthDate.FOURTH_DATE.timesanmp));
					 }else if(exerciseCycle==4){
						 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.FIFTH_TIME.timesanmp));
						 detailEntity.setNextUpdateTime(DateUtil.addOrSubHour(new Date(), LengthDate.FIFTH_DATE.timesanmp));
					 }else if(exerciseCycle==5){
						 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.SIXTH_TIME.timesanmp));
						 detailEntity.setNextUpdateTime(DateUtil.addOrSubHour(new Date(), LengthDate.SIXTH_DATE.timesanmp));
						 
					 }else if(exerciseCycle==6){
						 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.SEVENTH_TIME.timesanmp));
						 detailEntity.setNextUpdateTime(DateUtil.addOrSubHour(new Date(), LengthDate.SEVENTH_DATE.timesanmp));
						 
					 }else if(exerciseCycle==7){
						 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.EIGHTH_TIME.timesanmp));
						 detailEntity.setNextUpdateTime(DateUtil.addOrSubHour(new Date(), LengthDate.EIGHTH_DATE.timesanmp));
						 
					 }else if(exerciseCycle==8){
						 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.NINTH_TIME.timesanmp));
						 detailEntity.setNextUpdateTime(DateUtil.addOrSubHour(new Date(), LengthDate.NINTH_DATE.timesanmp));
					 }else if(exerciseCycle==9){
						 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.TEN_TIME.timesanmp));
						 detailEntity.setNextUpdateTime(DateUtil.addOrSubHour(new Date(), LengthDate.TEN_DATE.timesanmp));
						 
					 }else if(exerciseCycle==10){
						 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.ELEVEN_TIME.timesanmp));
						 detailEntity.setNextUpdateTime(DateUtil.addOrSubHour(new Date(), LengthDate.ELEVEN_DATE.timesanmp));
						 
					 }else if(exerciseCycle==11){
						 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.TWELVE_TIME.timesanmp));
						 detailEntity.setNextUpdateTime(DateUtil.addOrSubHour(new Date(), LengthDate.TWELVE_DATE.timesanmp));
					 }
					 
					 detailEntity.setExerciseCycle(exerciseCycle+1);//记忆等级 +1 
					 detailEntity.setLastUpdateTime(new Date());//上次记忆等级更新时间
				 }else{
					 if(exerciseCycle==0){
						 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.FIRST_TIME.timesanmp));
					 }else if(exerciseCycle==1){
						 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.SECOND_TIME.timesanmp));
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
					 }else if(exerciseCycle==9){
						 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.TEN_TIME.timesanmp));
					 }else if(exerciseCycle==10){
						 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.ELEVEN_TIME.timesanmp));
					 }else if(exerciseCycle==11){
						 detailEntity.setNextExerciseTime(DateUtil.addOrSubHour(new Date(), LearningCycle.TWELVE_TIME.timesanmp));
					 }
				 }
				 detailEntity.setLastExerciseTime(new Date());//上次练习时间
				 detailEntity.setExerciseNumber(detailEntity.getExerciseNumber()+1); //练习次数
				 detailEntity.setConCorrectNumber(detailEntity.getConCorrectNumber()+1);//连续回答正确次数
				 detailEntity.setConErrorNumber(0);//连续回答错误次数
				 detailEntity.setCorrectNumber(detailEntity.getCorrectNumber()+1);//正确数
				 detailEntity.setState(2);//巩固
				 
			 }else{//-----------答错
				 detailEntity.setExerciseCycle(0);//记忆等级 +1 
				 detailEntity.setLastExerciseTime(new Date());//上次练习时间
				 detailEntity.setNextExerciseTime(new Date());//下次联系时间
				 detailEntity.setLastUpdateTime(null);//上次记忆等级更新时间
				 detailEntity.setNextUpdateTime(null);
				 
				 detailEntity.setExerciseNumber(detailEntity.getExerciseNumber()+1); //练习次数
				 detailEntity.setExerciseNumber(detailEntity.getExerciseNumber()+1); //练习次数
				 detailEntity.setConCorrectNumber(0);//连续回答正确次数
				 detailEntity.setConErrorNumber(detailEntity.getConErrorNumber()+1);//连续回答错误次数
				 detailEntity.setErrorNumber(detailEntity.getErrorNumber()+1);//错误数
				 detailEntity.setState(1);//答错后 提示上次答错
			 }	
		}
		
		 reviewDao.updateLorePointExerciseDetail(detailEntity);
		 LOGGER.info("更新练习明细成功！");
		 
		 utilService.checkErrorCard(detailEntity.getBookId(),detailEntity.getPointId(),Integer.parseInt(cradId),userId,right);
		 LOGGER.info("处理错题库信息成功！");
		 
		 utilService.bookProgress(userId, detailEntity.getBookId());
		 LOGGER.info("练习本进度计算统计完成");
	}

}
