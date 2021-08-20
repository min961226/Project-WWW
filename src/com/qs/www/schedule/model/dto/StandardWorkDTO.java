package com.qs.www.schedule.model.dto;

import java.io.Serializable;

public class StandardWorkDTO implements Serializable {
	
	private int workCode;
	private String workName;
	private String breakStartTime;
	private String breakEndTime;
	private String checkInTime;
	private int minimalWorkHour;
	private String checkOutTime;
	private String activeYn;
	
	public StandardWorkDTO() {}

	public StandardWorkDTO(int workCode, String workName, String breakStartTime, String breakEndTime,
			String checkInTime, int minimalWorkHour, String checkOutTime, String activeYn) {
		super();
		this.workCode = workCode;
		this.workName = workName;
		this.breakStartTime = breakStartTime;
		this.breakEndTime = breakEndTime;
		this.checkInTime = checkInTime;
		this.minimalWorkHour = minimalWorkHour;
		this.checkOutTime = checkOutTime;
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

	public String getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(String checkInTime) {
		this.checkInTime = checkInTime;
	}

	public int getMinimalWorkHour() {
		return minimalWorkHour;
	}

	public void setMinimalWorkHour(int minimalWorkHour) {
		this.minimalWorkHour = minimalWorkHour;
	}

	public String getCheckOutTime() {
		return checkOutTime;
	}

	public void setCheckOutTime(String checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

	public String getActiveYn() {
		return activeYn;
	}

	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
	}

	@Override
	public String toString() {
		return "StandardWorkDTO [workCode=" + workCode + ", workName=" + workName + ", breakStartTime=" + breakStartTime
				+ ", breakEndTime=" + breakEndTime + ", checkInTime=" + checkInTime + ", minimalWorkHour="
				+ minimalWorkHour + ", checkOutTime=" + checkOutTime + ", activeYn=" + activeYn + "]";
	}

	
	
	
	

}
