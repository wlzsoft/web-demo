package com.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.dto.PonitDto;
import com.demo.entity.LorePointEntity;
import com.demo.entity.LorePointExerciseDetailEntity;
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
	
	/**
	 * 保存知识点信息
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	@RequestMapping("/savePoint")
	public Result<?> savaLorePoint(HttpServletRequest request ,HttpServletResponse response,LorePointEntity entity){
		lorePointService.savaLorePoint(entity);
		return ResultObject.successMessage("保存成功");
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
		lorePointService.editLorePoint(entity);
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
		lorePointService.delLorePoint(Integer.parseInt(pointId));
		return ResultObject.successMessage("删除成功");
	}
	
	
	@RequestMapping("/findPoint")
	public Result<PonitDto> findLorePointId(HttpServletRequest request ,HttpServletResponse response,String pointId){
		PonitDto entity = lorePointService.findLorePointId(Integer.parseInt(pointId));
		return ResultObject.successObject(entity);
	}
	
	@RequestMapping("/pointList")
	public Result<List<PonitDto>> searchAllLorePoint(HttpServletRequest request ,HttpServletResponse response){
		List<PonitDto> entityList = lorePointService.searchAllLorePoint();
		return ResultObject.successObject(entityList);
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
		LorePointExerciseDetailEntity list = lorePointService.findPointIdByDetail(Integer.parseInt(pointId));
		return ResultObject.successObject(list);
	}

}
