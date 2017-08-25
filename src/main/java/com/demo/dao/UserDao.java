package com.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.demo.entity.ExcerciseBookEntity;
import com.demo.entity.UserEntity;
import com.demo.entity.UserExerciseDetailEntity;

@Repository
public interface UserDao {
	
	 public UserEntity findUserLogin(@Param("username")String username ,@Param("password")String password);
	 
	 public void savaUser(UserEntity entity);
	 
	 public List<UserExerciseDetailEntity> findUserExerciseDetailById(@Param("userId")Integer userId);
	 
	 public List<ExcerciseBookEntity> findExerciseById(@Param("userId")Integer userId);

}
