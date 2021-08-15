package com.qs.www.welfare.model.dto;

import java.io.Serializable;

public class SeminarRoomDTO implements Serializable{
	
	private int roomNo;
	private int maxCapacity;
	private char projectorYn;
	private String roomName;
	
	public SeminarRoomDTO() {}

	public SeminarRoomDTO(int roomNo, int maxCapacity, char projectorYn, String roomName) {
		super();
		this.roomNo = roomNo;
		this.maxCapacity = maxCapacity;
		this.projectorYn = projectorYn;
		this.roomName = roomName;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public char getProjectorYn() {
		return projectorYn;
	}

	public void setProjectorYn(char projectorYn) {
		this.projectorYn = projectorYn;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	@Override
	public String toString() {
		return "SeminarRoomDTO [roomNo=" + roomNo + ", maxCapacity=" + maxCapacity + ", projectorYn=" + projectorYn
				+ ", roomName=" + roomName + "]";
	}
	
	
}
