package com.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.UserBookDao;
import com.demo.entity.UserBookEntity;

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
		userBookDao.addUserBook(entity);
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
	public void delUserBookBybooKId(Integer bookId){
		userBookDao.delUserBookByBookId(bookId);
	}
	
	/**
	 * 根据练习本ID ,用户ID 删除
	 * @param userId
	 * @param bookId
	 */
	public void delUserBook(Integer userId ,Integer bookId){
		userBookDao.delUserBook(userId, bookId);
	}
	
	/**
	 * 根据练习本ID 查找订阅该练习本的所有用户
	 * @param bookId
	 * @return
	 */
	public List<UserBookEntity> findUser(Integer bookId){	
		return userBookDao.findUser(bookId);
	}
	

}
