package com.qs.www.welfare.model.dto;

public class DomitoryListDTO {
	
	private int domitoryManageNo;
	private int domitoryBlockNo;
	private int roomNo;
	private int maxCapacity;
	
	
	public DomitoryListDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public DomitoryListDTO(int domitoryManageNo, int domitoryBlockNo, int roomNo, int maxCapacity) {
		super();
		this.domitoryManageNo = domitoryManageNo;
		this.domitoryBlockNo = domitoryBlockNo;
		this.roomNo = roomNo;
		this.maxCapacity = maxCapacity;
	}
	public int getDomitoryManageNo() {
		return domitoryManageNo;
	}
	public void setDomitoryManageNo(int domitoryManageNo) {
		this.domitoryManageNo = domitoryManageNo;
	}
	public int getDomitoryBlockNo() {
		return domitoryBlockNo;
	}
	public void setDomitoryBlockNo(int domitoryBlockNo) {
		this.domitoryBlockNo = domitoryBlockNo;
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
	@Override
	public String toString() {
		return "DomitoryListDTO [domitoryManageNo=" + domitoryManageNo + ", domitoryBlockNo=" + domitoryBlockNo
				+ ", roomNo=" + roomNo + ", maxCapacity=" + maxCapacity + "]";
	}
	
	
}
