package com.demo.entity;

import java.io.Serializable;
import java.util.Date;

public class UserEntity implements Serializable{
	
	private static final long serialVersionUID = -482262390418753384L;

	public Integer id;
	
	public String username ;
	
	public String password ;
	
	public String avata;//用户头像地址
	
	public String email;
	
	public String telphone;
	
	public Date birthday;
	
	public Integer degree;
	
	public Date registerTime;
	
	public Date lasterLoginTime;
	
	public String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getAvata() {
		return avata;
	}

	public void setAvata(String avata) {
		this.avata = avata;
	}
	
}
