package com.qs.www.schedule.model.dto;

import java.io.Serializable;

public class HolidayTypeDTO implements Serializable {
	
	private int holidayCode;			//휴가코드
	private String holidayName;			//유형명
	private String holidayType;			//휴가구분
	private int dayNumber;				//일수
	private String holidayNote;			//비고
	private String useYn;				//활성화여부
	
	public HolidayTypeDTO() {}

	public HolidayTypeDTO(int holidayCode, String holidayName, String holidayType, int dayNumber, String holidayNote,
			String useYn) {
		super();
		this.holidayCode = holidayCode;
		this.holidayName = holidayName;
		this.holidayType = holidayType;
		this.dayNumber = dayNumber;
		this.holidayNote = holidayNote;
		this.useYn = useYn;
	}

	public int getHolidayCode() {
		return holidayCode;
	}

	public void setHolidayCode(int holidayCode) {
		this.holidayCode = holidayCode;
	}

	public String getHolidayName() {
		return holidayName;
	}

	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}

	public String getHolidayType() {
		return holidayType;
	}

	public void setHolidayType(String holidayType) {
		this.holidayType = holidayType;
	}

	public int getDayNumber() {
		return dayNumber;
	}

	public void setDayNumber(int dayNumber) {
		this.dayNumber = dayNumber;
	}

	public String getHolidayNote() {
		return holidayNote;
	}

	public void setHolidayNote(String holidayNote) {
		this.holidayNote = holidayNote;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	@Override
	public String toString() {
		return "HolidayTypeDTO [holidayCode=" + holidayCode + ", holidayName=" + holidayName + ", holidayType="
				+ holidayType + ", dayNumber=" + dayNumber + ", holidayNote=" + holidayNote + ", useYn=" + useYn + "]";
	}
	
	
	

}
