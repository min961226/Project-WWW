package com.qs.www.approval.model.dto;

import java.io.Serializable;

public class ApproverDTO  implements Serializable {
	private int memberNo;
	private int lineNo;
	private String approverName;
	private String approverType;
	private String jobName;
	private String deptName;
	private int priority;
	
	
	public ApproverDTO() {}


	public ApproverDTO(int memberNo, int lineNo, String approverName, String approverType, String jobName,
			String deptName, int priority) {
		super();
		this.memberNo = memberNo;
		this.lineNo = lineNo;
		this.approverName = approverName;
		this.approverType = approverType;
		this.jobName = jobName;
		this.deptName = deptName;
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
		return approverName;
	}


	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}


	public String getApproverType() {
		return approverType;
	}


	public void setApproverType(String approverType) {
		this.approverType = approverType;
	}


	public String getJobName() {
		return jobName;
	}


	public void setJobName(String jobName) {
		this.jobName = jobName;
	}


	public String getDeptName() {
		return deptName;
	}


	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}


	public int getPriority() {
		return priority;
	}


	public void setPriority(int priority) {
		this.priority = priority;
	}


	@Override
	public String toString() {
		return "ApproverDTO [memberNo=" + memberNo + ", lineNo=" + lineNo + ", approverName=" + approverName
				+ ", approverType=" + approverType + ", jobName=" + jobName + ", deptName=" + deptName + ", priority="
				+ priority + "]";
	}

	
	
	
	

}
