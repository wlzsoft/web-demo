package com.demo.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.demo.dto.IdEntity;
import com.demo.dto.PointExerciseDetailDto;
import com.demo.dto.PonitBatchDto;
import com.demo.dto.PonitDto;
import com.demo.dto.PonitSkilledDto;
import com.demo.entity.ExcerciseBookEntity;
import com.demo.entity.LorePointEntity;
import com.demo.entity.UserBookEntity;
import com.demo.service.ChapterService;
import com.demo.service.ExcerciseBookService;
import com.demo.service.LorePointService;
import com.demo.service.SystemService;
import com.demo.service.UserBookService;
import com.smartframe.basics.util.EmojiUtil;
import com.smartframe.dto.Result;
import com.smartframe.dto.ResultObject;


/**
 * 知识点控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/point")
public class PointController {
	
	@Autowired
	private LorePointService lorePointService;
	
	@Autowired
	private ExcerciseBookService excerciseService ;	
	
	@Autowired
	private ChapterService chapterService;
	
	@Autowired
	private SystemService systemService ;
	
	@Autowired
	private UserBookService userBookService;
	
	/**
	 * 保存知识点信息
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	@RequestMapping("/addPoint")
	public Result<?> savaLorePoint(HttpServletRequest request ,HttpServletResponse response,LorePointEntity entity){
		
		
		if(null==entity.getBookId()||entity.getBookId().equals("")){
			return ResultObject.warnMessage("所属练习本ID不能为空");
		}
		
		/**
		 * 加操作权限
		 * */
		Integer bookId = entity.getBookId();
		ExcerciseBookEntity bookEntity = excerciseService.findBook(bookId.toString());
		
		if(null==bookEntity){
			return ResultObject.warnMessage("无操作权限");	
		}
		
		Integer userId = systemService.getCurrentUser().getId();
		if(userId!=bookEntity.getCreateId()){
			return ResultObject.warnMessage("无操作权限");
		}else{
			if(null==entity.getPointName()||entity.getPointName().equals("")){
				return ResultObject.warnMessage("知识点名称不能为空");
			}
			String pointName = entity.getPointName();
			try {
				pointName =EmojiUtil.emojiRecovery2(pointName);
				entity.setPointName(pointName);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			
			
			IdEntity identity = lorePointService.savaLorePoint(entity);
			return ResultObject.successObject(identity,"保存成功");
		}
	}
	
	
	
	@RequestMapping("/batchAdd")
	public Result<?> batchAdd(HttpServletRequest request ,HttpServletResponse response,String entityJson,String bookId){
		
		if(null==bookId||bookId.equals("")){
			return ResultObject.warnMessage("所属练习本ID不能为空");
		}
		
		if(null==entityJson||entityJson.equals("")){
			return ResultObject.warnMessage("更新数据不能为空");
		}
		
		List<LorePointEntity> entityList = (List<LorePointEntity>) JSONArray.parseArray(entityJson, LorePointEntity.class);
		
		if(entityList.size()==0){
			return ResultObject.warnMessage("更新数据不能为空");
		}
		
		/**
		 * 加操作权限
		 * */
		ExcerciseBookEntity bookEntity = excerciseService.findBook(bookId.toString());
		if(null==bookEntity){
			return ResultObject.warnMessage("无操作权限");	
		}
		Integer userId = systemService.getCurrentUser().getId();
		if(userId!=bookEntity.getCreateId()){
			return ResultObject.warnMessage("无操作权限");
		}else{
			List<PonitBatchDto> idList = new ArrayList<>();
			for(LorePointEntity entity :entityList){
				if(null==entity.getPointName()||entity.getPointName().equals("")){
					return ResultObject.warnMessage("知识点名称不能为空");
				}
				String pointName = entity.getPointName();
				try {
					pointName =EmojiUtil.emojiConvert1(pointName);
					entity.setPointName(pointName);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				entity.setBookId(Integer.parseInt(bookId));//从新设置bookId
				
				IdEntity identity = lorePointService.savaLorePoint(entity);
				
				//拼装返回数据集
				PonitBatchDto dto = new PonitBatchDto();
				dto.setId(identity.getId());
				try {
					dto.setPointName(EmojiUtil.emojiRecovery2(pointName));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				dto.setSkilled(0);
				dto.setExerciseCycle(0);
				dto.setChapterSort((null==entity.getSort()||entity.getSort().equals(""))?0:entity.getSort());
				dto.setSort((null==entity.getSort()||entity.getSort().equals(""))?0:entity.getSort());
				
				idList.add(dto);
			}
			return ResultObject.successObject(idList,"保存成功");
		}
	}
	
	/**
	 * 修改知识点信息
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	@RequestMapping("/editPoint")
	public Result<?> editLorePoint(HttpServletRequest request ,HttpServletResponse response,LorePointEntity entity){
		if(null==entity.getId()||entity.getId().equals("")){
			return ResultObject.warnMessage("主键ID不能为空");
		}
		if(null==entity.getBookId()||entity.getBookId().equals("")){
			return ResultObject.warnMessage("所属练习本ID不能为空");
		}
		
		if(null==entity.getPointName()||entity.getPointName().equals("")){
			return ResultObject.warnMessage("知识点名称不能为空");
		}
		
		/**
		 * 加操作权限
		 * */
		Integer bookId = entity.getBookId();
		ExcerciseBookEntity bookEntity = excerciseService.findBook(bookId.toString());
		if(null==bookEntity){
			return ResultObject.warnMessage("无操作权限");	
		}
		Integer userId = systemService.getCurrentUser().getId();
		if(userId!=bookEntity.getCreateId()){
			return ResultObject.warnMessage("无操作权限");
		}else{
			
			String pointName = entity.getPointName();
			try {
				pointName =EmojiUtil.emojiRecovery2(pointName);
				entity.setPointName(pointName);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			
			
			int count = lorePointService.editLorePoint(entity);
			if(count==0){
				return ResultObject.successMessage("无操作数据");
			}
			return ResultObject.successMessage("修改成功");
		}

	}
	
	
	/**
	 * 修改知识点信息
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	@RequestMapping("/batchEdit")
	public Result<?> batchEdit(HttpServletRequest request ,HttpServletResponse response,String entityJson,String bookId){
		
		if(null==bookId||bookId.equals("")){
			return ResultObject.warnMessage("所属练习本ID不能为空");
		}
		
		if(null==entityJson||entityJson.equals("")){
			return ResultObject.warnMessage("更新数据不能为空");
		}
		
		List<LorePointEntity> entityList = (List<LorePointEntity>) JSONArray.parseArray(entityJson, LorePointEntity.class);
		
		if(entityList.size()==0){
			return ResultObject.warnMessage("更新数据不能为空");
		}
		
		/**
		 * 加操作权限
		 * */
		ExcerciseBookEntity bookEntity = excerciseService.findBook(bookId);
		if(null==bookEntity){
			return ResultObject.warnMessage("无操作权限");	
		}
		Integer userId = systemService.getCurrentUser().getId();
		if(userId!=bookEntity.getCreateId()){
			return ResultObject.warnMessage("无操作权限");
		}else{
			for(LorePointEntity entity : entityList ){
				if(null==entity.getId()||entity.getId().equals("")){
					return ResultObject.warnMessage("主键ID不能为空");
				}
				
 			    if(null==entity.getPointName()||entity.getPointName().equals("")){
					//return ResultObject.warnMessage("知识点名称不能为空");
				}else{
					String pointName = entity.getPointName();
					try {
						pointName =EmojiUtil.emojiRecovery2(pointName);
						entity.setPointName(pointName);
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
				entity.setBookId(Integer.parseInt(bookId));//从新设置bookId
				lorePointService.editLorePoint(entity);
			}
			
			return ResultObject.successMessage("修改成功");
		}

	}

	


	/**
	 * 根据知识ID 批量更新知识点章节Id
	 * @param request
	 * @param response
	 * @param pointIds 知识点数组
	 * @param chapterId 章节Id
	 * @return
	 */
	@RequestMapping("/batchUpdateChapter")
	public Result<?> batchUpdate(HttpServletRequest request ,HttpServletResponse response,String pointIds ,String chapterId){
		String [] pointIdArray= pointIds.split(",");
		if(pointIdArray.length>0){
			lorePointService.batchUpdate(pointIdArray,Integer.parseInt(chapterId));
		}else{
			return ResultObject.warnMessage("知识点ID不能为空");
		}
        return ResultObject.successMessage("更新成功");
	}
	
	/**
	 * 根据知识点ID 删除知识点信息
	 * @param request
	 * @param response
	 * @param pointId
	 * @return
	 */
	@RequestMapping("/delPoint")
	public Result<?> delLorePoint(HttpServletRequest request ,HttpServletResponse response,String pointId){
		if(null==pointId||pointId.equals("")){
			return ResultObject.warnMessage("参数不能为空");
		}
		
		/**
		 * 加操作权限
		 * */
		LorePointEntity point = lorePointService.findLorePoint(Integer.parseInt(pointId));
		
		if(null==point){
			return ResultObject.warnMessage("无操作权限");	
		}
		
		Integer userId = systemService.getCurrentUser().getId();
		if(userId!=point.getCreateId()){
			return ResultObject.warnMessage("无操作权限");	
		}else{
			int count = lorePointService.delLorePoint(Integer.parseInt(pointId));
			if(count==0){
				return ResultObject.successMessage("无操作数据");
			}
			return ResultObject.successMessage("删除成功");
		}
	}
	
	
	@RequestMapping("/findPoint")
	public Result<?> findLorePointId(HttpServletRequest request ,HttpServletResponse response,String pointId){
		if(null==pointId||pointId.equals("")){
			return ResultObject.warnMessage("参数不能为空");
		}
		
		/**
		 * 加操作权限
		 * */
		ExcerciseBookEntity bookEntity = excerciseService.findBookByPointId(Integer.parseInt(pointId));
		
		if(null==bookEntity){
			return ResultObject.warnMessage("无操作权限");	
		}else{
			if(bookEntity.getSharedType()==0){
				Integer userId = systemService.getCurrentUser().getId();
	 			List<UserBookEntity>  list = userBookService.findUser_userId_bookId(userId, bookEntity.getId());
	 			if(list.size()==0){
	 				return ResultObject.warnMessage("无操作权限");	
	 			}
			}
		}
		
		PonitDto entity = lorePointService.findLorePointId(Integer.parseInt(pointId));
		
		String pointName = entity.getPointName();
		try {
			pointName =EmojiUtil.emojiRecovery2(pointName);
			entity.setPointName(pointName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return ResultObject.successObject(entity,null);
	}
	
	@RequestMapping("/pointList")
	public Result<List<PonitDto>> searchAllLorePoint(HttpServletRequest request ,HttpServletResponse response){
		List<PonitDto> entityList = lorePointService.searchAllLorePoint();
		
		if(entityList.size()>0){
			for(PonitDto entity:entityList){
				String pointName = entity.getPointName();
				try {
					pointName =EmojiUtil.emojiRecovery2(pointName);
					entity.setPointName(pointName);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}	
		}
		return ResultObject.successObject(entityList,null);
	}
	
	/**
	 * 根据知识点ID 获取用户知识点练习详情
	 * @param request
	 * @param response
	 * @param pointId 知识点Id
	 * @return
	 */
	@RequestMapping("/pointDetail")
	public Result<?> pointDetail(HttpServletRequest request ,HttpServletResponse response,String pointId){
		if(null==pointId||pointId.equals("")){
			return ResultObject.warnMessage("参数不能为空");
		}
		
		/**
		 * 加操作权限
		 * */
		ExcerciseBookEntity entity = excerciseService.findBookByPointId(Integer.parseInt(pointId));
		if(null==entity){
			return ResultObject.warnMessage("无操作权限");	
		}else{
			if(entity.getSharedType()==0){
				Integer userId = systemService.getCurrentUser().getId();
	 			List<UserBookEntity>  list = userBookService.findUser_userId_bookId(userId, entity.getId());
	 			if(list.size()==0){
	 				return ResultObject.warnMessage("无操作权限");	
	 			}
			}
		}
		
		PointExerciseDetailDto list = lorePointService.findPointIdByDetail(Integer.parseInt(pointId));
		return ResultObject.successObject(list,null);
	}
	
	
	
	/**
	 * 根据章节Id,练习本ID , 查询章节下面所有知识点
	 * @param request
	 * @param response
	 * @param bookId
	 * @return
	 */
	@RequestMapping("/chapterPointList")
	public Result<?> chapterPointList(HttpServletRequest request ,HttpServletResponse response,String chapterId,String bookId){
		if(null==chapterId||chapterId.equals("")){
			return ResultObject.warnMessage("参数不能为空");
		}
		
		/**
		 * 加操作权限
		 * */
		ExcerciseBookEntity entity = excerciseService.findBook(bookId);
		if(null==entity){
			return ResultObject.warnMessage("无操作权限");	
		}else{
			if(entity.getSharedType()==0){
				Integer userId = systemService.getCurrentUser().getId();
	 			List<UserBookEntity>  list = userBookService.findUser_userId_bookId(userId, entity.getId());
	 			if(list.size()==0){
	 				return ResultObject.warnMessage("无操作权限");	
	 			}
			}
		}

		
		List<PonitDto> list = chapterService.findChapterPoint(Integer.parseInt(chapterId),Integer.parseInt(bookId));
		
		for(PonitDto dto:list){
			String pointName = dto.getPointName();
			try {
				pointName =EmojiUtil.emojiRecovery2(pointName);
				dto.setPointName(pointName);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		
		return ResultObject.successObject(list,null);
	
	}
	
	
	/**
	 * 根据练习本节点ID 查询该用户订阅节点下所有知识点信息
	 * @param request
	 * @param response
	 * @param bookId
	 * @return
	 */
	@RequestMapping("/bookPointList")
	public Result<?> bookPointList(HttpServletRequest request ,HttpServletResponse response,String bookId){
		if(null==bookId||bookId.equals("")){
			return ResultObject.warnMessage("参数不能为空");
		}
		
		List<PonitSkilledDto> ponitSkilledList =null;
		/**
		 * 加操作权限
		 * */
		ExcerciseBookEntity entity = excerciseService.findBook(bookId);
		if(null==entity){
			return ResultObject.warnMessage("无操作权限");	
		}else{
			if(entity.getSharedType()==0){//0 私有
	 			Integer userId = systemService.getCurrentUser().getId();
	 			List<UserBookEntity>  list = userBookService.findUser_userId_bookId(userId, Integer.parseInt(bookId));
	 			if(list.size()==0){
	 				return ResultObject.warnMessage("无操作权限");	
	 			}
	 			   ponitSkilledList = lorePointService.findBookIdToPonit(Integer.parseInt(bookId));
			}else{//共公
	 			Integer userId = systemService.getCurrentUser().getId();
	 			//查看是否订阅或是否是自己创建的
	 			List<UserBookEntity>  list = userBookService.findUser_userId_bookId(userId, Integer.parseInt(bookId));
	 			if(list.size()==0){//没有订阅
	 				ponitSkilledList = lorePointService.searchLorePointByBookId(Integer.parseInt(bookId));
	 			}else{
	 				ponitSkilledList = lorePointService.findBookIdToPonit(Integer.parseInt(bookId));
	 			}
				
			}
		}
	    
		if(ponitSkilledList.size()>0){
			for(PonitSkilledDto dto:ponitSkilledList){
				String pointName = dto.getPointName();
				try {
					pointName =EmojiUtil.emojiRecovery2(pointName);
					dto.setPointName(pointName);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		return ResultObject.successObject(ponitSkilledList,null); 
	}

	/**
	 * 隐藏知识点 【让在复习时不出现在 答题列中】
	 * @param request
	 * @param response
	 * @param pointIds
	 * @return
	 */
	@RequestMapping("/hidePoint")
	public Result<?> hidePoint(HttpServletRequest request ,HttpServletResponse response,String pointIds,Integer state){
		String [] pointIdArray= pointIds.split(",");
		if(pointIdArray.length>0){
			lorePointService.hidePoint(pointIdArray,state);
		}else{
			return ResultObject.warnMessage("知识点ID不能为空");
		}
        return ResultObject.successMessage("成功");
	} 
	
}
