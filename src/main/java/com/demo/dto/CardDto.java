package com.demo.dto;

public class CardDto {
	
	public Integer id	;	
	public Integer pointId	;//知识点ID
	public String  pointName;//知识点标题 lorePointName
	public String  questionType	;//	卡片题型 (单选：radio  多选： checkbox  填空： bland)				
	public Integer difficultyLevel	;//卡片难度系数				
	public String  titleText	;//	卡片标题文本
	public Integer titleVoiceFlag;//0:没有音频  1:使用在线语音合成  2:指定音频资源
	public String titleVoiceSrc;//卡片标题语音地址
	public String questionText	;//	问题描述文本				
	public String questionImages	;//问题图片组	
	public String answers;//卡片答案	

	public Integer getPointId() {
		return pointId;
	}
	public void setPointId(Integer pointId) {
		this.pointId = pointId;
	}
	public String getPointName() {
		return pointName;
	}
	public void setPointName(String pointName) {
		this.pointName = pointName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	
	public Integer getTitleVoiceFlag() {
		return titleVoiceFlag;
	}
	public void setTitleVoiceFlag(Integer titleVoiceFlag) {
		this.titleVoiceFlag = titleVoiceFlag;
	}
	public String getTitleVoiceSrc() {
		return titleVoiceSrc;
	}
	public void setTitleVoiceSrc(String titleVoiceSrc) {
		this.titleVoiceSrc = titleVoiceSrc;
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
