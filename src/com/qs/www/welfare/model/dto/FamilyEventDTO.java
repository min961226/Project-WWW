package com.qs.www.welfare.model.dto;

import java.io.Serializable;

public class FamilyEventDTO implements Serializable{

	private int eventNo;
	private String eventType;
	private int supportFund;
	private String relation;
	private char wreathStatus;
	
	public FamilyEventDTO() {}
	
	public FamilyEventDTO(int eventNo, String eventType, int supportFund, String relation, char wreathStatus) {
		super();
		this.eventNo = eventNo;
		this.eventType = eventType;
		this.supportFund = supportFund;
		this.relation = relation;
		this.wreathStatus = wreathStatus;
	}

	public int getEventNo() {
		return eventNo;
	}

	public void setEventNo(int eventNo) {
		this.eventNo = eventNo;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public int getSupportFund() {
		return supportFund;
	}

	public void setSupportFund(int supportFund) {
		this.supportFund = supportFund;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public char getWreathStatus() {
		return wreathStatus;
	}

	public void setWreathStatus(char wreathStatus) {
		this.wreathStatus = wreathStatus;
	}

	@Override
	public String toString() {
		return "FamilyEventDTO [eventNo=" + eventNo + ", eventType=" + eventType + ", supportFund=" + supportFund
				+ ", relation=" + relation + ", wreathStatus=" + wreathStatus + "]";
	}	
}
