package com.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pmp.entity.DeviceEntity;
import com.pmp.entity.SystemInfoEntity;

@Service
public class SysVersionServier {
	
	/**
	 * 
	 * 上传APP升级软件包
	 * @param entity
	 * @return
	 */
	@RequestMapping("/uploadApp")
	public void uploadApp(SystemInfoEntity entity){
		
	}
	
	
	/**
	 * 
	 * 下载APP升级软件包
	 * @param entity
	 * @return
	 */
	@RequestMapping("/downloadApp")
	public String downloadApp(SystemInfoEntity entity){
		
		return null ;
	}
	
	
	/**
	 * 加入设备型号
	 * @param entity
	 * @return
	 */
	@RequestMapping("/addDiscuss")
	public void addDiscuss(DeviceEntity entity){
		
	}

}
