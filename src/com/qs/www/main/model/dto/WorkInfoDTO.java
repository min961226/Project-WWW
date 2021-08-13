package com.qs.www.main.model.dto;

import java.io.Serializable;

public class WorkInfoDTO implements Serializable {
	
	private int memberNo;
	private String appWorkType;
	private int workCode;
	private String today;
	private String weekStartDate;
	private String weekEndDate;
	private String selectedDate;
	
	public WorkInfoDTO() {}

	public WorkInfoDTO(int memberNo, String appWorkType, int workCode, String today, String weekStartDate,
			String weekEndDate, String selectedDate) {
		super();
		this.memberNo = memberNo;
		this.appWorkType = appWorkType;
		this.workCode = workCode;
		this.today = today;
		this.weekStartDate = weekStartDate;
		this.weekEndDate = weekEndDate;
		this.selectedDate = selectedDate;
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

	public String getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(String selectedDate) {
		this.selectedDate = selectedDate;
	}

	@Override
	public String toString() {
		return "WorkInfoDTO [memberNo=" + memberNo + ", appWorkType=" + appWorkType + ", workCode=" + workCode
				+ ", today=" + today + ", weekStartDate=" + weekStartDate + ", weekEndDate=" + weekEndDate
				+ ", selectedDate=" + selectedDate + "]";
	}
}
