package com.demo.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.CommentDao;
import com.demo.dto.IdEntity;
import com.demo.entity.DiscussEntity;

@Service
public class CommentService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CommentService.class);
	
	@Autowired
	private CommentDao commentDao;
	
	@Autowired
	private SystemService systemService ;
	
	/**
	 * 新增评论
	 * @param entity
	 * @return
	 */
	public IdEntity addDiscuss(DiscussEntity entity){
		Integer userId = systemService.getCurrentUser().getId();
		String username = systemService.getCurrentUser().getUsername();
		entity.setCreateTime(new Date());
		entity.setUserId(userId);
		entity.setUsername(username);
		commentDao.addDiscuss(entity);
		
		IdEntity identity = new IdEntity();	
		identity.setId(entity.getId());
		return identity;
	}
	
	/**
	 * 删除评论
	 * @param discussId
	 * @return
	 */
	public int delDiscuss(Integer discussId){
		Integer userId = systemService.getCurrentUser().getId();
		int con = commentDao.delDiscuss(discussId,userId);
		return con;
	}
	
	
	/**
	 * 查询练习本评论
	 * @param bookId
	 * @return
	 */
	public List<DiscussEntity> discussBook(Integer bookId){
		List<DiscussEntity> list = commentDao.discussBook(bookId) ;
		return list ;
	}
	
	/**
	 * 查询知识点本评论
	 * @param bookId
	 * @param pointId
	 * @return
	 */
	public List<DiscussEntity> discussPoint(Integer bookId,Integer pointId){
		List<DiscussEntity> list = commentDao.discussPoint(bookId, pointId);
		return list ;
	}

	
	/**
	 * 查询卡片评论
	 * @param bookId
	 * @param pointId
	 * @param cardId
	 * @return
	 */
	public List<DiscussEntity> discussCard(Integer bookId,Integer pointId,Integer cardId){
		List<DiscussEntity> list = commentDao.discussCard(bookId, pointId, cardId);
		return list ;
	}
	
	/**
	 * 根据评论Id 查询 评论信息
	 * @param discussId
	 * @return
	 */
	public DiscussEntity findDiscuss(Integer discussId){
		DiscussEntity entity = commentDao.findDiscuss(discussId);
		return entity;
	}
}
