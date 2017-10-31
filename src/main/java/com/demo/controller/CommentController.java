package com.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smartframe.dto.Result;

@Controller
@RequestMapping("/comm")
public class CommentController {
	
	@RequestMapping("addDiscuss")
	public Result<?> addDiscuss(HttpServletRequest request ,HttpServletResponse response){
		
		return null ;
	}
	
	@RequestMapping("delDiscuss")
	public Result<?> delDiscuss(HttpServletRequest request ,HttpServletResponse response){
		
		return null ;
	}
	
	
	@RequestMapping("discussList")
	public Result<?> discussList(HttpServletRequest request ,HttpServletResponse response){
		
		return null ;
	}

}
