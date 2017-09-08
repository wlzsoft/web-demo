package com.demo.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.demo.dto.UserDto;

@Service
public class SystemService  {
//	@Resource
	private HttpServletRequest request;
	
	/**
	 * 
	 * 方法用途: 获取当前用户信息--自动获取注入的当前请求<br>
	 * 操作步骤: TODO<br>
	 * @return
	 */
	public UserDto getCurrentUser() {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		if(ra != null) {
			request = ((ServletRequestAttributes)ra).getRequest(); 
		}
		return getCurrentUser(request);
	}
	
	
	/**
	 * 
	 * 方法用途: 获取当前用户信息-无法注入request的场景使用<br>
	 * 操作步骤: TODO<br>
	 * @param request
	 * @return
	 */
	public UserDto getCurrentUser(HttpServletRequest request) {
		HttpSession  session = request.getSession();
		if(session == null) {
			return null;
		}
		//UserEntity  user = (UserEntity)session.getAttribute(session.getId());
		UserDto  user = (UserDto)session.getAttribute("userCur");
		return user;
	}
	
	
	/**
	 * 
	 * 方法用途: 获取当前用户信息--自动获取注入的当前请求<br>
	 * 操作步骤: 如果用户未登录，或抛出未登录异常信息，中断请求并返回<br>
	 * @return
	 * @throws Exception 
	 */
	public UserDto getCurrentUserForInterrupt() throws Exception {
		UserDto user = getCurrentUser();
		 if(user == null) {
			 throw new Exception("用户未登录");
		 }
		 return user;
	}

	
	
	/**
	 * 
	 * 方法用途: 获取当前用户信息-无法注入request的场景使用<br>
	 * 操作步骤: TODO<br>
	 * @return
	 */
	public String getInterfaceUserName() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
		return  (String) request.getAttribute("userName");
	}
}
