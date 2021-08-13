package com.qs.www.schedule.model.dto;

import java.io.Serializable;
import java.sql.Date;

public class ReportDTO implements Serializable{
	
	private int reportNo;					//상신번호
	private java.sql.Date reportDate;		//상신일자
	private int memberNo;					//상신자 사번
	private int documentNo;					//문서번호
	private String reportNote;				//비고
	private String reportStatus;			//결재상태
	private String lineName;				//라인명
	private String reportTitle;				//상신명(상신문서제목)
	private String memberName;               //상신자명(상신테이블에는 없는속성)
	
	public ReportDTO() {}

	public ReportDTO(int reportNo, Date reportDate, int memberNo, int documentNo, String reportNote,
			String reportStatus, String lineName, String reportTitle, String memberName) {
		super();
		this.reportNo = reportNo;
		this.reportDate = reportDate;
		this.memberNo = memberNo;
		this.documentNo = documentNo;
		this.reportNote = reportNote;
		this.reportStatus = reportStatus;
		this.lineName = lineName;
		this.reportTitle = reportTitle;
		this.memberName = memberName;
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

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	@Override
	public String toString() {
		return "ReportDTO [reportNo=" + reportNo + ", reportDate=" + reportDate + ", memberNo=" + memberNo
				+ ", documentNo=" + documentNo + ", reportNote=" + reportNote + ", reportStatus=" + reportStatus
				+ ", lineName=" + lineName + ", reportTitle=" + reportTitle + ", memberName=" + memberName + "]";
	}

	
	
	
	

}
