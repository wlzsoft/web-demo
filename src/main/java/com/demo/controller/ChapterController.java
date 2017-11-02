package com.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.dto.ChapterDto;
import com.demo.dto.IdEntity;
import com.demo.entity.ChapterEntity;
import com.demo.entity.ExcerciseBookEntity;
import com.demo.entity.UserBookEntity;
import com.demo.service.ChapterService;
import com.demo.service.ExcerciseBookService;
import com.demo.service.SystemService;
import com.demo.service.UserBookService;
import com.smartframe.dto.Result;
import com.smartframe.dto.ResultObject;

@Controller
@RequestMapping("/chapter")
public class ChapterController {
	
	@Autowired
	private ChapterService chapterService;
	
	
	@Autowired
	private ExcerciseBookService excerciseService ;	
	
	@Autowired
	private SystemService systemService ;
	
	@Autowired
	private UserBookService userBookService;
	
	/**
	 * 新增 修改 章节信息
	 * @param request
	 * @param response
	 * @param entity
	 * @param chapterSort 序号
	 * @return
	 */	 
	@RequestMapping("/saveChapter")
	public Result<?> saveChapter(HttpServletRequest request ,HttpServletResponse response,ChapterEntity entity,String chapterSorts){
		
		if(null!=entity.getChapterJson()||!entity.getChapterJson().equals("")){
			 if(entity.getChapterJson().length()>10){
				 if(null==chapterSorts||chapterSorts.equals("")){
					 return ResultObject.warnMessage("章节ID不能为空");
				 }
			 }
		}
		
		if(null==entity.getBookId()||entity.getBookId().equals("")){
			return ResultObject.warnMessage("练习本ID不能为空");
		}
		
		/**
		 * 加操作权限
		 * */
		ExcerciseBookEntity bookBntity = excerciseService.findBook(entity.getBookId().toString());
		if(null==bookBntity){
			return ResultObject.warnMessage("无操作权限");	
		}
		Integer userId = systemService.getCurrentUser().getId();
		if(userId!=bookBntity.getCreateId()){
			return ResultObject.warnMessage("无操作权限");
		}else{
			ChapterDto chapterDto = chapterService.bookChapterList(entity.getBookId());//判断bookId 在章节表中是否存在
			
			if(null==chapterDto){
				IdEntity identity = chapterService.addChapter(entity);
				return ResultObject.successObject(identity,"新增成功");
			}else{
				if(null==chapterSorts||chapterSorts.equals("")){
					chapterService.editChapter(entity);
					chapterService.updateChapterSort( entity.getBookId());	
				}else{
					String[] chapterIds = chapterSorts.split(",");
					if(chapterIds.length>0){
						chapterService.editChapter(entity);
						chapterService.updateChapterSort(chapterIds, entity.getBookId());
					}else{
						if(entity.getChapterJson().length()<10){
							chapterService.updateChapterSort( entity.getBookId());	
						}
					}
				}
	
				return ResultObject.successMessage("修改成功") ;	
			}
		}
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
		
		if(null==entity){
			return ResultObject.successMessage("参数不能为空");
		}
		
		if(null==entity.getBookId()||entity.getBookId().equals("")){
			return ResultObject.warnMessage("章节所属练习本ID不能为空");
		}else{
			/**
			 * 加操作权限
			 * */
			ExcerciseBookEntity bookEntity = excerciseService.findBook(entity.getBookId().toString());
			
			if(null==bookEntity){
				return ResultObject.warnMessage("无操作权限");	
			}
			
			Integer userId = systemService.getCurrentUser().getId();
			if(userId!=bookEntity.getCreateId()){
				return ResultObject.warnMessage("无操作权限");
			}else{
				int count = chapterService.editChapter(entity);
				if(count==0){
					return ResultObject.successMessage("无操作数据");
				}
				return ResultObject.successMessage("修改成功") ;	
			}
			
		}
	}
	
	
	/**
	 * 删除章节信息
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	@RequestMapping("/delChapter")
	public Result<?> delChapter(HttpServletRequest request ,HttpServletResponse response,ChapterEntity entity){
		if(null==entity.getId()||entity.getId().equals("")){
			return ResultObject.warnMessage("主键ID不能为空");
		}else{
			
			/**
			 * 加操作权限
			 * */
			ExcerciseBookEntity bookEntity = excerciseService.findBook(entity.getId().toString());
			if(null==bookEntity){
				return ResultObject.warnMessage("无操作权限");	
			}
			Integer userId = systemService.getCurrentUser().getId();
			if(userId!=bookEntity.getCreateId()){
				return ResultObject.warnMessage("无操作权限");
			}else{
				int count = chapterService.editChapter(entity);
				if(count==0){
					return ResultObject.successMessage("无操作数据");
				}
				return ResultObject.successMessage("修改成功") ;
			}
		}
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
		
		/**
		 * 加操作权限
		 * */
		ExcerciseBookEntity BookEntity = excerciseService.findBook(bookId);
		if(null==BookEntity){
			return ResultObject.warnMessage("无操作权限");	
		}
		if(BookEntity.getSharedType()==0){
			Integer userId = systemService.getCurrentUser().getId();
 			List<UserBookEntity>  list = userBookService.findUser_userId_bookId(userId, Integer.parseInt(bookId));
 			if(list.size()==0){
 				return ResultObject.warnMessage("无操作权限");	
 			}
 			/*	if(userId!=entity.getCreateId()){
				return ResultObject.warnMessage("无操作权限");	
			}*/
			
		}
		
		
		ChapterDto entityList = chapterService.bookChapterList(Integer.parseInt(bookId));
		if(null==entityList){
			return ResultObject.successObject(null,null); 
		}else{
			return ResultObject.successObject(entityList.getChapterJson(),null); 
		}
	}
	

}
