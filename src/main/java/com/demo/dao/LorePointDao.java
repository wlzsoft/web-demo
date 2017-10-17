package com.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.demo.dto.PointExerciseDetailDto;
import com.demo.dto.PonitDto;
import com.demo.entity.LorePointEntity;
import com.demo.entity.LorePointExerciseDetailEntity;

@Repository
public interface LorePointDao {
	
	public Integer install(LorePointEntity entity);
	
	public Integer batchAdd(@Param("entityList")List<LorePointExerciseDetailEntity> entityList);
	
	public int dellById(@Param("id")Integer id);
	
	public int update(LorePointEntity entity);

	public int batchUpdate(@Param("pointIdArray")Integer[] pointIdArray,@Param("chapterId") Integer chapterId);
	
	public PonitDto findById(@Param("id")Integer id);
	
	public LorePointEntity findLorePoint(Integer id);
	
	public List<PonitDto> searchAllLorePoint();
	
	/**
	 * 根据知识点ID 获取知识点练习详情
	 * @param id 知识点Id
	 * @return
	 */
	public PointExerciseDetailDto findPointIdByDetail(@Param("id")Integer id);
	

	/**
	 * 根据知识点ID 获取知识点练习详情
	 * @param pointId
	 * @param userId
	 * @return
	 */
	public PointExerciseDetailDto pointIdByDetail(@Param("pointId")Integer pointId,@Param("userId")Integer userId);
	
	public void addPointDetail(LorePointExerciseDetailEntity entity);
	
	/**
	 * 根据知识点ID ,练习本Id 新增【推送】到订阅用户
	 * @param entityList
	 */
	public void addPointDetailList(@Param("entityList")List<LorePointExerciseDetailEntity> entityList);
	
	public List<PonitDto> roundPoint(@Param("userId")Integer userId);
	
	/**
	 * 根据知识点ID ,练习本Id 删除【推送】到订阅用户的练习明细
	 * @param pointId
	 * @param bookId
	 */
	public void delPoinDetailtByPointId(@Param("pointId")Integer pointId);
	
	/**
	 * 根据练习本Id 删除【推送】到订阅用户的练习明细
	 * @param pointId
	 * @param bookId
	 */
	public void delPoinDetailtByBookId(@Param("bookId")Integer bookId);
	
	/**
	 * 根据练习本Id 删除知识点
	 * @param bookId
	 */
	public void delPointByBookId(@Param("bookId")Integer bookId);
	
	
	/**
	 * 更新知识点章节的序号
	 * @param bookId
	 * @param pointId
	 * @param chapterId
	 * @param chapterSort
	 */
	public void updatePointChapterSort(@Param("bookId")Integer bookId ,@Param("chapterId")Integer chapterId ,@Param("chapterSort")Integer chapterSort);
	
	public void updateDelPointChapterSort(@Param("bookId")Integer bookId ,@Param("chapterIds")Integer[] chapterIds );
	
	public void updateDelPointChapterSortAll(@Param("bookId")Integer bookId );
	
	
	
}
