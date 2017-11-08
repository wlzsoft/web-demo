package com.demo.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.dto.IdEntity;
import com.demo.entity.DiscussEntity;
import com.demo.service.CommentService;
import com.demo.service.ReviewService;
import com.smartframe.basics.util.EmojiUtil;
import com.smartframe.dto.Result;
import com.smartframe.dto.ResultObject;

@Controller
@RequestMapping("/comment")
public class CommentController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReviewService.class);
	
	@Autowired
	private CommentService commentService;	
	
	/**
	 * 新增评论
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("addComment")
	public Result<?> addComment(HttpServletRequest request ,HttpServletResponse response,DiscussEntity entity){
		if(null==entity){
			return ResultObject.successMessage("评论数据异常") ;	
		}else{
			if(null==entity.getBookId()||entity.getBookId().equals("")){
				return ResultObject.successMessage("需要选择一个课程评论") ;
			}else{
				if(null!=entity.getCardId()){
					if(null==entity.getPointId()||entity.getPointId().equals("")){
						return ResultObject.successMessage("评论卡片时，需要选择卡片对应的知识点才能评论") ;
					}
				}
			}
			
			if(null==entity.getContent()||entity.getContent().equals("")){
				return ResultObject.successMessage("评论内容不能为空") ;
			}else{
				try {
					String	content = EmojiUtil.emojiConvert1(entity.getContent());//emjoin 字符转换
					entity.setContent(content);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		
		IdEntity identity = commentService.addDiscuss(entity);
		return ResultObject.successObject(identity,"评论成功");
	}
	
	/**删除评论
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("delComment")
	public Result<?> delComment(HttpServletRequest request ,HttpServletResponse response,String commentId){
		if(null==commentId||commentId.equals("")){
			return ResultObject.successMessage("参数不能为空") ;
		}
		
		 int con = commentService.delDiscuss(Integer.parseInt(commentId));
		 if(con==0){
			 return ResultObject.sucreMessage("没有权限删除") ;  
		 }else{
			 return ResultObject.successMessage("删除成功") ; 
		 }
	}
	
	
	/**
	 * 查询卡片评论
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("findComment")
	public Result<?> findComment(HttpServletRequest request ,HttpServletResponse response,String bookId,String pointId,String cardId){
		if(null==bookId||bookId.equals("")){
			return ResultObject.successMessage("课程参数不能为空") ;
		}
		List<DiscussEntity> list = new ArrayList<>();
		if(null==cardId||cardId.equals("")){
			if(null==pointId||pointId.equals("")){
				LOGGER.info("进入练习本评论");
				 list =commentService.discussBook(Integer.parseInt(bookId));
			}else{
				LOGGER.info("进入知识点评论");
				 list =commentService.discussPoint(Integer.parseInt(bookId), Integer.parseInt(pointId));
			}
		}else{
			if(null==pointId||pointId.equals("")){
				return ResultObject.successMessage("知识点参数不能为空") ;
			}else{
				LOGGER.info("进入卡片评论");
			   list = commentService.discussCard(Integer.parseInt(bookId), Integer.parseInt(pointId), Integer.parseInt(cardId));
			}
		}
		
		if(list.size()>0){
			return ResultObject.successObject(list, null) ;	
		}else{
			return ResultObject.successMessage("没有评论数据");
		}
		
	}
	
	
	/**
	 * 查询练习本评论
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("discussBook")
	public Result<?> discussBook(HttpServletRequest request ,HttpServletResponse response,Integer bookId){
		
		if(null==bookId||bookId.equals("")){
			return ResultObject.successMessage("课程参数不能为空") ;
		}
		
		List<DiscussEntity> list = commentService.discussBook(bookId);
		if(list.size()>0){
			return ResultObject.successObject(list, null) ;	
		}else{
			return ResultObject.successMessage("没有评论数据");
		}
		
	}
	
	/**
	 * 查询知识点本评论
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("discussPoint")
	public Result<?> discussPoint(HttpServletRequest request ,HttpServletResponse response,Integer bookId,Integer pointId){
		
		if(null==bookId||bookId.equals("")){
			return ResultObject.successMessage("课程参数不能为空") ;
		}
		
		if(null==pointId||pointId.equals("")){
			return ResultObject.successMessage("知识点参数不能为空") ;
		}
		
		List<DiscussEntity> list = commentService.discussPoint(bookId, pointId);
		if(list.size()>0){
			return ResultObject.successObject(list, null) ;	
		}else{
			return ResultObject.successMessage("没有评论数据");
		}
	}
	
	/**
	 * 查询卡片评论
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("discussCard")
	public Result<?> discussCard(HttpServletRequest request ,HttpServletResponse response,Integer bookId,Integer pointId,Integer cardId){
		
		if(null==bookId||bookId.equals("")){
			return ResultObject.successMessage("课程参数不能为空") ;
		}
		
		if(null==pointId||pointId.equals("")){
			return ResultObject.successMessage("知识点参数不能为空") ;
		}
		
		if(null==cardId||cardId.equals("")){
			return ResultObject.successMessage("卡片参数不能为空") ;
		}
		
		List<DiscussEntity> list = commentService.discussCard(bookId, pointId, cardId);
		if(list.size()>0){
			return ResultObject.successObject(list, null) ;	
		}else{
			return ResultObject.successMessage("没有评论数据");
		}
	}

}
