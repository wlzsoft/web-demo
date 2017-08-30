package com.demo.entity;

import java.io.Serializable;
import java.util.Date;

public class LoreCradAnswersEntity implements Serializable {
	
	private static final long serialVersionUID = 5137654231298881852L;
	  public Integer id ;//
	  public Integer cardId ;// '卡片ID',
	  public Integer answersRight;//  '是否是正确答案',
	  public String answersText;// '答案文本',
	  public String answersVoiceSrc ;//'答案文本音频地址',
	  public Integer answersAutoPlay ;// '答案是否自动播放音频',
	  public String answersImages ;// '答案图片组',
	  public Date createTime	;//创建时间				
	  public Integer createId	;//创建人ID				
	  public Date updateTime	;//修改时间				
	  public Integer updateId	;//修改人ID	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCardId() {
		return cardId;
	}
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
	public Integer getAnswersRight() {
		return answersRight;
	}
	public void setAnswersRight(Integer answersRight) {
		this.answersRight = answersRight;
	}
	public String getAnswersText() {
		return answersText;
	}
	public void setAnswersText(String answersText) {
		this.answersText = answersText;
	}
	public String getAnswersVoiceSrc() {
		return answersVoiceSrc;
	}
	public void setAnswersVoiceSrc(String answersVoiceSrc) {
		this.answersVoiceSrc = answersVoiceSrc;
	}
	public Integer getAnswersAutoPlay() {
		return answersAutoPlay;
	}
	public void setAnswersAutoPlay(Integer answersAutoPlay) {
		this.answersAutoPlay = answersAutoPlay;
	}
	public String getAnswersImages() {
		return answersImages;
	}
	public void setAnswersImages(String answersImages) {
		this.answersImages = answersImages;
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
