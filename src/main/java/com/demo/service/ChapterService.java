package com.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.ChapterDao;
import com.demo.dto.IdEntity;
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
	public IdEntity addChapter(ChapterEntity entity){
		chapterDao.addChapter(entity);
		IdEntity identity = new IdEntity();	
		identity.setId(entity.getId());
	  return identity;
	}
	
	/**
	 * 删除章节信息
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	public int delChapter(Integer chapterId){
		int count = chapterDao.delChapter(chapterId);
		System.out.println("================="+count);
		//更新章节下面的知识点，把知识点的对应的章节ID 设置为null
		return count;
	}
	
	/**
	 * 修改章节信息
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	public int editChapter(ChapterEntity entity){
		entity.setUpdateId(systemService.getCurrentUser().getId());
		entity.setUpdateTime(new Date());
		int count = chapterDao.editChapter(entity);
		return count;
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
