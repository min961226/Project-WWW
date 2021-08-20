package com.qs.www.mng.welfare.model.dto;

import java.io.Serializable;
import java.sql.Date;

public class ItemDTO implements Serializable{

	private int logNo;
	private int itemNo;
	private String itemCategory;
	private String itemName;
	private String reservationStatus;
	private int memberNo;
	private Date reservationDate;
	private Date returnDate;
	private Date returnDueDate;
	
	public ItemDTO() {}

	public ItemDTO(int logNo, int itemNo, String itemCategory, String itemName, String reservationStatus, int memberNo,
			Date reservationDate, Date returnDate, Date returnDueDate) {
		super();
		this.logNo = logNo;
		this.itemNo = itemNo;
		this.itemCategory = itemCategory;
		this.itemName = itemName;
		this.reservationStatus = reservationStatus;
		this.memberNo = memberNo;
		this.reservationDate = reservationDate;
		this.returnDate = returnDate;
		this.returnDueDate = returnDueDate;
	}

	public int getLogNo() {
		return logNo;
	}

	public void setLogNo(int logNo) {
		this.logNo = logNo;
	}

	public int getItemNo() {
		return itemNo;
	}

	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getReservationStatus() {
		return reservationStatus;
	}

	public void setReservationStatus(String reservationStatus) {
		this.reservationStatus = reservationStatus;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Date getReturnDueDate() {
		return returnDueDate;
	}

	public void setReturnDueDate(Date returnDueDate) {
		this.returnDueDate = returnDueDate;
	}

	@Override
	public String toString() {
		return "ItemDTO [logNo=" + logNo + ", itemNo=" + itemNo + ", itemCategory=" + itemCategory + ", itemName="
				+ itemName + ", reservationStatus=" + reservationStatus + ", memberNo=" + memberNo
				+ ", reservationDate=" + reservationDate + ", returnDate=" + returnDate + ", returnDueDate="
				+ returnDueDate + "]";
	}
}
