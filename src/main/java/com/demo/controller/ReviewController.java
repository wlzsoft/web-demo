package com.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.dto.CardDto;
import com.demo.dto.PonitDto;
import com.demo.service.ReviewService;
import com.demo.service.SystemService;
import com.smartframe.dto.Result;
import com.smartframe.dto.ResultObject;

@Controller
@RequestMapping("/review")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private SystemService systemService ;
	
	/**
	 * 复习保存
	 * @param request
	 * @param response
	 * @param lorePointId 知识点ID
	 * @param cradId 卡片ID
	 * @param right 回答是否正确 1：正确   0：错误
	 * @return
	 */
	@RequestMapping("/addReview")
	public Result<?> reviewCard(HttpServletRequest request ,HttpServletResponse response,String pointId,String cardId,Integer right){
		if(null==pointId||pointId.equals("")){
			return ResultObject.warnMessage("参数不能为空");
		}
		if(null==cardId||cardId.equals("")){
			return ResultObject.warnMessage("参数不能为空");
		}
		if(null==right||right.equals("")){
			return ResultObject.warnMessage("参数不能为空");
		}
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
	public Result<?> excercise(HttpServletRequest request ,HttpServletResponse response,String bookId){
		Integer userId =systemService.getCurrentUser().getId();
		List<CardDto> cardList = new ArrayList<>();
		if(null==bookId||bookId.equals("")||bookId.equals("0")){
			List<PonitDto> ponitDtoList = reviewService.excercise(userId);
			for(PonitDto dto: ponitDtoList){
				CardDto cardDto = reviewService.roundCard(dto.getId());
				if(null!=cardDto){
					cardList.add(cardDto);	
				}
			}
		}else{
			List<PonitDto> ponitDtoList = reviewService.excercise(userId,Integer.parseInt(bookId));
			for(PonitDto dto: ponitDtoList){
				CardDto cardDto = reviewService.roundCard(dto.getId());
				cardList.add(cardDto);
			}
		}
		return ResultObject.successObject(cardList,null) ;
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
		if(null==pointId||pointId.equals("")){
			return ResultObject.warnMessage("参数不能为空");
		}
		CardDto cardDto = reviewService.roundCard(Integer.parseInt(pointId));
		return ResultObject.successObject(cardDto,null) ;
	}

}
