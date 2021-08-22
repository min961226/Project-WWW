package com.qs.www.welfare.model.dto;

import java.io.Serializable;
import java.sql.Date;

public class NightTransportationLogDTO implements Serializable{

	private int logNo;
	private int memberNo;
	private Date depositDate;
	private int refReportNo;
	private int transBill;
	
	public NightTransportationLogDTO() {}
	
	public NightTransportationLogDTO(int logNo, int memberNo, Date depositDate, int refReportNo, int transBill) {
		super();
		this.logNo = logNo;
		this.memberNo = memberNo;
		this.depositDate = depositDate;
		this.refReportNo = refReportNo;
		this.transBill = transBill;
	}
	public int getLogNo() {
		return logNo;
	}
	public void setLogNo(int logNo) {
		this.logNo = logNo;
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
	public int getRefReportNo() {
		return refReportNo;
	}
	public void setRefReportNo(int refReportNo) {
		this.refReportNo = refReportNo;
	}
	public int getTransBill() {
		return transBill;
	}
	public void setTransBill(int transBill) {
		this.transBill = transBill;
	}
	
	@Override
	public String toString() {
		return "NightTransportationLogDTO [logNo=" + logNo + ", memberNo=" + memberNo + ", depositDate=" + depositDate
				+ ", refReportNo=" + refReportNo + ", transBill=" + transBill + "]";
	}
	
	
}
