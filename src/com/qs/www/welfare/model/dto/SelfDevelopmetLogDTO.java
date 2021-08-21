package com.qs.www.welfare.model.dto;

import java.io.Serializable;
import java.sql.Date;

public class SelfDevelopmetLogDTO implements Serializable{
	
	private int logNo;
	private int developmentNo;
	private int memberNo;
	private Date depositDate;
	private int reportNo;
	
	public SelfDevelopmetLogDTO() {}

	public SelfDevelopmetLogDTO(int logNo, int developmentNo, int memberNo, Date depositDate, int reportNo) {
		super();
		this.logNo = logNo;
		this.developmentNo = developmentNo;
		this.memberNo = memberNo;
		this.depositDate = depositDate;
		this.reportNo = reportNo;
	}

	public int getLogNo() {
		return logNo;
	}

	public void setLogNo(int logNo) {
		this.logNo = logNo;
	}

	public int getDevelopmentNo() {
		return developmentNo;
	}

	public void setDevelopmentNo(int developmentNo) {
		this.developmentNo = developmentNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public Date getDepositDate() {
		return depositDate;
	}

	public void setDepositDate(Date depositDate) {
		this.depositDate = depositDate;
	}

	public int getReportNo() {
		return reportNo;
	}

	public void setReportNo(int reportNo) {
		this.reportNo = reportNo;
	}

	@Override
	public String toString() {
		return "SelfDevelopmetLogDTO [logNo=" + logNo + ", developmentNo=" + developmentNo + ", memberNo=" + memberNo
				+ ", depositDate=" + depositDate + ", reportNo=" + reportNo + "]";
	}
	
	
}
