package com.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.LorePointDao;
import com.demo.dto.PonitDto;
import com.demo.entity.LorePointEntity;
import com.demo.entity.LorePointExerciseDetailEntity;


@Service("lorePointService")
public class LorePointService {
	
	@Autowired
	private SystemService systemService ;
	
	@Autowired
	private LorePointDao lorePointDao;
	
	@Transactional
	public void savaLorePoint(LorePointEntity entity){
		//entity.setCreateId(systemService.getCurrentUser().getId());
		//entity.setCreateTime(new Date());
		  entity.setNumber(0);
		 lorePointDao.install(entity);
	   
		//插入知识点练习明细
		LorePointExerciseDetailEntity pointDetail = new LorePointExerciseDetailEntity();
			//pointDetail.setUserId(systemService.getCurrentUser().getId());
			pointDetail.setUserId(1);
			pointDetail.setLorePointId(entity.getId());
			pointDetail.setNextExerciseTime(new Date());
			pointDetail.setExerciseCycle(0);
		lorePointDao.addPointDetail(pointDetail);	
	}
	
	@Transactional
	public void editLorePoint(LorePointEntity entity){
		//entity.setUpdateId(systemService.getCurrentUser().getId());
		//entity.setUpdateTime(new Date());
		lorePointDao.update(entity);
	}
	
	public void delLorePoint(Integer id){
		lorePointDao.dellById(id);
	}

	public PonitDto findLorePointId(Integer id){
		return lorePointDao.findById(id);
	}
	
	public List<PonitDto> searchAllLorePoint(){
		return lorePointDao.searchAllLorePoint();
	}
	
	public LorePointExerciseDetailEntity findPointIdByDetail(Integer id){
		return lorePointDao.findPointIdByDetail(id);
	}
	
}
