package com.demo.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.dto.IdEntity;
import com.demo.entity.DiscussEntity;
import com.demo.service.CommentService;
import com.smartframe.basics.util.EmojiUtil;
import com.smartframe.dto.Result;
import com.smartframe.dto.ResultObject;

@Controller
@RequestMapping("/comm")
public class CommentController {
	
	@Autowired
	private CommentService commentService;	
	
	/**
	 * 新增评论
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("addDiscuss")
	public Result<?> addDiscuss(HttpServletRequest request ,HttpServletResponse response,DiscussEntity entity){
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
				return ResultObject.successMessage("评论内容不能为null") ;
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
	@RequestMapping("delDiscuss")
	public Result<?> delDiscuss(HttpServletRequest request ,HttpServletResponse response,Integer discussId){
		if(null==discussId||discussId.equals("")){
			return ResultObject.successMessage("参数不能为空") ;
		}
		
		 int con = commentService.delDiscuss(discussId);
		 if(con==0){
			 return ResultObject.successMessage("没有权限删除") ;  
		 }else{
			 return ResultObject.successMessage("删除成功") ; 
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
			return ResultObject.sucreMessage("没有评论数据");
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
			return ResultObject.sucreMessage("没有评论数据");
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
			return ResultObject.sucreMessage("没有评论数据");
		}
	}

}
