package com.demo.entity;

import java.io.Serializable;
import java.util.Date;

public class LoreCardEntity implements Serializable{

	private static final long serialVersionUID = 461079389536773396L;
	
	public Integer id	;	
	public Integer pointId	;//知识点ID				
	public Integer language	;//	卡片使用的语言				
	public Integer area	;//	卡片所属领域				
	public String questionType	;//	卡片题型 (单选：radio  多选： checkbox  填空： bland)				
	public Integer difficultyLevel	;//卡片难度系数				
	public String  titleText	;//	卡片标题文本				
	public String titleVoiceSrc	;//	卡片标题语音地址				
	public boolean titleAutoPlay;// 卡片是否自动播放标题音频(1：是  0：否）				
	public String questionText	;//	问题描述文本				
	public String questionVoiceSrc	;//问题描述音频源地址				
	public boolean questionAutoPlay	;//问题是否自动播放音频(1：是  0：否）				
	public String questionImages	;//问题图片组	
	public String answers;//卡片答案
	public Date createTime	;//创建时间				
	public Integer createId	;//创建人ID				
	public Date updateTime	;//修改时间				
	public Integer updateId	;//修改人ID				
	public boolean isOpen	;//是否公开(1：是  0：否）	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPointId() {
		return pointId;
	}
	public void setPointId(Integer pointId) {
		this.pointId = pointId;
	}
	public Integer getLanguage() {
		return language;
	}
	public void setLanguage(Integer language) {
		this.language = language;
	}
	public Integer getArea() {
		return area;
	}
	public void setArea(Integer area) {
		this.area = area;
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
	public String getTitleVoiceSrc() {
		return titleVoiceSrc;
	}
	public void setTitleVoiceSrc(String titleVoiceSrc) {
		this.titleVoiceSrc = titleVoiceSrc;
	}
	public boolean isTitleAutoPlay() {
		return titleAutoPlay;
	}
	public void setTitleAutoPlay(boolean titleAutoPlay) {
		this.titleAutoPlay = titleAutoPlay;
	}
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	public String getQuestionVoiceSrc() {
		return questionVoiceSrc;
	}
	public void setQuestionVoiceSrc(String questionVoiceSrc) {
		this.questionVoiceSrc = questionVoiceSrc;
	}
	public boolean isQuestionAutoPlay() {
		return questionAutoPlay;
	}
	public void setQuestionAutoPlay(boolean questionAutoPlay) {
		this.questionAutoPlay = questionAutoPlay;
	}
	public String getQuestionImages() {
		return questionImages;
	}
	public void setQuestionImages(String questionImages) {
		this.questionImages = questionImages;
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
	public boolean isOpen() {
		return isOpen;
	}
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	public String getAnswers() {
		return answers;
	}
	public void setAnswers(String answers) {
		this.answers = answers;
	}
}
