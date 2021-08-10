package com.qs.www.approval.model.dto;

public class ApprovalLineDTO implements java.io.Serializable {
	
	private int lineNo;
	private String lineName;
	private String workType;
	private int memberNo;
	
	
	public ApprovalLineDTO() {}
	public ApprovalLineDTO(int lineNo, String lineName, String workType, int memberNo) {
		super();
		this.lineNo = lineNo;
		this.lineName = lineName;
		this.workType = workType;
		this.memberNo = memberNo;
	}
	public int getLineNo() {
		return lineNo;
	}
	public void setLineNo(int lineNo) {
		this.lineNo = lineNo;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	@Override
	
	public String toString() {
		return "ApprovalLineDTO [lineNo=" + lineNo + ", lineName=" + lineName + ", workType=" + workType + ", memberNo="
				+ memberNo + "]";
	}
	
}
