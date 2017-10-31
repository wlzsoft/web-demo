package com.demo.dao;

import java.util.List;

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
	public int delDiscuss(Integer discussId,Integer userId);
	
	/**
	 * 查询练习本评论
	 * @return
	 */
	public List<DiscussEntity> discussBook(Integer bookId);
	
	/**
	 * 查询知识点本评论
	 * @return
	 */
	public List<DiscussEntity> discussPoint(Integer bookId,Integer pointId);

	
	/**
	 * 查询卡片评论
	 * @return
	 */
	public List<DiscussEntity> discussCard(Integer bookId,Integer pointId,Integer cardId);
	
	
	/**
	 * 根据评论Id 查询 评论信息
	 * @param discussId
	 * @return
	 */
	public DiscussEntity findDiscuss(Integer discussId);

}
