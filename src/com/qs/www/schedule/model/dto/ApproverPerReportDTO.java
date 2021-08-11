package com.qs.www.schedule.model.dto;

import java.io.Serializable;

public class ApproverPerReportDTO implements Serializable{
	
	private int reportNo;					//상신번호
	private int memberNo;					//결재자사번
	private int priority;					//결재순번
	private String approverType;			//결재상태
	
	public ApproverPerReportDTO() {}

	public ApproverPerReportDTO(int reportNo, int memberNo, int priority, String approverType) {
		super();
		this.reportNo = reportNo;
		this.memberNo = memberNo;
		this.priority = priority;
		this.approverType = approverType;
	}

	public int getReportNo() {
		return reportNo;
	}

	public void setReportNo(int reportNo) {
		this.reportNo = reportNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getApproverType() {
		return approverType;
	}

	public void setApproverType(String approverType) {
		this.approverType = approverType;
	}

	@Override
	public String toString() {
		return "ApproverPerReportDTO [reportNo=" + reportNo + ", memberNo=" + memberNo + ", priority=" + priority
				+ ", approverType=" + approverType + "]";
	}

	
	
	
	

}
