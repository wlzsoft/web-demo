package com.demo.service;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.LorePointDao;
import com.demo.dao.ReviewDao;
import com.demo.dao.UserBookDao;
import com.demo.dto.PointExerciseDetailDto;
import com.demo.dto.PonitSkilledDto;
import com.demo.entity.ErrorWarehouseEntity;

@Service
public class UtilService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UtilService.class);
	
	@Autowired
	private LorePointDao lorePointDao;
	
	@Autowired
	private UserBookDao userBookDao;
	
	@Autowired
	private ReviewDao reviewDao;
	
	
	/**
	 * 计算用户练习本完成率
	 * @param userId
	 * @param bookId
	 * @return
	 */
	public void bookProgress(Integer userId ,Integer bookId){
		//1、查询练习本下所有知识点
		List<PonitSkilledDto> pointList1 =lorePointDao.findBookIdToPonit_card(bookId,userId);
		LOGGER.info("用户：【"+userId+"】在练习本"+bookId+"中总共有"+pointList1.size()+"个知识点");
		//2、查询练习本下所有被练习的知识点
		List<PonitSkilledDto> pointList2 =lorePointDao.findBookIdToPonit_ex(bookId, userId);
		LOGGER.info("用户：【"+userId+"】在练习本"+bookId+"中总共有"+pointList2.size()+"个知识点已经练习过");
		float progress=0;
		
		if(pointList1.size()>0){
			if(pointList2.size()>0){
				int a = pointList2.size();
				int b = pointList1.size();
				DecimalFormat df=new DecimalFormat("0.00");
				progress =Float.valueOf(df.format((float)a/b));
			}
			
		}
		LOGGER.info("用户：【"+userId+"】在练习本"+bookId+"的练习进度为："+progress);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("bookId", bookId);
		map.put("progress", progress);
		map.put("lastTime", new Date());
		userBookDao.updateUserBook(map);
	}
	
	
   /**
    * 对错误库知识点进行处理
	 * @param bookId
	 * @param pointId
	 * @param cardId
	 * @param right
   */
    public void checkErrorCard(Integer bookId,Integer pointId,Integer cardId,Integer userId,Integer right){
		     //先检查错题库里面是否存在改卡片的数据信息
		   ErrorWarehouseEntity entity= reviewDao.findErrorwarehouse(bookId, pointId, cardId,userId);
		    if(right==1){
		    	 if(null!=entity){
		    		 entity.setUpdateTime(new Date());
		    		 entity.setIsRight(1);
		    		 reviewDao.updateErrorwarehouse(entity); 
		    	 }
		    }else{
		    	if(null==entity){
		    		reviewDao.installErrorwarehouse(bookId, pointId, cardId,userId,0);
		    	}else{
		    		 entity.setUpdateTime(new Date());
		    		 entity.setIsRight(0);
		    		 reviewDao.updateErrorwarehouse(entity); 
		    	}
		    }
	 }
	
    
		/**
		 * 根据知识点pointId 获取是否有练习权限
		 * @param pointId
		 * @param userId
		 * @return
		 */
		public Boolean getAuthByPointId(Integer[] pointIds,Integer userId){
			List<PointExerciseDetailDto> dto  = reviewDao.getAuthByPointId(pointIds,userId);
			if(dto.size()==pointIds.length){
				return true;
			}else{
			    return false;
			}
		}
	
		/**
		 * 根据练习本 bookId 获取是否有练习权限
		 * @param bookId
		 * @param userId
		 * @return
		 */
		public Boolean getAuthByBookId(Integer bookId,Integer userId){
			List<PointExerciseDetailDto> dto  = reviewDao.getAuthByBookId(bookId,userId);
			if(dto.size()>0){
				return true;
			}else{
			    return false;
			}
		}
}
