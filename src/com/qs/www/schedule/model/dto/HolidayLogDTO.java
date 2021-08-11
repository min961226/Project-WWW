package com.qs.www.schedule.model.dto;

import java.io.Serializable;

public class HolidayLogDTO implements Serializable{
	
	private int holidayLogNo;					//휴가내역번호
	private int memberNo;						//사번
	private java.sql.Date logOccurDate;			//휴가내역발생일자
	private String logNote;						//비고
	private String logType;						//내역구분(사용, 만료, 생성, 지급)
	private int holidayCode;					//휴가코드(휴가를 사용한 경우)
	private String ruleCode;					//규칙코드(규칙으로 발생한 경우)
	
	public HolidayLogDTO() {}
	

}
