package com.qs.www.main.model.dto;

import java.io.Serializable;

public class MainDTO implements Serializable {
	
	private int memberNo;
	private String today;
	private String weekStartDate;
	private String weekEndDate;
	
	public MainDTO() {}

	public MainDTO(int memberNo, String today, String weekStartDate, String weekEndDate) {
		super();
		this.memberNo = memberNo;
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
		return "MainDTO [memberNo=" + memberNo + ", today=" + today + ", weekStartDate=" + weekStartDate
				+ ", weekEndDate=" + weekEndDate + "]";
	}
}
