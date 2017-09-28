package com.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.demo.dto.BookDto;
import com.demo.dto.CardDto;
import com.demo.dto.PointExerciseDetailDto;
import com.demo.dto.PonitDto;
import com.demo.entity.UserExerciseDetailEntity;

@Repository
public interface ReviewDao {
	
	public void savaUserExcerciseDetail(UserExerciseDetailEntity entity);
	
	public void updateLorePointExerciseDetail(PointExerciseDetailDto entity);
	
	public List<BookDto> bookList(@Param("userId")Integer userId);
	
	public List<PonitDto> reviewPointAll(@Param("bookId")Integer bookId,@Param("userId")Integer userId);
	
	public List<PonitDto> reviewPoint(@Param("bookId")Integer bookId,@Param("userId")Integer userId);
	
	public List<PonitDto> reviewPointNull(@Param("bookId")Integer bookId,@Param("userId")Integer userId);
	
	public List<CardDto> roundCard(@Param("pointId")Integer pointId);
	
	public List<PonitDto> roundPoint(@Param("userId")Integer userId,@Param("pointIdArray")Integer[] pointIdArray);
	
	public List<PonitDto> roundPointByUserId(@Param("userId")Integer userId);
	
	
	/**
	 * 根据知识点pointId 获取是否有练习权限
	 * @param pointId
	 * @param userId
	 * @return
	 */
	public List<PointExerciseDetailDto> getAuthByPointId(@Param("pointId")Integer pointId,@Param("userId")Integer userId);
	
	/**
	 * 根据练习本 bookId 获取是否有练习权限
	 * @param bookId
	 * @param userId
	 * @return
	 */
	public List<PointExerciseDetailDto> getAuthByBookId(@Param("bookId")Integer bookId,@Param("userId")Integer userId);
	

}
