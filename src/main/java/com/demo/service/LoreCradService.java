package com.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.ExcerciseBookDao;
import com.demo.dao.LoreCradDao;
import com.demo.dao.ReviewDao;
import com.demo.dto.CardDto;
import com.demo.dto.IdEntity;
import com.demo.entity.CardEntity;
import com.demo.entity.ExcerciseBookEntity;
import com.demo.entity.LoreCardExerciseDetailEntity;
import com.demo.entity.LoreCradAnswersEntity;


@Service("loreCradService")
public class LoreCradService {
	
	@Autowired
	private ReviewDao reviewDao;

	@Autowired
	private LoreCradDao loreCradDao;
	
	@Autowired
	private ExcerciseBookDao excerciseBookDao;
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private ExcerciseBookService excerciseService ;
	
	@Transactional
	public IdEntity savaLoreCrad(CardEntity entity){
		   entity.setCreateId(systemService.getCurrentUser().getId());
		   entity.setCreateTime(new Date());
		 loreCradDao.addPonitNumber(entity.getPointId());
		 loreCradDao.savaLoreCrad(entity);
		 //保存卡片的联系详情
		 LoreCardExerciseDetailEntity cardExerciseDetail = new LoreCardExerciseDetailEntity();
		   cardExerciseDetail.setUserId(systemService.getCurrentUser().getId()); //-============该处需要需求 后面修改为 该卡片练习人的ID
		   cardExerciseDetail.setCardId(entity.getId());
		 loreCradDao.savaCardExerciseDetail(cardExerciseDetail);
		 IdEntity idEntity = new IdEntity();
		 idEntity.setId(entity.getId());
		 excerciseService.updateDetailByPointId(entity.getPointId());
		 return idEntity;
	}
	
	@Transactional
	public int delLoreCrad(Integer loreCardId){
		ExcerciseBookEntity entity = excerciseBookDao.findBookByCardId(loreCardId);
		int count = loreCradDao.delPonitNumber(loreCardId);
		loreCradDao.dellById(loreCardId);
		excerciseService.updateDetailBybookId(entity.getId());
		//同时删除 错题库里面的 卡片信息
		reviewDao.delErrorwarehouse(entity.getId(), loreCardId);
		return count;
	}
	
	@Transactional
	public int editLoreCrad(CardEntity entity){
		 entity.setUpdateId(systemService.getCurrentUser().getId());
		 entity.setUpdateTime(new Date());
		int count = loreCradDao.editLoreCrad(entity);
		excerciseService.updateDetailBycardId(entity.getId());
		return count;
	}
	
	public CardDto findLoreCradById(Integer loreCardId){
	   return loreCradDao.findLoreCradById(loreCardId);
	}
	
	public CardEntity findLoreCrad(Integer loreCardId){
		   return loreCradDao.findLoreCrad(loreCardId);
	}
	
	public List<CardDto> findLoreCradByPointId(Integer lorePointId){
		return loreCradDao.findLoreCradByPointId(lorePointId);
	}
	
	public List<CardEntity> getOpenLoreCrad(){
		return loreCradDao.getOpenLoreCrad();
	}
	
	public LoreCardExerciseDetailEntity getLoreCradDetailByPointId(Integer loreCardId){
		return loreCradDao.getLoreCradDetailByPointId(loreCardId);
	}
	
	public List<LoreCradAnswersEntity> getLoreCradAnswerByPointId(Integer loreCardId){
		return loreCradDao.getLoreCradAnswerByPointId(loreCardId);
	}
	
	/**
	 * 根据知识点ID ，随机获取一个卡片信息
	 * @param request
	 * @param response
	 * @param pointId
	 * @return
	 */
	public CardDto roundCard(Integer pointId){
		  List<CardDto> cardList = reviewDao.roundCard(pointId);
		  if(cardList.size()>0){
		      java.util.Random random = new java.util.Random();
		      int randomPos = random.nextInt(cardList.size()); 
		      return cardList.get(randomPos);
		  }else{
			  return null;
		  }
	}
	
}
