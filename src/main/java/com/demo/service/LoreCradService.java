package com.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.LoreCradDao;
import com.demo.dto.CardDto;
import com.demo.dto.IdEntity;
import com.demo.entity.LoreCardEntity;
import com.demo.entity.LoreCardExerciseDetailEntity;
import com.demo.entity.LoreCradAnswersEntity;


@Service("loreCradService")
public class LoreCradService {

	@Autowired
	private LoreCradDao loreCradDao;
	
	@Autowired
	private SystemService systemService;
	
	@Transactional
	public IdEntity savaLoreCrad(LoreCardEntity entity){
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
		 return idEntity;
	}
	
	@Transactional
	public int delLoreCrad(Integer loreCardId){
		int count = loreCradDao.delPonitNumber(loreCardId);
		loreCradDao.dellById(loreCardId);
		return count;
	}
	
	
	public int editLoreCrad(LoreCardEntity entity){
		 entity.setUpdateId(systemService.getCurrentUser().getId());
		 entity.setUpdateTime(new Date());
		int count = loreCradDao.editLoreCrad(entity);
		return count;
	}
	
	public CardDto findLoreCradById(Integer loreCardId){
	   return loreCradDao.findLoreCradById(loreCardId);
	}
	
	public List<CardDto> findLoreCradByPointId(Integer lorePointId){
		return loreCradDao.findLoreCradByPointId(lorePointId);
	}
	
	public List<LoreCardEntity> getOpenLoreCrad(){
		return loreCradDao.getOpenLoreCrad();
	}
	
	public LoreCardExerciseDetailEntity getLoreCradDetailByPointId(Integer loreCardId){
		return loreCradDao.getLoreCradDetailByPointId(loreCardId);
	}
	
	public List<LoreCradAnswersEntity> getLoreCradAnswerByPointId(Integer loreCardId){
		return loreCradDao.getLoreCradAnswerByPointId(loreCardId);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
