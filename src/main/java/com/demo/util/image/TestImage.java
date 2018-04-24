package com.demo.util.image;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class TestImage {
	
    @Test
    public void testCompare() throws IOException{
        String url_1="d:\\tmp\\1.jpg";
        String url_2="d:\\tmp\\图片1.jpg";
        String url_3="d:\\tmp\\1-1.jpg";
        
        System.out.println("url_1:"+getMD5(url_1));
        System.out.println("url_2:"+getMD5(url_2));
        System.out.println("url_3:"+getMD5(url_3));
    }

    
    public static String getMD5(String URLName){          
        String name="";  
        try {             
        	FileInputStream inputStream = new FileInputStream(new File(URLName));
            byte[] bytes = new byte[10024];  
            int len = 0;                      
            MessageDigest messagedigest = MessageDigest.getInstance("MD5");           
            while ((len = inputStream.read(bytes)) > 0) {  
                messagedigest.update(bytes, 0, len);  
            }  
            name = MD5Util.bufferToHex(messagedigest.digest());   
            inputStream.close();              
        } catch (MalformedURLException e) {  
        } catch (IOException e) {  
        } catch (NoSuchAlgorithmException e) {  
        }  
        return name;  
    }  
}
