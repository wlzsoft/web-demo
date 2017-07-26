package com.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/loreCrad")
public class LoreCradController {

	
	@RequestMapping("/savaLoreCrad")
	public ModelAndView savaLoreCrad(HttpServletRequest request ,HttpServletResponse response,ModelAndView model){
		
		return null;
	}
	
	@RequestMapping("/delLoreCrad")
	public ModelAndView delLoreCrad(HttpServletRequest request ,HttpServletResponse response,ModelAndView model){
		
		return null;
	}
	
	@RequestMapping("/editLoreCrad")
	public ModelAndView editLoreCrad(HttpServletRequest request ,HttpServletResponse response,ModelAndView model){
		
		return null;
	}
	
	@RequestMapping("/searchLoreCrad")
	public ModelAndView searchLoreCrad(HttpServletRequest request ,HttpServletResponse response,ModelAndView model){
		
		return null;
	}
	
	@RequestMapping("/searchAllLoreCrad")
	public ModelAndView searchAllLoreCrad(HttpServletRequest request ,HttpServletResponse response,ModelAndView model){
		
		return null;
	}
	
}
