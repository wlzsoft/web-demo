package com.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.demo.dto.CardDto;
import com.demo.dto.PonitDto;

@Repository
public interface ExcerciseDao {
		
	public List<PonitDto> excerciseError_bookId(Integer bookId,Integer userId);
	
	public List<PonitDto> excerciseNew_chapterIds(Integer bookId,Integer chapterId[],Integer userId);
	
	public List<PonitDto> excerciseNew_bookId(Integer bookId,Integer userId);
	
	public List<PonitDto> excerciseStrenthen_chapterIds(Integer bookId,Integer chapterId[],Integer userId);
	
	public List<PonitDto> excerciseStrenthen_bookId(Integer bookId,Integer userId);
	
	public List<CardDto> findCardByPoindId(@Param("pointId")Integer pointId);

}
