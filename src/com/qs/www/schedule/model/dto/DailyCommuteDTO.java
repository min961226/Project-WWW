package com.qs.www.schedule.model.dto;

import java.io.Serializable;

public class DailyCommuteDTO implements Serializable {
	
	//월간 출근기록을 view에 가져오기위한 DTO
	private String dateNum;
	private String dayOfWeek;
	private String checkInTime;
	private String checkOutTime;
	private String lateYn;
	private String leaveEarlyYn;
	
	public DailyCommuteDTO() {}

	public DailyCommuteDTO(String dateNum, String dayOfWeek, String checkInTime, String checkOutTime, String lateYn,
			String leaveEarlyYn) {
		super();
		this.dateNum = dateNum;
		this.dayOfWeek = dayOfWeek;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
		this.lateYn = lateYn;
		this.leaveEarlyYn = leaveEarlyYn;
	}

	public String getDateNum() {
		return dateNum;
	}

	public void setDateNum(String dateNum) {
		this.dateNum = dateNum;
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

	public String getLateYn() {
		return lateYn;
	}

	public void setLateYn(String lateYn) {
		this.lateYn = lateYn;
	}

	public String getLeaveEarlyYn() {
		return leaveEarlyYn;
	}

	public void setLeaveEarlyYn(String leaveEarlyYn) {
		this.leaveEarlyYn = leaveEarlyYn;
	}

	@Override
	public String toString() {
		return "DailyCommuteDTO [dateNum=" + dateNum + ", dayOfWeek=" + dayOfWeek + ", checkInTime=" + checkInTime
				+ ", checkOutTime=" + checkOutTime + ", lateYn=" + lateYn + ", leaveEarlyYn=" + leaveEarlyYn + "]";
	}
	
	

}
