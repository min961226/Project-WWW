package com.qs.www.main.model.dto;

import java.io.Serializable;
import java.sql.Date;

public class WorkingLogDTO implements Serializable {
	
	private int workLogNo;
	private int workNo;
	private String workType;
	private WorkingTypeDTO workingType;
	private String selectedDate;
	private String selectedDayOfWeek;
	private Date changeDate;
	private long dailyWorkTime;
	private java.util.Date selectedSqlDate;
	
	public WorkingLogDTO() {}

	public WorkingLogDTO(int workLogNo, int workNo, String workType, WorkingTypeDTO workingType, String selectedDate,
			String selectedDayOfWeek, Date changeDate, long dailyWorkTime, java.util.Date selectedSqlDate) {
		super();
		this.workLogNo = workLogNo;
		this.workNo = workNo;
		this.workType = workType;
		this.workingType = workingType;
		this.selectedDate = selectedDate;
		this.selectedDayOfWeek = selectedDayOfWeek;
		this.changeDate = changeDate;
		this.dailyWorkTime = dailyWorkTime;
		this.selectedSqlDate = selectedSqlDate;
	}

	public int getWorkLogNo() {
		return workLogNo;
	}

	public void setWorkLogNo(int workLogNo) {
		this.workLogNo = workLogNo;
	}

	public int getWorkNo() {
		return workNo;
	}

	public void setWorkNo(int workNo) {
		this.workNo = workNo;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public WorkingTypeDTO getWorkingType() {
		return workingType;
	}

	public void setWorkingType(WorkingTypeDTO workingType) {
		this.workingType = workingType;
	}

	public String getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(String selectedDate) {
		this.selectedDate = selectedDate;
	}

	public String getSelectedDayOfWeek() {
		return selectedDayOfWeek;
	}

	public void setSelectedDayOfWeek(String selectedDayOfWeek) {
		this.selectedDayOfWeek = selectedDayOfWeek;
	}

	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	public long getDailyWorkTime() {
		return dailyWorkTime;
	}

	public void setDailyWorkTime(long dailyWorkTime) {
		this.dailyWorkTime = dailyWorkTime;
	}

	public java.util.Date getSelectedSqlDate() {
		return selectedSqlDate;
	}

	public void setSelectedSqlDate(java.util.Date selectedSqlDate) {
		this.selectedSqlDate = selectedSqlDate;
	}

	@Override
	public String toString() {
		return "WorkingLogDTO [workLogNo=" + workLogNo + ", workNo=" + workNo + ", workType=" + workType
				+ ", workingType=" + workingType + ", selectedDate=" + selectedDate + ", selectedDayOfWeek="
				+ selectedDayOfWeek + ", changeDate=" + changeDate + ", dailyWorkTime=" + dailyWorkTime
				+ ", selectedSqlDate=" + selectedSqlDate + "]";
	}
}
