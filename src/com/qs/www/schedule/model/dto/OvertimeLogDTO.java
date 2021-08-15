package com.qs.www.schedule.model.dto;

import java.sql.Date;

public class OvertimeLogDTO {
	
	private int memberOvertimeLogNo;
	private int overtimeReportNo;
	private int memberNo;
	private java.sql.Date overtimeStartDay;
	private String overtimeStartTime;
	private java.sql.Date overtimeEndDay;
	private String overtimeEndTime;
	private int overtimeDuring;
	private String transBill;
	
	private String weekStartDate;
	private String weekEndDate;
	
	
	public OvertimeLogDTO() {}


	public OvertimeLogDTO(int memberOvertimeLogNo, int overtimeReportNo, int memberNo, Date overtimeStartDay,
			String overtimeStartTime, Date overtimeEndDay, String overtimeEndTime, int overtimeDuring, String transBill,
			String weekStartDate, String weekEndDate) {
		super();
		this.memberOvertimeLogNo = memberOvertimeLogNo;
		this.overtimeReportNo = overtimeReportNo;
		this.memberNo = memberNo;
		this.overtimeStartDay = overtimeStartDay;
		this.overtimeStartTime = overtimeStartTime;
		this.overtimeEndDay = overtimeEndDay;
		this.overtimeEndTime = overtimeEndTime;
		this.overtimeDuring = overtimeDuring;
		this.transBill = transBill;
		this.weekStartDate = weekStartDate;
		this.weekEndDate = weekEndDate;
	}


	public int getMemberOvertimeLogNo() {
		return memberOvertimeLogNo;
	}


	public void setMemberOvertimeLogNo(int memberOvertimeLogNo) {
		this.memberOvertimeLogNo = memberOvertimeLogNo;
	}


	public int getOvertimeReportNo() {
		return overtimeReportNo;
	}


	public void setOvertimeReportNo(int overtimeReportNo) {
		this.overtimeReportNo = overtimeReportNo;
	}


	public int getMemberNo() {
		return memberNo;
	}


	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}


	public java.sql.Date getOvertimeStartDay() {
		return overtimeStartDay;
	}


	public void setOvertimeStartDay(java.sql.Date overtimeStartDay) {
		this.overtimeStartDay = overtimeStartDay;
	}


	public String getOvertimeStartTime() {
		return overtimeStartTime;
	}


	public void setOvertimeStartTime(String overtimeStartTime) {
		this.overtimeStartTime = overtimeStartTime;
	}


	public java.sql.Date getOvertimeEndDay() {
		return overtimeEndDay;
	}


	public void setOvertimeEndDay(java.sql.Date overtimeEndDay) {
		this.overtimeEndDay = overtimeEndDay;
	}


	public String getOvertimeEndTime() {
		return overtimeEndTime;
	}


	public void setOvertimeEndTime(String overtimeEndTime) {
		this.overtimeEndTime = overtimeEndTime;
	}


	public int getOvertimeDuring() {
		return overtimeDuring;
	}


	public void setOvertimeDuring(int overtimeDuring) {
		this.overtimeDuring = overtimeDuring;
	}


	public String getTransBill() {
		return transBill;
	}


	public void setTransBill(String transBill) {
		this.transBill = transBill;
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
		return "OvertimeLogDTO [memberOvertimeLogNo=" + memberOvertimeLogNo + ", overtimeReportNo=" + overtimeReportNo
				+ ", memberNo=" + memberNo + ", overtimeStartDay=" + overtimeStartDay + ", overtimeStartTime="
				+ overtimeStartTime + ", overtimeEndDay=" + overtimeEndDay + ", overtimeEndTime=" + overtimeEndTime
				+ ", overtimeDuring=" + overtimeDuring + ", transBill=" + transBill + ", weekStartDate=" + weekStartDate
				+ ", weekEndDate=" + weekEndDate + "]";
	}
	
	
	
	
	
	

}
