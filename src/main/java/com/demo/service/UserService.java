package com.demo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.UserDao;
import com.demo.entity.UserEntity;

@Service("userService")
public class UserService {
	
	@Autowired
	public UserDao userDao;
	
	public UserEntity findUserLogin(String userName ,String password){
		return userDao.findUserLogin(userName, password);
	}

	
	public void savaUser(UserEntity entity){
		entity.setRegisterTime(new Date());
		
	}
}
