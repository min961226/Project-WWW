package com.qs.www.mng.employee.model.service;

import static com.qs.www.common.mybatis.Template.getSqlSession;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.main.model.dto.AuthorityDTO;
import com.qs.www.member.model.dto.DepartmentDTO;
import com.qs.www.member.model.dto.JobDTO;
import com.qs.www.member.model.dto.MemberDTO;
import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.member.model.dto.RoleDTO;
import com.qs.www.mng.employee.model.dao.MngEmployeeDAO;

public class MngEmployeeService {
	
	private final MngEmployeeDAO mngEmployeeDAO;
	
	public MngEmployeeService() {
		mngEmployeeDAO = new MngEmployeeDAO();
	}
	
	public int selectMemberNo() {
		
		SqlSession sqlSession = getSqlSession();
		
		int memberNo = mngEmployeeDAO.selectMemberNo(sqlSession);
		
		sqlSession.close();
		
		return memberNo;
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

	public int checkMemberId(String memberId) {
		
		SqlSession sqlSession = getSqlSession();
		
		int result = mngEmployeeDAO.selectMemberId(sqlSession, memberId);
		
		sqlSession.close();
		
		return result;
	}

	public int insertMngEmployee(MemberInfoDTO memberInfo) {
		
		SqlSession sqlSession = getSqlSession();
		
		int result = mngEmployeeDAO.insertMngEmployee(sqlSession, memberInfo);
		
		if(result > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		
		sqlSession.close();
		
		return result;
	}

	public List<MemberInfoDTO> selectMngEmployeeList() {
		
		SqlSession sqlSession = getSqlSession();
		
		List<MemberInfoDTO> memberInfoList = mngEmployeeDAO.selectMngEmployeeList(sqlSession);
		
		sqlSession.close();
		
		return memberInfoList;
	}

	public MemberInfoDTO selectOneMngEmployee(int memberNo) {
		
		SqlSession sqlSession = getSqlSession();
		
		MemberInfoDTO memberInfo = mngEmployeeDAO.selectOneMngEmployee(sqlSession, memberNo);
		
		sqlSession.close();
		
		return memberInfo;
	}

	public int updateMngEmployee(MemberDTO member) {
		
		SqlSession sqlSession = getSqlSession();
		
		int result = mngEmployeeDAO.updateMngEmployee(sqlSession, member);
		
		if(result > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		
		sqlSession.close();
		
		return result;
	}

	public List<AuthorityDTO> selectAuthorityList() {
		
		SqlSession sqlSession = getSqlSession();
		
		List<AuthorityDTO> authorityList = mngEmployeeDAO.selectAuthorityList(sqlSession);
		
		sqlSession.close();
		
		return authorityList;
	}
	
	public List<AuthorityDTO> selectRoleAuthorityList(String roleCode) {
		
		SqlSession sqlSession = getSqlSession();
		
		List<AuthorityDTO> roleAuthorityList = mngEmployeeDAO.selectRoleAuthorityList(sqlSession, roleCode);
		
		sqlSession.close();
		
		return roleAuthorityList;
	}

	public int insertWorkingLog(MemberInfoDTO memberInfo) {
		
		SqlSession sqlSession = getSqlSession();
		
		int result = mngEmployeeDAO.insertWorkingLog(sqlSession, memberInfo);
		
		if(result > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		
		return result;
	}
}
