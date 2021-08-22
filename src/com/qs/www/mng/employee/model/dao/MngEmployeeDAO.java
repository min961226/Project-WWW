package com.qs.www.mng.employee.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.main.model.dto.AuthorityDTO;
import com.qs.www.member.model.dto.DepartmentDTO;
import com.qs.www.member.model.dto.JobDTO;
import com.qs.www.member.model.dto.MemberDTO;
import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.member.model.dto.RoleDTO;

public class MngEmployeeDAO {
	
	public int selectMemberNo(SqlSession sqlSession) {
		return sqlSession.selectOne("MngEmployeeDAO.selectMemberNo");
	}

	public List<DepartmentDTO> selectDeptList(SqlSession sqlSession) {
		return sqlSession.selectList("MngEmployeeDAO.selectDeptList");
	}

	public List<JobDTO> selectJobList(SqlSession sqlSession) {
		return sqlSession.selectList("MngEmployeeDAO.selectJobList");
	}

	public List<RoleDTO> selectRoleList(SqlSession sqlSession) {
		return sqlSession.selectList("MngEmployeeDAO.selectRoleList");
	}

	public int selectMemberId(SqlSession sqlSession, String memberId) {
		return sqlSession.selectOne("MngEmployeeDAO.selectMemberId", memberId);
	}

	public int insertMngEmployee(SqlSession sqlSession, MemberInfoDTO memberInfo) {
		return sqlSession.insert("MngEmployeeDAO.insertMngEmployee", memberInfo);
	}

	public List<MemberInfoDTO> selectMngEmployeeList(SqlSession sqlSession) {
		return sqlSession.selectList("MngEmployeeDAO.selectMngEmployeeList");
	}

	public MemberInfoDTO selectOneMngEmployee(SqlSession sqlSession, int memberNo) {
		return sqlSession.selectOne("MngEmployeeDAO.selectOneMngEmployee", memberNo);
	}

	public int updateMngEmployee(SqlSession sqlSession, MemberDTO member) {
		return sqlSession.update("MngEmployeeDAO.updateMngEmployee", member);
	}

	public List<AuthorityDTO> selectAuthorityList(SqlSession sqlSession) {
		return sqlSession.selectList("MngEmployeeDAO.selectAuthorityList");
	}
	
	public List<AuthorityDTO> selectRoleAuthorityList(SqlSession sqlSession, String roleCode) {
		return sqlSession.selectList("MngEmployeeDAO.selectRoleAuthorityList", roleCode);
	}
}
