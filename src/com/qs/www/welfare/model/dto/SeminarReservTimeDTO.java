package com.qs.www.welfare.model.dto;

import java.io.Serializable;


public class SeminarReservTimeDTO implements Serializable{
	
	private int reservNo;
	private String reservTime;
	
	public SeminarReservTimeDTO() {}

	public SeminarReservTimeDTO(int reservNo, String reservTime) {
		super();
		this.reservNo = reservNo;
		this.reservTime = reservTime;
	}

	public int getReservNo() {
		return reservNo;
	}

	public void setReservNo(int reservNo) {
		this.reservNo = reservNo;
	}

	public String getReservTime() {
		return reservTime;
	}

	public void setReservTime(String reservTime) {
		this.reservTime = reservTime;
	}

	@Override
	public String toString() {
		return "SeminarReservTimeDTO [reservNo=" + reservNo + ", reservTime=" + reservTime + "]";
	}

}
