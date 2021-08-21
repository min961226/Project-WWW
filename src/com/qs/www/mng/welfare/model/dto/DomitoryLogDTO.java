package com.qs.www.mng.welfare.model.dto;

import java.sql.Date;

public class DomitoryLogDTO {
	
	private int logNo;
	private Date inDate;
	private Date outDate;
	private String outReason;
	private int domitoryManageNo;
	private int memberNo;
	private String memberName;
	private int blockNo;
	private int roomNo;
	
	public DomitoryLogDTO() {}

	public DomitoryLogDTO(int logNo, Date inDate, Date outDate, String outReason, int domitoryManageNo, int memberNo,
			String memberName, int blockNo, int roomNo) {
		super();
		this.logNo = logNo;
		this.inDate = inDate;
		this.outDate = outDate;
		this.outReason = outReason;
		this.domitoryManageNo = domitoryManageNo;
		this.memberNo = memberNo;
		this.memberName = memberName;
		this.blockNo = blockNo;
		this.roomNo = roomNo;
	}

	public int getLogNo() {
		return logNo;
	}

	public void setLogNo(int logNo) {
		this.logNo = logNo;
	}

	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public String getOutReason() {
		return outReason;
	}

	public void setOutReason(String outReason) {
		this.outReason = outReason;
	}

	public int getDomitoryManageNo() {
		return domitoryManageNo;
	}

	public void setDomitoryManageNo(int domitoryManageNo) {
		this.domitoryManageNo = domitoryManageNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public int getBlockNo() {
		return blockNo;
	}

	public void setBlockNo(int blockNo) {
		this.blockNo = blockNo;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	@Override
	public String toString() {
		return "DomitoryLogDTO [logNo=" + logNo + ", inDate=" + inDate + ", outDate=" + outDate + ", outReason="
				+ outReason + ", domitoryManageNo=" + domitoryManageNo + ", memberNo=" + memberNo + ", memberName="
				+ memberName + ", blockNo=" + blockNo + ", roomNo=" + roomNo + "]";
	}
	
}
