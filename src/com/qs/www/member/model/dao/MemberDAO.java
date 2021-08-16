package com.qs.www.member.model.dao;

import org.apache.ibatis.session.SqlSession;

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
}
