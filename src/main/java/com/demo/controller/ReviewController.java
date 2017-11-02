package com.demo.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.dto.CardDto;
import com.demo.dto.PointNumDto;
import com.demo.service.ExcerciseService;
import com.demo.service.LoreCradService;
import com.demo.service.RecommendService;
import com.demo.service.ReviewService;
import com.demo.service.SystemService;
import com.demo.service.UtilService;
import com.smartframe.basics.util.EmojiUtil;
import com.smartframe.dto.Result;
import com.smartframe.dto.ResultObject;

@Controller
@RequestMapping("/review")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private UtilService utilService;
	
	@Autowired
	private  LoreCradService loreCradService;
	
	@Autowired
	private SystemService systemService ;
	
	@Autowired
	private ExcerciseService excerciseService;
	
	@Autowired
	private RecommendService recommendService;
	
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
		Boolean flag = utilService.getAuthByPointId(Integer.parseInt(pointId), userId);
		if(!flag){
			return ResultObject.warnMessage("无操作权限");
		}
		
		reviewService.reviewCrad(pointId, cardId, right);
		
		return ResultObject.successMessage("保存成功") ;
	}
	
	
	/**
	 * 用户智能推荐复习算法
	 * @param request
	 * @param response
	 * @param bookId 
	 * @return
	 */
	@RequestMapping("/exRecommend")
	public Result<?> recommend(HttpServletRequest request ,HttpServletResponse response,String bookId,String chapterIds){
		Integer userId =systemService.getCurrentUser().getId();
		List<CardDto> cardList = new ArrayList<>();
		if(null==bookId||bookId.equals("")){
			return ResultObject.warnMessage("练习本ID不能为空");
		}else{
			/**
			 * 添加权限
			 * **/
			Boolean flag = utilService.getAuthByBookId(Integer.parseInt(bookId), userId);//验证当前用户是否有 练习该练习本的权限
			if(!flag){
				return ResultObject.warnMessage("无操作权限");
			}
			
			 cardList = recommendService.excerciseCard(userId, Integer.parseInt(bookId), chapterIds);
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
	 * 复习错误的知识点 （错题，熟练度、知识点排序）
	 * 
	 * @param request
	 * @param response
	 * @param bookId
	 * @param chapterIds
	 * @return
	 */
	@RequestMapping("exError")
	@ResponseBody
	public Result<?> excerciseError(HttpServletRequest request ,HttpServletResponse response,String bookId,String chapterIds){
		if(null==bookId||bookId.equals("")){
			return ResultObject.warnMessage("参数不能为空");
		}
		Integer userId =systemService.getCurrentUser().getId();
		/**
		 * 添加权限
		 * **/
		Boolean flag = utilService.getAuthByBookId(Integer.parseInt(bookId), userId);//验证当前用户是否有 练习该练习本的权限
		if(!flag){
			return ResultObject.warnMessage("无操作权限");
		}
		List<CardDto> cardList = new ArrayList<>();
		
		cardList = excerciseService.excerciseError(bookId,chapterIds,userId);
		
		//对emoji转换
		if(cardList.size()>0){
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
		}
		
	  return ResultObject.successObject(cardList,null) ;
	} 
	
	/**
	 * 复习新的的知识点 （学习新的知识点，周期为0的知识点，知识点排序）
	 * @param request
	 * @param response
	 * @param bookId
	 * @param chapterIds
	 * @return
	 */
	@RequestMapping("exNew")
	public Result<?> excerciseNew(HttpServletRequest request ,HttpServletResponse response,String bookId,String chapterIds){
		if(null==bookId||bookId.equals("")){
			return ResultObject.warnMessage("参数不能为空");
		}
		Integer userId =systemService.getCurrentUser().getId();
		/**
		 * 添加权限
		 * **/
		Boolean flag = utilService.getAuthByBookId(Integer.parseInt(bookId), userId);//验证当前用户是否有 练习该练习本的权限
		if(!flag){
			return ResultObject.warnMessage("无操作权限");
		}
		List<CardDto> cardList = new ArrayList<>();
		
		cardList = excerciseService.excerciseNew(bookId, chapterIds, userId);
		
		//对emoji转换
		if(cardList.size()>0){
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
		}
		
	  return ResultObject.successObject(cardList,null) ;
	} 
	
	
	/**
	 * 巩固复习知识点（熟练度、知识点排序）
	 * @param request
	 * @param response
	 * @param bookId
	 * @param chapterIds
	 * @return
	 */
	@RequestMapping("exStrengthen")
	public Result<?> excerciseStrengthen(HttpServletRequest request ,HttpServletResponse response,String bookId,String chapterIds){
		if(null==bookId||bookId.equals("")){
			return ResultObject.warnMessage("参数不能为空");
		}
		Integer userId =systemService.getCurrentUser().getId();
		/**
		 * 添加权限
		 * **/
		Boolean flag = utilService.getAuthByBookId(Integer.parseInt(bookId), userId);//验证当前用户是否有 练习该练习本的权限
		if(!flag){
			return ResultObject.warnMessage("无操作权限");
		}
		List<CardDto> cardList = new ArrayList<>();
		cardList = excerciseService.excerciseStrenthen(bookId, chapterIds, userId);
		
		//对emoji转换
		if(cardList.size()>0){
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
		}
		
	  return ResultObject.successObject(cardList,null) ;
	} 
	
	
	
	/**
	 * 获取练习本中，复习，错题，新的 的数量
	 * @param request
	 * @param response
	 * @param bookId
	 * @return
	 */
	@RequestMapping("pointNum")
	public Result<?> pointNum(HttpServletRequest request ,HttpServletResponse response,Integer bookId){
		if(null==bookId||bookId.equals("")){
			return ResultObject.warnMessage("参数不能为空");
		}
		
		Integer userId =systemService.getCurrentUser().getId();
		
		PointNumDto dot = excerciseService.getPointNum(userId,bookId);
		return ResultObject.successObject(dot,null) ;
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
		Boolean flag = utilService.getAuthByPointId(Integer.parseInt(pointId), userId);
		if(!flag){
			return ResultObject.warnMessage("无操作权限");
		}
		
		CardDto cardDto = loreCradService.roundCard(Integer.parseInt(pointId));
		
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
