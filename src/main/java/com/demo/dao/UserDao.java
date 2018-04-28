package com.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.demo.dto.UserDto;
import com.pmp.entity.BookEntity;
import com.pmp.entity.UserEntity;
import com.pmp.entity.UserExerciseDetailEntity;

@Repository
public interface UserDao {
	
	 public UserDto findUserLogin(@Param("username")String username ,@Param("password")String password);
	 
	 public void savaUser(UserEntity entity);
	 
	 public void editUser(UserEntity entity);
	 
	 public void updateLasterLoginTime(UserDto entity);
	 
	 public UserEntity getUserById(@Param("userId")Integer userId);
	 
	 public List<UserExerciseDetailEntity> findUserExerciseDetailById(@Param("userId")Integer userId);
	 
	 public List<BookEntity> findExerciseById(@Param("userId")Integer userId);
	 
	 public  List<UserEntity> getUserByName(@Param("username")String userName);
	 
	 public void updatePassword(Map<String, Object> map);
	 
	 public UserEntity getUserByopenId(@Param("openId")String openId);
	 
	 

}
