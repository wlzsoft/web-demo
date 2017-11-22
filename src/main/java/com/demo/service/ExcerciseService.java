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
	
	private final String[] arrt={"A","B","C","D","E","F","G","H","I","J","K","L","M","I","O","P","Q","R","S","T","U","V","W","S","Y","Z"};
	
	
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
		//cardListAll =getCardAlgorithm_count(pointList,COUNT);//根据知识点 获取知识点卡片算法(无穿插排序,有数量限制)
		cardListAll =getCardAlgorithm(pointList,COUNT);//根据知识点 获取知识点卡片算法(有穿插混合排序)
		
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
		//cardListAll =getCardAlgorithm_count(pointList,COUNT);//根据知识点 获取知识点卡片算法(无穿插排序,有数量限制)
		cardListAll =getCardAlgorithm(pointList,COUNT);//根据知识点 获取知识点卡片算法(有穿插混合排序)
	  return cardListAll;
	} 
	
	
	/**
	 * 巩固复习知识点
	 * @param request
	 * @param response
	 * @param bookId
	 * @param chapterIds
	 * @return
	 */
	public List<CardDto> excerciseStrenthen_Button(String bookId,String chapterIds,Integer userId){
		List<CardDto> cardListAll = excerciseStrenthen( bookId, chapterIds, userId);
		return cardListAll;
	}
	
	
	/**
	 * 巩固复习知识点（熟练度、知识点排序 、复习熟悉度小于4的知识点，）【熟练度小于 4 的状态】
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
		//cardListAll =getCardAlgorithm_count(pointList,COUNT);//根据知识点 获取知识点卡片算法(无穿插排序,有数量限制)
		cardListAll =getCardAlgorithm(pointList,COUNT);//根据知识点 获取知识点卡片算法(有穿插混合排序)
	  return cardListAll;
	} 
	
	
	/**
	 * 巩固复习知识点（熟练度、知识点排序 、复习熟悉度= 4的知识点，）【熟练度等于 4 的状态】
	 * @param request
	 * @param response
	 * @param bookId
	 * @param chapterIds
	 * @return
	 */
	public List<CardDto> excerciseStrenthenFull(String bookId,String chapterIds,Integer userId){
		List<CardDto> cardListAll = new ArrayList<>();
		List<PonitDto> pointList = new ArrayList<>();
		if(null==chapterIds||chapterIds.equals("")){//如果章节为 null，则根据练习本bookId来获取巩固复习知识点练习
			pointList = excerciseDao.excerciseStrenthenFull_bookId(Integer.parseInt(bookId), userId);
		}else{
			String[] chapterId = chapterIds.split(",");
			if(chapterId.length>0){
				Integer[] chapter = new Integer[chapterId.length];
				for(int i=0;i<chapterId.length;i++){
					chapter[i]=Integer.parseInt(chapterId[i]);
				}
				pointList =excerciseDao.excerciseStrenthenFull_chapterIds(Integer.parseInt(bookId), chapter, userId);
			}
		}
		//cardListAll =getCardAlgorithm_count(pointList,COUNT);//根据知识点 获取知识点卡片算法(无穿插排序,有数量限制)
		cardListAll =getCardAlgorithm(pointList,COUNT);//根据知识点 获取知识点卡片算法(有穿插混合排序)
	  return cardListAll;
	}
	
	
	
	
	
	/**
	 * 根据知识点 获取知识点卡片算法(有穿插混合排序)
	 * @param pointList
	 * @return
	 */
	//List<CardDto> cardList_A = excerciseDao.findCardByPoindId(pointDao_A.getId());//根据知识点Id获取知识点下所有的卡片信息
	public List<CardDto> getCardAlgorithm(List<PonitDto> pointList,int cont){
		cont=COUNT;
		List<CardDto> cardListAll = new ArrayList<>();
		if(pointList.size()>0){
			PonitDto pointDao_B = null;
			List<CardDto> cardList_B =null;
			boolean flag =true;
			int j=0;
			for(int i=0;i<=pointList.size();i++){
				if(i==pointList.size()){
					for(int b=j;b<cardList_B.size();b++){
						cardListAll.add(cardList_B.get(b));
					}
					break;
				}else{
					PonitDto pointDao_A = pointList.get(i);
					List<CardDto> cardList_A = excerciseDao.findCardByPoindId(pointDao_A.getId());
					if(cardList_A.size()>0){
						if(flag){
							if((i+1)<pointList.size()){
								pointDao_B = pointList.get(++i);
							    cardList_B = excerciseDao.findCardByPoindId(pointDao_B.getId());	
							}else{
								cardListAll.addAll(cardList_A);
								break;
							}
						}
						for(int a=0;a<cardList_A.size();a++){
							CardDto dto_A = cardList_A.get(a);
							if(a==cardList_A.size()){
								break;
							}else{
								 while(cardList_B.size()==0){//一直找到下一个知识点有卡片为止
									if(i>=pointList.size()){//防止数组越界
										break;
									}else if((i+1)<pointList.size()){
										pointDao_B = pointList.get(++i);
										cardList_B = excerciseDao.findCardByPoindId(pointDao_B.getId());
									}
								 }
								//当A 的卡片个数比B 的多时
								if(cardList_A.size()>cardList_B.size()-(j>0?j-1:j)){
									  cardListAll.add(dto_A);
									if(a<cardList_B.size()){
									  cardListAll.add(cardList_B.get(j));	
									  j++;
									}else{
										do{
											if(i==pointList.size()){//防止数组越界
												break;
											}else{
												pointDao_B = pointList.get(++i);
												cardList_B = excerciseDao.findCardByPoindId(pointDao_B.getId());
											}
										}while(cardList_B.size()==0);//一直找到下一个知识点有卡片为止
										
										cardListAll.add(cardList_B.get(j)); 
										j++;
										if(cardList_A.size()<cardList_B.size()){
											flag=false;
										}
									}
									if(j==cardList_B.size()){
										j=0;
										flag=true;
									}
								}else if(cardList_A.size()<=cardList_B.size()-(j>0?j-1:j)){ //当A 的卡片个数比B 的少时
									cardListAll.add(dto_A);
									if(a<cardList_B.size()){
									  cardListAll.add(cardList_B.get(j));	
									  j++;
									}
									if(j==cardList_B.size()){
										j=0;
										flag=true;
									}else{
										flag=false;	
									}
									
								}
							}
						}
					}
	                if(cardListAll.size()>=COUNT){
	                	break;
	                }
				}
			}
		}
		return cardListAll;
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
