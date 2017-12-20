package com.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.demo.dto.CardDto;
import com.demo.dto.PonitDto;

@Repository
public interface ExcerciseDao {
	//	
	public List<PonitDto> excerciseError_bookId(@Param("bookId")Integer bookId,@Param("userId")Integer userId);
	
	public List<PonitDto> excerciseError_chapterIds(@Param("bookId")Integer bookId,@Param("chapterId")Integer chapterId[],@Param("userId")Integer userId);
	
	public List<PonitDto> excerciseNew_chapterIds(@Param("bookId")Integer bookId,@Param("chapterId")Integer chapterId[],@Param("userId")Integer userId);
	//
	public List<PonitDto> excerciseNew_bookId(@Param("bookId")Integer bookId,@Param("userId")Integer userId);
	
	//巩固练习 熟练度 =1
	public List<PonitDto> excerciseStrenthen_chapterIds(@Param("bookId")Integer bookId,@Param("chapterId")Integer chapterId[],@Param("userId")Integer userId);
	public List<PonitDto> excerciseStrenthen_bookId(@Param("bookId")Integer bookId,@Param("userId")Integer userId);

	//强化训练 熟练度>1
	public List<PonitDto> excerciseIntensify_chapterIds(@Param("bookId")Integer bookId,@Param("chapterId")Integer chapterId[],@Param("userId")Integer userId);
	public List<PonitDto> excerciseIntensify_bookId(@Param("bookId")Integer bookId,@Param("userId")Integer userId);
	public List<PonitDto> excerciseIntensifyFull_chapterIds(@Param("bookId")Integer bookId,@Param("chapterId")Integer chapterId[],@Param("userId")Integer userId);
	public List<PonitDto> excerciseIntensifyFull_bookId(@Param("bookId")Integer bookId,@Param("userId")Integer userId);
	
	
	public List<CardDto> findCardByPoindId(@Param("pointId")Integer pointId);
	
	public List<PonitDto> findStrenthen_bookId(@Param("bookId")Integer bookId,@Param("userId")Integer userId);
	
	
	
	//	
	public List<PonitDto> excerciseError_bookIds(@Param("bookId")Integer[] bookIds,@Param("userId")Integer userId);
	//
	public List<PonitDto> excerciseNew_bookIds(@Param("bookId")Integer[] bookIds,@Param("userId")Integer userId);
	//
	public List<PonitDto> excerciseStrenthen_bookIds(@Param("bookId")Integer[] bookIds,@Param("userId")Integer userId);
	
	
	
	
	
	
	
	

}
