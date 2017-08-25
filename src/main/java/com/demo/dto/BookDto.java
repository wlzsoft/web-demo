package com.demo.dto;


public class BookDto {

	public Integer id ;
	
	public String  bookName ;//练习本名称
	
	public String  language ;
	
	public String  area ;
	
	public Integer  sharedType ;//共享类型 (0私有、1只读共享)
	
	public Integer  createId ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSharedType() {
		return sharedType;
	}

	public void setSharedType(Integer sharedType) {
		this.sharedType = sharedType;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Integer getCreateId() {
		return createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}
	
}
