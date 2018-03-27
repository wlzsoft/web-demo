package com.demo.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.pmp.entity.ImageResEntity;

@Repository
public interface ImageResDao {

	 public ImageResEntity getImageRes(@Param("mdsKey")String mdsKey);
	   
	 public void addImageRes(ImageResEntity entity);
}
