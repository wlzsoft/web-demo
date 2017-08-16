package com.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.demo.dto.PonitDto;
import com.demo.entity.LorePointEntity;
import com.demo.entity.LorePointExerciseDetailEntity;

@Repository
public interface LorePointDao {
	
	public void install(LorePointEntity entity);
	
	public void dellById(@Param("id")Integer id);
	
	public void update(LorePointEntity entity);
	
	public LorePointEntity findById(@Param("id")Integer id);
	
	public List<PonitDto> searchAllLorePoint();
	
	/**
	 * @param id 知识点Id
	 */
	public void inesterExcerciseOrPoint(@Param("excerciseId")Integer excerciseId,@Param("pointId")Integer pointId);
	
	/**
	 * @param id 知识点Id
	 */
	public void delExcerciseOrPoint(@Param("id")Integer id);
	
	
	/**
	 * 根据知识点ID 获取知识点练习详情
	 * @param id 知识点Id
	 * @return
	 */
	public LorePointExerciseDetailEntity findPointIdByDetail(@Param("id")Integer id);
	
	
	
}
