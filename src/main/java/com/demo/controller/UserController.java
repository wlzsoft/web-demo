package com.demo.controller;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.entity.UserEntity;
import com.demo.entity.UserExerciseDetailEntity;
import com.demo.service.SystemService;
import com.demo.service.UserService;
import com.smartframe.basics.util.DES;
import com.smartframe.dto.Result;
import com.smartframe.dto.ResultObject;


@Controller
@RequestMapping("/user")	
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SystemService systemService ;
	
	/**
	 * 根据用户ID 获取用户练习流水记录信息
	 * @param request
	 * @param response
	 * @param model
	 * @param userId
	 * @return
	 */
	@RequestMapping("/findUserExerciseDetailById")
	public Result<List<UserExerciseDetailEntity>> findUserExerciseDetailById(HttpServletRequest request ,HttpServletResponse response){
		Integer userId =systemService.getCurrentUser().getId();
		List<UserExerciseDetailEntity> list = userService.findUserExerciseDetailById(userId);
		return ResultObject.successObject(list,null);
	}
	
	

	/**
	 * 修改用户信息
	 * @param request
	 * @param response
	 * @param userentity
	 * @return
	 */
	@RequestMapping("/editUser")
	public Result<?> editUser(HttpServletRequest request ,HttpServletResponse response,UserEntity userentity ){

		if(null!=userentity.getTelphone()&&!userentity.getTelphone().equals("")){
			if(userentity.getTelphone().length()>12){
				return ResultObject.warnMessage("电话号码异常");
			}
			if(!isNumeric(userentity.getTelphone())){
				return ResultObject.warnMessage("电话号码异常");
			}
		}
		
		if(null!=userentity.getPassword()&&!userentity.getPassword().equals("")){
			if(userentity.getPassword().length()>20){
				 return ResultObject.warnMessage("密码过长");
			}else {
				String desPassword =userentity.getPassword().replaceAll(" ","");
				try {
					desPassword = DES.DESAndBase64Encrypt(desPassword, "w#_L9~za", "UTF-8");//DES加密处理
					userentity.setPassword(desPassword);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		
		String username =  systemService.getCurrentUser().getUsername();
		
		if(null!=userentity.getUsername()&&!userentity.getUsername().equals("")){
			
		    if(userentity.getUsername().length()>20){
				   return ResultObject.warnMessage("用户名过长");
			}
		    
		    if(!username.equals(userentity.getUsername())){
				List<UserEntity> list = userService.getUserByName(userentity.getUsername().replaceAll(" ",""));
				if(list.size()>0){
					return ResultObject.warnMessage("用户名已经存在");
				}else{
					userentity.setUsername(userentity.getUsername().replaceAll(" ",""));
				}
		    }

		}
		
		Integer curId = systemService.getCurrentUser().getId();//获取当前登录用户信息
		
		userentity.setId(curId);
		
   /*	if(null==userentity.getId()||userentity.getId().equals("")){
			return ResultObject.warnMessage("修改用户信息异常");	
		}
		
		if(curId.intValue()!=userentity.getId().intValue()){
			return ResultObject.warnMessage("修改用户信息异常");
		}*/
		
		userService.editUser(userentity);
		
		return ResultObject.successMessage("修改成功") ;
	}
	
	
	
	public boolean isNumeric(String str){ 
		   Pattern pattern = Pattern.compile("[0-9]*"); 
		   Matcher isNum = pattern.matcher(str);
		   if( !isNum.matches() ){
		       return false; 
		   } 
		   return true; 
	}
}
