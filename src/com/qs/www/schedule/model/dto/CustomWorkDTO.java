package com.qs.www.schedule.model.dto;

import java.io.Serializable;

public class CustomWorkDTO implements Serializable {
	
	private int workNo;
	private String breakStartTime;
	private String breakEndTime;
	private int memberNo;
	private String activeYN;
	
	public CustomWorkDTO() {}
	
	public CustomWorkDTO(int workNo, String breakStartTime, String breakEndTime, int memberNo, String activeYN) {
		super();
		this.workNo = workNo;
		this.breakStartTime = breakStartTime;
		this.breakEndTime = breakEndTime;
		this.memberNo = memberNo;
		this.activeYN = activeYN;
	}

	public int getWorkNo() {
		return workNo;
	}

	public void setWorkNo(int workNo) {
		this.workNo = workNo;
	}

	public String getBreakStartTime() {
		return breakStartTime;
	}

	public void setBreakStartTime(String breakStartTime) {
		this.breakStartTime = breakStartTime;
	}

	public String getBreakEndTime() {
		return breakEndTime;
	}

	public void setBreakEndTime(String breakEndTime) {
		this.breakEndTime = breakEndTime;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getActiveYN() {
		return activeYN;
	}

	public void setActiveYN(String activeYN) {
		this.activeYN = activeYN;
	}

	@Override
	public String toString() {
		return "CustomWorkDTO [workNo=" + workNo + ", breakStartTime=" + breakStartTime + ", breakEndTime="
				+ breakEndTime + ", memberNo=" + memberNo + ", activeYN=" + activeYN + "]";
	}
	
	
	
	
}
