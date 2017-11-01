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
	
	/**
	 * 根据指定练习本的章节进行练习
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
			List<CardDto> cardList_error = excerciseService.getCardAlgorithm_count(pointList_error, COUNT);
			cardListAll.addAll(cardList_error);
			if(cardListAll.size()>=COUNT){
				 return cardListAll;
			 }
			 
		   //****2、再查询是否有需要复习的知识点
			 List<PonitDto> pointList_strenthen = excerciseDao.excerciseStrenthen_bookId(bookId, userId);
			 List<CardDto> cardList_strenthen = excerciseService.getCardAlgorithm_count(pointList_strenthen, COUNT-cardListAll.size());
			 cardListAll.addAll(cardList_strenthen);
			 if(cardListAll.size()>=COUNT){
				 return cardListAll; 
			 }
		   //****3、最后查询新的知识点
			 List<PonitDto> pointList_new = excerciseDao.excerciseNew_bookId(bookId, userId);
			 List<CardDto> cardList_new = excerciseService.getCardAlgorithm_count(pointList_new, COUNT-cardListAll.size());
			 cardListAll.addAll(cardList_new);
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
					List<CardDto> cardList_error = excerciseService.getCardAlgorithm_count(pointList_error, COUNT);
					cardListAll.addAll(cardList_error);
					if(cardListAll.size()>=COUNT){
						 return cardListAll;
					 }
			    //****2、再查询是否有需要复习的知识点
					List<PonitDto> pointList_strenthen =excerciseDao.excerciseStrenthen_chapterIds(bookId, chapter, userId);
					List<CardDto> cardList_strenthen = excerciseService.getCardAlgorithm_count(pointList_strenthen, COUNT-cardListAll.size());
					 cardListAll.addAll(cardList_strenthen);
					 if(cardListAll.size()>=COUNT){
						 return cardListAll; 
					 }
				//****3、最后查询新的知识点
					List<PonitDto> pointList_new =excerciseDao.excerciseNew_chapterIds(bookId, chapter, userId);
					List<CardDto> cardList_new = excerciseService.getCardAlgorithm_count(pointList_new, COUNT-cardListAll.size());
					cardListAll.addAll(cardList_new);
			}
		}
		return cardListAll;	
	}
	
	
	
}
