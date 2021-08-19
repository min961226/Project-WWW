package com.qs.www.mng.employee.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.member.model.dto.DepartmentDTO;
import com.qs.www.member.model.dto.JobDTO;
import com.qs.www.member.model.dto.RoleDTO;

public class MngEmployeeDAO {

	public List<DepartmentDTO> selectDeptList(SqlSession sqlSession) {
		return sqlSession.selectList("MngEmployeeDAO.selectDeptList");
	}

	public List<JobDTO> selectJobList(SqlSession sqlSession) {
		return sqlSession.selectList("MngEmployeeDAO.selectJobList");
	}

	public List<RoleDTO> selectRoleList(SqlSession sqlSession) {
		return sqlSession.selectList("MngEmployeeDAO.selectRoleList");
	}
}
