package com.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.UserDao;
import com.demo.entity.ExcerciseBookEntity;
import com.demo.entity.UserEntity;
import com.demo.entity.UserExerciseDetailEntity;

@Service("userService")
public class UserService {
	
	@Autowired
	public UserDao userDao;
	
	public UserEntity findUserLogin(String userName ,String password){
		return userDao.findUserLogin(userName, password);
	}
	
	public void savaUser(UserEntity entity){
		entity.setRegisterTime(new Date());
		userDao.savaUser(entity);
	}
	
	public List<UserExerciseDetailEntity> findUserExerciseDetailById(Integer userId){
		return userDao.findUserExerciseDetailById(userId);
	}
	
	public List<ExcerciseBookEntity> findExerciseById(Integer userId){
		return userDao.findExerciseById(userId);
	}
}
