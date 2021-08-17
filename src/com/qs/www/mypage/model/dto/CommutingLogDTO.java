package com.qs.www.mypage.model.dto;

import java.io.Serializable;

public class CommutingLogDTO implements Serializable {
	
	private int commuteNo;
	private int memberNo;
	private String yearMonth;
	private String day;
	private String inTime;
	private String outTime;
	private String lateYn;
	private String leaveEarlyYn;
	
	private String dayOfWeek;
	
	public CommutingLogDTO() {}

	public CommutingLogDTO(int commuteNo, int memberNo, String yearMonth, String day, String inTime, String outTime,
			String lateYn, String leaveEarlyYn, String dayOfWeek) {
		super();
		this.commuteNo = commuteNo;
		this.memberNo = memberNo;
		this.yearMonth = yearMonth;
		this.day = day;
		this.inTime = inTime;
		this.outTime = outTime;
		this.lateYn = lateYn;
		this.leaveEarlyYn = leaveEarlyYn;
		this.dayOfWeek = dayOfWeek;
	}

	public int getCommuteNo() {
		return commuteNo;
	}

	public void setCommuteNo(int commuteNo) {
		this.commuteNo = commuteNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
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

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	@Override
	public String toString() {
		return "CommutingLogDTO [commuteNo=" + commuteNo + ", memberNo=" + memberNo + ", yearMonth=" + yearMonth
				+ ", day=" + day + ", inTime=" + inTime + ", outTime=" + outTime + ", lateYn=" + lateYn
				+ ", leaveEarlyYn=" + leaveEarlyYn + ", dayOfWeek=" + dayOfWeek + "]";
	}

	

	
}
