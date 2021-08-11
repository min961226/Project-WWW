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
		
		MemberInfoDTO loginMember = null;
		
		String encPwd = memberDAO.selectEncryptedPwd(sqlSession, requestMember);
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		if(encPwd.equals(requestMember.getPassword())) {
			loginMember = memberDAO.selectLoginMember(sqlSession, requestMember);
		}
		
//		if(passwordEncoder.matches(requestMember.getPassword(), encPwd)) {
//			loginMember = memberDAO.selectLoginMember(session, requestMember);
//		}
		
		sqlSession.close();
		
		return loginMember;
	}
}
