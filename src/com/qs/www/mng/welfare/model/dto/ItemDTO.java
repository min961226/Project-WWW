package com.qs.www.mng.welfare.model.dto;

import java.io.Serializable;

public class ItemDTO implements Serializable{

	private int itemNo;
	private String itemCategory;
	private String itemName;
	
	public ItemDTO() {}
	
	public ItemDTO(int itemNo, String itemCategory, String itemName) {
		super();
		this.itemNo = itemNo;
		this.itemCategory = itemCategory;
		this.itemName = itemName;
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

	@Override
	public String toString() {
		return "ItemDTO [itemNo=" + itemNo + ", itemCategory=" + itemCategory + ", itemName=" + itemName + "]";
	}
	
	
	
	
}
