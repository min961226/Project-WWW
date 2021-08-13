package com.qs.www.main.model.dto;

import java.io.Serializable;
import java.sql.Date;

public class WorkingLogDTO implements Serializable {
	
	private int workLogNo;
	private String workType;
	private int workNo;
	private String selectedDate;
	private WorkingTypeDTO workingType;
	private Date changeDate;
	
	public WorkingLogDTO() {}

	public WorkingLogDTO(int workLogNo, String workType, int workNo, String selectedDate, WorkingTypeDTO workingType,
			Date changeDate) {
		super();
		this.workLogNo = workLogNo;
		this.workType = workType;
		this.workNo = workNo;
		this.selectedDate = selectedDate;
		this.workingType = workingType;
		this.changeDate = changeDate;
	}

	public int getWorkLogNo() {
		return workLogNo;
	}

	public void setWorkLogNo(int workLogNo) {
		this.workLogNo = workLogNo;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public int getWorkNo() {
		return workNo;
	}

	public void setWorkNo(int workNo) {
		this.workNo = workNo;
	}

	public String getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(String selectedDate) {
		this.selectedDate = selectedDate;
	}

	public WorkingTypeDTO getWorkingType() {
		return workingType;
	}

	public void setWorkingType(WorkingTypeDTO workingType) {
		this.workingType = workingType;
	}

	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	@Override
	public String toString() {
		return "WorkingLogDTO [workLogNo=" + workLogNo + ", workType=" + workType + ", workNo=" + workNo
				+ ", selectedDate=" + selectedDate + ", workingType=" + workingType + ", changeDate=" + changeDate
				+ "]";
	}
}
