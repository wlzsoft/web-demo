package com.demo.fiter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.smartframe.rediscache.dao.RedisStringDao;


public class WxInterceptor implements HandlerInterceptor{

	@Autowired
    private RedisStringDao redisStringDao;
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		//获取请求的URL 
		String url = request.getRequestURI(); 
		System.out.println("请求地址："+url);
		if(url.indexOf("/wxlogin")>=0){ 
		    return true;    
		}
		String  rdSessionKey= request.getParameter("sessionid"); 
		String openId = (String)redisStringDao.get(rdSessionKey);
		System.out.println("WX登录拦截到openId:"+openId);
		if(null!=openId||!"".equals(openId)){
		  return true; 
		}
		response.setStatus(401);
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
