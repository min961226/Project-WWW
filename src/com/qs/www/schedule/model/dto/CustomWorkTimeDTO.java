package com.qs.www.schedule.model.dto;

public class CustomWorkTimeDTO {
	
	private int workNo;
	private String dayOfWeek;
	private String checkInTime;
	private String checkOutTime;
	
	public CustomWorkTimeDTO() {}

	public CustomWorkTimeDTO(int workNo, String dayOfWeek, String checkInTime, String checkOutTime) {
		super();
		this.workNo = workNo;
		this.dayOfWeek = dayOfWeek;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
	}

	public int getWorkNo() {
		return workNo;
	}

	public void setWorkNo(int workNo) {
		this.workNo = workNo;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
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

	@Override
	public String toString() {
		return "CustomWorkTimeDTO [workNo=" + workNo + ", dayOfWeek=" + dayOfWeek + ", checkInTime=" + checkInTime
				+ ", checkOutTime=" + checkOutTime + "]";
	}
	
	
	
}
