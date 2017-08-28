package com.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.demo.dto.PonitDto;
import com.demo.entity.ChapterEntity;


@Repository
public interface ChapterDao {
	
	/**
	 * 新增章节信息
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	public void addChapter(ChapterEntity entity);
	
	/**
	 * 删除章节信息
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	public void delChapter(@Param("chapterId")Integer chapterId);
	/**
	 * 修改章节信息
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	public void editChapter(ChapterEntity entity);
	
	/**
	 * 根据章节Id 查询章节下面所有知识点
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	public List<PonitDto> findChapterPoint(@Param("chapterId")Integer chapterId);

}
