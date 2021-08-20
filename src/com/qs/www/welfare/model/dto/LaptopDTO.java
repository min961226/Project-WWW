package com.qs.www.welfare.model.dto;

public class LaptopDTO {
	
	private int itemNo;
	private String itemCateGory;
	private String itemName;
	private String reservationStatus;
	private String deleteYn;
	
	public LaptopDTO() {}

	public LaptopDTO(int itemNo, String itemCateGory, String itemName, String reservationStatus, String deleteYn) {
		super();
		this.itemNo = itemNo;
		this.itemCateGory = itemCateGory;
		this.itemName = itemName;
		this.reservationStatus = reservationStatus;
		this.deleteYn = deleteYn;
	}

	public int getItemNo() {
		return itemNo;
	}

	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}

	public String getItemCateGory() {
		return itemCateGory;
	}

	public void setItemCateGory(String itemCateGory) {
		this.itemCateGory = itemCateGory;
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

	public String getDeleteYn() {
		return deleteYn;
	}

	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}

	@Override
	public String toString() {
		return "LaptopDTO [itemNo=" + itemNo + ", itemCateGory=" + itemCateGory + ", itemName=" + itemName
				+ ", reservationStatus=" + reservationStatus + ", deleteYn=" + deleteYn + "]";
	}
	
	
	
	

}
