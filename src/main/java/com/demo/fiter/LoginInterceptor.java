package com.demo.fiter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.demo.entity.UserEntity;

public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		//获取请求的URL 
		String url = request.getRequestURI(); 
		if(url.indexOf("/login")>=0){ 
		    return true;    
		}
		HttpSession session = request.getSession(); 
		UserEntity userCur = (UserEntity)session.getAttribute("userCur"); 
		if(userCur != null){
		  return true; 
		}
		response.sendRedirect("https://www.baidu.com");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
	
	

}
