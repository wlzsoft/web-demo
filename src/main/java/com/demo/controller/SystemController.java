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

@Controller
public class SystemController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	public ModelAndView	login(HttpServletRequest request ,HttpServletResponse response ,ModelAndView model){
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		if(null==userName||userName.equals("")||null==password||password.equals("")){
			model.addObject("message", "�û��������벻��Ϊ��");
			model.setViewName("/view/login");	
			return model ;
		}
		 UserEntity userCur = userService.findUserLogin(userName, password);
		if(null == userCur){
			model.addObject("message", "�û������������");
			model.setViewName("/view/login");	
			return model ;
		}else{
			request.getSession().setAttribute("userCur_"+userCur.getId(), userCur);
			model.addObject("userName", userName);
			model.setViewName("/view/index");	
			return model;
		}
		
	 }
	

	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request ,HttpServletResponse response ,ModelAndView model){
		HttpSession session = request.getSession(false);//��ֹ����Session
		String userId = request.getParameter("userId");
		if(null == session){
			model.setViewName("/view/login");	
		}else{
			request.getSession().removeAttribute("userCur_"+userId);
			model.setViewName("/view/login");
		}
		return model;
	}
	
}
