package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.ImageResDao;
import com.demo.entity.ImageResEntity;

@Service("imageResService")
public class ImageResService {
	
	@Autowired
	private ImageResDao imageResDao;
	
   public ImageResEntity getImageRes(String mdsKey){
	   
	   return imageResDao.getImageRes(mdsKey) ;
   }
   
   public void addImageRes(ImageResEntity entity){
	   imageResDao.addImageRes(entity);
   }

}
