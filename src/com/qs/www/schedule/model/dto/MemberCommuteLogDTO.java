package com.qs.www.schedule.model.dto;

import java.io.Serializable;
import java.util.List;

public class MemberCommuteLogDTO implements Serializable{
	
	private int memberNo;
	private String deptName;
	private String jobName;
	private List<DailyCommuteDTO> dailyCommuteList;
	
	public MemberCommuteLogDTO() {}

	public MemberCommuteLogDTO(int memberNo, String deptName, String jobName, List<DailyCommuteDTO> dailyCommuteList) {
		super();
		this.memberNo = memberNo;
		this.deptName = deptName;
		this.jobName = jobName;
		this.dailyCommuteList = dailyCommuteList;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public List<DailyCommuteDTO> getDailyCommuteList() {
		return dailyCommuteList;
	}

	public void setDailyCommuteList(List<DailyCommuteDTO> dailyCommuteList) {
		this.dailyCommuteList = dailyCommuteList;
	}

	@Override
	public String toString() {
		return "MemberCommuteLogDTO [memberNo=" + memberNo + ", deptName=" + deptName + ", jobName=" + jobName
				+ ", dailyCommuteList=" + dailyCommuteList + "]";
	}
	
	
	
	

}
