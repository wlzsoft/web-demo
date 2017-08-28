package com.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.ChapterDao;
import com.demo.dto.PonitDto;
import com.demo.entity.ChapterEntity;

@Service
public class ChapterService {
	
	@Autowired
	private SystemService systemService ;
	
	@Autowired
	private ChapterDao chapterDao;
	
	/**
	 * 新增章节信息
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	public void addChapter(ChapterEntity entity){
		chapterDao.addChapter(entity);
	
	}
	
	/**
	 * 删除章节信息
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	public void delChapter(Integer chapterId){
		chapterDao.delChapter(chapterId);
	}
	
	/**
	 * 修改章节信息
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	public void editChapter(ChapterEntity entity){
		entity.setUpdateId(systemService.getCurrentUser().getId());
		entity.setUpdateTime(new Date());
		chapterDao.editChapter(entity);
	}
	
	
	/**
	 * 根据章节Id 查询章节下面所有知识点
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	public List<PonitDto> findChapterPoint(Integer chapterId){
		return chapterDao.findChapterPoint(chapterId);
	
	}

}
