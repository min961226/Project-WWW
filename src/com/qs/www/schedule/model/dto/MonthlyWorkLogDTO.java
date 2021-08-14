package com.qs.www.schedule.model.dto;

import java.io.Serializable;
import java.sql.Date;

public class MonthlyWorkLogDTO implements Serializable {

	private int memberNo;
	private String monthAndYear;
	private java.sql.Date monthStartDate;
	private java.sql.Date monthEndDate;
	private String selectedDate;
	
	public MonthlyWorkLogDTO() {}

	public MonthlyWorkLogDTO(int memberNo, String monthAndYear, Date monthStartDate, Date monthEndDate,
			String selectedDate) {
		super();
		this.memberNo = memberNo;
		this.monthAndYear = monthAndYear;
		this.monthStartDate = monthStartDate;
		this.monthEndDate = monthEndDate;
		this.selectedDate = selectedDate;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMonthAndYear() {
		return monthAndYear;
	}

	public void setMonthAndYear(String monthAndYear) {
		this.monthAndYear = monthAndYear;
	}

	public java.sql.Date getMonthStartDate() {
		return monthStartDate;
	}

	public void setMonthStartDate(java.sql.Date monthStartDate) {
		this.monthStartDate = monthStartDate;
	}

	public java.sql.Date getMonthEndDate() {
		return monthEndDate;
	}

	public void setMonthEndDate(java.sql.Date monthEndDate) {
		this.monthEndDate = monthEndDate;
	}

	public String getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(String selectedDate) {
		this.selectedDate = selectedDate;
	}

	@Override
	public String toString() {
		return "MonthlyWorkLogDTO [memberNo=" + memberNo + ", monthAndYear=" + monthAndYear + ", monthStartDate="
				+ monthStartDate + ", monthEndDate=" + monthEndDate + ", selectedDate=" + selectedDate + "]";
	}

	

	
	
	
	
}
