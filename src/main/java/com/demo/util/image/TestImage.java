package com.demo.util.image;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.junit.Test;

public class TestImage {
	
    
    public void testCompare() throws IOException{
        String url_1="d:\\tmp\\1.jpg";
        String url_2="d:\\tmp\\1-2.jpg";
        String url_5="d:\\tmp\\1-5.jpg";
        String url_3="d:\\tmp\\2.jpg";
        String url_4="d:\\tmp\\2-2.jpg";
        
        System.out.println("url_1:"+getMD5(url_1));
        System.out.println("url_3:"+getMD5(url_3));
        System.out.println("=======================");
        removeExis(url_2, url_5);
        //removeExis(url_3, url_4);
        System.out.println("url_2:"+getMD5(url_2));
        System.out.println("url_5:"+getMD5(url_5));
    }

    public static void removeExis(String oldUrl,String newUrl){
      	 try {
      		WriteExifMetadataExample we =new WriteExifMetadataExample();
				we.removeExifMetadata(new File(oldUrl), new File(newUrl));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ImageReadException e) {
				e.printStackTrace();
			} catch (ImageWriteException e) {
				e.printStackTrace();
			}
    }
    
    public static String getMD5(String URLName){          
        String name="";  
        try {   
        	FileInputStream newinput = new FileInputStream(new File(URLName));
        	 
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
