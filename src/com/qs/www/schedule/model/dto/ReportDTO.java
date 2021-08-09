package com.qs.www.schedule.model.dto;

import java.io.Serializable;

public class ReportDTO implements Serializable{
	
	private int memberNo;
	private int documentNo;
	private String reportNote;
	
	public ReportDTO() {}

	public ReportDTO(int memberNo, int documentNo, String reportNote) {
		super();
		this.memberNo = memberNo;
		this.documentNo = documentNo;
		this.reportNote = reportNote;
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

	@Override
	public String toString() {
		return "ReportDTO [memberNo=" + memberNo + ", documentNo=" + documentNo + ", reportNote=" + reportNote + "]";
	}
	
	

}
