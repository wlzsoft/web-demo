package com.demo.dto;

import java.io.UnsupportedEncodingException;

import com.smartframe.basics.util.EmojiUtil;

public class CardDto_old {
	
	public Integer id	;	
	public Integer pointId	;//知识点ID
	public String  pointName;//知识点标题 lorePointName
	public String  questionType	;//	卡片题型 (单选：radio  多选： checkbox  填空： bland)				
	public Integer difficultyLevel	;//卡片难度系数				
	public String  titleText	;//	卡片标题文本
	public Integer titleVoiceFlag;//0:没有音频  1:使用在线语音合成  2:指定音频资源
	public String titleVoiceSrc;//卡片标题语音地址
	public String questionText	;//	问题提示
	public String questionLongText;//问题描述：图片、音频、长文本
	public String questionDescType;//问题描述类型(text 文本 	image 图片 	audio 音频)
	public String questionVoiceSrc;//问题描述音频源地址
	public String questionImages	;//问题图片组	
	public String answers;//卡片答案	
	public Integer pointState;//卡片知识点状态

	public Integer getPointId() {
		return pointId;
	}
	public void setPointId(Integer pointId) {
		this.pointId = pointId;
	}

	public String getPointName() {
		try {
			pointName =  EmojiUtil.emojiConvert1(pointName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return pointName;
	}

	public void setPointName(String pointName) {
		try {
			pointName =EmojiUtil.emojiRecovery2(pointName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
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
	public String getQuestionLongText() {
		return questionLongText;
	}
	public void setQuestionLongText(String questionLongText) {
		this.questionLongText = questionLongText;
	}
	public String getQuestionDescType() {
		return questionDescType;
	}
	public void setQuestionDescType(String questionDescType) {
		this.questionDescType = questionDescType;
	}
	public String getQuestionVoiceSrc() {
		return questionVoiceSrc;
	}
	public void setQuestionVoiceSrc(String questionVoiceSrc) {
		this.questionVoiceSrc = questionVoiceSrc;
	}
	public Integer getPointState() {
		return pointState;
	}
	public void setPointState(Integer pointState) {
		this.pointState = pointState;
	}

}
