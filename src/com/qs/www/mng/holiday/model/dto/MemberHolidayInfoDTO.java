package com.qs.www.mng.holiday.model.dto;

import java.io.Serializable;
import java.sql.Date;

public class MemberHolidayInfoDTO implements Serializable {
	private int memberNo;
	private String name;
	private Date enrollDate;
	private int remainingHoliday;
	private int passivedayNumber;
	private int autoDayNumber;
	
	public MemberHolidayInfoDTO() {}
	public MemberHolidayInfoDTO(int memberNo, String name, Date enrollDate, int remainingHoliday, int passivedayNumber,
			int autoDayNumber) {
		super();
		this.memberNo = memberNo;
		this.name = name;
		this.enrollDate = enrollDate;
		this.remainingHoliday = remainingHoliday;
		this.passivedayNumber = passivedayNumber;
		this.autoDayNumber = autoDayNumber;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}
	public int getRemainingHoliday() {
		return remainingHoliday;
	}
	public void setRemainingHoliday(int remainingHoliday) {
		this.remainingHoliday = remainingHoliday;
	}
	public int getPassivedayNumber() {
		return passivedayNumber;
	}
	public void setPassivedayNumber(int passivedayNumber) {
		this.passivedayNumber = passivedayNumber;
	}
	public int getAutoDayNumber() {
		return autoDayNumber;
	}
	public void setAutoDayNumber(int autoDayNumber) {
		this.autoDayNumber = autoDayNumber;
	}
	@Override
	public String toString() {
		return "MemberHolidayInfoDTO [memberNo=" + memberNo + ", name=" + name + ", enrollDate=" + enrollDate
				+ ", remainingHoliday=" + remainingHoliday + ", passivedayNumber=" + passivedayNumber
				+ ", autoDayNumber=" + autoDayNumber + "]";
	}
	
	
	
}
