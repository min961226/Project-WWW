package com.qs.www.main.model.dto;

public class WorkingTypeDTO {
	
	private String dayOfWeek;
	private String checkInTime;
	private String checkOutTime;
	private String breakStartTime;
	private String breakEndTime;
	
	public WorkingTypeDTO() {}

	public WorkingTypeDTO(String dayOfWeek, String checkInTime, String checkOutTime, String breakStartTime,
			String breakEndTime) {
		super();
		this.dayOfWeek = dayOfWeek;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
		this.breakStartTime = breakStartTime;
		this.breakEndTime = breakEndTime;
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

	@Override
	public String toString() {
		return "WorkingTypeDTO [dayOfWeek=" + dayOfWeek + ", checkInTime=" + checkInTime + ", checkOutTime="
				+ checkOutTime + ", breakStartTime=" + breakStartTime + ", breakEndTime=" + breakEndTime + "]";
	}
}
