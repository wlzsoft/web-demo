package com.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.ExcerciseBookDao;
import com.demo.dto.PonitDto;
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
		entity.setUpdateId(systemService.getCurrentUser().getId());
		entity.setUpdateTime(new Date());
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
	
	public List<PonitDto> findExcerciseIdToPonit(Integer id){
		return excerciseBookDao.findExcerciseIdToPonit(id);
	}
	
	
	

}
