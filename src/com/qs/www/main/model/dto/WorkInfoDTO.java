package com.qs.www.main.model.dto;

import java.io.Serializable;

public class WorkInfoDTO implements Serializable {
	
	private int memberNo;
	private String appWorkType;
	private int workCode;
	private int remainingHoliday;
	private String today;
	private String weekStartDate;
	private String weekEndDate;
	
	public WorkInfoDTO() {}

	public WorkInfoDTO(int memberNo, String appWorkType, int workCode, int remainingHoliday, String today,
			String weekStartDate, String weekEndDate) {
		super();
		this.memberNo = memberNo;
		this.appWorkType = appWorkType;
		this.workCode = workCode;
		this.remainingHoliday = remainingHoliday;
		this.today = today;
		this.weekStartDate = weekStartDate;
		this.weekEndDate = weekEndDate;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getAppWorkType() {
		return appWorkType;
	}

	public void setAppWorkType(String appWorkType) {
		this.appWorkType = appWorkType;
	}

	public int getWorkCode() {
		return workCode;
	}

	public void setWorkCode(int workCode) {
		this.workCode = workCode;
	}

	public int getRemainingHoliday() {
		return remainingHoliday;
	}

	public void setRemainingHoliday(int remainingHoliday) {
		this.remainingHoliday = remainingHoliday;
	}

	public String getToday() {
		return today;
	}

	public void setToday(String today) {
		this.today = today;
	}

	public String getWeekStartDate() {
		return weekStartDate;
	}

	public void setWeekStartDate(String weekStartDate) {
		this.weekStartDate = weekStartDate;
	}

	public String getWeekEndDate() {
		return weekEndDate;
	}

	public void setWeekEndDate(String weekEndDate) {
		this.weekEndDate = weekEndDate;
	}

	@Override
	public String toString() {
		return "WorkInfoDTO [memberNo=" + memberNo + ", appWorkType=" + appWorkType + ", workCode=" + workCode
				+ ", remainingHoliday=" + remainingHoliday + ", today=" + today + ", weekStartDate=" + weekStartDate
				+ ", weekEndDate=" + weekEndDate + "]";
	}
}
