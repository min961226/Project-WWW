package com.qs.www.schedule.model.dto;

import java.sql.Date;

import sun.misc.JavaAWTAccess;

public class MemberWorkLogDTO implements java.io.Serializable{
	
	private int memberNo;
	private String workType;
	private int approverLine;
	private java.sql.Date startDay;
	private java.sql.Date endDay;
	private int workNo;
	private java.sql.Date changeDate;
	private String changeReason;	
	
	public MemberWorkLogDTO() {}

	public MemberWorkLogDTO(int memberNo, String workType, int approverLine, Date startDay, Date endDay, int workNo,
			Date changeDate, String changeReason) {
		super();
		this.memberNo = memberNo;
		this.workType = workType;
		this.approverLine = approverLine;
		this.startDay = startDay;
		this.endDay = endDay;
		this.workNo = workNo;
		this.changeDate = changeDate;
		this.changeReason = changeReason;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public int getApproverLine() {
		return approverLine;
	}

	public void setApproverLine(int approverLine) {
		this.approverLine = approverLine;
	}

	public java.sql.Date getStartDay() {
		return startDay;
	}

	public void setStartDay(java.sql.Date startDay) {
		this.startDay = startDay;
	}

	public java.sql.Date getEndDay() {
		return endDay;
	}

	public void setEndDay(java.sql.Date endDay) {
		this.endDay = endDay;
	}

	public int getWorkNo() {
		return workNo;
	}

	public void setWorkNo(int workNo) {
		this.workNo = workNo;
	}

	public java.sql.Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(java.sql.Date changeDate) {
		this.changeDate = changeDate;
	}

	public String getChangeReason() {
		return changeReason;
	}

	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}

	@Override
	public String toString() {
		return "MemberWorkLogDTO [memberNo=" + memberNo + ", workType=" + workType + ", approverLine=" + approverLine
				+ ", startDay=" + startDay + ", endDay=" + endDay + ", workNo=" + workNo + ", changeDate=" + changeDate
				+ ", changeReason=" + changeReason + "]";
	}

	
	
	

}
