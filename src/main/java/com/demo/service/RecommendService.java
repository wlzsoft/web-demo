package com.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.ExcerciseDao;
import com.demo.dto.CardDto;
import com.demo.dto.PonitDto;

/**
 * 智能推荐复习算法
 * @author Administrator
 *
 */
@Service
public class RecommendService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RecommendService.class);

	private final int COUNT=20;
	
	@Autowired
	private ExcerciseDao excerciseDao;
	
	@Autowired
	private ExcerciseService excerciseService;
	
	@Autowired
	private UtilService utilService;
	
	/**
	 * 根据指定练习本的章节进行练习【用户智能推荐复习算法】
	 * @param userId 用户ID
	 * @param bookId 练习本ID
	 * @param chapterId 章节ID数组
	 * @return
	 */
	public List<CardDto> excerciseCard(Integer userId,Integer bookId,String chapterIds){
		List<CardDto> cardListAll = new ArrayList<>();
		if(null==chapterIds||chapterIds.equals("")){
			LOGGER.info("按练习本来获取练习本知识点.....");
           //****1、先查询是否有错题
			List<PonitDto> pointList_error =  excerciseDao.excerciseError_bookId(bookId, userId);
			List<CardDto> cardList_error = excerciseService.getCardAlgorithm(pointList_error, COUNT);
			cardListAll.addAll(cardList_error);
			if(cardListAll.size()>=COUNT){
				 return cardListAll;
			 }
			 
		   //****2、再查询是否有需要巩固复习的知识点
			 List<PonitDto> pointList_strenthen = excerciseDao.excerciseStrenthen_bookId(bookId, userId);
			 List<CardDto> cardList_strenthen = excerciseService.getCardAlgorithm(pointList_strenthen, COUNT-cardListAll.size());
			 cardListAll.addAll(cardList_strenthen);
			 if(cardListAll.size()>=COUNT){
				 return cardListAll; 
			 }
		   //****3、再查询新的知识点
		     //判断今日是否完成目标
			 Boolean flag = utilService.getIsComplete(userId, bookId);//返回 true 标示完成  ,false 标示未完成
			 if(flag){
				 LOGGER.info("用户："+userId+" 练习本:"+bookId+" 今日目标已经完成不需要练习新的知识点");
			 }else{
				 
				//获取练习本练习目标数量
				 Integer dailyGoalsNumber = utilService.getDailyGoalsNumber(userId, bookId);
				//获取今日完成数量
				 Integer completeNuber = utilService.getCompleteNumber(userId, bookId);
				//还继续需要完成 知识点的数量
				 Integer count=dailyGoalsNumber-completeNuber; 
				 
				 List<PonitDto> pointList_new = excerciseDao.excerciseNew_bookId(bookId, userId);
				 
				 List<PonitDto> pointCountList = new ArrayList<PonitDto>();
				 if(pointList_new.size()>count){
						for(int i=0;i<count;i++){
							pointCountList.add(pointList_new.get(i));
						}	
					}else if(pointList_new.size()<=count){
						pointCountList.addAll(pointList_new);
				 }
				 
				 List<CardDto> cardList_new = excerciseService.getCardAlgorithm(pointCountList, COUNT-cardListAll.size());
				 cardListAll.addAll(cardList_new);
				 if(cardListAll.size()>=COUNT){
					 return cardListAll; 
				 }
			 }

			 
			//****4、最后查询强化知识点
/*			 
			List<PonitDto> pointList_intensify =excerciseDao.excerciseIntensify_bookId(bookId, userId);
			List<CardDto> cardList_intensify = excerciseService.getCardAlgorithm(pointList_intensify, COUNT-cardListAll.size());
			cardListAll.addAll(cardList_intensify);
			 if(cardListAll.size()>=COUNT){
				 return cardListAll; 
			 }	
			 
	*/		 
			 
		   //****5、最后查询熟练度满分的知识点
/*			 
			 List<PonitDto> pointList_full = excerciseDao.excerciseIntensifyFull_bookId(bookId, userId);
			 List<CardDto> cardList_full = excerciseService.getCardAlgorithm(pointList_full, COUNT-cardListAll.size());
			 cardListAll.addAll(cardList_full);
*/		
		}else{
			LOGGER.info("按练习本+章节 来获取练习本知识点.....");
			String[] chapterId_arry = chapterIds.split(",");
			if(chapterId_arry.length>0){
				Integer[] chapter = new Integer[chapterId_arry.length];
				for(int i=0;i<chapterId_arry.length;i++){
					chapter[i]=Integer.parseInt(chapterId_arry[i]);
				}

				//****1、先查询是否有错题
					List<PonitDto> pointList_error=excerciseDao.excerciseError_chapterIds(bookId, chapter, userId);
					List<CardDto> cardList_error = excerciseService.getCardAlgorithm(pointList_error, COUNT);
					cardListAll.addAll(cardList_error);
					if(cardListAll.size()>=COUNT){
						 return cardListAll;
					 }
			    //****2、再查询是否有需要巩固的知识点
					List<PonitDto> pointList_strenthen =excerciseDao.excerciseStrenthen_chapterIds(bookId, chapter, userId);
					List<CardDto> cardList_strenthen = excerciseService.getCardAlgorithm(pointList_strenthen, COUNT-cardListAll.size());
					 cardListAll.addAll(cardList_strenthen);
					 if(cardListAll.size()>=COUNT){
						 return cardListAll; 
					 }
				//****3、最后查询新的知识点
				     //判断今日是否完成目标
					 Boolean flag = utilService.getIsComplete(userId, bookId);//返回 true 标示完成  ,false 标示未完成
					 if(flag){
						 LOGGER.info("用户："+userId+" 练习本:"+bookId+" 今日目标已经完成不需要练习新的知识点");
					 }else{
						 
						//获取练习本练习目标数量
						 Integer dailyGoalsNumber = utilService.getDailyGoalsNumber(userId, bookId);
						//获取今日完成数量
						 Integer completeNuber = utilService.getCompleteNumber(userId, bookId);
						//还继续需要完成 知识点的数量
						 Integer count=dailyGoalsNumber-completeNuber; 
						 
						 List<PonitDto> pointList_new =excerciseDao.excerciseNew_chapterIds(bookId, chapter, userId);
						 
						 List<PonitDto> pointCountList = new ArrayList<PonitDto>();
						 if(pointList_new.size()>count){
								for(int i=0;i<count;i++){
									pointCountList.add(pointList_new.get(i));
								}	
							}else if(pointList_new.size()<=count){
								pointCountList.addAll(pointList_new);
						 }
						 
						
						List<CardDto> cardList_new = excerciseService.getCardAlgorithm(pointCountList, COUNT-cardListAll.size());
						cardListAll.addAll(cardList_new);
						 if(cardListAll.size()>=COUNT){
							 return cardListAll; 
						 } 
					 }
				
			   //****4、最后查询强化知识点
/*					 
					List<PonitDto> pointList_intensify =excerciseDao.excerciseIntensify_chapterIds(bookId, chapter, userId);
					List<CardDto> cardList_intensify = excerciseService.getCardAlgorithm(pointList_intensify, COUNT-cardListAll.size());
					cardListAll.addAll(cardList_intensify);
					 if(cardListAll.size()>=COUNT){
						 return cardListAll; 
					 }		 
					 
*/					 
			    //****5、最后查询熟练度满分的知识点
/*					 
				    List<PonitDto> pointList_full = excerciseDao.excerciseIntensifyFull_chapterIds(bookId, chapter, userId);
				    List<CardDto> cardList_full = excerciseService.getCardAlgorithm(pointList_full, COUNT-cardListAll.size());
				    cardListAll.addAll(cardList_full);
*/		
					 }
		}
		return cardListAll;	
	}
	
	
	
}
