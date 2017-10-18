package com.demo.dto;

public class PonitBatchDto {
	
	public Integer id ;//知识点主键ID
	
	public String pointName ;//知识点名称
	
	public Integer sort; //排序
	
	public Integer chapterSort;//章节排序号

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPointName() {
		return pointName;
	}

	public void setPointName(String pointName) {
		this.pointName = pointName;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getChapterSort() {
		return chapterSort;
	}

	public void setChapterSort(Integer chapterSort) {
		this.chapterSort = chapterSort;
	}

}
