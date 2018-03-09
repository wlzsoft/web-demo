package com.demo.dto;


public class CardDto {
	
	public Integer id	;	
	public Integer pointId	;//知识点ID
	public String  pointName;//知识点标题 lorePointName
	public String cardData;//卡片数据
	public Integer pointState;//卡片知识点状态
	
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
	public String getPointName() {
		return pointName;
	}
	public void setPointName(String pointName) {
		this.pointName = pointName;
	}
	public String getCardData() {
		return cardData;
	}
	public void setCardData(String cardData) {
		this.cardData = cardData;
	}
	public Integer getPointState() {
		return pointState;
	}
	public void setPointState(Integer pointState) {
		this.pointState = pointState;
	}

	

}
