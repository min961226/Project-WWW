package com.qs.www.welfare.model.dto;

import java.io.Serializable;
import java.sql.Date;

public class WelfareListDTO implements Serializable{

	private int rowNum;
	private int reportNo;
	private String memberNo;
	private int documentNo;
	private String reportNote;
	private String lineName;
	private String selfDevName;
	private String welfareTitle;
	private Date selfDevDate;
	private int priority;
	
	public WelfareListDTO() {}

	public WelfareListDTO(int rowNum, int reportNo, String memberNo, int documentNo, String reportNote, String lineName,
			String selfDevName, String welfareTitle, Date selfDevDate, int priority) {
		super();
		this.rowNum = rowNum;
		this.reportNo = reportNo;
		this.memberNo = memberNo;
		this.documentNo = documentNo;
		this.reportNote = reportNote;
		this.lineName = lineName;
		this.selfDevName = selfDevName;
		this.welfareTitle = welfareTitle;
		this.selfDevDate = selfDevDate;
		this.priority = priority;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
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

	public String getSelfDevName() {
		return selfDevName;
	}

	public void setSelfDevName(String selfDevName) {
		this.selfDevName = selfDevName;
	}

	public String getWelfareTitle() {
		return welfareTitle;
	}

	public void setWelfareTitle(String welfareTitle) {
		this.welfareTitle = welfareTitle;
	}

	public Date getSelfDevDate() {
		return selfDevDate;
	}

	public void setSelfDevDate(Date selfDevDate) {
		this.selfDevDate = selfDevDate;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		return "WelfareListDTO [rowNum=" + rowNum + ", reportNo=" + reportNo + ", memberNo=" + memberNo
				+ ", documentNo=" + documentNo + ", reportNote=" + reportNote + ", lineName=" + lineName
				+ ", selfDevName=" + selfDevName + ", welfareTitle=" + welfareTitle + ", selfDevDate=" + selfDevDate
				+ ", priority=" + priority + "]";
	}

	
	
}
