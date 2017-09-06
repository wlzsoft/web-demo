package com.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.dto.IdEntity;
import com.demo.dto.PointExerciseDetailDto;
import com.demo.dto.PonitDto;
import com.demo.entity.LorePointEntity;
import com.demo.service.ChapterService;
import com.demo.service.ExcerciseBookService;
import com.demo.service.LorePointService;
import com.smartframe.dto.Result;
import com.smartframe.dto.ResultObject;


/**
 * 知识点控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/point")
public class LorePointController {
	
	@Autowired
	private LorePointService lorePointService;
	
	@Autowired
	private ExcerciseBookService excerciseService ;	
	
	@Autowired
	private ChapterService chapterService;
	
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
		
		if(null==entity.getPointName()||entity.getPointName().equals("")){
			return ResultObject.warnMessage("知识点名称不能为空");
		}
		
		IdEntity identity = lorePointService.savaLorePoint(entity);
		return ResultObject.successObject(identity,"保存成功");
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
		int count = lorePointService.editLorePoint(entity);
		if(count==0){
			return ResultObject.successMessage("无操作数据");
		}
		return ResultObject.successMessage("修改成功");
	}
	
	/**
	 * 根据知识点ID 删除知识点信息
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping("/delPoint")
	public Result<?> delLorePoint(HttpServletRequest request ,HttpServletResponse response,String pointId){
		if(null==pointId||pointId.equals("")){
			return ResultObject.warnMessage("参数不能为空");
		}
		int count = lorePointService.delLorePoint(Integer.parseInt(pointId));
		if(count==0){
			return ResultObject.successMessage("无操作数据");
		}
		return ResultObject.successMessage("删除成功");
	}
	
	
	@RequestMapping("/findPoint")
	public Result<?> findLorePointId(HttpServletRequest request ,HttpServletResponse response,String pointId){
		if(null==pointId||pointId.equals("")){
			return ResultObject.warnMessage("参数不能为空");
		}
		PonitDto entity = lorePointService.findLorePointId(Integer.parseInt(pointId));
		return ResultObject.successObject(entity,null);
	}
	
	@RequestMapping("/pointList")
	public Result<List<PonitDto>> searchAllLorePoint(HttpServletRequest request ,HttpServletResponse response){
		List<PonitDto> entityList = lorePointService.searchAllLorePoint();
		return ResultObject.successObject(entityList,null);
	}
	
	/**
	 * 根据知识点ID 获取知识点练习详情
	 * @param request
	 * @param response
	 * @param id 知识点Id
	 * @return
	 */
	@RequestMapping("/pointDetail")
	public Result<?> findPointIdByDetail(HttpServletRequest request ,HttpServletResponse response,String pointId){
		if(null==pointId||pointId.equals("")){
			return ResultObject.warnMessage("参数不能为空");
		}
		PointExerciseDetailDto list = lorePointService.findPointIdByDetail(Integer.parseInt(pointId));
		return ResultObject.successObject(list,null);
	}
	
	
	
	/**
	 * 根据章节Id,练习本ID , 查询章节下面所有知识点
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	@RequestMapping("/chapterPointList")
	public Result<?> findChapterPoint(HttpServletRequest request ,HttpServletResponse response,String chapterId,String bookId){
		if(null==chapterId||chapterId.equals("")){
			return ResultObject.warnMessage("参数不能为空");
		}
		List<PonitDto> list = chapterService.findChapterPoint(Integer.parseInt(chapterId),Integer.parseInt(bookId));
		return ResultObject.successObject(list,null);
	
	}
	
	
	/**
	 * 根据练习本节点ID 查询该节点下所有知识点信息
	 * @param request
	 * @param response
	 * @param excerciseId
	 * @return
	 */
	@RequestMapping("/bookPointList")
	public Result<?> findExcerciseIdToPonit(HttpServletRequest request ,HttpServletResponse response,String bookId){
		if(null==bookId||bookId.equals("")){
			return ResultObject.warnMessage("参数不能为空");
		}
		List<PonitDto> entityList = excerciseService.findExcerciseIdToPonit(Integer.parseInt(bookId));
		return ResultObject.successObject(entityList,null); 
	}

}
