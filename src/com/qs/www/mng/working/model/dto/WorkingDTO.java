package com.qs.www.mng.working.model.dto;

import java.io.Serializable;

public class WorkingDTO implements Serializable{
	
	private int workCode;
	private String workName;
	private String breakStartTime;
	private String breakEndTime;
	private int minimalWorkHour;
	private String checkInTime;
	private String checkOutTime;
	private int memberNo;
	private String activeYn;
	
	public WorkingDTO() {}

	public WorkingDTO(int workCode, String workName, String breakStartTime, String breakEndTime, int minimalWorkHour,
			String checkInTime, String checkOutTime, int memberNo, String activeYn) {
		super();
		this.workCode = workCode;
		this.workName = workName;
		this.breakStartTime = breakStartTime;
		this.breakEndTime = breakEndTime;
		this.minimalWorkHour = minimalWorkHour;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
		this.memberNo = memberNo;
		this.activeYn = activeYn;
	}

	public int getWorkCode() {
		return workCode;
	}

	public void setWorkCode(int workCode) {
		this.workCode = workCode;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
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

	public int getMinimalWorkHour() {
		return minimalWorkHour;
	}

	public void setMinimalWorkHour(int minimalWorkHour) {
		this.minimalWorkHour = minimalWorkHour;
	}

	public String getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(String checkInTime) {
		this.checkInTime = checkInTime;
	}

	public String getCheckOutTime() {
		return checkOutTime;
	}

	public void setCheckOutTime(String checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getActiveYn() {
		return activeYn;
	}

	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
	}

	@Override
	public String toString() {
		return "WorkingDTO [workCode=" + workCode + ", workName=" + workName + ", breakStartTime=" + breakStartTime
				+ ", breakEndTime=" + breakEndTime + ", minimalWorkHour=" + minimalWorkHour + ", checkInTime="
				+ checkInTime + ", checkOutTime=" + checkOutTime + ", memberNo=" + memberNo + ", activeYn=" + activeYn
				+ "]";
	}
	
	
	
}
