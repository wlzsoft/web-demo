package com.demo.service;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.ExcerciseBookDao;
import com.demo.dto.BookProgressDto;
import com.demo.dto.PonitDto;
import com.demo.entity.ChapterEntity;
import com.demo.entity.ExcerciseBookEntity;

@Service("excerciseService")
public class ExcerciseBookService {
	
	@Autowired
	private SystemService systemService ;
	
	@Autowired
	private ExcerciseBookDao excerciseBookDao;
	
	public void excerciseSava(ExcerciseBookEntity entity){
		entity.setCreateId(systemService.getCurrentUser().getId());
		entity.setCreateTime(new Date());
		excerciseBookDao.excerciseSava(entity);
	}
	
	public void delExcercise(String excerciseId){
		excerciseBookDao.delExcerciseById(excerciseId);
	}
	
	public void editExcercise(ExcerciseBookEntity entity){
		//entity.setUpdateId(systemService.getCurrentUser().getId());
		//entity.setUpdateTime(new Date());
		excerciseBookDao.editExcercise(entity);
	}
	
	public ExcerciseBookEntity findExcerciseId(String id){
		ExcerciseBookEntity entity = excerciseBookDao.findExcerciseId(Integer.parseInt(id));
		return entity;
	}
	
	public List<ExcerciseBookEntity> searchAllExcercise(Integer userId){
		List<ExcerciseBookEntity> list = excerciseBookDao.searchAllExcercise(userId);
		return list;
	}
	
	public List<PonitDto> findExcerciseIdToPonit(Integer excerciseId){
		return excerciseBookDao.findExcerciseIdToPonit(excerciseId);
	}
	
	public List<ChapterEntity> bookChapterList(Integer excerciseId){
		return excerciseBookDao.bookChapterList(excerciseId);
	}
	
	public BookProgressDto bookProgress(Integer bookId){
		Integer userId =  systemService.getCurrentUser().getId();
		Integer count = excerciseBookDao.bookProgress(bookId,userId);
		Integer yes= excerciseBookDao.bookProgressYes(bookId,userId);
		DecimalFormat df=new DecimalFormat("0.00");
		double progress =Double.valueOf(df.format((float)yes/count));
		 BookProgressDto dto = new BookProgressDto();
		 dto.setBookId(bookId);
		 dto.setProgress(progress);
		return dto;
	}

}
