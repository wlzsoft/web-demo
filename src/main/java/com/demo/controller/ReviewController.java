package com.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.service.ReviewService;
import com.smartframe.dto.Result;
import com.smartframe.dto.ResultObject;

@Controller
@RequestMapping("/review")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	/**
	 * 复习保存
	 * @param request
	 * @param response
	 * @param lorePointId 知识点ID
	 * @param cradId 卡片ID
	 * @param right 回答是否正确 1：正确   0：错误
	 * @return
	 */
	@RequestMapping("/reviewCrad")
	public Result<?> reviewCrad(HttpServletRequest request ,HttpServletResponse response,String lorePointId,String cradId,Integer right){
		reviewService.reviewCrad(lorePointId, cradId, right);
		return ResultObject.successMessage("保存成功") ;
	}

}
