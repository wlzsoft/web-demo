package com.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.UserBookDao;
import com.demo.dto.UserBookDto;
import com.pmp.entity.PointEntity;
import com.pmp.entity.UserBookEntity;

@Service
public class UserBookService {
	
	@Autowired
	private SystemService systemService ;
	
	@Autowired
	private UserBookDao userBookDao;
	
	/**
	 * 新增用户练习本关系
	 * @param entity
	 */
	public void addUserBook(UserBookEntity entity){
		entity.setCreateId(systemService.getCurrentUser().getId());
		entity.setCreateTime(new Date());
		entity.setSubTime(new Date());
		userBookDao.addUserBook(entity);
	}
	
	public void updateUserBook(UserBookEntity entity){
		Integer userId = systemService.getCurrentUser().getId();
		entity.setUserId(userId);
		entity.setUpdateId(systemService.getCurrentUser().getId());
		entity.setUpdateTime(new Date());
		userBookDao.updateUser_Book(entity);
	}
	
	
	
	/**
	 * 根据id删除
	 * @param id
	 */
	public void delUserBook(Integer id){
		userBookDao.delUserBookById(id);
	}
	
	/**
	 * 根据练习本ID 删除
	 * @param bookId
	 */
	public int delUserBookBybooKId(Integer bookId){
		return userBookDao.delUserBookByBookId(bookId);
	}
	
	/**
	 * 根据练习本ID ,用户ID 删除
	 * @param userId
	 * @param bookId
	 */
	public int delUserBook(Integer userId ,Integer bookId){
		return userBookDao.delUserBook(userId, bookId);
	}
	
	/**
	 * 根据练习本ID 查找订阅该练习本的所有用户
	 * @param bookId
	 * @return
	 */
	public List<UserBookEntity> findUser(Integer bookId){	
		return userBookDao.findUser(bookId);
	}
	
	/**
	 * 根据练习本ID ,用户ID 查询信息
	 * @param userId
	 * @param bookId
	 */
	public List<UserBookEntity> findUser_userId_bookId(Integer userId ,Integer bookId){
		return userBookDao.findUser_userId_bookId(userId, bookId);
	}
	

	/**
	 * 根据练习本ID ,用户ID 查询信息
	 * @param userId
	 * @param bookId
	 */
	public UserBookDto findUserBook(Integer userId ,Integer bookId){
		return userBookDao.findUserBook(userId, bookId);
	}
	
	//查询用户所订阅的所有练习本
	public List<UserBookEntity> findUserBookList(Integer userId){
		return userBookDao.findUserBookList(userId);
	}
	//查询用户所订阅的所有练习本下所有知识点
	public List<PointEntity> findUserPointList(Integer userId){
		return userBookDao.findUserPointList(userId);
	}
	
	
}
