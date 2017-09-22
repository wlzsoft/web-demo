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
	
	public int dellById(@Param("id")Integer id);
	
	public int editLoreCrad(LoreCardEntity entity);
	
	public CardDto findLoreCradById(@Param("id")Integer id);
	
	public LoreCardEntity findLoreCrad(@Param("cardId")Integer cardId);
	
	public List<CardDto> findLoreCradByPointId(@Param("pointId")Integer lorePointId);
	
	public List<LoreCardEntity> getOpenLoreCrad();
	
	public LoreCardExerciseDetailEntity getLoreCradDetailByPointId(Integer loreCardId);
	
	public List<LoreCradAnswersEntity> getLoreCradAnswerByPointId(Integer loreCardId);
	
	public void savaCradAnswers(LoreCradAnswersEntity entity);
	
	public void savaCardExerciseDetail(LoreCardExerciseDetailEntity entity);
	
	public void addPonitNumber(@Param("id")Integer id);
	
	public int delPonitNumber(@Param("id")Integer id);
	
	/**
	 * 根据知识点Id删除 知识点下面所有卡片信息
	 * @param pointId
	 * @return
	 */
	public int delCardByPointId(@Param("pointId")Integer pointId);
	
	
 	
}
