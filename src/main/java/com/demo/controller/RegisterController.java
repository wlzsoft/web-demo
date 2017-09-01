package com.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.entity.UserEntity;
import com.demo.service.UserService;
import com.smartframe.dto.Result;
import com.smartframe.dto.ResultObject;


@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/addUser")
	public Result<?> savaUser(HttpServletRequest request ,HttpServletResponse response,UserEntity userentity ){
		if(null==userentity.getUsername()||userentity.getUsername().equals("")){
			return ResultObject.warnMessage("用户名不能为空");
		}
		if(null==userentity.getPassword()||userentity.getPassword().equals("")){
			return ResultObject.warnMessage("密码不能为空");
		}
		userService.savaUser(userentity);
		return ResultObject.successMessage("保存成功") ;
	}

}
