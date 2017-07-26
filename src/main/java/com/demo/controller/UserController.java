package com.demo.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/user")	
public class UserController {
	
	@RequestMapping("/savaUser")
	public ModelAndView savaUser(HttpServletRequest request ,HttpServletResponse response,ModelAndView model){
		
		return model ;
	}
	
	@RequestMapping("/searchUser")
	public ModelAndView searchUser(HttpServletRequest request ,HttpServletResponse response,ModelAndView model){
		
		return model ;
	}
	
	@RequestMapping("/editUser")
	public ModelAndView editUser(HttpServletRequest request ,HttpServletResponse response,ModelAndView model){
		
		return model ;
	}
	
	@RequestMapping("/updateUser")
	public ModelAndView updateUser(HttpServletRequest request ,HttpServletResponse response,ModelAndView model){
		
		return model ;
	}
	
}
