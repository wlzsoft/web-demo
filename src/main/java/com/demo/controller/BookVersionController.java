package com.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.service.BookVersionService;
import com.pmp.entity.BookBranchEntity;
import com.smartframe.dto.Result;
import com.smartframe.dto.ResultObject;

@Controller
@RequestMapping("/bookVer")
public class BookVersionController {
	
	@Autowired
	public BookVersionService bookVersionService ;
	/**
	 * 练习版本提交审核
	 * @param request
	 * @param response
	 * @param bookId
	 * @return
	 */
	@RequestMapping("/commit")
	public Result<?> commitBookVer(HttpServletRequest request ,HttpServletResponse response,Integer bookId){
		BookBranchEntity entity = bookVersionService.findBookBranch(bookId);
		if(null==entity){
			bookVersionService.commitBook(bookId);
		}else{
			if(entity.getStatus()==1){
				return ResultObject.warnMessage("有版本提交待审核状态，不能继续提交！");
			}else if(entity.getStatus()==2){
				return ResultObject.warnMessage("有版本提交审核中状态，不能继续提交！");
			}
		}
		return ResultObject.successMessage("提交成功");
	}
	
	
	@RequestMapping("/getBookVer")
	public Result<?> getBookVer(HttpServletRequest request ,HttpServletResponse response,Integer bookId){
		
		return null;
	}
	
	
	/**版本审核
	 * @param request
	 * @param response
	 * @param bookId
	 * @param status
	 * @return
	 */
	@RequestMapping("/auditorBook")
	public Result<?> auditorBook(HttpServletRequest request ,HttpServletResponse response,Integer bookId,Integer status){
		bookVersionService.auditorBook(bookId, status);
		return ResultObject.successMessage("审核成功");
	}

}
