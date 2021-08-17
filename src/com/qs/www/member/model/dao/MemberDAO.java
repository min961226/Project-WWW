package com.qs.www.member.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.member.model.dto.CheckPwdDTO;
import com.qs.www.member.model.dto.MemberDTO;
import com.qs.www.member.model.dto.MemberInfoDTO;

public class MemberDAO {

	public String selectEncryptedPwd(SqlSession sqlSession, String memberId) {
		return sqlSession.selectOne("MemberDAO.selectEncryptedPwd", memberId);
	}

	public MemberInfoDTO selectLoginMember(SqlSession sqlSession, String memberId) {	
		return sqlSession.selectOne("MemberDAO.selectLoginMember", memberId);
	}

	public String selectMemberId(SqlSession sqlSession, MemberDTO member) {
		return sqlSession.selectOne("MemberDAO.selectMemberId", member);
	}
	
	public String selectQuestionCode(SqlSession sqlSession, String questionBody) {
		return sqlSession.selectOne("MemberDAO.selectQuestionCode", questionBody);
	}

	public CheckPwdDTO selectMemberPwd(SqlSession sqlSession, CheckPwdDTO checkPwd) {
		return sqlSession.selectOne("MemberDAO.selectMemberPwd", checkPwd);
	}

	public int updateMemberPwd(SqlSession sqlSession, MemberDTO changePwdMember) {
		return sqlSession.update("MemberDAO.updateMemberPwd", changePwdMember);
	}
}
