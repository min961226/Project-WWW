package com.qs.www.schedule.model.dto;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

import com.qs.www.member.model.dto.CheckQuestionDTO;
import com.qs.www.member.model.dto.DepartmentDTO;
import com.qs.www.member.model.dto.JobDTO;
import com.qs.www.member.model.dto.RoleDTO;

public class TeamWorkingHourDTO implements Serializable {
	
	private int memberNo;
	private String memberId;
	private String name;
	private String roleCode;
	private String roleName;
	
	private String deptCode;
	private String deptName;
	
	private String jobCode;
	private String jobName;

	private String appWorkType;
	private int workCode;
	
	private LocalDate selectedDate;
	
	private StandardWorkDTO standardWorkDTO;
	private CustomWorkDTO customWorkDTO;
	private CustomWorkTimeDTO customWorkTimeDTO;
	
	public TeamWorkingHourDTO() {}

	public TeamWorkingHourDTO(int memberNo, String memberId, String name, String roleCode, String roleName,
			String deptCode, String deptName, String jobCode, String jobName, String appWorkType, int workCode,
			LocalDate selectedDate, StandardWorkDTO standardWorkDTO, CustomWorkDTO customWorkDTO,
			CustomWorkTimeDTO customWorkTimeDTO) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.name = name;
		this.roleCode = roleCode;
		this.roleName = roleName;
		this.deptCode = deptCode;
		this.deptName = deptName;
		this.jobCode = jobCode;
		this.jobName = jobName;
		this.appWorkType = appWorkType;
		this.workCode = workCode;
		this.selectedDate = selectedDate;
		this.standardWorkDTO = standardWorkDTO;
		this.customWorkDTO = customWorkDTO;
		this.customWorkTimeDTO = customWorkTimeDTO;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getAppWorkType() {
		return appWorkType;
	}

	public void setAppWorkType(String appWorkType) {
		this.appWorkType = appWorkType;
	}

	public int getWorkCode() {
		return workCode;
	}

	public void setWorkCode(int workCode) {
		this.workCode = workCode;
	}

	public LocalDate getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(LocalDate selectedDate) {
		this.selectedDate = selectedDate;
	}

	public StandardWorkDTO getStandardWorkDTO() {
		return standardWorkDTO;
	}

	public void setStandardWorkDTO(StandardWorkDTO standardWorkDTO) {
		this.standardWorkDTO = standardWorkDTO;
	}

	public CustomWorkDTO getCustomWorkDTO() {
		return customWorkDTO;
	}

	public void setCustomWorkDTO(CustomWorkDTO customWorkDTO) {
		this.customWorkDTO = customWorkDTO;
	}

	public CustomWorkTimeDTO getCustomWorkTimeDTO() {
		return customWorkTimeDTO;
	}

	public void setCustomWorkTimeDTO(CustomWorkTimeDTO customWorkTimeDTO) {
		this.customWorkTimeDTO = customWorkTimeDTO;
	}

	@Override
	public String toString() {
		return "TeamWorkingHourDTO [memberNo=" + memberNo + ", memberId=" + memberId + ", name=" + name + ", roleCode="
				+ roleCode + ", roleName=" + roleName + ", deptCode=" + deptCode + ", deptName=" + deptName
				+ ", jobCode=" + jobCode + ", jobName=" + jobName + ", appWorkType=" + appWorkType + ", workCode="
				+ workCode + ", selectedDate=" + selectedDate + ", standardWorkDTO=" + standardWorkDTO
				+ ", customWorkDTO=" + customWorkDTO + ", customWorkTimeDTO=" + customWorkTimeDTO + "]";
	}
	
	

}
