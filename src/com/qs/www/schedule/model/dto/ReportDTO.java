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
	private String lineName;
	private String reportTitle;
	
	public ReportDTO() {}

	public ReportDTO(int reportNo, Date reportDate, int memberNo, int documentNo, String reportNote,
			String reportStatus, String lineName, String reportTitle) {
		super();
		this.reportNo = reportNo;
		this.reportDate = reportDate;
		this.memberNo = memberNo;
		this.documentNo = documentNo;
		this.reportNote = reportNote;
		this.reportStatus = reportStatus;
		this.lineName = lineName;
		this.reportTitle = reportTitle;
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

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getReportTitle() {
		return reportTitle;
	}

	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}

	@Override
	public String toString() {
		return "ReportDTO [reportNo=" + reportNo + ", reportDate=" + reportDate + ", memberNo=" + memberNo
				+ ", documentNo=" + documentNo + ", reportNote=" + reportNote + ", reportStatus=" + reportStatus
				+ ", lineName=" + lineName + ", reportTitle=" + reportTitle + "]";
	}
	
	
	

}
