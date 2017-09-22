package com.demo.qiniu.po;

public class QiniuImageAuthPo {
	
    public int code;// 调用成功；
    
    public String message;//与code对应的状态描述信息
    
    public Result result;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}
    
    

}
