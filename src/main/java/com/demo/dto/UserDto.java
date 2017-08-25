package com.demo.dto;

import java.util.Date;

public class UserDto {
	
	public String username ;
	
	public String email;
	
	public String telphone;
	
	public Date birthday;
	
	public Integer degree;
	
	public Date registerTime;
	
	public Date lasterLoginTime;
	
	public String remark;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getDegree() {
		return degree;
	}

	public void setDegree(Integer degree) {
		this.degree = degree;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Date getLasterLoginTime() {
		return lasterLoginTime;
	}

	public void setLasterLoginTime(Date lasterLoginTime) {
		this.lasterLoginTime = lasterLoginTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
