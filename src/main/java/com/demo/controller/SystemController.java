package com.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.entity.UserEntity;
import com.demo.service.UserService;
import com.smartframe.dto.Result;
import com.smartframe.dto.ResultObject;


@Controller
public class SystemController {
	
	@Autowired
	private UserService userService;

	
	@RequestMapping("/login")
	public Result<?> login(HttpServletRequest request ,HttpServletResponse response){
		
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		if(null==userName||userName.equals("")||null==password||password.equals("")){
			return ResultObject.sucreMessage("用户名或密码不能为空!") ;
		}
		UserEntity userCur = userService.findUserLogin(userName, password);
		if(null == userCur){
			return ResultObject.sucreMessage("用户名或密码错误!") ;
		}else{
			request.getSession().setAttribute("userCur", userCur);
			return  ResultObject.successObject(userCur,null) ;
		}
		
	 }
	

	@RequestMapping("/logout")
	public Result<?> logout(HttpServletRequest request ,HttpServletResponse response){
		HttpSession session = request.getSession(false);//防止创建Session
	//	String userId = request.getParameter("userId");
		session = request.getSession(); 
		if(null == session){
			return ResultObject.successMessage("退出成功") ;	
		}else{
			request.getSession().removeAttribute("userCur");
			return ResultObject.successMessage("退出成功") ;
		}
	}
	
}
