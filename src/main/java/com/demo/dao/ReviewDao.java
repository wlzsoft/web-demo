package com.demo.dao;

import org.springframework.stereotype.Repository;

import com.demo.entity.LorePointExerciseDetailEntity;
import com.demo.entity.UserExerciseDetailEntity;

@Repository
public interface ReviewDao {
	
	public void savaUserExcerciseDetail(UserExerciseDetailEntity entity);
	
	public void updateLorePointExerciseDetail(LorePointExerciseDetailEntity entity);

}
