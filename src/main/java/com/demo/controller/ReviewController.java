package com.demo.controller;

import java.io.UnsupportedEncodingException;
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
import com.smartframe.basics.util.EmojiUtil;
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
		
		/**
		 * 添加权限
		 * **/
		Integer userId =systemService.getCurrentUser().getId();
		Boolean flag = reviewService.getAuthByPointId(Integer.parseInt(pointId), userId);
		if(!flag){
			return ResultObject.warnMessage("无操作权限");
		}
		
		reviewService.reviewCrad(pointId, cardId, right);
		return ResultObject.successMessage("保存成功") ;
	}
	
	
	/**
	 * 用户复习算法
	 * @param request
	 * @param response
	 * @param bookId 0:全部练习本   ，有值时 指定练习本
	 * @return
	 */
	@RequestMapping("/excercise")
	public Result<?> excercise(HttpServletRequest request ,HttpServletResponse response,String bookId,String chapterIds){
		Integer userId =systemService.getCurrentUser().getId();
		List<CardDto> cardList = new ArrayList<>();
		if(null==bookId||bookId.equals("")||bookId.equals("0")){//-----------------复习用户下全部练习本
			List<PonitDto> ponitDtoList = reviewService.excercise(userId);
			for(PonitDto dto: ponitDtoList){
				CardDto cardDto = reviewService.roundCard(dto.getId());
				if(null!=cardDto){
					cardList.add(cardDto);	
				}
			}
		}else{//------------------根据练习本Id复习用户下指定练习本ID
			/**
			 * 添加权限
			 * **/
			Boolean flag = reviewService.getAuthByBookId(Integer.parseInt(bookId), userId);//验证当前用户是否有 练习该练习本的权限
			if(!flag){
				return ResultObject.warnMessage("无操作权限");
			}
			
	       /* 修改于 2017-10-16	
	        * if(null==chapterId||chapterId.equals("")){
				   ponitDtoList = reviewService.excercise(userId,Integer.parseInt(bookId));//----根据练习本Id复习用户下指定练习本ID
				}else{
				   ponitDtoList	=reviewService.excerciseCard(userId,Integer.parseInt(bookId),chapterId); //--------指定练习本的章节进行练习
				}
				
				for(PonitDto dto: ponitDtoList){
					CardDto cardDto = reviewService.roundCard(dto.getId());
					cardList.add(cardDto);
				}
			*/
			if(null==chapterIds||chapterIds.equals("")){
				List<CardDto> cardDto = reviewService.excerciseCard(userId, Integer.parseInt(bookId));
				cardList.addAll(cardDto);
			}else{
				String[] chapterId = chapterIds.split(",");
				if(chapterId.length>0){
					Integer[] chapter = new Integer[chapterId.length];
					for(int i=0;i<chapterId.length;i++){
						chapter[i]=Integer.parseInt(chapterId[i]);
					}
					List<CardDto> cardDto = reviewService.excerciseCard(userId, Integer.parseInt(bookId), chapter);	
					cardList.addAll(cardDto);
				}
			}
		}			
		
		
		//对emoji转换
		for(CardDto dto :cardList){
			try {
				if(null!=dto.getTitleText()||dto.getTitleText().equals("")){
					String	titleText = EmojiUtil.emojiRecovery2(dto.getTitleText());
					dto.setTitleText(titleText);
				}
				if(null!=dto.getQuestionText()||dto.getQuestionText().equals("")){
					String questionText =  EmojiUtil.emojiRecovery2(dto.getQuestionText());
					dto.setQuestionText(questionText);
				}

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
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
		
		/**
		 * 添加权限
		 * **/
		Integer userId =systemService.getCurrentUser().getId();
		Boolean flag = reviewService.getAuthByPointId(Integer.parseInt(pointId), userId);
		if(!flag){
			return ResultObject.warnMessage("无操作权限");
		}
		
		CardDto cardDto = reviewService.roundCard(Integer.parseInt(pointId));
		
		//对emoji转换
		try {
			if(null!=cardDto.getTitleText()||cardDto.getTitleText().equals("")){
				String	titleText = EmojiUtil.emojiRecovery2(cardDto.getTitleText());
				cardDto.setTitleText(titleText);
			}
			if(null!=cardDto.getQuestionText()||cardDto.getQuestionText().equals("")){
				String questionText =  EmojiUtil.emojiRecovery2(cardDto.getQuestionText());
				cardDto.setQuestionText(questionText);
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return ResultObject.successObject(cardDto,null) ;
	}

}
