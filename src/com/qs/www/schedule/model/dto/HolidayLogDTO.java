package com.qs.www.schedule.model.dto;

import java.io.Serializable;
import java.sql.Date;

public class HolidayLogDTO implements Serializable{
	
	private int holidayLogNo;					//휴가내역번호
	private int memberNo;						//사번
	private java.sql.Date logOccurDate;			//휴가내역발생일자
	private String logNote;						//비고
	private String logType;						//내역구분(사용, 만료, 생성, 지급)
	private int holidayCode;					//휴가코드(휴가를 사용한 경우)
	private String holidayDuringDate;			//기간일수
	private String ruleCode;					//규칙코드(규칙으로 발생한 경우)
	private String isProducedByRule;			//규칙생성여부(규칙으로 만들어진 것인지 판단)
	
	public HolidayLogDTO() {}

	public HolidayLogDTO(int holidayLogNo, int memberNo, Date logOccurDate, String logNote, String logType,
			int holidayCode, String holidayDuringDate, String ruleCode, String isProducedByRule) {
		super();
		this.holidayLogNo = holidayLogNo;
		this.memberNo = memberNo;
		this.logOccurDate = logOccurDate;
		this.logNote = logNote;
		this.logType = logType;
		this.holidayCode = holidayCode;
		this.holidayDuringDate = holidayDuringDate;
		this.ruleCode = ruleCode;
		this.isProducedByRule = isProducedByRule;
	}

	public int getHolidayLogNo() {
		return holidayLogNo;
	}

	public void setHolidayLogNo(int holidayLogNo) {
		this.holidayLogNo = holidayLogNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public java.sql.Date getLogOccurDate() {
		return logOccurDate;
	}

	public void setLogOccurDate(java.sql.Date logOccurDate) {
		this.logOccurDate = logOccurDate;
	}

	public String getLogNote() {
		return logNote;
	}

	public void setLogNote(String logNote) {
		this.logNote = logNote;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public int getHolidayCode() {
		return holidayCode;
	}

	public void setHolidayCode(int holidayCode) {
		this.holidayCode = holidayCode;
	}

	public String getHolidayDuringDate() {
		return holidayDuringDate;
	}

	public void setHolidayDuringDate(String holidayDuringDate) {
		this.holidayDuringDate = holidayDuringDate;
	}

	public String getRuleCode() {
		return ruleCode;
	}

	public void setRuleCode(String ruleCode) {
		this.ruleCode = ruleCode;
	}

	public String getIsProducedByRule() {
		return isProducedByRule;
	}

	public void setIsProducedByRule(String isProducedByRule) {
		this.isProducedByRule = isProducedByRule;
	}

	@Override
	public String toString() {
		return "HolidayLogDTO [holidayLogNo=" + holidayLogNo + ", memberNo=" + memberNo + ", logOccurDate="
				+ logOccurDate + ", logNote=" + logNote + ", logType=" + logType + ", holidayCode=" + holidayCode
				+ ", holidayDuringDate=" + holidayDuringDate + ", ruleCode=" + ruleCode + ", isProducedByRule="
				+ isProducedByRule + "]";
	}
	
	
	
	
}
