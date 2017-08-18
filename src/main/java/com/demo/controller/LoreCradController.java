package com.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.dto.CardDto;
import com.demo.entity.LoreCardEntity;
import com.demo.entity.LoreCardExerciseDetailEntity;
import com.demo.entity.LoreCradAnswersEntity;
import com.demo.service.LoreCradService;
import com.smartframe.dto.Result;
import com.smartframe.dto.ResultObject;


@Controller
@RequestMapping("/crad")
public class LoreCradController {

	@Autowired
	private LoreCradService loreCradService;
	
	/**
	 * 保存知识卡片
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	@RequestMapping("/savaCrad")
	public Result<?> savaLoreCrad(HttpServletRequest request ,HttpServletResponse response,LoreCardEntity loreCardEntity ){
		 loreCradService.savaLoreCrad(loreCardEntity);
		return ResultObject.successMessage("保存成功");
	}
	
	/**
	 * 根据知识卡片ID 删除知识卡片
	 * @param request
	 * @param response
	 * @param loreCardId
	 * @return
	 */
	@RequestMapping("/delCrad")
	public Result<?> delLoreCrad(HttpServletRequest request ,HttpServletResponse response,String cardId){
		   loreCradService.delLoreCrad(Integer.parseInt(cardId));
		return ResultObject.successMessage("删除成功");
	}
	
	/**
	 * 更新知识卡片信息
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	@RequestMapping("/editCrad")
	public Result<?> editLoreCrad(HttpServletRequest request ,HttpServletResponse response,LoreCardEntity entity ){
		 loreCradService.editLoreCrad(entity);
		return ResultObject.successMessage("修改成功");
	}
	
	/**
	 * 根据知识卡片ID ，获取知识卡片信息
	 * @param request
	 * @param response
	 * @param loreCardId
	 * @return
	 */
	@RequestMapping("/findCrad")
	public Result<?> findLoreCradById(HttpServletRequest request ,HttpServletResponse response,String cardId){
		CardDto entity = loreCradService.findLoreCradById(Integer.parseInt(cardId));
		if(null ==entity){
			return ResultObject.successMessage("没有数据");
		}
		return ResultObject.successObject(entity);
	}
	
	/**
	 * 根据知识点ID ,获取知识卡片信息
	 * @param request
	 * @param response
	 * @param lorePointId
	 * @return
	 */
	@RequestMapping("/findCradPointId")
	public Result<?> findLoreCradByPointId(HttpServletRequest request ,HttpServletResponse response,String pointId){
		List<CardDto> entityList = loreCradService.findLoreCradByPointId(Integer.parseInt(pointId));
		if(entityList.size()==0){
			return ResultObject.successMessage("没有数据");
		}
		return ResultObject.successObject(entityList);
	}
	
	/**
	 * 获取所有开放的知识卡片信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/openCrad")
	public Result<?> getOpenLoreCrad(HttpServletRequest request ,HttpServletResponse response){
		List<LoreCardEntity> entityList = loreCradService.getOpenLoreCrad();
		if(entityList.size()==0){
			return ResultObject.successMessage("没有数据");
		}
		return ResultObject.successObject(entityList);
	}
	
	
	/**
	 * 根据知识卡片ID ，获取知识卡片的练习详情
	 * @param request
	 * @param response
	 * @param lorePointId
	 * @return
	 */
	@RequestMapping("/getCradDetailByPointId")
	public Result<?> getLoreCradDetailByPointId(HttpServletRequest request ,HttpServletResponse response,String loreCardId){
		LoreCardExerciseDetailEntity entity = loreCradService.getLoreCradDetailByPointId(Integer.parseInt(loreCardId));
		if(null==entity){
			return ResultObject.successMessage("没有数据");
		}
		return ResultObject.successObject(entity);
	}
	
	/**
	 * 根据卡片ID ，获取卡片的答案信息
	 * @param request
	 * @param response
	 * @param loreCardId
	 * @return
	 */
	@RequestMapping("/getCradAnswerByPointId")
	public Result<?> getLoreCradAnswerByPointId(HttpServletRequest request ,HttpServletResponse response,String loreCardId){
		List<LoreCradAnswersEntity> entityList = loreCradService.getLoreCradAnswerByPointId(Integer.parseInt(loreCardId));
		if(entityList.size()<1){
			return ResultObject.successMessage("没有数据");
		}
		return ResultObject.successObject(entityList);
	}
	
}
