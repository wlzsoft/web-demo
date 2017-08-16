package com.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.LoreCradDao;
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
	public void savaLoreCrad(LoreCardEntity entity,LoreCradAnswersEntity answersEntity ){
		   entity.setCreateId(systemService.getCurrentUser().getId());
		   entity.setCreateTime(new Date());
		   entity.setTitleAutoPlay(true);
		   entity.setQuestionAutoPlay(true);
		   loreCradDao.savaLoreCrad(entity);
		 // loreCradDao.savaCradAnswers(answersEntity);
		 // loreCradDao.savaCardExerciseDetail(cradId);
	}
	
	public void delLoreCrad(Integer loreCardId){
		loreCradDao.dellById(loreCardId);
	}
	
	public void editLoreCrad(LoreCardEntity entity){
		entity.setUpdateId(systemService.getCurrentUser().getId());
		entity.setUpdateTime(new Date());
		loreCradDao.update(entity);
	}
	
	public LoreCardEntity findLoreCradById(Integer loreCardId){
	   return loreCradDao.findLoreCradById(loreCardId);
	}
	
	public List<LoreCardEntity> findLoreCradByPointId(Integer lorePointId){
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
