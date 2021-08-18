package com.qs.www.schedule.model.dto;

import java.io.Serializable;

public class WorkingDocumentItemDTO implements Serializable{

	private int reportNo;
	private int documentNo;
	private int priority;
	private String itemContent;
	private String itemName;
	
	public WorkingDocumentItemDTO() {}

	public WorkingDocumentItemDTO(int reportNo, int documentNo, int priority, String itemContent, String itemName) {
		super();
		this.reportNo = reportNo;
		this.documentNo = documentNo;
		this.priority = priority;
		this.itemContent = itemContent;
		this.itemName = itemName;
	}

	public int getReportNo() {
		return reportNo;
	}

	public void setReportNo(int reportNo) {
		this.reportNo = reportNo;
	}

	public int getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(int documentNo) {
		this.documentNo = documentNo;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getItemContent() {
		return itemContent;
	}

	public void setItemContent(String itemContent) {
		this.itemContent = itemContent;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Override
	public String toString() {
		return "WorkingDocumentItemDTO [reportNo=" + reportNo + ", documentNo=" + documentNo + ", priority=" + priority
				+ ", itemContent=" + itemContent + ", itemName=" + itemName + "]";
	}
	
	
	
}
