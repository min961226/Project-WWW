package com.qs.www.common.attachment.model.dto;

import java.io.Serializable;

public class AttachmentDTO implements Serializable {
	
	private int reportNo;
	private int attachmentNo;
	private String originalName;
	private String savedName;
	private String savePath;
	private char attatchmentStatus;
	
	public AttachmentDTO() {}
	
	public AttachmentDTO(int reportNo, int attachmentNo, String originalName, String savedName, String savePath,
			char attatchmentStatus) {
		super();
		this.reportNo = reportNo;
		this.attachmentNo = attachmentNo;
		this.originalName = originalName;
		this.savedName = savedName;
		this.savePath = savePath;
		this.attatchmentStatus = attatchmentStatus;
	}

	public int getReportNo() {
		return reportNo;
	}

	public void setReportNo(int reportNo) {
		this.reportNo = reportNo;
	}

	public int getAttachmentNo() {
		return attachmentNo;
	}

	public void setAttachmentNo(int attachmentNo) {
		this.attachmentNo = attachmentNo;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public String getSavedName() {
		return savedName;
	}

	public void setSavedName(String savedName) {
		this.savedName = savedName;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public char getAttatchmentStatus() {
		return attatchmentStatus;
	}

	public void setAttatchmentStatus(char attatchmentStatus) {
		this.attatchmentStatus = attatchmentStatus;
	}

	@Override
	public String toString() {
		return "AttachmentDTO [reportNo=" + reportNo + ", attachmentNo=" + attachmentNo + ", originalName="
				+ originalName + ", savedName=" + savedName + ", savePath=" + savePath + ", attatchmentStatus="
				+ attatchmentStatus + "]";
	}
	
	
	
}
