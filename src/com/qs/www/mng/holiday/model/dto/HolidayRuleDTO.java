package com.qs.www.mng.holiday.model.dto;

import java.io.Serializable;

public class HolidayRuleDTO implements Serializable {
	private String ruleCode;
	private int workPeriod;
	private int dayNumber;
	private String FiscalStateDate;
	
	public HolidayRuleDTO() {}
	public HolidayRuleDTO(String ruleCode, int workPeriod, int dayNumber, String fiscalStateDate) {
		super();
		this.ruleCode = ruleCode;
		this.workPeriod = workPeriod;
		this.dayNumber = dayNumber;
		FiscalStateDate = fiscalStateDate;
	}
	public String getRuleCode() {
		return ruleCode;
	}
	public void setRuleCode(String ruleCode) {
		this.ruleCode = ruleCode;
	}
	public int getWorkPeriod() {
		return workPeriod;
	}
	public void setWorkPeriod(int workPeriod) {
		this.workPeriod = workPeriod;
	}
	public int getDayNumber() {
		return dayNumber;
	}
	public void setDayNumber(int dayNumber) {
		this.dayNumber = dayNumber;
	}
	public String getFiscalStateDate() {
		return FiscalStateDate;
	}
	public void setFiscalStateDate(String fiscalStateDate) {
		FiscalStateDate = fiscalStateDate;
	}
	@Override
	public String toString() {
		return "holidayRuleDTO [ruleCode=" + ruleCode + ", workPeriod=" + workPeriod + ", dayNumber=" + dayNumber
				+ ", FiscalStateDate=" + FiscalStateDate + "]";
	}
	
	
	
}
