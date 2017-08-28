package com.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.dto.PonitDto;
import com.demo.entity.ChapterEntity;
import com.demo.service.ChapterService;
import com.smartframe.dto.Result;
import com.smartframe.dto.ResultObject;

@Controller
@RequestMapping("/chapter")
public class ChapterController {
	
	@Autowired
	private ChapterService chapterService;
	
	/**
	 * 新增章节信息
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	@RequestMapping("/addChapter")
	public Result<?> addChapter(HttpServletRequest request ,HttpServletResponse response,ChapterEntity entity){
		chapterService.addChapter(entity);
		return ResultObject.successMessage("保存成功") ;
	
	}
	
	/**
	 * 删除章节信息
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	@RequestMapping("/delChapter")
	public Result<?> delChapter(HttpServletRequest request ,HttpServletResponse response,String chapterId){
		chapterService.delChapter(Integer.parseInt(chapterId));
		return ResultObject.successMessage("删除成功") ;
	}
	
	/**
	 * 修改章节信息
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	@RequestMapping("/editChapter")
	public Result<?> editChapter(HttpServletRequest request ,HttpServletResponse response,ChapterEntity entity){
		chapterService.editChapter(entity);
		return ResultObject.successMessage("修改成功") ;
	
	}
	
	
	/**
	 * 根据章节Id 查询章节下面所有知识点
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	@RequestMapping("/findChapterPoint")
	public Result<?> findChapterPoint(HttpServletRequest request ,HttpServletResponse response,String chapterId){
		List<PonitDto> list = chapterService.findChapterPoint(Integer.parseInt(chapterId));
		return ResultObject.successObject(list);
	
	}

}
