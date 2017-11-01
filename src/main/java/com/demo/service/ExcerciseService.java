package com.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.ExcerciseDao;
import com.demo.dto.CardDto;
import com.demo.dto.PointNumDto;
import com.demo.dto.PonitDto;

@Service
public class ExcerciseService {
	
	
	@Autowired
	private ExcerciseDao excerciseDao;
	
	private final int COUNT=20;
	
	
	/**
	 * 复习错误的知识点 （错题，熟练度、知识点排序）
	 * 
	 * @param request
	 * @param response
	 * @param bookId
	 * @param chapterIds
	 * @return
	 */
	public List<CardDto> excerciseError(String bookId,String chapterIds,Integer userId){
		List<CardDto> cardListAll = new ArrayList<>();
	    List<PonitDto> pointList = new ArrayList<>();
		
		
		if(null==chapterIds||chapterIds.equals("")){//如果章节为 null，则根据练习本bookId来获取新的的知识点练习
			pointList =  excerciseDao.excerciseError_bookId(Integer.parseInt(bookId), userId);
			
		}else{
			String[] chapterId = chapterIds.split(",");
			if(chapterId.length>0){
				Integer[] chapter = new Integer[chapterId.length];
				for(int i=0;i<chapterId.length;i++){
					chapter[i]=Integer.parseInt(chapterId[i]);
				}
				pointList =excerciseDao.excerciseError_chapterIds(Integer.parseInt(bookId), chapter, userId);
			}
		}
		cardListAll =getCardAlgorithm_count(pointList,COUNT);
		
	  return cardListAll;
	} 
	
	/**
	 * 复习新的的知识点 （学习新的知识点，周期为0的知识点，知识点排序）
	 * @param request
	 * @param response
	 * @param bookId
	 * @param chapterIds
	 * @return
	 */
	public List<CardDto> excerciseNew(String bookId,String chapterIds,Integer userId){
		 List<CardDto> cardListAll = new ArrayList<>();
		 List<PonitDto> pointList = new ArrayList<>();
		if(null==chapterIds||chapterIds.equals("")){//如果章节为 null，则根据练习本bookId来获取新的的知识点练习
			pointList= excerciseDao.excerciseNew_bookId(Integer.parseInt(bookId), userId);
			
		}else{
			String[] chapterId = chapterIds.split(",");
			if(chapterId.length>0){
				Integer[] chapter = new Integer[chapterId.length];
				for(int i=0;i<chapterId.length;i++){
					chapter[i]=Integer.parseInt(chapterId[i]);
				}
				pointList =excerciseDao.excerciseNew_chapterIds(Integer.parseInt(bookId), chapter, userId);
			}
		}
		cardListAll =getCardAlgorithm_count(pointList,COUNT);
	  return cardListAll;
	} 
	
	
	/**
	 * 巩固复习知识点（熟练度、知识点排序 、复习熟悉度小于4的知识点，）
	 * @param request
	 * @param response
	 * @param bookId
	 * @param chapterIds
	 * @return
	 */
	public List<CardDto> excerciseStrenthen(String bookId,String chapterIds,Integer userId){
		List<CardDto> cardListAll = new ArrayList<>();
		List<PonitDto> pointList = new ArrayList<>();
		if(null==chapterIds||chapterIds.equals("")){//如果章节为 null，则根据练习本bookId来获取巩固复习知识点练习
			pointList = excerciseDao.excerciseStrenthen_bookId(Integer.parseInt(bookId), userId);
		}else{
			String[] chapterId = chapterIds.split(",");
			if(chapterId.length>0){
				Integer[] chapter = new Integer[chapterId.length];
				for(int i=0;i<chapterId.length;i++){
					chapter[i]=Integer.parseInt(chapterId[i]);
				}
				pointList =excerciseDao.excerciseStrenthen_chapterIds(Integer.parseInt(bookId), chapter, userId);
			}
		}
		cardListAll =getCardAlgorithm_count(pointList,COUNT);
	  return cardListAll;
	} 
	
	
	/**
	 * 根据知识点 获取知识点卡片算法(有穿插排序)
	 * @param pointList
	 * @return
	 */
	public List<CardDto> getCardAlgorithm(List<PonitDto> pointList){
		if(pointList.size()>0){
			int coun =0;
			for(int i=0;i<pointList.size();i++){
				PonitDto pointDao_fist = pointList.get(i);
				int j=i++;
				List<CardDto> cardList = excerciseDao.findCardByPoindId(pointDao_fist.getId());//根据知识点Id获取知识点下所有的卡片信息
				
			}
		}
		return null;
	}
	
	
	/**
	 * 根据知识点 获取知识点卡片算法(无穿插排序,有数量限制)
	 * @param pointList
	 * @return
	 */
	public List<CardDto> getCardAlgorithm_count(List<PonitDto> pointList,Integer count){
		List<CardDto> cardListAll = new ArrayList<>();
		if(pointList.size()>0){
			for(int i=0;i<pointList.size();i++){
				PonitDto pointDao_fist = pointList.get(i);
				List<CardDto> cardList = excerciseDao.findCardByPoindId(pointDao_fist.getId());//根据知识点Id获取知识点下所有的卡片信息
				cardListAll.addAll(cardList);
				if(cardListAll.size()>=count){
					break;
				}
			}
		}
		return cardListAll;
	}
    
	
	/**
	 * 获取练习本中，复习，错题，新的 的数量
	 * @param userId
	 * @param bookId
	 * @return
	 */
	public PointNumDto getPointNum(Integer userId,Integer bookId){
		PointNumDto dto = new PointNumDto();
		List<PonitDto> pointList_error =  excerciseDao.excerciseError_bookId(bookId, userId);
		    dto.setExErrorNum(pointList_error.size());
		List<PonitDto> pointList_new= excerciseDao.excerciseNew_bookId(bookId, userId);
		    dto.setExNewNum(pointList_new.size());
		List<PonitDto> pointList_strenthen = excerciseDao.excerciseStrenthen_bookId(bookId, userId);
			dto.setExStrengthenNum(pointList_strenthen.size());
		return dto;
	}
	
	
}
