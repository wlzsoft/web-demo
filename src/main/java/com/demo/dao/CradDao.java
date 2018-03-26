package com.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.demo.dto.CardDto;
import com.pmp.entity.CardEntity;
import com.pmp.entity.CardExerciseDetailEntity;
import com.pmp.entity.CradAnswersEntity;

@Repository
public interface CradDao {
    
	public Integer savaLoreCrad(CardEntity entity); 

	public int dellById(@Param("id")Integer id);
	
	public int editLoreCrad(CardEntity entity);
	
	public CardDto findLoreCradById(@Param("id")Integer id);
	
	public CardEntity findLoreCrad(@Param("cardId")Integer cardId);
	
	public List<CardDto> findLoreCradByPointId(@Param("pointId")Integer lorePointId);
	
	public List<CardEntity> getOpenLoreCrad();
	
	public CardExerciseDetailEntity getLoreCradDetailByPointId(Integer loreCardId);
	
	public List<CradAnswersEntity> getLoreCradAnswerByPointId(Integer loreCardId);
	
	public void savaCradAnswers(CradAnswersEntity entity);
	
	public void savaCardExerciseDetail(CardExerciseDetailEntity entity);
	
	public void addPonitNumber(@Param("id")Integer id);
	
	public int delPonitNumber(@Param("id")Integer id);
	
	/**
	 * 根据知识点Id删除 知识点下面所有卡片信息
	 * @param pointId
	 * @return
	 */
	public int delCardByPointId(@Param("pointId")Integer pointId);
	
	
 	
}
