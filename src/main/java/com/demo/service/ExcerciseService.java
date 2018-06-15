package com.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.ExcerciseDao;
import com.demo.dto.CardDto;
import com.demo.dto.PointNumDto;
import com.demo.dto.PonitDto;
import com.pmp.entity.BookEntity;

@Service
public class ExcerciseService {
	
	
	@Autowired
	private ExcerciseDao excerciseDao;
	
	@Autowired
	private UserBookService userBookService;
	
	@Autowired
	private UtilService utilService;
	
	@Autowired
	private LoreCradService cradService;
	
	@Autowired
	private ExcerciseBookService bookService;
	
	@Autowired
	private CacheObjectService cacheObjectService;
	
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
	    
		Integer bookVer = Integer.valueOf(cacheObjectService.getBookVer(bookId));//获取练习当前版本号
		
		if(null==chapterIds||chapterIds.equals("")){//如果章节为 null，则根据练习本bookId来获取新的的知识点练习
			pointList =  excerciseDao.excerciseError_bookId(Integer.parseInt(bookId), userId,bookVer);
			
		}else{
			String[] chapterId = chapterIds.split(",");
			if(chapterId.length>0){
				Integer[] chapter = new Integer[chapterId.length];
				for(int i=0;i<chapterId.length;i++){
					chapter[i]=Integer.parseInt(chapterId[i]);
				}
				pointList =excerciseDao.excerciseError_chapterIds(Integer.parseInt(bookId), chapter, userId,bookVer);
			}
		}
		//cardListAll =getCardAlgorithm_count(pointList,COUNT);//根据知识点 获取知识点卡片算法(无穿插排序,有数量限制)
	    //cardListAll =getCardAlgorithm(pointList,COUNT);//根据知识点 获取知识点卡片算法(有穿插混合排序)
		cardListAll =getRoundCard(pointList,COUNT);//随机获取知识点卡片
		
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
		 
		 Integer bookVer = Integer.valueOf(cacheObjectService.getBookVer(bookId));//获取练习当前版本号
		 
		if(null==chapterIds||chapterIds.equals("")){//如果章节为 null，则根据练习本bookId来获取新的的知识点练习
			pointList= excerciseDao.excerciseNew_bookId(Integer.parseInt(bookId), userId,bookVer);
		}else{
			String[] chapterId = chapterIds.split(",");
			if(chapterId.length>0){
				Integer[] chapter = new Integer[chapterId.length];
				for(int i=0;i<chapterId.length;i++){
					chapter[i]=Integer.parseInt(chapterId[i]);
				}
				pointList =excerciseDao.excerciseNew_chapterIds(Integer.parseInt(bookId), chapter, userId,bookVer);
			}
		}
		
		Integer count=5;//默认练新 为5个
		Integer dailyGoalNum = utilService.getDailyGoalsNumber(userId, Integer.parseInt(bookId));//获取练习本练习目标 数量
		Integer completeNum  = utilService.getCompleteNumber(userId, Integer.parseInt(bookId));//获取每日完成目标数
		
		if(completeNum>=dailyGoalNum){
			count=dailyGoalNum;
		}else{
			count=dailyGoalNum-completeNum;
		}
		
		List<PonitDto> pointCountList = new ArrayList<PonitDto>();
		if(pointList.size()>count){
			for(int i=0;i<count;i++){
				pointCountList.add(pointList.get(i));
			}	
		}else if(pointList.size()<=count){
			pointCountList.addAll(pointList);
		}

		//cardListAll =getCardAlgorithm(pointCountList,COUNT);//根据知识点 获取知识点卡片算法(有穿插混合排序)
		 cardListAll =getRoundCard(pointCountList,COUNT);//随机获取知识点卡片
	  return cardListAll;
	} 
	
	
	/**
	 * 巩固复习知识点（熟练度、知识点排序 、复习熟悉度 等于 1 的知识点，）【熟练度等于 1 的状态】
	 * @param request
	 * @param response
	 * @param bookId
	 * @param chapterIds
	 * @return
	 */
	public List<CardDto> excerciseStrenthen(String bookId,String chapterIds,Integer userId){
		List<CardDto> cardListAll = new ArrayList<>();
		List<PonitDto> pointList = new ArrayList<>();
		
		Integer bookVer = Integer.valueOf(cacheObjectService.getBookVer(bookId));//获取练习当前版本号
		
		if(null==chapterIds||chapterIds.equals("")){//如果章节为 null，则根据练习本bookId来获取巩固复习知识点练习
			pointList = excerciseDao.excerciseStrenthen_bookId(Integer.parseInt(bookId), userId,bookVer);
		}else{
			String[] chapterId = chapterIds.split(",");
			if(chapterId.length>0){
				Integer[] chapter = new Integer[chapterId.length];
				for(int i=0;i<chapterId.length;i++){
					chapter[i]=Integer.parseInt(chapterId[i]);
				}
				pointList =excerciseDao.excerciseStrenthen_chapterIds(Integer.parseInt(bookId), chapter, userId,bookVer);
			}
		}
		//cardListAll =getCardAlgorithm_count(pointList,COUNT);//根据知识点 获取知识点卡片算法(无穿插排序,有数量限制)
		//cardListAll =getCardAlgorithm(pointList,COUNT);//根据知识点 获取知识点卡片算法(有穿插混合排序)
		cardListAll =getRoundCard(pointList,COUNT);//随机获取知识点卡片
	  return cardListAll;
	} 
	
	
	/**
	 * 强化训练知识点（熟练度、知识点排序 、复习熟悉度= 4的知识点，）【熟练度等于 4 的状态】
	 * @param request
	 * @param response
	 * @param bookId
	 * @param chapterIds
	 * @return
	 */
	public List<CardDto> excerciseIntensifyFull(String bookId,String chapterIds,Integer userId){
		List<CardDto> cardListAll = new ArrayList<>();
		List<PonitDto> pointList = new ArrayList<>();
		
		Integer bookVer = Integer.valueOf(cacheObjectService.getBookVer(bookId));//获取练习当前版本号
		
		if(null==chapterIds||chapterIds.equals("")){//如果章节为 null，则根据练习本bookId来获取巩固复习知识点练习
			pointList = excerciseDao.excerciseIntensifyFull_bookId(Integer.parseInt(bookId), userId,bookVer);
		}else{
			String[] chapterId = chapterIds.split(",");
			if(chapterId.length>0){
				Integer[] chapter = new Integer[chapterId.length];
				for(int i=0;i<chapterId.length;i++){
					chapter[i]=Integer.parseInt(chapterId[i]);
				}
				pointList =excerciseDao.excerciseIntensifyFull_chapterIds(Integer.parseInt(bookId), chapter, userId,bookVer);
			}
		}
		//cardListAll =getCardAlgorithm_count(pointList,COUNT);//根据知识点 获取知识点卡片算法(无穿插排序,有数量限制)
		//cardListAll =getCardAlgorithm(pointList,COUNT);//根据知识点 获取知识点卡片算法(有穿插混合排序)
		cardListAll =getRoundCard(pointList,COUNT);//随机获取知识点卡片
	  return cardListAll;
	}
	
	
	/**
	 * 强化训练知识点（熟练度、知识点排序 、复习熟悉度大于1的知识点，）【熟练度大于 1  的状态】
	 * @param request
	 * @param response
	 * @param bookId
	 * @param chapterIds
	 * @return
	 */
	public List<CardDto> excerciseIntensify(String bookId,String chapterIds,Integer userId){
		List<CardDto> cardListAll = new ArrayList<>();
		List<PonitDto> pointList = new ArrayList<>();
		
		Integer bookVer = Integer.valueOf(cacheObjectService.getBookVer(bookId));//获取练习当前版本号
		
		if(null==chapterIds||chapterIds.equals("")){//如果章节为 null，则根据练习本bookId来获取巩固复习知识点练习
			pointList = excerciseDao.excerciseIntensify_bookId(Integer.parseInt(bookId), userId,bookVer);
		}else{
			String[] chapterId = chapterIds.split(",");
			if(chapterId.length>0){
				Integer[] chapter = new Integer[chapterId.length];
				for(int i=0;i<chapterId.length;i++){
					chapter[i]=Integer.parseInt(chapterId[i]);
				}
				pointList =excerciseDao.excerciseIntensify_chapterIds(Integer.parseInt(bookId), chapter, userId,bookVer);
			}
		}
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
			Integer b_cardList=0;//用来标记 B 知识卡片的数量；
			int j=0;
			for(int i=0;i<=pointList.size();i++){
				if(i==pointList.size()){
					if(b_cardList>0){
						for(int b=j;b<cardList_B.size();b++){
							cardListAll.add(cardList_B.get(b));
							
						}
					}
				}else{
					PonitDto pointDao_A = pointList.get(i);
					Integer bookVer_a = Integer.valueOf(cacheObjectService.getBookVer(pointDao_A.getBookId().toString()));//获取练习当前版本号
					List<CardDto> cardList_A = excerciseDao.findCardByPoindId(pointDao_A.getId(),bookVer_a);
					if(cardList_A.size()>0){
						if(flag){
							if((i+1)<pointList.size()){
								pointDao_B = pointList.get(++i);
								Integer bookVer = Integer.valueOf(cacheObjectService.getBookVer(pointDao_B.getBookId().toString()));//获取练习当前版本号
							    cardList_B = excerciseDao.findCardByPoindId(pointDao_B.getId(),bookVer);	
							}else{
								cardListAll.addAll(cardList_A);
								break;
							}
							b_cardList=cardList_B.size();
						}
						
						//开始对A的 知识点 遍历 卡片
						for(int a=0;a<cardList_A.size();a++){
							CardDto dto_A = cardList_A.get(a);
							if(a==cardList_A.size()){
								break;
							}else{
								
								while(b_cardList==0){//一直找到下一个知识点有卡片为止
									if(i>=pointList.size()-1){//防止数组越界
										b_cardList=0;
										break;
									}else{
										pointDao_B = pointList.get(++i);
										Integer bookVer = Integer.valueOf(cacheObjectService.getBookVer(pointDao_B.getBookId().toString()));//获取练习当前版本号
										cardList_B = excerciseDao.findCardByPoindId(pointDao_B.getId(),bookVer);
										b_cardList=cardList_B.size();
									}
								 }
								 
								//当A 的卡片个数比B 的多时
								if(cardList_A.size()>b_cardList-(j>0?j-1:j)){
									  cardListAll.add(dto_A);// ------取  A知识点 卡片的数据
									
									if(a<b_cardList){
									  cardListAll.add(cardList_B.get(j));	
									  j++;
									}else{
										do{
											if(i>=pointList.size()-1){//防止数组越界
												b_cardList=0;
												break;
											}else{
												pointDao_B = pointList.get(++i);
												Integer bookVer = Integer.valueOf(cacheObjectService.getBookVer(pointDao_B.getBookId().toString()));//获取练习当前版本号
												cardList_B = excerciseDao.findCardByPoindId(pointDao_B.getId(),bookVer);
												b_cardList=cardList_B.size();
											}
										}while(b_cardList==0);//一直找到下一个知识点有卡片为止
										
										if(b_cardList>0){
											cardListAll.add(cardList_B.get(j)); 	
										}
										
										j++;
										if(cardList_A.size()<b_cardList){
											flag=false;
										}
									}
									if(j==b_cardList){
										j=0;
										flag=true;
										b_cardList=0;
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
										b_cardList=0;
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
				Integer bookVer = Integer.valueOf(cacheObjectService.getBookVer(pointDao_fist.getBookId().toString()));//获取练习当前版本号
				List<CardDto> cardList = excerciseDao.findCardByPoindId(pointDao_fist.getId(),bookVer);//根据知识点Id获取知识点下所有的卡片信息
				cardListAll.addAll(cardList);
				if(cardListAll.size()>=count){
					break;
				}
			}
		}
		return cardListAll;
	}
    
	/**
	 * 随机获取知识点卡片
	 * @param pointList
	 * @param count 卡片数量
	 * @return
	 */
	public List<CardDto> getRoundCard(List<PonitDto> pointList,Integer count){
		List<CardDto> cardListAll = new ArrayList<>();
		if(pointList.size()>0){
			for(PonitDto dto:pointList){
				CardDto cardDto = cradService.roundCard(dto.getId());
				if(cardListAll.size()<count){
					cardListAll.add(cardDto);
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
	public List<PointNumDto> getPointNum(Integer userId,String[] bookId_arry){
		List<PointNumDto> pintNumList =new ArrayList<>();
		for(int i=0;i<bookId_arry.length;i++){
			Integer bookId=Integer.parseInt(bookId_arry[i]);
			PointNumDto dto = new PointNumDto();
			
			Integer bookVer = Integer.valueOf(cacheObjectService.getBookVer(bookId.toString()));//获取练习当前版本号
			
			List<PonitDto> pointList_error =  excerciseDao.excerciseError_bookId(bookId, userId,bookVer);
			    dto.setExErrorNum(pointList_error.size());//错题
			List<PonitDto> pointList_new= excerciseDao.excerciseNew_bookId(bookId, userId,bookVer);
			    dto.setExNewNum(pointList_new.size());//练新
			List<PonitDto> pointList_strenthen = excerciseDao.excerciseStrenthen_bookId(bookId, userId,bookVer);
				dto.setExStrengthenNum(pointList_strenthen.size());//巩固
			List<PonitDto> pointList_intensify = excerciseDao.excerciseIntensify_bookId(bookId, userId,bookVer);
			    dto.setExIntensifyNum(pointList_intensify.size());//强化
				dto.setBookId(bookId);
			BookEntity bookDto = bookService.findBook(bookId.toString());
				dto.setBookName(bookDto.getBookName());
				pintNumList.add(dto);
		}
		return pintNumList;
	}
	
	
	
	
	
}
