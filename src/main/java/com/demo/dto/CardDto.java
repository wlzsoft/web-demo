package com.demo.dto;

public class CardDto {
	
	public Integer id	;	
	public Integer lorePointId	;//知识点ID
	public String lorePointName;//知识点标题 lorePointName
	public String questionType	;//	卡片题型 (单选：radio  多选： checkbox  填空： bland)				
	public Integer difficultyLevel	;//卡片难度系数				
	public String  titleText	;//	卡片标题文本				
	public String questionText	;//	问题描述文本				
	public String questionImages	;//问题图片组	
	public String answers;//卡片答案	
	
	
	public String getLorePointName() {
		return lorePointName;
	}
	public void setLorePointName(String lorePointName) {
		this.lorePointName = lorePointName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getLorePointId() {
		return lorePointId;
	}
	public void setLorePointId(Integer lorePointId) {
		this.lorePointId = lorePointId;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public Integer getDifficultyLevel() {
		return difficultyLevel;
	}
	public void setDifficultyLevel(Integer difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}
	public String getTitleText() {
		return titleText;
	}
	public void setTitleText(String titleText) {
		this.titleText = titleText;
	}
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	public String getQuestionImages() {
		return questionImages;
	}
	public void setQuestionImages(String questionImages) {
		this.questionImages = questionImages;
	}
	public String getAnswers() {
		return answers;
	}
	public void setAnswers(String answers) {
		this.answers = answers;
	}
	
}
