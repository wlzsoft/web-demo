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
	 * 修改章节信息
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	public int editChapter(ChapterEntity entity);
	
	/**
	 * 
	 * 方法用途: 删除章节信息
	 * 操作步骤: TODO<br>
	 * @param chapterIds
	 * @return
	 */
	public int delChapter(@Param("chapterIds")Integer[] chapterIds,@Param("bookId")Integer bookId);
	
	
	
	public void updateChapterId(@Param("chapterIds")Integer[] chapterIds);
	
	/**
	 * 根据章节Id 查询章节下面所有知识点
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	public List<PonitDto> findChapterPoint(@Param("chapterId")Integer chapterId,@Param("bookId")Integer bookId);
	
	/**
	 * 根据章节Ids 查询章节下面所有知识点
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	public List<PonitDto> findChapterPointByIds(@Param("chapterIds")Integer[] chapterId,@Param("bookId")Integer bookId);
	
	
	
	/**
	 * 根据练习本ID，查询练习本下面所有章节
	 * @param bookId
	 * @return
	 */
	public List<ChapterEntity> bookChapterList(@Param("bookId")Integer bookId);
	
	
	public List<ChapterEntity> findChapterSort(@Param("parentId")Integer chapterParentId,@Param("bookId")Integer bookId ,@Param("nextSort")Integer nextSort);
	
	public void upateChapterSort(ChapterEntity entity);
	
	public List<ChapterEntity> findChapterById(@Param("chapterIds")Integer[] chapterIds);
	
	
}
