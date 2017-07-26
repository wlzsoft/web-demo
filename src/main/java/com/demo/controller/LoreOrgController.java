package com.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/loreOrg")
public class LoreOrgController {
	
	@RequestMapping("/savaLoreOrg")
	public ModelAndView savaLoreOrg(HttpServletRequest request ,HttpServletResponse response,ModelAndView model){
		
		return null;
	}
	
	@RequestMapping("/delLoreOrg")
	public ModelAndView delLoreOrg(HttpServletRequest request ,HttpServletResponse response,ModelAndView model){
		
		return null;
	}
	
	@RequestMapping("/editLoreOrg")
	public ModelAndView editLoreOrg(HttpServletRequest request ,HttpServletResponse response,ModelAndView model){
		
		return null;
	}
	
	@RequestMapping("/searchLoreOrg")
	public ModelAndView searchLoreOrg(HttpServletRequest request ,HttpServletResponse response,ModelAndView model){
		
		return null;
	}
	
	@RequestMapping("/searchAllLoreOrg")
	public ModelAndView searchAllLoreOrg(HttpServletRequest request ,HttpServletResponse response,ModelAndView model){
		
		return null;
	}

}
