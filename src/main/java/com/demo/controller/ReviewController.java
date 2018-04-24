package com.demo.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.dao.ExcerciseDao;
import com.demo.dto.CardDto;
import com.demo.dto.PointNumDto;
import com.demo.service.AnswerService;
import com.demo.service.ExcerciseService;
import com.demo.service.LoreCradService;
import com.demo.service.RecommendService;
import com.demo.service.SystemService;
import com.demo.service.UtilService;
import com.smartframe.basics.util.EmojiUtil;
import com.smartframe.dto.Result;
import com.smartframe.dto.ResultObject;

@Controller
@RequestMapping("/review")
public class ReviewController {
	
	@Autowired
	private AnswerService answerService;
	
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
	
	@Autowired
	private ExcerciseDao excerciseDao;
	
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
	public Result<?> reviewCard(HttpServletRequest request ,HttpServletResponse response,String pointId,String cardId,Integer right,Integer duration){
		if(null==pointId||pointId.equals("")){
			return ResultObject.warnMessage("参数不能为空");
		}
		if(null==cardId||cardId.equals("")){
			return ResultObject.warnMessage("参数不能为空");
		}
		if(null==right||right.equals("")){
			return ResultObject.warnMessage("参数不能为空");
		}
		if(null==duration||duration.equals("")){
			return ResultObject.warnMessage("参数不能为空");
		}
		
		/**
		 * 添加权限
		 * **/
		Integer userId =systemService.getCurrentUser().getId();
		Integer[] pointIds = {Integer.parseInt(pointId)};
		Boolean flag = utilService.getAuthByPointId(pointIds, userId);
		if(!flag){
			return ResultObject.warnMessage("无操作权限");
		}
		
		answerService.cardAnswer(pointId, cardId, right,duration);
		
		return ResultObject.successMessage("保存成功") ;
	}
	
	
	/**
	 * 用户智能推荐复习算法【停用】
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
				if(null!=dto.getCardData()||dto.getCardData().equals("")){
					String	cardData = EmojiUtil.emojiRecovery2(dto.getCardData());
					dto.setCardData(cardData);
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		return ResultObject.successObject(cardList,null) ;
	}
	
	
	
	/**
	 * 复习错误的知识点 （错题，熟练度、知识点排序）【停用】
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
					if(null!=dto.getCardData()||dto.getCardData().equals("")){
						String	cardData = EmojiUtil.emojiRecovery2(dto.getCardData());
						dto.setCardData(cardData);
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}	
		}
		
	  return ResultObject.successObject(cardList,null) ;
	} 
	
	/**
	 * 复习新的的知识点 （学习新的知识点，周期为0的知识点，知识点排序）【停用】
	 * @param request
	 * @param response
	 * @param bookId
	 * @param chapterIds
	 * @return
	 */
	@RequestMapping("exNew")
	public Result<?> excerciseNew(HttpServletRequest request ,HttpServletResponse response,String bookId,String chapterIds,String count){
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
		
    	if(null==count||count.equals("")){
    		count="5";// 
    	}
		
		cardList = excerciseService.excerciseNew(bookId, chapterIds, userId);
		
		//对emoji转换
		if(cardList.size()>0){
			for(CardDto dto :cardList){
				try {
					if(null!=dto.getCardData()||dto.getCardData().equals("")){
						String	cardData = EmojiUtil.emojiRecovery2(dto.getCardData());
						dto.setCardData(cardData);
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}	
		}
		
	  return ResultObject.successObject(cardList,null) ;
	} 
	
	
	/**
	 * 巩固复习知识点（熟练度、知识点排序）【停用】
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
					if(null!=dto.getCardData()||dto.getCardData().equals("")){
						String	cardData = EmojiUtil.emojiRecovery2(dto.getCardData());
						dto.setCardData(cardData);
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
	public Result<?> pointNum(HttpServletRequest request ,HttpServletResponse response,String bookIds){
		if(null==bookIds||bookIds.equals("")){
			return ResultObject.warnMessage("参数不能为空");
		}
		String[] bookId_array =bookIds.split(",");
		if(bookId_array.length>0){
			Integer userId =systemService.getCurrentUser().getId();
			List<PointNumDto> dot = excerciseService.getPointNum(userId,bookId_array);
			return ResultObject.successObject(dot,null) ;	
		}else{
			return ResultObject.warnMessage("参数不能为空");
		}
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
		Integer[] pointIds ={Integer.parseInt(pointId)};
		Boolean flag = utilService.getAuthByPointId(pointIds, userId);
		if(!flag){
			return ResultObject.warnMessage("无操作权限");
		}
		
		CardDto cardDto = loreCradService.roundCard(Integer.parseInt(pointId));
		
		//对emoji转换
		try {
			if(null!=cardDto.getCardData()||cardDto.getCardData().equals("")){
				String	cardData = EmojiUtil.emojiRecovery2(cardDto.getCardData());
				cardDto.setCardData(cardData);
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return ResultObject.successObject(cardDto,null) ;
	}
	

}
