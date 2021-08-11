package com.qs.www.member.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.member.model.dto.MemberDTO;
import com.qs.www.member.model.dto.MemberInfoDTO;

public class MemberDAO {

	public String selectEncryptedPwd(SqlSession session, String memberId) {
		return session.selectOne("MemberDAO.selectEncryptedPwd", memberId);
	}

	public MemberInfoDTO selectLoginMember(SqlSession session, String memberId) {	
		return session.selectOne("MemberDAO.selectLoginMember", memberId);
	}

}
