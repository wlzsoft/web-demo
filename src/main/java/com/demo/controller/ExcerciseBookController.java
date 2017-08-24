package com.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.dto.PonitDto;
import com.demo.entity.ExcerciseBookEntity;
import com.demo.service.ExcerciseBookService;
import com.smartframe.dto.Result;
import com.smartframe.dto.ResultObject;


/**
 * 练习本控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/book")
public class ExcerciseBookController {
	
	@Autowired
	private ExcerciseBookService excerciseService ;	
	
	
	/**
	 * 
	 * 新增保存练习本节点
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	@RequestMapping("/saveBook")
	public Result<?> savaExcercise(HttpServletRequest request ,HttpServletResponse response,ExcerciseBookEntity entity){
		excerciseService.excerciseSava(entity);
		return ResultObject.successMessage("保存成功");
	}
	
	/**
	 * 根据练习本ID删除 练习本节点
	 * @param request
	 * @param response
	 * @param excerciseId 练习本ID
	 * @return
	 */
	@RequestMapping("/delBook")
	public Result<?> delExcercise(HttpServletRequest request ,HttpServletResponse response,String excerciseId){
		excerciseService.delExcercise(excerciseId);
		return ResultObject.successMessage("删除成功");
	}
	
	/**
	 * 更新练习本节点数据
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	@RequestMapping("/editBook")
	public Result<?> editExcercise(HttpServletRequest request ,HttpServletResponse response,ExcerciseBookEntity entity){
		excerciseService.editExcercise(entity);
		return ResultObject.successMessage("修改成功");
	}
	
	/**
	 * 根据练习本ID 查询练习本节点
	 * @param request
	 * @param response
	 * @param excerciseId
	 * @return
	 */
	@RequestMapping("/findBook")
	public Result<ExcerciseBookEntity> findExcerciseId(HttpServletRequest request ,HttpServletResponse response,String excerciseId){
		ExcerciseBookEntity entity = excerciseService.findExcerciseId(excerciseId);
		return ResultObject.successObject(entity);
	}
	
	/**
	 * 根据用户ID 查询所有练习本节点信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/bookList")
	public Result<List<ExcerciseBookEntity>> searchAllExcercise(HttpServletRequest request ,HttpServletResponse response,String userId){
		List<ExcerciseBookEntity> entityList = excerciseService.searchAllExcercise(Integer.parseInt(userId));
		return ResultObject.successObject(entityList);
	}
	
	/**
	 * 根据练习本节点ID 查询该节点下所有知识点信息
	 * @param request
	 * @param response
	 * @param excerciseId
	 * @return
	 */
	@RequestMapping("/bookPonitList")
	public Result<List<PonitDto>> findExcerciseIdToPonit(HttpServletRequest request ,HttpServletResponse response,String excerciseId){
		List<PonitDto> entityList = excerciseService.findExcerciseIdToPonit(Integer.parseInt(excerciseId));
		return ResultObject.successObject(entityList); 
	}

}
