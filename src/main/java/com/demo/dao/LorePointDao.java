package com.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.demo.dto.PonitDto;
import com.demo.entity.LorePointEntity;
import com.demo.entity.LorePointExerciseDetailEntity;

@Repository
public interface LorePointDao {
	
	public Integer install(LorePointEntity entity);
	
	public void dellById(@Param("id")Integer id);
	
	public void update(LorePointEntity entity);
	
	public PonitDto findById(@Param("id")Integer id);
	
	public List<PonitDto> searchAllLorePoint();
	
	/**
	 * 根据知识点ID 获取知识点练习详情
	 * @param id 知识点Id
	 * @return
	 */
	public LorePointExerciseDetailEntity findPointIdByDetail(@Param("id")Integer id);
	
	public void addPointDetail(LorePointExerciseDetailEntity entity);
	
	public List<PonitDto> roundPoint(@Param("userId")Integer userId);
	
	
	
}