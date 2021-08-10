package com.qs.www.schedule.model.dto;

import java.io.Serializable;
import java.sql.Date;

public class ReportDTO implements Serializable{
	
	private int reportNo;
	private java.sql.Date reportDate;
	private int memberNo;
	private int documentNo;
	private String reportNote;
	private String reportStatus;
	
	public ReportDTO() {}

	public ReportDTO(int reportNo, Date reportDate, int memberNo, int documentNo, String reportNote,
			String reportStatus) {
		super();
		this.reportNo = reportNo;
		this.reportDate = reportDate;
		this.memberNo = memberNo;
		this.documentNo = documentNo;
		this.reportNote = reportNote;
		this.reportStatus = reportStatus;
	}

	public int getReportNo() {
		return reportNo;
	}

	public void setReportNo(int reportNo) {
		this.reportNo = reportNo;
	}

	public java.sql.Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(java.sql.Date reportDate) {
		this.reportDate = reportDate;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(int documentNo) {
		this.documentNo = documentNo;
	}

	public String getReportNote() {
		return reportNote;
	}

	public void setReportNote(String reportNote) {
		this.reportNote = reportNote;
	}

	public String getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
	}

	@Override
	public String toString() {
		return "ReportDTO [reportNo=" + reportNo + ", reportDate=" + reportDate + ", memberNo=" + memberNo
				+ ", documentNo=" + documentNo + ", reportNote=" + reportNote + ", reportStatus=" + reportStatus + "]";
	}

	
	

}
