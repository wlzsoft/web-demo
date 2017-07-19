package com.demo.controller.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/index")	
public class UserController {
	
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request ,HttpServletResponse response,ModelAndView model){
		model.setViewName("index");
		return model ;
	}
	
}
