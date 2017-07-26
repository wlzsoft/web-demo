package com.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SystemController {
	
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request ,HttpServletResponse response,ModelAndView model){
		model.setViewName("index");
		return model ;
	}
	
	@RequestMapping("/logout")
    public  ModelAndView  logout(HttpServletRequest request ,HttpServletResponse response,ModelAndView model){
		return model ;
	}

}
