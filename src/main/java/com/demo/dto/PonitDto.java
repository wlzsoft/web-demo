package com.demo.dto;

import java.io.UnsupportedEncodingException;

import com.smartframe.basics.util.EmojiUtil;

public class PonitDto {
	
	public Integer id ;//知识点主键ID
	
	public String pointName ;//知识点名称
	
	public Integer  chapterId ;//章节ID
	
	public Integer chapterSort;//章节序号
	
	public Integer  bookId ;//练习本ID
	
	public  Integer number; //知识点卡片数量
	
	public Integer sort; //排序

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public void setChapterId(Integer chapterId) {
		this.chapterId = chapterId;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
		result = prime * result
				+ ((chapterId == null) ? 0 : chapterId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result
				+ ((pointName == null) ? 0 : pointName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PonitDto other = (PonitDto) obj;
		if (bookId == null) {
			if (other.bookId != null)
				return false;
		} else if (!bookId.equals(other.bookId))
			return false;
		if (chapterId == null) {
			if (other.chapterId != null)
				return false;
		} else if (!chapterId.equals(other.chapterId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (pointName == null) {
			if (other.pointName != null)
				return false;
		} else if (!pointName.equals(other.pointName))
			return false;
		return true;
	}

}
