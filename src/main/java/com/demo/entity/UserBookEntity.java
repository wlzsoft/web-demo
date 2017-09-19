package com.demo.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-09-19.
 */
public class UserBookEntity implements Serializable {
	
	private static final long serialVersionUID = 4599543678350818379L;

	public Integer id;
	
	public Integer bookId;
	
	public Integer userId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	

}
