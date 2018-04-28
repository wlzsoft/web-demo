package com.demo.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.UserDao;
import com.demo.dto.UserDto;
import com.pmp.entity.BookEntity;
import com.pmp.entity.UserEntity;
import com.pmp.entity.UserExerciseDetailEntity;
import com.smartframe.basics.util.DES;

@Service("userService")
public class UserService {
	
	@Autowired
	public UserDao userDao;
	
	public UserDto findUserLogin(String userName ,String password){
		try {
			password = DES.DESAndBase64Encrypt(password, "w#_L9~za", "UTF-8");//DES加密处理 
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return userDao.findUserLogin(userName, password);
	}
	
	public Integer savaUser(UserEntity entity){
		entity.setUsername(entity.getNickName());
		entity.setRegisterTime(new Date());
		userDao.savaUser(entity);
		return entity.getId();
	}
	
	public void editUser(UserEntity entity){
		entity.setUpdateTime(new Date());
		userDao.editUser(entity);
	}
	
	public UserEntity getUserById(Integer userId){
		return userDao.getUserById(userId);
	}
	
	public void updateLasterLoginTime(UserDto entity){
		entity.setLastloginTime(new Date());
		userDao.updateLasterLoginTime(entity);
	}
	
	public List<UserExerciseDetailEntity> findUserExerciseDetailById(Integer userId){
		return userDao.findUserExerciseDetailById(userId);
	}
	
	public List<BookEntity> findExerciseById(Integer userId){
		return userDao.findExerciseById(userId);
	}
	
	/**
	 * 根据用户名查询用户信息
	 * @param username
	 * @return
	 */
	public List<UserEntity>  getUserByName(String username){
		return userDao.getUserByName(username);
	}
	
	
	public void updatePassword(Integer userId ,String password){
		try {
			password = DES.DESAndBase64Encrypt(password, "w#_L9~za", "UTF-8");//DES加密处理 
			Map<String, Object> map = new HashMap<String, Object>();
				map.put("userId", userId);
				map.put("password", password);
			userDao.updatePassword(map);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	/**根据OpenId查询用户
	 * @param openId
	 * @return
	 */
	public UserEntity getUserByopenId(String openId){
		return userDao.getUserByopenId(openId);
	}
}
