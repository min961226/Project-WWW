package com.qs.www.member.model.service;

import static com.qs.www.common.mybatis.Template.getSqlSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.qs.www.member.model.dao.MemberDAO;
import com.qs.www.member.model.dto.MemberDTO;
import com.qs.www.member.model.dto.MemberInfoDTO;

public class MemberService {
	
	private final MemberDAO memberDAO;
	
	public MemberService() {
		memberDAO = new MemberDAO();
	}

	public MemberInfoDTO checkMember(MemberDTO requestMember) {
		
		SqlSession sqlSession = getSqlSession();
		String memberId = requestMember.getMemberId();
		MemberInfoDTO loginMember = null;
		
		String encPwd = memberDAO.selectEncryptedPwd(sqlSession, memberId);
		
		if(encPwd != null && encPwd.equals(requestMember.getPassword())) {
			loginMember = memberDAO.selectLoginMember(sqlSession, memberId);
		}
		
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		
//		if(passwordEncoder.matches(requestMember.getPassword(), encPwd)) {
//			loginMember = memberDAO.selectLoginMember(session, memberId);
//		}
		
		sqlSession.close();
		
		return loginMember;
	}

	public String checkMemberId(MemberDTO member) {
		
		SqlSession sqlSession = getSqlSession();
		
		String findId = memberDAO.selectMemberId(sqlSession, member);
		
		sqlSession.close();
		
		return findId;
	}
}
