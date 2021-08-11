package com.qs.www.member.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.member.model.dto.MemberDTO;
import com.qs.www.member.model.dto.MemberInfoDTO;

public class MemberDAO {

	public String selectEncryptedPwd(SqlSession session, MemberDTO requestMember) {
		return session.selectOne("MemberDAO.selectEncryptedPwd", requestMember);
	}

	public MemberInfoDTO selectLoginMember(SqlSession session, MemberDTO requestMember) {	
		return session.selectOne("MemberDAO.selectLoginMember", requestMember);
	}

}
