package com.qs.www.member.model.dto;

import java.io.Serializable;

public class DepartmentDTO implements Serializable {
	
	private String deptCode;
	private String deptName;
	private String deptCallNumber;
	
	public DepartmentDTO() {}

	public DepartmentDTO(String deptCode, String deptName, String deptCallNumber) {
		super();
		this.deptCode = deptCode;
		this.deptName = deptName;
		this.deptCallNumber = deptCallNumber;
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

	public String getDeptCallNumber() {
		return deptCallNumber;
	}

	public void setDeptCallNumber(String deptCallNumber) {
		this.deptCallNumber = deptCallNumber;
	}

	@Override
	public String toString() {
		return "DepartmentDTO [deptCode=" + deptCode + ", deptName=" + deptName + ", deptCallNumber=" + deptCallNumber
				+ "]";
	}
}
