package com.demo.util;

public class ToolUtil {
	
	public static String[] toStringArry(String str){
		   str=str.replace("\"", "");
		   str=str.replace("[", "");
		   str=str.replace("]", "");
		  String[] arry = str.split(",");
		  return arry ;
	}
}
