package com.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.demo.dto.UserBookDto;
import com.pmp.entity.PointEntity;
import com.pmp.entity.UserBookEntity;

@Repository
public interface UserBookDao {
	

	public void updateUserBook(Map<String, Object> map);
	
	public void updateUser_Book(UserBookEntity entity);
	
	/**
	 * 新增用户练习本关系
	 * @param entity
	 */
	public void addUserBook(UserBookEntity entity);
	
	/**
	 * 根据id删除
	 * @param id
	 */
	public void delUserBookById(@Param("id")Integer id);
	
	/**
	 * 根据练习本ID 删除
	 * @param bookId
	 */
	public int delUserBookByBookId(@Param("bookId")Integer bookId);
	
	/**
	 * 更加练习本ID 用户ID 删除
	 * @param userId
	 * @param bookId
	 */
	public int delUserBook(@Param("userId")Integer userId ,@Param("bookId")Integer bookId);
	
	/**
	 * 根据练习本ID查询  订阅该练习本的所有用户
	 * @param bookId
	 * @return
	 */
	public List<UserBookEntity> findUser(@Param("bookId")Integer bookId);
	
	
	/**
	 * 根据练习本ID ,用户ID 查询信息
	 * @param userId
	 * @param bookId
	 */
	public List<UserBookEntity> findUser_userId_bookId(@Param("userId")Integer userId ,@Param("bookId")Integer bookId);
	
	/**
	 * 根据练习本ID ,用户ID 查询信息
	 * @param userId
	 * @param bookId
	 */
	public UserBookDto findUserBook(@Param("userId")Integer userId ,@Param("bookId")Integer bookId);
	
	
	//查询用户所订阅的所有练习本
	public List<UserBookEntity> findUserBookList(@Param("userId")Integer userId);
	
	//查询用户所订阅的所有练习本下所有知识点
	public List<PointEntity> findUserPointList(@Param("userId")Integer userId);
	

}
