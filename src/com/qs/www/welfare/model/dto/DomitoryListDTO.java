package com.qs.www.welfare.model.dto;

public class DomitoryListDTO {
	
	private int domitoryManageNo;
	private int domitoryBlockNo;
	private int roomNo;
	private int currCapacity;
	private int maxCapacity;
	
	
	public DomitoryListDTO() {
		// TODO Auto-generated constructor stub
	}


	public DomitoryListDTO(int domitoryManageNo, int domitoryBlockNo, int roomNo, int currCapacity, int maxCapacity) {
		super();
		this.domitoryManageNo = domitoryManageNo;
		this.domitoryBlockNo = domitoryBlockNo;
		this.roomNo = roomNo;
		this.currCapacity = currCapacity;
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


	public int getCurrCapacity() {
		return currCapacity;
	}


	public void setCurrCapacity(int currCapacity) {
		this.currCapacity = currCapacity;
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
				+ ", roomNo=" + roomNo + ", currCapacity=" + currCapacity + ", maxCapacity=" + maxCapacity + "]";
	}
	
	
}
