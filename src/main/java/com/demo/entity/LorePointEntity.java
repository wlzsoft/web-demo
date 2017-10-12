package com.demo.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import com.smartframe.basics.util.EmojiUtil;
import com.smartframe.dto.ResultObject;

public class LorePointEntity implements Serializable{

	private static final long serialVersionUID = 563723650984037521L;
	
	 public Integer  id          ;
	 
	 public Integer bookId ;//练习本主键ID
	 
	 public Integer chapterId ;//章节主键ID
	 
	 public String  pointName    ;//知识点名称
	 
	 public Integer  number      ;//知识点卡片数量
	 
	 public Integer sort; //排序
	 
	 public Date  createTime ;
	 
	 public Integer  createId   ;
	 
	 public Date  updateTime ;
	 
	 public Integer  updateId   ; 

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

	public Integer getChapterId() {
		return chapterId;
	}

	public void setChapterId(Integer chapterId) {
		this.chapterId = chapterId;
	}

	public String getPointName() {
		
		if(null==pointName||pointName.equals("")){
			
		}else{
			try {
				pointName =  EmojiUtil.emojiConvert1(pointName);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		return pointName;
	}

	public void setPointName(String pointName) {
		if(null==pointName||pointName.equals("")){
			
		}else{
			try {
				pointName =EmojiUtil.emojiRecovery2(pointName);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}	
		}
		this.pointName = pointName;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateId() {
		return createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getUpdateId() {
		return updateId;
	}

	public void setUpdateId(Integer updateId) {
		this.updateId = updateId;
	}

    
	
}
