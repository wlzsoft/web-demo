package com.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.demo.entity.DiscussEntity;

@Repository
public interface CommentDao {
	
	
	/**
	 * 新增评论
	 * @return
	 */
	public void addDiscuss(DiscussEntity entity);
	
	/**删除评论
	 */
	public int delDiscuss(@Param("discussId")Integer discussId,@Param("userId")Integer userId);
	
	/**
	 * 查询练习本评论
	 * @return
	 */
	public List<DiscussEntity> discussBook(@Param("bookId")Integer bookId);
	
	/**
	 * 查询知识点本评论
	 * @return
	 */
	public List<DiscussEntity> discussPoint(@Param("bookId")Integer bookId,@Param("pointId")Integer pointId);

	
	/**
	 * 查询卡片评论
	 * @return
	 */
	public List<DiscussEntity> discussCard(@Param("bookId")Integer bookId,@Param("pointId")Integer pointId,@Param("cardId")Integer cardId);
	
	
	/**
	 * 根据评论Id 查询 评论信息
	 * @param discussId
	 * @return
	 */
	public DiscussEntity findDiscuss(@Param("discussId")Integer discussId);

}
