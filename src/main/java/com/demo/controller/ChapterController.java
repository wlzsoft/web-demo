package com.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.dto.IdEntity;
import com.demo.dto.PonitDto;
import com.demo.entity.ChapterEntity;
import com.demo.service.ChapterService;
import com.demo.service.ExcerciseBookService;
import com.smartframe.dto.Result;
import com.smartframe.dto.ResultObject;

@Controller
@RequestMapping("/chapter")
public class ChapterController {
	
	@Autowired
	private ChapterService chapterService;
	
	
	@Autowired
	private ExcerciseBookService excerciseService ;	
	
	/**
	 * 新增章节信息
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	@RequestMapping("/addChapter")
	public Result<?> addChapter(HttpServletRequest request ,HttpServletResponse response,ChapterEntity entity){
		
		if(null==entity.getName()||entity.getName().equals("")){
			return ResultObject.warnMessage("章节名称不能为空");
		}
		
		if(null==entity.getBookId()||entity.getBookId().equals("")){
			return ResultObject.warnMessage("章节所属练习本ID不能为空");
		}
		
		IdEntity identity = chapterService.addChapter(entity);
		return ResultObject.successObject(identity,"保存成功");
	
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
		if(null==chapterId||chapterId.equals("")){
			return ResultObject.warnMessage("参数不能为空");
		}
		int count = chapterService.delChapter(Integer.parseInt(chapterId));
		if(count==0){
			return ResultObject.successMessage("无操作数据");
		}
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
		if(null==entity.getId()||entity.getId().equals("")){
			return ResultObject.warnMessage("主键ID不能为空");
		}else{
			int count = chapterService.editChapter(entity);
			if(count==0){
				return ResultObject.successMessage("无操作数据");
			}
			return ResultObject.successMessage("修改成功") ;	
		}
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
		if(null==chapterId||chapterId.equals("")){
			return ResultObject.warnMessage("参数不能为空");
		}
		List<PonitDto> list = chapterService.findChapterPoint(Integer.parseInt(chapterId));
		return ResultObject.successObject(list,null);
	
	}
	
	
	/**
	 * 根据练习本ID ， 查询该节点下所有章节信息
	 * @param request
	 * @param response
	 * @param excerciseId
	 * @return
	 */
	@RequestMapping("/bookChapterList")
	public Result<?> bookChapterList(HttpServletRequest request ,HttpServletResponse response,String bookId){
		if(null==bookId||bookId.equals("")){
			return ResultObject.warnMessage("参数不能为空");
		}
		List<ChapterEntity> entityList = excerciseService.bookChapterList(Integer.parseInt(bookId));
		return ResultObject.successObject(entityList,null); 
	}
	

}
