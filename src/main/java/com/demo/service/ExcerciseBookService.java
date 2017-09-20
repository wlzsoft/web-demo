package com.demo.service;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.ExcerciseBookDao;
import com.demo.dto.BookDto;
import com.demo.dto.BookProgressDto;
import com.demo.dto.IdEntity;
import com.demo.dto.PonitDto;
import com.demo.entity.ExcerciseBookEntity;

@Service("excerciseService")
public class ExcerciseBookService {
	
	@Autowired
	private SystemService systemService ;
	
	@Autowired
	private ExcerciseBookDao excerciseBookDao;
	
	public IdEntity excerciseSava(ExcerciseBookEntity entity){
		entity.setCreateId(systemService.getCurrentUser().getId());
		entity.setCreateTime(new Date());
		excerciseBookDao.excerciseSava(entity);
		IdEntity identity = new IdEntity();	
		identity.setId(entity.getId());
		return identity;
	}
	
	public int delExcercise(String excerciseId){
		int count = excerciseBookDao.delExcerciseById(excerciseId);
		return count;
	}
	
	public int editExcercise(ExcerciseBookEntity entity){
		entity.setUpdateId(systemService.getCurrentUser().getId());
		entity.setUpdateTime(new Date());
		int count = excerciseBookDao.editExcercise(entity);
		return count;
	}
	
	public ExcerciseBookEntity findExcerciseId(String id){
		ExcerciseBookEntity entity = excerciseBookDao.findExcerciseId(Integer.parseInt(id));
		return entity;
	}
	
	public List<BookDto> searchAllExcercise(Integer userId){
		List<BookDto> list = excerciseBookDao.searchAllExcercise(userId);
		return list;
	}
	
	public List<PonitDto> findExcerciseIdToPonit(Integer excerciseId){
		return excerciseBookDao.findExcerciseIdToPonit(excerciseId);
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
