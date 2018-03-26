package com.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.service.SysVersionServier;
import com.pmp.entity.DeviceEntity;
import com.pmp.entity.SystemInfoEntity;
import com.smartframe.dto.Result;
import com.smartframe.dto.ResultObject;

@Controller
@RequestMapping("/ver")
public class SysVersionController {
	
	@Autowired
	private SysVersionServier sysVersionServier;
	
	/**
	 * 
	 * 上传APP升级软件包
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	@RequestMapping("/uploadApp")
	public Result<?> uploadApp(HttpServletRequest request ,HttpServletResponse response,SystemInfoEntity entity){
		
		if(null==entity.getPackageName()||entity.getPackageName().equals("")){
			return ResultObject.warnMessage("应用包名不能为空");
		}
		
		if(null==entity.getVersionNum()||entity.getVersionNum().equals("")){
			return ResultObject.warnMessage("应用版本号不能为空");
		}
		
		if(null==entity.getType()||entity.getType().equals("")){
			return ResultObject.warnMessage("更新类型不能为空");
		}
		
		if(null==entity.getSeqNum()||entity.getSeqNum().equals("")){
			return ResultObject.warnMessage("应用版本序号不能为空");
		}
		
		sysVersionServier.uploadApp(entity);
	    return ResultObject.successMessage("上传成功");
	}
	
	
	/**
	 * 
	 * 下载APP升级软件包
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	@RequestMapping("/downloadApp")
	public Result<?> downloadApp(HttpServletRequest request ,HttpServletResponse response,SystemInfoEntity entity){
		
		if(null==entity.getPackageName()||entity.getPackageName().equals("")){
			return ResultObject.warnMessage("应用包名不能为空");
		}
		
		if(null==entity.getSeqNum()||entity.getSeqNum().equals("")){
			return ResultObject.warnMessage("应用版本序号不能为空");
		}
		
		String url = sysVersionServier.downloadApp(entity);
		return ResultObject.successObject(url, "成功");
	}
	
	
	/**
	 * 加入设备型号
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	@RequestMapping("/addDiscuss")
	public Result<?> addDiscuss(HttpServletRequest request ,HttpServletResponse response,DeviceEntity entity){
		
		if(null==entity.getDisplayId()||entity.getDisplayId().equals("")){
			return ResultObject.warnMessage("软件版本号不能为空");
		}
		
		if(null==entity.getManufacturer()||entity.getManufacturer().equals("")){
			return ResultObject.warnMessage("厂商不能为空");
		}
		
		if(null==entity.getProducModel()||entity.getProducModel().equals("")){
			return ResultObject.warnMessage("产品型号不能为空");
		}
		
		sysVersionServier.addDiscuss(entity);
		
		return ResultObject.successMessage("新增成功");
	}
	

}
