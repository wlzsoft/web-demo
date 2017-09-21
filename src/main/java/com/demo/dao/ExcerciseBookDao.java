package com.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.demo.dto.BookDto;
import com.demo.dto.PonitDto;
import com.demo.entity.ExcerciseBookEntity;


@Repository
public interface ExcerciseBookDao {

	public void excerciseSava(ExcerciseBookEntity entity);
	
	public int delExcerciseById(@Param("bookId")String bookId);
	
	public int editExcercise(ExcerciseBookEntity entity);
	
	public ExcerciseBookEntity findExcerciseId(@Param("bookId")Integer bookId);
	
	/**
	 * 根据用户ID 查询所有练习本信息
	 * @param userId用户Id
	 * @return
	 */
	public List<BookDto> searchAllExcercise(@Param("userId")Integer userId);
	
	
	/**
	 * 获取所有共享类型练习本
	 * @return
	 */
	public List<BookDto> getOpenBook();
	
	
	public List<ExcerciseBookEntity> fidByName();
	
	public List<PonitDto> findExcerciseIdToPonit(@Param("bookId")Integer bookId);
	
	public Integer bookProgress(@Param("bookId")Integer bookId,@Param("userId")Integer userId);
	
	public Integer bookProgressYes(@Param("bookId")Integer bookId,@Param("userId")Integer userId);
	
}
