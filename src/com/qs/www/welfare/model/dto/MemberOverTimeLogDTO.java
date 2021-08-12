package com.qs.www.welfare.model.dto;

import java.io.Serializable;
import java.sql.Date;

public class MemberOverTimeLogDTO implements Serializable{
	
	private int overTimelogNo;
	private int reportNo;
	private int memberNo;
	private Date overTimeStartDay;
	private String overTimeStartTime;
	private Date overTimeEndDay;
	private String overTimeEndTime;
	private int overTimeHour;
	private char TransBill;
	
	public MemberOverTimeLogDTO() {}
	
	public MemberOverTimeLogDTO(int overTimelogNo, int reportNo, int memberNo, Date overTimeStartDay,
			String overTimeStartTime, Date overTimeEndDay, String overTimeEndTime, int overTimeHour, char transBill) {
		super();
		this.overTimelogNo = overTimelogNo;
		this.reportNo = reportNo;
		this.memberNo = memberNo;
		this.overTimeStartDay = overTimeStartDay;
		this.overTimeStartTime = overTimeStartTime;
		this.overTimeEndDay = overTimeEndDay;
		this.overTimeEndTime = overTimeEndTime;
		this.overTimeHour = overTimeHour;
		TransBill = transBill;
	}

	public int getOverTimelogNo() {
		return overTimelogNo;
	}

	public void setOverTimelogNo(int overTimelogNo) {
		this.overTimelogNo = overTimelogNo;
	}

	public int getReportNo() {
		return reportNo;
	}

	public void setReportNo(int reportNo) {
		this.reportNo = reportNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public Date getOverTimeStartDay() {
		return overTimeStartDay;
	}

	public void setOverTimeStartDay(Date overTimeStartDay) {
		this.overTimeStartDay = overTimeStartDay;
	}

	public String getOverTimeStartTime() {
		return overTimeStartTime;
	}

	public void setOverTimeStartTime(String overTimeStartTime) {
		this.overTimeStartTime = overTimeStartTime;
	}

	public Date getOverTimeEndDay() {
		return overTimeEndDay;
	}

	public void setOverTimeEndDay(Date overTimeEndDay) {
		this.overTimeEndDay = overTimeEndDay;
	}

	public String getOverTimeEndTime() {
		return overTimeEndTime;
	}

	public void setOverTimeEndTime(String overTimeEndTime) {
		this.overTimeEndTime = overTimeEndTime;
	}

	public int getOverTimeHour() {
		return overTimeHour;
	}

	public void setOverTimeHour(int overTimeHour) {
		this.overTimeHour = overTimeHour;
	}

	public char getTransBill() {
		return TransBill;
	}

	public void setTransBill(char transBill) {
		TransBill = transBill;
	}

	@Override
	public String toString() {
		return "MemberOverTimeLogDTO [overTimelogNo=" + overTimelogNo + ", reportNo=" + reportNo + ", memberNo="
				+ memberNo + ", overTimeStartDay=" + overTimeStartDay + ", overTimeStartTime=" + overTimeStartTime
				+ ", overTimeEndDay=" + overTimeEndDay + ", overTimeEndTime=" + overTimeEndTime + ", overTimeHour="
				+ overTimeHour + ", TransBill=" + TransBill + "]";
	}
	
	
	
	
	
	}

