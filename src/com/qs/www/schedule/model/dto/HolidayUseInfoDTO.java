package com.qs.www.schedule.model.dto;

import java.sql.Date;

public class HolidayUseInfoDTO {
	
	private int holidayLogNo;
	private java.sql.Date holidayStartDay;
	private java.sql.Date holidayEndDay;
	private int holidayReportNo;
	
	public HolidayUseInfoDTO() {}

	public HolidayUseInfoDTO(int holidayLogNo, Date holidayStartDay, Date holidayEndDay, int holidayReportNo) {
		super();
		this.holidayLogNo = holidayLogNo;
		this.holidayStartDay = holidayStartDay;
		this.holidayEndDay = holidayEndDay;
		this.holidayReportNo = holidayReportNo;
	}

	public int getHolidayLogNo() {
		return holidayLogNo;
	}

	public void setHolidayLogNo(int holidayLogNo) {
		this.holidayLogNo = holidayLogNo;
	}

	public java.sql.Date getHolidayStartDay() {
		return holidayStartDay;
	}

	public void setHolidayStartDay(java.sql.Date holidayStartDay) {
		this.holidayStartDay = holidayStartDay;
	}

	public java.sql.Date getHolidayEndDay() {
		return holidayEndDay;
	}

	public void setHolidayEndDay(java.sql.Date holidayEndDay) {
		this.holidayEndDay = holidayEndDay;
	}

	public int getHolidayReportNo() {
		return holidayReportNo;
	}

	public void setHolidayReportNo(int holidayReportNo) {
		this.holidayReportNo = holidayReportNo;
	}

	@Override
	public String toString() {
		return "HolidayUseInfoDTO [holidayLogNo=" + holidayLogNo + ", holidayStartDay=" + holidayStartDay
				+ ", holidayEndDay=" + holidayEndDay + ", holidayReportNo=" + holidayReportNo + "]";
	}
	
	

}
