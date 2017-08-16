package com.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.demo.dto.PonitDto;
import com.demo.entity.ExcerciseBookEntity;


@Repository
public interface ExcerciseBookDao {

	public void excerciseSava(ExcerciseBookEntity entity);
	
	public void delExcerciseById(@Param("excerciseId")String excerciseId);
	
	public void editExcercise(ExcerciseBookEntity entity);
	
	public ExcerciseBookEntity findExcerciseId(@Param("excerciseId")Integer excerciseId);
	
	public List<ExcerciseBookEntity> searchAllExcercise();
	
	public List<ExcerciseBookEntity> fidByName();
	
	public List<PonitDto> findExcerciseIdToPonit(@Param("excerciseId")Integer id);
	
}
