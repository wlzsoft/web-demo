package com.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.dto.BookProgressDto;
import com.demo.dto.IdEntity;
import com.demo.dto.PonitDto;
import com.demo.entity.ChapterEntity;
import com.demo.entity.ExcerciseBookEntity;
import com.demo.service.ExcerciseBookService;
import com.demo.service.SystemService;
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
	
	
	@Autowired
	private SystemService systemService ;
	
	
	/**
	 * 
	 * 新增保存练习本节点
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	@RequestMapping("/addBook")
	public Result<?> savaExcercise(HttpServletRequest request ,HttpServletResponse response,ExcerciseBookEntity entity){
		IdEntity identity = excerciseService.excerciseSava(entity);
		return ResultObject.successObject(identity,"保存成功");
	}
	
	/**
	 * 根据练习本ID删除 练习本节点
	 * @param request
	 * @param response
	 * @param excerciseId 练习本ID
	 * @return
	 */
	@RequestMapping("/delBook")
	public Result<?> delExcercise(HttpServletRequest request ,HttpServletResponse response,String bookId){
		excerciseService.delExcercise(bookId);
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
	 * 根据练习本ID 查询练习本信息
	 * @param request
	 * @param response
	 * @param excerciseId
	 * @return
	 */
	@RequestMapping("/findBook")
	public Result<ExcerciseBookEntity> findExcerciseId(HttpServletRequest request ,HttpServletResponse response,String bookId){
		ExcerciseBookEntity entity = excerciseService.findExcerciseId(bookId);
		return ResultObject.successObject(entity,null);
	}
	
	/**
	 * 根据用户ID 查询所有练习本信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/bookList")
	public Result<List<ExcerciseBookEntity>> searchAllExcercise(HttpServletRequest request ,HttpServletResponse response,Integer userId){
		userId =systemService.getCurrentUser().getId();
		List<ExcerciseBookEntity> entityList = excerciseService.searchAllExcercise(userId);
		return ResultObject.successObject(entityList,null);
	}
	

    
	/**
	 * 根据练习本ID ， 查询用户练习本练习进度
	 * @param request
	 * @param response
	 * @param excerciseId
	 * @return
	 */
	@RequestMapping("/bookProgress")
	public Result<?> bookProgress(HttpServletRequest request ,HttpServletResponse response,String bookId){
		BookProgressDto entity = excerciseService.bookProgress(Integer.parseInt(bookId));
		return ResultObject.successObject(entity,null); 
	}
	
	
	

}
