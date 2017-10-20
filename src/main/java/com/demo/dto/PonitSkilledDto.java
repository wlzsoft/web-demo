package com.demo.dto;

import java.io.UnsupportedEncodingException;

import com.smartframe.basics.util.EmojiUtil;

public class PonitSkilledDto {
	
	public Integer id ;//知识点主键ID
	
	public String pointName ;//知识点名称
	
	public Integer  chapterId ;//章节ID
	
	public Integer chapterSort;//章节序号
	
	public Integer  bookId ;//练习本ID
	
	public  Integer number; //知识点卡片数量
	
	public Integer sort; //排序
	
	public Integer exerciseCycle;//知识点当前练习周期
	
	public Integer skilled;//熟练度（0，1，2，3）

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

	public Integer getChapterSort() {
		return chapterSort;
	}

	public void setChapterSort(Integer chapterSort) {
		this.chapterSort = chapterSort;
	}

	public Integer getExerciseCycle() {
		return exerciseCycle;
	}

	public void setExerciseCycle(Integer exerciseCycle) {
		this.exerciseCycle = exerciseCycle;
	}

	public Integer getSkilled() {
		return skilled;
	}

	public void setSkilled(Integer skilled) {
		this.skilled = skilled;
	}

	public Integer getChapterId() {
		return chapterId;
	}

}
