package com.demo.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.demo.dto.UserDto;
import com.demo.service.UserService;
import com.demo.wx.WeChatAppLoginReq;
import com.pmp.entity.UserEntity;
import com.smartframe.basics.util.EmojiUtil;
import com.smartframe.dto.Result;
import com.smartframe.dto.ResultObject;
import com.smartframe.rediscache.dao.RedisStringDao;

@Controller
public class WxController {
	
	private static final Logger logger = LoggerFactory.getLogger(WxController.class);
	
	public static boolean initialized = false;
	
	//wlz
	private static final String APPID="wx5b3c6776c8d2028f";
	private static final String SECRET="fb59d323b7251efb2ab7a2929d54e771";
	
/*	private static final String APPID="wx618e74db9875dada";
	private static final String SECRET="58c0afffda3f84a205c060a2518ad123";*/
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private RedisStringDao redisStringDao;
	
	@RequestMapping("/wxlogin")
	public Result<?> login(HttpServletRequest request ,HttpServletResponse response,WeChatAppLoginReq req){
		if(null==req.getCode()||req.getCode().equals("")){
			return ResultObject.sucreMessage("参数不能为空");
		}else{
			JSONObject objret = new JSONObject();
			String JSCODE=req.getCode();
			logger.info("请求code:"+JSCODE);
			//String JSCODE="0034M26C1RCoa30dtd7C1CUh6C14M267";
			String url="https://api.weixin.qq.com/sns/jscode2session?appid="+APPID+"&secret="+SECRET+"&js_code="+JSCODE+"&grant_type=authorization_code";
			 RestTemplate restTemplate = new RestTemplate();  
		     ResponseEntity<String>  responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
		     if(responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
		    	    logger.info("调取WX接口成功");
		            String sessionData = responseEntity.getBody();  
		            logger.info("sessionData = "+ sessionData);  
		            JSONObject jsonObj = JSONObject.fromObject(sessionData);  
		            if(jsonObj.containsKey("errcode")){
		            	String errcode = jsonObj.get("errcode").toString();
		                logger.info("微信返回的错误码{}", errcode);
		            	return ResultObject.warnMessage(jsonObj.getString("errmsg"));
		            }else if(jsonObj.containsKey("session_key")){
		            	logger.info("调微信成功");
		            	String openId = jsonObj.getString("openid");  //获取openId
			            String session_key = jsonObj.getString("session_key");//获取session_key
			            
			            JSONObject jsonObject = JSONObject.fromObject(req.rawData);
			            
			            
			            UserEntity userInfo = (UserEntity)JSONObject.toBean(jsonObject, UserEntity.class);
						try{
							String nickName =  EmojiUtil.emojiConvert1(userInfo.getNickName());
							userInfo.setNickName(nickName);
						}catch (UnsupportedEncodingException e) {
							logger.error("知识点标题描述 emoji 表情符 转换异常");
							e.printStackTrace();
						}
			            
			            //先查询openId在数据库中存在不存在，存在不入库，不存在就入库
			            UserEntity userList = userService.getUserByopenId(openId);
			            UserDto dto = new UserDto();
			            if(null!=userList){
			            	dto.setId(userList.getId());
			            	logger.info("openId已经存在，不需要插入");
			            }else{
			            	logger.info("openId不存在，插入");
			            	userInfo.setOpenId(openId);
			            	Integer id  =userService.savaUser(userInfo);
			            	dto.setId(id);
			            }
			            
			            dto.setUsername(userInfo.getNickName());
			            dto.setAvatarUrl(userInfo.getAvatarUrl());
			            dto.setCity(userInfo.getCity());
			            dto.setCountry(userInfo.getCountry());
			            dto.setProvince(userInfo.getProvince());
			            dto.setNickName(userInfo.getNickName());
			            dto.setRegisterTime(new Date());
			          // 1、得到sessionkey以后存到缓存，key值采用不会重复的uuid
			            String rsession = UUID.randomUUID().toString();
			          //2、根据openId 获取老的sessionkey
			            String old_rsession =(String)redisStringDao.get(openId);
			            if(null!=old_rsession||!old_rsession.equals("")){
			            	// 3、删除老的 session_key
			            	redisStringDao.delete(old_rsession);
			            }
			           // 4、缓存建立新的对应关系
			            redisStringDao.add(openId, rsession);
			            redisStringDao.add(rsession,60*24*7,openId);
			            
			            redisStringDao.add("userCur_"+openId,dto);//TOKEN保存到中(保存一周)
			            
			            //3、 把新的sessionKey返回给小程序
			            objret.put("sessionid", rsession);
			            logger.info("sessionid 值设置成功=="+rsession);
		            }
		     }else{  
		    	 return ResultObject.warnMessage("请求异常"); 
		     }
		     return   ResultObject.successObject(objret,"登录成功") ;  
		}
	}

	
	
	
}
