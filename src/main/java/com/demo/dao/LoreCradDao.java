package com.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.demo.dto.CardDto;
import com.demo.entity.LoreCardEntity;
import com.demo.entity.LoreCardExerciseDetailEntity;
import com.demo.entity.LoreCradAnswersEntity;

@Repository
public interface LoreCradDao {
    
	public Integer savaLoreCrad(LoreCardEntity entity); 
	
	public void install(LoreCardEntity entity);
	
	public void dellById(@Param("id")Integer id);
	
	public void editLoreCrad(LoreCardEntity entity);
	
	public CardDto findLoreCradById(@Param("id")Integer id);
	
	public List<CardDto> findLoreCradByPointId(@Param("lorePointId")Integer lorePointId);
	
	public List<LoreCardEntity> getOpenLoreCrad();
	
	public LoreCardExerciseDetailEntity getLoreCradDetailByPointId(Integer loreCardId);
	
	public List<LoreCradAnswersEntity> getLoreCradAnswerByPointId(Integer loreCardId);
	
	public void savaCradAnswers(LoreCradAnswersEntity entity);
	
	public void savaCardExerciseDetail(@Param("cradId")Integer cradId);
	
	public void addPonitNumber(@Param("id")Integer id);
	
	public void delPonitNumber(@Param("id")Integer id);
	
	
 	
}
