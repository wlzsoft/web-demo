package com.demo.controller;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.demo.dto.ImageUrlDto;
import com.demo.qiniu.UploadManager;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.util.Auth;
import com.smartframe.dto.Result;
import com.smartframe.dto.ResultObject;


@Controller
@RequestMapping("/file")
public class FileUpController {
	
	@RequestMapping("/uploadFile")
	public Result<?> uploadImages(@RequestParam(value = "file") CommonsMultipartFile file,HttpServletRequest request ,HttpServletResponse response){
		
		ImageUrlDto dto = new ImageUrlDto();
		
		try {
			  Date date = new Date(); 
			  long unixTimestamp = date.getTime()/1000; 
			  
			 UUID uuid = UUID.randomUUID();
		     String key = unixTimestamp+""+uuid;//上传到七牛后保存的文件名
             String prefix="";
             String contentType = file.getContentType();
             if(contentType.equals("audio/mp3")){
            	 prefix=".mp3";
             }else if(contentType.equals("audio/wav")){
            	 prefix=".wav";
             }else if(contentType.equals("video/mpeg4")){
            	 prefix=".m4a";
             }else{
            	 ResultObject.warnMessage("不上传失败，请选择 mp3、wav 或 m4a 格式的音频");
             }
             
             key=key+prefix;//生成文件名
             
		    //第二种方式: 自动识别要上传的空间(bucket)的存储区域是华东、华北、华南。
		    Zone z = Zone.autoZone();
		    Configuration c = new Configuration(z);
		    //创建上传对象
		    UploadManager uploadManager = new UploadManager(c);
		
	        try {
	            //调用put方法上传
	        	
	            Response res = uploadManager.put(file.getBytes(), key, getUpToken());
	              
	            //打印返回的信息
	            System.out.println(res.bodyString());
	        } catch (QiniuException e) {
	            Response r = e.response;
	            // 请求失败时打印的异常的信息
	            System.out.println(r.toString());
	            try {
	                //响应的文本信息
	                System.out.println(r.bodyString());
	            } catch (QiniuException e1) {
	                //ignore
	            }
	        }
	        
			dto.setFileName(key);
			dto.setFileUrl("https://ebird.res.simfun.net/"+key);
		} catch (RuntimeException e2) {
			e2.printStackTrace();
		}
		return ResultObject.successObject(dto,null);
		
	}
	
	   //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public String getUpToken() {
    	
	    //设置好账号的ACCESS_KEY和SECRET_KEY
/*	    String ACCESS_KEY = "HymZmx8L-J-BsHaV77YicdQt4kStrcW5lGuPW0l-";
	    String SECRET_KEY = "1TgHByYcmoEn-x-1Em3QiVS3PKBbrdmNPSA-fxkV";*/
	    String ACCESS_KEY = "3logzQq7VRF5q4M5NSeneqv-B5cqiAwcO2jm-fKq";
	    String SECRET_KEY = "HSqVTmW9BN7iCmD7PPLpzr8d8P8BvBx2Bj5BeeyV";
	    //密钥配置
	    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
	    //要上传的空间
	    String bucketname = "ebird";
        return auth.uploadToken(bucketname);
    }

	
}
