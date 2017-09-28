package com.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.ExcerciseBookDao;
import com.demo.dao.LoreCradDao;
import com.demo.dao.LorePointDao;
import com.demo.dto.IdEntity;
import com.demo.dto.PointExerciseDetailDto;
import com.demo.dto.PonitDto;
import com.demo.entity.ExcerciseBookEntity;
import com.demo.entity.LorePointEntity;
import com.demo.entity.LorePointExerciseDetailEntity;
import com.demo.entity.UserBookEntity;


@Service("lorePointService")
public class LorePointService {
	
	@Autowired
	private SystemService systemService ;
	
	@Autowired
	private LorePointDao lorePointDao;
	
	@Autowired
	private ExcerciseBookDao excerciseBookDao;
	
	@Autowired
	private LoreCradDao cardDao;
	
	@Autowired
	private UserBookService userBookService;
	
	@Autowired
	private ExcerciseBookService excerciseService ;	
	
	@Transactional
	public IdEntity savaLorePoint(LorePointEntity entity){
		  entity.setCreateId(systemService.getCurrentUser().getId());
		  entity.setCreateTime(new Date());
		  entity.setNumber(0);
		 lorePointDao.install(entity);
    /*	   
		//插入知识点练习明细
		LorePointExerciseDetailEntity pointDetail = new LorePointExerciseDetailEntity();
			pointDetail.setUserId(systemService.getCurrentUser().getId());
			pointDetail.setPointId(entity.getId());
			pointDetail.setBookId(entity.getBookId());
			pointDetail.setNextExerciseTime(new Date());
			pointDetail.setExerciseCycle(0);
		lorePointDao.addPointDetail(pointDetail);	*/
		
		/**
		 * 新方案，加入用户和练习本关连关系，当有新增知识点时会对应把该知识推送到 订阅知识点所属的练习本下所有的用户
		 * */
		//获取订阅练习本下所有的用户
	    List<UserBookEntity> userBookEntity = userBookService.findUser(entity.getBookId());
		pushAddPointToUser(entity.getId() ,entity.getBookId(),userBookEntity);
		
		excerciseService.updateDetailBybookId(entity.getBookId());//推到练习本更新
		
		IdEntity identity = new IdEntity();	
		identity.setId(entity.getId());
		return identity;
	}
	
	@Transactional
	public int editLorePoint(LorePointEntity entity){
		entity.setUpdateId(systemService.getCurrentUser().getId());
		entity.setUpdateTime(new Date());
		int count = lorePointDao.update(entity);
		excerciseService.updateDetailBybookId(entity.getBookId());//推到练习本更新
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
	public int delLorePoint(Integer pointId){
		ExcerciseBookEntity entity = excerciseBookDao.findBookByPointId(pointId);
		int count = lorePointDao.dellById(pointId);
		//同时删除该知识点下所有的知识卡片信息
		cardDao.delCardByPointId(pointId);
		//同时删除知识点对应的用户练习记录
		delPoinDetailtByPointId(pointId);
		excerciseService.updateDetailBybookId(entity.getId());//推到练习本更新
		return count ;
	}

	public PonitDto findLorePointId(Integer id){
		return lorePointDao.findById(id);
	}
	
	public LorePointEntity findLorePoint(Integer id){
		return lorePointDao.findLorePoint(id);
	}
	
	public List<PonitDto> searchAllLorePoint(){
		return lorePointDao.searchAllLorePoint();
	}
	
	public PointExerciseDetailDto findPointIdByDetail(Integer id){
		return lorePointDao.findPointIdByDetail(id);
	}
	
	
	/**
	 * 根据知识点ID ,练习本Id 新增【推送】到所有订阅用户
	 * @param pointId
	 * @param bookId
	 * @param entityList 订阅该练习本下的所有用户
	 */
	public void pushAddPointToUser(Integer pointId ,Integer bookId,List<UserBookEntity> entityList){
		if(entityList.size()>0){
			List<LorePointExerciseDetailEntity> detailList = new ArrayList<>();
			for(UserBookEntity entity :entityList){
				//插入知识点练习明细
			    LorePointExerciseDetailEntity pointDetail = new LorePointExerciseDetailEntity();
					pointDetail.setUserId(entity.getUserId());
					pointDetail.setPointId(pointId);
					pointDetail.setBookId(entity.getBookId());
					pointDetail.setNextExerciseTime(new Date());
					pointDetail.setExerciseCycle(0);
			    detailList.add(pointDetail);
			}
			lorePointDao.addPointDetailList(detailList);
		}
	}
	
	
	/**
	 * 根据练习本Id  把该练习本下所有知识点【推送】到所有订阅用户
	 * @param bookId
	 */
	public void pushPointToUser(Integer bookId){
		//获取练习本下所有的知识点
		List<PonitDto> entityList = excerciseService.findExcerciseIdToPonit(bookId);
		//获取订阅练习本下所有的用户
		List<UserBookEntity> userBookEntity = userBookService.findUser(bookId);
        for(PonitDto dto :entityList ){
        	pushAddPointToUser(dto.getId() ,bookId,userBookEntity);
        }
	}
	
	
	/**
	 * 根据用户推送
	 * @param bookId
	 * @param userId
	 */
	public void pushPoint(Integer bookId,Integer userId){
		//获取练习本下所有的知识点
		List<PonitDto> entityList = excerciseService.findExcerciseIdToPonit(bookId);
		if(entityList.size()>0){
			List<LorePointExerciseDetailEntity> detailList = new ArrayList<>();
			for(PonitDto entity :entityList){
				//插入知识点练习明细
			    LorePointExerciseDetailEntity pointDetail = new LorePointExerciseDetailEntity();
					pointDetail.setUserId(userId);
					pointDetail.setPointId(entity.getId());
					pointDetail.setBookId(bookId);
					pointDetail.setNextExerciseTime(new Date());
					pointDetail.setExerciseCycle(0);
			    detailList.add(pointDetail);
			}
			lorePointDao.addPointDetailList(detailList);
		}
	}
	
	
	/**
	 * 根据知识点ID  删除【推送】到订阅用户的练习明细
	 * @param pointId
	 * @param bookId
	 */
	public void delPoinDetailtByPointId(Integer pointId){
		lorePointDao.delPoinDetailtByPointId(pointId);
	}
	
	/**
	 * 根据练习本Id 删除【推送】到订阅用户的练习明细
	 * @param pointId
	 * @param bookId
	 */
	public void delPoinDetailtByBookId(Integer bookId){
		lorePointDao.delPoinDetailtByBookId(bookId);
	}
	
}
