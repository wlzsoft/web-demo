package com.demo.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.demo.entity.UserEntity;

@Repository
public interface UserDao {
	
	 public UserEntity findUserLogin(@Param("userName")String userName ,@Param("password")String password);

}
