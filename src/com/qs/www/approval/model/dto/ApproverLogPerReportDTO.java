package com.qs.www.approval.model.dto;

import java.io.Serializable;
import java.sql.Date;

public class ApproverLogPerReportDTO implements Serializable{
	
	private int reportNo;
	private int appLogNo;
	private int memberNo;
	private java.sql.Date appDate;
	private String appNote;
	private String appStatus;
	private String memberName;
	
	public ApproverLogPerReportDTO() {}

	public ApproverLogPerReportDTO(int reportNo, int appLogNo, int memberNo, Date appDate, String appNote,
			String appStatus, String memberName) {
		super();
		this.reportNo = reportNo;
		this.appLogNo = appLogNo;
		this.memberNo = memberNo;
		this.appDate = appDate;
		this.appNote = appNote;
		this.appStatus = appStatus;
		this.memberName = memberName;
	}

	public int getReportNo() {
		return reportNo;
	}

	public void setReportNo(int reportNo) {
		this.reportNo = reportNo;
	}

	public int getAppLogNo() {
		return appLogNo;
	}

	public void setAppLogNo(int appLogNo) {
		this.appLogNo = appLogNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public java.sql.Date getAppDate() {
		return appDate;
	}

	public void setAppDate(java.sql.Date appDate) {
		this.appDate = appDate;
	}

	public String getAppNote() {
		return appNote;
	}

	public void setAppNote(String appNote) {
		this.appNote = appNote;
	}

	public String getAppStatus() {
		return appStatus;
	}

	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	@Override
	public String toString() {
		return "ApproverLogPerReportDTO [reportNo=" + reportNo + ", appLogNo=" + appLogNo + ", memberNo=" + memberNo
				+ ", appDate=" + appDate + ", appNote=" + appNote + ", appStatus=" + appStatus + ", memberName="
				+ memberName + "]";
	}
	
	
	

}
