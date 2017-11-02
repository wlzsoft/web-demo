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
import com.demo.service.ExcerciseService;
import com.demo.service.RecommendService;
import com.demo.service.SystemService;
import com.demo.service.UtilService;
import com.smartframe.basics.util.EmojiUtil;
import com.smartframe.dto.Result;
import com.smartframe.dto.ResultObject;

@Controller
@RequestMapping("/practice")
public class PractiseController {
	
	@Autowired
	private SystemService systemService ;
	
	@Autowired
	private UtilService utilService;
	
	@Autowired
	private RecommendService recommendService;
	
	@Autowired
	private ExcerciseService excerciseService;
	
/*	@RequestMapping("/exCardList")
	public String exCardList(HttpServletRequest request ,HttpServletResponse response,String bookId,String chapterIds,String stata,RedirectAttributes attr){
		attr.addAttribute("bookId", bookId);
		attr.addAttribute("chapterIds", chapterIds);
		attr.addAttribute("stata", stata);
		if(null==stata||stata.equals("0")){//进入智能推荐算法
			return "redirect:/review/exRecommend.json";
	     }else if(stata.equals("0")){//进入 练新 算法
			 return "redirect:/review/exNew.json";
		}else if(stata.equals("1")){//进入 错题 算法
			return "redirect:/review/exError.json";
		}else if(stata.equals("2")){//进入巩固 算法
			return "redirect:/review/exStrengthen.json";
		}else{//进入智能推荐算法
			return "redirect:/review/exRecommend.json";
		}
	}*/
	
	@RequestMapping("/exCardList")
	public Result<?> exCardList(HttpServletRequest request ,HttpServletResponse response,String bookId,String chapterIds,String state){
		List<CardDto> cardList = new ArrayList<>();
		if(null==bookId||bookId.equals("")){
			return ResultObject.warnMessage("练习本ID不能为空");
		}else{
			Integer userId =systemService.getCurrentUser().getId();
			/**
			 * 添加权限
			 * **/
			Boolean flag = utilService.getAuthByBookId(Integer.parseInt(bookId), userId);//验证当前用户是否有 练习该练习本的权限
			if(!flag){
				return ResultObject.warnMessage("无操作权限");
			}
			
			if(null==state||state.equals("")){//进入智能推荐算法
				
				cardList = recommendService.excerciseCard(userId, Integer.parseInt(bookId), chapterIds);
				
		    }else if(state.equals("0")){//进入 练新 算法
		    	
		    	cardList = excerciseService.excerciseNew(bookId, chapterIds, userId);
		    	
			}else if(state.equals("1")){//进入 错题 算法
				cardList = excerciseService.excerciseError(bookId,chapterIds,userId);
				
			}else if(state.equals("2")){//进入巩固 算法
				cardList = excerciseService.excerciseStrenthen_Button(bookId, chapterIds, userId);
				if(cardList.size()==0){
					cardList=excerciseService.excerciseStrenthenFull(bookId, chapterIds, userId);
				}
			}else{ //全部条件不符合：进入智能推荐算法
				cardList = recommendService.excerciseCard(userId, Integer.parseInt(bookId), chapterIds);
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
		
		return ResultObject.successObject(cardList,null);
	}

}
