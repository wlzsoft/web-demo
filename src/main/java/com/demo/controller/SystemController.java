package com.demo.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.entity.UserEntity;
import com.demo.service.UserService;
import com.smartframe.basics.util.DES;
import com.smartframe.dto.Result;
import com.smartframe.dto.ResultObject;


@Controller
public class SystemController {
	
	@Autowired
	private UserService userService;

	
	@RequestMapping("/login")
	public Result<?> login(HttpServletRequest request ,HttpServletResponse response){
		
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		if(null==userName||userName.equals("")||null==password||password.equals("")){
			return ResultObject.sucreMessage("用户名或密码不能为空!") ;
		}
		UserEntity userCur = userService.findUserLogin(userName, password);//查询用户是否存现
		if(null == userCur){
			return ResultObject.sucreMessage("用户名或密码错误!") ;
		}else{
			userService.updateLasterLoginTime(userCur);
			request.getSession().setAttribute("userCur", userCur);
			return  ResultObject.successObject(userCur,"登录成功") ;
		}
		
	 }
	

	@RequestMapping("/logout")
	public Result<?> logout(HttpServletRequest request ,HttpServletResponse response){
		HttpSession session = request.getSession(false);//防止创建Session
		session = request.getSession(); 
		if(null == session){
			return ResultObject.successMessage("退出成功") ;	
		}else{
			request.getSession().removeAttribute("userCur");
			return ResultObject.successMessage("退出成功") ;
		}
	}
	
	@RequestMapping("/signup")
	public Result<?> savaUser(HttpServletRequest request ,HttpServletResponse response,UserEntity userentity ){
		if(null==userentity.getUsername()||userentity.getUsername().equals("")){
			return ResultObject.warnMessage("用户名不能为空");
		}else if(userentity.getUsername().length()>20){
			   return ResultObject.warnMessage("用户名过长");
		}
		
		if(null!=userentity.getTelphone()&&!userentity.getTelphone().equals("")){
			if(userentity.getTelphone().length()>12){
				return ResultObject.warnMessage("电话号码异常");
			}
			if(!isNumeric(userentity.getTelphone())){
				return ResultObject.warnMessage("电话号码异常");
			}
		}else if(userentity.getTelphone().equals("")){
			userentity.setTelphone(null);
		}
		
		if(null==userentity.getPassword()||userentity.getPassword().equals("")){
			return ResultObject.warnMessage("密码不能为空");
		}else if(userentity.getPassword().length()>20){
		   return ResultObject.warnMessage("密码过长");
		}
		
		List<UserEntity> list = userService.getUserByName(userentity.getUsername().replaceAll(" ",""));
		if(list.size()>0){
			return ResultObject.warnMessage("用户名已经存在");
		}
		
		userentity.setUsername(userentity.getUsername().replaceAll(" ",""));
		String desPassword =userentity.getPassword().replaceAll(" ","");
		try {
			desPassword = DES.DESAndBase64Encrypt(desPassword, "w#_L9~za", "UTF-8");//DES加密处理
			userentity.setPassword(desPassword);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		userService.savaUser(userentity);
		return ResultObject.successMessage("注册成功") ;
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
