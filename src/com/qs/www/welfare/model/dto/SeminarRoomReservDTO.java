package com.qs.www.welfare.model.dto;

import java.sql.Date;

public class SeminarRoomReservDTO {

	private int meetingRoomNo;
	private int memberNo;
	private Date useDate;
	private String reservTime;
	private int reservNo;
	private String seminarInfo;
	
	public SeminarRoomReservDTO() {}

	public SeminarRoomReservDTO(int meetingRoomNo, int memberNo, Date useDate, String reservTime, int reservNo,
			String seminarInfo) {
		super();
		this.meetingRoomNo = meetingRoomNo;
		this.memberNo = memberNo;
		this.useDate = useDate;
		this.reservTime = reservTime;
		this.reservNo = reservNo;
		this.seminarInfo = seminarInfo;
	}

	public int getMeetingRoomNo() {
		return meetingRoomNo;
	}

	public void setMeetingRoomNo(int meetingRoomNo) {
		this.meetingRoomNo = meetingRoomNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public Date getUseDate() {
		return useDate;
	}

	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}

	public String getReservTime() {
		return reservTime;
	}

	public void setReservTime(String reservTime) {
		this.reservTime = reservTime;
	}

	public int getReservNo() {
		return reservNo;
	}

	public void setReservNo(int reservNo) {
		this.reservNo = reservNo;
	}

	public String getSeminarInfo() {
		return seminarInfo;
	}

	public void setSeminarInfo(String seminarInfo) {
		this.seminarInfo = seminarInfo;
	}

	@Override
	public String toString() {
		return "SeminarRoomReservDTO [meetingRoomNo=" + meetingRoomNo + ", memberNo=" + memberNo + ", useDate="
				+ useDate + ", reservTime=" + reservTime + ", reservNo=" + reservNo + ", seminarInfo=" + seminarInfo
				+ "]";
	}

	
}
