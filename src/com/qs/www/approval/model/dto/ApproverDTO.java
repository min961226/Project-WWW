package com.qs.www.approval.model.dto;

import java.io.Serializable;

public class ApproverDTO  implements Serializable {
	private int memberNo;
	private int lineNo;
	private String ApproverName;
	private String ApproverType;
	private int priority;
	
	public ApproverDTO() {}
	public ApproverDTO(int memberNo, int lineNo, String approverName, String approverType, int priority) {
		super();
		this.memberNo = memberNo;
		this.lineNo = lineNo;
		ApproverName = approverName;
		ApproverType = approverType;
		this.priority = priority;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getLineNo() {
		return lineNo;
	}
	public void setLineNo(int lineNo) {
		this.lineNo = lineNo;
	}
	public String getApproverName() {
		return ApproverName;
	}
	public void setApproverName(String approverName) {
		ApproverName = approverName;
	}
	public String getApproverType() {
		return ApproverType;
	}
	public void setApproverType(String approverType) {
		ApproverType = approverType;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	@Override
	public String toString() {
		return "ApproverDTO [memberNo=" + memberNo + ", lineNo=" + lineNo + ", ApproverName=" + ApproverName
				+ ", ApproverType=" + ApproverType + ", priority=" + priority + "]";
	}
	
	

}
