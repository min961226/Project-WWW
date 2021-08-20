package com.qs.www.common.attachment.model.dto;

import java.io.Serializable;

public class BoardAttachmentDTO implements Serializable {
	
	private int boardNo;
	private int attachmentNo;
	private String originalName;
	private String savedName;
	private String savePath;
	private String thumbnailPath;
	private String attatchmentStatus;
	
	public BoardAttachmentDTO() {}

	public BoardAttachmentDTO(int boardNo, int attachmentNo, String originalName, String savedName, String savePath,
			String thumbnailPath, String attatchmentStatus) {
		super();
		this.boardNo = boardNo;
		this.attachmentNo = attachmentNo;
		this.originalName = originalName;
		this.savedName = savedName;
		this.savePath = savePath;
		this.thumbnailPath = thumbnailPath;
		this.attatchmentStatus = attatchmentStatus;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
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

	public String getThumbnailPath() {
		return thumbnailPath;
	}

	public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}

	public String getAttatchmentStatus() {
		return attatchmentStatus;
	}

	public void setAttatchmentStatus(String attatchmentStatus) {
		this.attatchmentStatus = attatchmentStatus;
	}

	@Override
	public String toString() {
		return "BoardAttachmentDTO [boardNo=" + boardNo + ", attachmentNo=" + attachmentNo + ", originalName="
				+ originalName + ", savedName=" + savedName + ", savePath=" + savePath + ", thumbnailPath="
				+ thumbnailPath + ", attatchmentStatus=" + attatchmentStatus + "]";
	}
	
	
	
}
