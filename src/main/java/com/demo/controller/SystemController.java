package com.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.demo.entity.UserEntity;
import com.demo.service.UserService;
import com.smartframe.dto.Result;
import com.smartframe.dto.ResultObject;


@Controller
public class SystemController {
	
/*	@Autowired
	private UserService userService;
	

	
	@Autowired
	private JedisCluster jedisCluster;
	

	
	@RequestMapping("/login")
	public Result<?> login(HttpServletRequest request ,HttpServletResponse response){
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		if(null==userName||userName.equals("")||null==password||password.equals("")){
			return ResultObject.successMessage("用户名或密码不能为空!") ;
		}
		 UserEntity userCur = userService.findUserLogin(userName, password);
		if(null == userCur){
			return ResultObject.successMessage("户名或密码错误") ;
		}else{
			request.getSession().setAttribute("userCur_"+userCur.getId(), userCur);
			return  ResultObject.successObject(userCur) ;
		}
		
	 }
	

	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request ,HttpServletResponse response ,ModelAndView model){
		HttpSession session = request.getSession(false);//防止创建Session
		String userId = request.getParameter("userId");
		if(null == session){
			model.setViewName("/view/login");	
		}else{
			request.getSession().removeAttribute("userCur_"+userId);
			model.setViewName("/view/login");
		}
		return model;
	}*/
	
}
