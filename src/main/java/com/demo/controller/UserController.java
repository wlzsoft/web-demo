package com.demo.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.demo.entity.ExcerciseBookEntity;
import com.demo.entity.UserEntity;
import com.demo.entity.UserExerciseDetailEntity;
import com.demo.service.UserService;
import com.smartframe.dto.Result;
import com.smartframe.dto.ResultObject;


@Controller
@RequestMapping("/user")	
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/savaUser")
	public Result<?> savaUser(HttpServletRequest request ,HttpServletResponse response,UserEntity userentity ){
		userService.savaUser(userentity);
		return ResultObject.successMessage("保存成功") ;
	}
	
	/**
	 * 根据用户ID 获取用户练习流水记录信息
	 * @param request
	 * @param response
	 * @param model
	 * @param userId
	 * @return
	 */
	@RequestMapping("/findUserExerciseDetailById")
	public Result<List<UserExerciseDetailEntity>> findUserExerciseDetailById(HttpServletRequest request ,HttpServletResponse response,String userId){
		List<UserExerciseDetailEntity> list = userService.findUserExerciseDetailById(Integer.parseInt(userId));
		return ResultObject.successObject(list);
	}
	
	/**
	 * 根据用户ID获取 用户所有练习本
	 * @param request
	 * @param response
	 * @param model
	 * @param userId
	 * @return
	 */
	@RequestMapping("/findExerciseById")
	public Result<?> findExerciseById(HttpServletRequest request ,HttpServletResponse response,String userId){
		List<ExcerciseBookEntity>  list = userService.findExerciseById(Integer.parseInt(userId));
		return ResultObject.successObject(list);
	}
}
