package com.qs.www.main.model.dto;

import java.io.Serializable;

public class CommutingLogDTO implements Serializable {
	
	private int commuteNo;
	private String yearMonth;
	private String day;
	private String inTime;
	private String outTime;
	
	public CommutingLogDTO() {}

	public CommutingLogDTO(int commuteNo, String yearMonth, String day, String inTime, String outTime) {
		super();
		this.commuteNo = commuteNo;
		this.yearMonth = yearMonth;
		this.day = day;
		this.inTime = inTime;
		this.outTime = outTime;
	}

	public int getCommuteNo() {
		return commuteNo;
	}

	public void setCommuteNo(int commuteNo) {
		this.commuteNo = commuteNo;
	}

	public String getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	@Override
	public String toString() {
		return "CommutingLogDTO [commuteNo=" + commuteNo + ", yearMonth=" + yearMonth + ", day=" + day + ", inTime="
				+ inTime + ", outTime=" + outTime + "]";
	}
}
