package com.qs.www.welfare.model.dto;

import java.sql.Date;

public class WelfareListDTO {

	private int reportNo;
	private String memberNo;
	private int documentNo;
	private String reportNote;
	private String lineName;
	private String welfareTitle;
	private Date date;
	private int priority;
	
	public WelfareListDTO() {}
	
	public WelfareListDTO(int reportNo, String memberNo, int documentNo, String reportNote, String lineName,
			String welfareTitle, Date date, int priority) {
		super();
		this.reportNo = reportNo;
		this.memberNo = memberNo;
		this.documentNo = documentNo;
		this.reportNote = reportNote;
		this.lineName = lineName;
		this.welfareTitle = welfareTitle;
		this.date = date;
		this.priority = priority;
	}
	
	public int getReportNo() {
		return reportNo;
	}
	public void setReportNo(int reportNo) {
		this.reportNo = reportNo;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
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
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public String getWelfareTitle() {
		return welfareTitle;
	}
	public void setWelfareTitle(String welfareTitle) {
		this.welfareTitle = welfareTitle;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	@Override
	public String toString() {
		return "WelfareListDTO [reportNo=" + reportNo + ", memberNo=" + memberNo + ", documentNo=" + documentNo
				+ ", reportNote=" + reportNote + ", lineName=" + lineName + ", welfareTitle=" + welfareTitle + ", date="
				+ date + ", priority=" + priority + "]";
	}
	
	
	

	
	
	
}
