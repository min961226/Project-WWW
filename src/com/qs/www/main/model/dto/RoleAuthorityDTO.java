package com.qs.www.main.model.dto;

import java.io.Serializable;

import com.qs.www.member.model.dto.DepartmentDTO;
import com.qs.www.member.model.dto.JobDTO;
import com.qs.www.member.model.dto.RoleDTO;

public class RoleAuthorityDTO implements Serializable {
	
	private RoleDTO role;
	private DepartmentDTO dept;
	private JobDTO job;
	private AuthorityDTO authority;
	
	public RoleAuthorityDTO() {}

	public RoleAuthorityDTO(RoleDTO role, DepartmentDTO dept, JobDTO job, AuthorityDTO authority) {
		super();
		this.role = role;
		this.dept = dept;
		this.job = job;
		this.authority = authority;
	}

	public RoleDTO getRole() {
		return role;
	}

	public void setRole(RoleDTO role) {
		this.role = role;
	}

	public DepartmentDTO getDept() {
		return dept;
	}

	public void setDept(DepartmentDTO dept) {
		this.dept = dept;
	}

	public JobDTO getJob() {
		return job;
	}

	public void setJob(JobDTO job) {
		this.job = job;
	}

	public AuthorityDTO getAuthority() {
		return authority;
	}

	public void setAuthority(AuthorityDTO authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "RoleAuthorityDTO [role=" + role + ", dept=" + dept + ", job=" + job + ", authority=" + authority + "]";
	}
}
