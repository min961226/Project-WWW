package com.qs.www.mng.employee.model.service;

import static com.qs.www.common.mybatis.Template.getSqlSession;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.member.model.dto.DepartmentDTO;
import com.qs.www.member.model.dto.JobDTO;
import com.qs.www.member.model.dto.RoleDTO;
import com.qs.www.mng.employee.model.dao.MngEmployeeDAO;

public class MngEmployeeService {
	
	private final MngEmployeeDAO mngEmployeeDAO;
	
	public MngEmployeeService() {
		mngEmployeeDAO = new MngEmployeeDAO();
	}

	public List<DepartmentDTO> selectDeptList() {
		
		SqlSession sqlSession = getSqlSession();
		
		List<DepartmentDTO> deptList = mngEmployeeDAO.selectDeptList(sqlSession);
		
		sqlSession.close();
		
		return deptList;
	}

	public List<JobDTO> selectJobList() {
		
		SqlSession sqlSession = getSqlSession();
		
		List<JobDTO> jobList = mngEmployeeDAO.selectJobList(sqlSession);
		
		sqlSession.close();
		
		return jobList;
	}

	public List<RoleDTO> selectRoleList() {
		
		SqlSession sqlSession = getSqlSession();
		
		List<RoleDTO> roleList = mngEmployeeDAO.selectRoleList(sqlSession);
		
		sqlSession.close();
		
		return roleList;
	}
	
}
