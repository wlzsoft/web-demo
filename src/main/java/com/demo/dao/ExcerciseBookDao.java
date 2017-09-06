package com.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.demo.dto.PonitDto;
import com.demo.entity.ChapterEntity;
import com.demo.entity.ExcerciseBookEntity;


@Repository
public interface ExcerciseBookDao {

	public void excerciseSava(ExcerciseBookEntity entity);
	
	public int delExcerciseById(@Param("bookId")String bookId);
	
	public int editExcercise(ExcerciseBookEntity entity);
	
	public ExcerciseBookEntity findExcerciseId(@Param("bookId")Integer bookId);
	
	public List<ExcerciseBookEntity> searchAllExcercise(@Param("userId")Integer userId);
	
	public List<ExcerciseBookEntity> fidByName();
	
	public List<PonitDto> findExcerciseIdToPonit(@Param("bookId")Integer bookId);
	
	public Integer bookProgress(@Param("bookId")Integer bookId,@Param("userId")Integer userId);
	
	public Integer bookProgressYes(@Param("bookId")Integer bookId,@Param("userId")Integer userId);
	
}
