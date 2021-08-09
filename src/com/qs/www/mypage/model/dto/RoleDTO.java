package com.qs.www.mypage.model.dto;

import java.io.Serializable;

public class RoleDTO implements Serializable {
	
	private String roleCode;
	private String roleName;
	private String deptCode;
	private String jobCode;
	
	public RoleDTO() {}

	public RoleDTO(String roleCode, String roleName, String deptCode, String jobCode) {
		super();
		this.roleCode = roleCode;
		this.roleName = roleName;
		this.deptCode = deptCode;
		this.jobCode = jobCode;
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

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	@Override
	public String toString() {
		return "RoleDTO [roleCode=" + roleCode + ", roleName=" + roleName + ", deptCode=" + deptCode + ", jobCode="
				+ jobCode + "]";
	}
}
