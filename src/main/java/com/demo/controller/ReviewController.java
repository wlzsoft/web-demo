package com.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.dto.CardDto;
import com.demo.dto.PonitDto;
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
	@RequestMapping("/saveReview")
	public Result<?> reviewCrad(HttpServletRequest request ,HttpServletResponse response,String pointId,String cardId,Integer right){
		reviewService.reviewCrad(pointId, cardId, right);
		return ResultObject.successMessage("保存成功") ;
	}
	
	
	/**
	 * 用户复习算法
	 * @param request
	 * @param response
	 * @param userId
	 * @return
	 */
	@RequestMapping("/excercise")
	public Result<?> excercise(HttpServletRequest request ,HttpServletResponse response ,String userId){
		List<PonitDto> ponitDto = reviewService.excercise(Integer.parseInt(userId));
		return ResultObject.successObject(ponitDto) ;
	}
	
	/**
	 * 根据知识点ID ，随机获取一个卡片信息
	 * @param request
	 * @param response
	 * @param pointId
	 * @return
	 */
	@RequestMapping("/card")
	public Result<?> getCard(HttpServletRequest request ,HttpServletResponse response ,String pointId ){
		CardDto cardDto = reviewService.roundCard(Integer.parseInt(pointId));
		return ResultObject.successObject(cardDto) ;
	}

}
