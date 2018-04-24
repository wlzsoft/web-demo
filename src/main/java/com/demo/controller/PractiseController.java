package com.demo.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.dao.PointDao;
import com.demo.dto.CardDto;
import com.demo.dto.PointExerciseDetailDto;
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
	
	@Autowired
	private PointDao pointDao;
	
	
	/**
	 * @param request
	 * @param response
	 * @param bookId
	 * @param chapterIds
	 * @param state
	 * @param isContinue // true 1 继续，  false 0 不继续   默认不继续 0
 	 * @return
	 */
	@RequestMapping("/exCardList")
	public Result<?> exCardList(HttpServletRequest request ,HttpServletResponse response,String bookId,String chapterIds,String state){
		List<CardDto> cardList = new ArrayList<>();
		Integer userId =systemService.getCurrentUser().getId();
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
			
			if(null==state||state.equals("")){//进入智能推荐算法
				
				cardList = recommendService.excercise_Card(userId, Integer.parseInt(bookId), chapterIds);
				
		    }else if(state.equals("0")){//进入 练新 算法
		    	cardList = excerciseService.excerciseNew(bookId, chapterIds, userId);
			}else if(state.equals("1")){//进入 错题 算法
				cardList = excerciseService.excerciseError(bookId,chapterIds,userId);
				
			}else if(state.equals("2")){//进入巩固 算法 ..只练习熟练度 为0 的 并且是已经练习过的
				cardList = excerciseService.excerciseStrenthen(bookId, chapterIds, userId);
			}else if(state.equals("3")){//强化练习 ..练习熟练度 大于0 的
				cardList = excerciseService.excerciseIntensify(bookId, chapterIds, userId);
				if(cardList.size()==0){
					cardList=excerciseService.excerciseIntensifyFull(bookId, chapterIds, userId);
				}
				
			} else{ //全部条件不符合：进入智能推荐算法
				cardList = recommendService.excerciseCard(userId, Integer.parseInt(bookId), chapterIds);
			}
		}
		
		//对emoji转换
		for(CardDto dto :cardList){
			try {
				if(null!=dto.getCardData()||dto.getCardData().equals("")){
					String	cardData = EmojiUtil.emojiRecovery2(dto.getCardData());
					dto.setCardData(cardData);
				}
				
				PointExerciseDetailDto entity = pointDao.pointIdByDetail(dto.getPointId(),userId);
				dto.setPointState(entity.getState());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		return ResultObject.successObject(cardList,null);
	}

}
