package com.qs.www.schedule.model.dto;

import java.sql.Date;

import sun.misc.JavaAWTAccess;

public class MemberWorkLogDTO implements java.io.Serializable{
	
	private int memberWorkLogNo;			//근무제변경이력번호
	private int memberNo;					//사번
	private String workType;				//변경후근무제유형
	private int approverLine;				
	private int workNo;						//변경후근무제유형코드
	private java.sql.Date startDay;			//시작일
	private java.sql.Date endDay;			//종료일
	private java.sql.Date changeDate;		//변경일자
	private String changeReason;			//변경사유
	
	public MemberWorkLogDTO() {}

	public MemberWorkLogDTO(int memberWorkLogNo, int memberNo, String workType, int approverLine, int workNo,
			Date startDay, Date endDay, Date changeDate, String changeReason) {
		super();
		this.memberWorkLogNo = memberWorkLogNo;
		this.memberNo = memberNo;
		this.workType = workType;
		this.approverLine = approverLine;
		this.workNo = workNo;
		this.startDay = startDay;
		this.endDay = endDay;
		this.changeDate = changeDate;
		this.changeReason = changeReason;
	}

	public int getMemberWorkLogNo() {
		return memberWorkLogNo;
	}

	public void setMemberWorkLogNo(int memberWorkLogNo) {
		this.memberWorkLogNo = memberWorkLogNo;
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

	public int getWorkNo() {
		return workNo;
	}

	public void setWorkNo(int workNo) {
		this.workNo = workNo;
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
		return "MemberWorkLogDTO [memberWorkLogNo=" + memberWorkLogNo + ", memberNo=" + memberNo + ", workType="
				+ workType + ", approverLine=" + approverLine + ", workNo=" + workNo + ", startDay=" + startDay
				+ ", endDay=" + endDay + ", changeDate=" + changeDate + ", changeReason=" + changeReason + "]";
	}

	

	
	
	

}
