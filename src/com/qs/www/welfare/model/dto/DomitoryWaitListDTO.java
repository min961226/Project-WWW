package com.qs.www.welfare.model.dto;

import java.io.Serializable;
import java.sql.Date;

public class DomitoryWaitListDTO implements Serializable{

	private int waitingNo;
	private int memberNo;
	private String memberName;
	private Date hopeDate;
	private String waitingStatus;
	
	public DomitoryWaitListDTO() {}


	public DomitoryWaitListDTO(int waitingNo, int memberNo, String memberName, Date hopeDate, String waitingStatus) {
		super();
		this.waitingNo = waitingNo;
		this.memberNo = memberNo;
		this.memberName = memberName;
		this.hopeDate = hopeDate;
		this.waitingStatus = waitingStatus;
	}


	public int getWaitingNo() {
		return waitingNo;
	}


	public void setWaitingNo(int waitingNo) {
		this.waitingNo = waitingNo;
	}


	public int getMemberNo() {
		return memberNo;
	}


	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}


	public String getMemberName() {
		return memberName;
	}


	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}


	public Date getHopeDate() {
		return hopeDate;
	}


	public void setHopeDate(Date hopeDate) {
		this.hopeDate = hopeDate;
	}


	public String getWaitingStatus() {
		return waitingStatus;
	}


	public void setWaitingStatus(String waitingStatus) {
		this.waitingStatus = waitingStatus;
	}


	@Override
	public String toString() {
		return "DomitoryWaitListDTO [waitingNo=" + waitingNo + ", memberNo=" + memberNo + ", memberName=" + memberName
				+ ", hopeDate=" + hopeDate + ", waitingStatus=" + waitingStatus + "]";
	}	
	
	
}
