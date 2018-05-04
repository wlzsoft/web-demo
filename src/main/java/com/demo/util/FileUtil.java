package com.demo.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.demo.comm.CommonConstants;

public class FileUtil {
	
   public static File multipartToFile(CommonsMultipartFile multfile) throws IOException{
	   CommonsMultipartFile cf = (CommonsMultipartFile)multfile;   
       //这个myfile是MultipartFile的  
       DiskFileItem fi = (DiskFileItem) cf.getFileItem();  
       File file = fi.getStoreLocation();  
       //手动创建临时文件  
       if(file.length() < CommonConstants.MIN_FILE_SIZE){  
           File tmpFile = new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") +   
                   file.getName());  
           multfile.transferTo(tmpFile);  
           return tmpFile;  
       }  
       return file;
   }
}
