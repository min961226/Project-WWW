package com.qs.www.schedule.model.dto;

import java.sql.Date;

public class HolidayUseInfoDTO {
	
	private int holidayLogNo;
	private java.sql.Date holidayStartDay;
	private String holidayStartDayAllday;
	private java.sql.Date holidayEndDay;
	private String holidayEndDayAllday;
	private int holidayReportNo;
	
	public HolidayUseInfoDTO() {}

	public HolidayUseInfoDTO(int holidayLogNo, Date holidayStartDay, String holidayStartDayAllday, Date holidayEndDay,
			String holidayEndDayAllday, int holidayReportNo) {
		super();
		this.holidayLogNo = holidayLogNo;
		this.holidayStartDay = holidayStartDay;
		this.holidayStartDayAllday = holidayStartDayAllday;
		this.holidayEndDay = holidayEndDay;
		this.holidayEndDayAllday = holidayEndDayAllday;
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

	public String getHolidayStartDayAllday() {
		return holidayStartDayAllday;
	}

	public void setHolidayStartDayAllday(String holidayStartDayAllday) {
		this.holidayStartDayAllday = holidayStartDayAllday;
	}

	public java.sql.Date getHolidayEndDay() {
		return holidayEndDay;
	}

	public void setHolidayEndDay(java.sql.Date holidayEndDay) {
		this.holidayEndDay = holidayEndDay;
	}

	public String getHolidayEndDayAllday() {
		return holidayEndDayAllday;
	}

	public void setHolidayEndDayAllday(String holidayEndDayAllday) {
		this.holidayEndDayAllday = holidayEndDayAllday;
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
				+ ", holidayStartDayAllday=" + holidayStartDayAllday + ", holidayEndDay=" + holidayEndDay
				+ ", holidayEndDayAllday=" + holidayEndDayAllday + ", holidayReportNo=" + holidayReportNo + "]";
	}

	

}
