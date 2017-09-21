package com.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.LoreCradDao;
import com.demo.dao.LorePointDao;
import com.demo.dto.IdEntity;
import com.demo.dto.PointExerciseDetailDto;
import com.demo.dto.PonitDto;
import com.demo.entity.LorePointEntity;
import com.demo.entity.LorePointExerciseDetailEntity;


@Service("lorePointService")
public class LorePointService {
	
	@Autowired
	private SystemService systemService ;
	
	@Autowired
	private LorePointDao lorePointDao;
	
	@Autowired
	private LoreCradDao cardDao;
	
	@Transactional
	public IdEntity savaLorePoint(LorePointEntity entity){
		  entity.setCreateId(systemService.getCurrentUser().getId());
		  entity.setCreateTime(new Date());
		  entity.setNumber(0);
		 lorePointDao.install(entity);
	   
		//插入知识点练习明细
		LorePointExerciseDetailEntity pointDetail = new LorePointExerciseDetailEntity();
			pointDetail.setUserId(systemService.getCurrentUser().getId());
			pointDetail.setPointId(entity.getId());
			pointDetail.setBookId(entity.getBookId());
			pointDetail.setNextExerciseTime(new Date());
			pointDetail.setExerciseCycle(0);
		lorePointDao.addPointDetail(pointDetail);	
		IdEntity identity = new IdEntity();	
		identity.setId(entity.getId());
		return identity;
	}
	
	@Transactional
	public int editLorePoint(LorePointEntity entity){
		entity.setUpdateId(systemService.getCurrentUser().getId());
		entity.setUpdateTime(new Date());
		int count = lorePointDao.update(entity);
		return count;
	}

	public int batchUpdate(String[] pointIdArray,Integer chapterId){
		Integer[] points =new Integer[pointIdArray.length];
		for (int i=0;i<pointIdArray.length;i++){
			points[i]=Integer.parseInt(pointIdArray[i]);
		}
		int count = lorePointDao.batchUpdate(points,chapterId);
		return count;
	}
	
	@Transactional
	public int delLorePoint(Integer id){
		int count = lorePointDao.dellById(id);
		//同时删除该知识点下所有的知识卡片信息
		cardDao.delCardByPointId(id);
		return count ;
	}

	public PonitDto findLorePointId(Integer id){
		return lorePointDao.findById(id);
	}
	
	public List<PonitDto> searchAllLorePoint(){
		return lorePointDao.searchAllLorePoint();
	}
	
	public PointExerciseDetailDto findPointIdByDetail(Integer id){
		return lorePointDao.findPointIdByDetail(id);
	}
	
}
