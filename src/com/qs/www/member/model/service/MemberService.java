package com.qs.www.member.model.service;

import static com.qs.www.common.mybatis.Template.getSqlSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.qs.www.member.model.dao.MemberDAO;
import com.qs.www.member.model.dto.CheckPwdDTO;
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
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		if(passwordEncoder.matches(requestMember.getPassword(), encPwd)) {
			loginMember = memberDAO.selectLoginMember(sqlSession, memberId);
		}
		
		sqlSession.close();
		
		return loginMember;
	}
	
	public int checkPwd(String changePwd, String changePwd2) {
		
		SqlSession sqlSession = getSqlSession();
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		int result = 0;
		
		if(passwordEncoder.matches(changePwd, changePwd2)) {
			result = 1;
		}
		sqlSession.close();
		
		return result;
	}

	public String checkMemberId(MemberDTO member) {
		
		SqlSession sqlSession = getSqlSession();
		
		String findId = memberDAO.selectMemberId(sqlSession, member);
		
		sqlSession.close();
		
		return findId;
	}

	public int checkMemberPwd(CheckPwdDTO checkPwd) {
		
		SqlSession sqlSession = getSqlSession();
		
		String questionCode = checkPwd.getQuestion().getQuestionCode();
		checkPwd.getQuestion().setQuestionCode(questionCode);
		
		int result = 0;

		CheckPwdDTO checkedMember = memberDAO.selectMemberPwd(sqlSession, checkPwd);
		
		if(checkedMember != null) {
			result = 1;
		}
		
		sqlSession.close();
		
		return result;
	}

	public int updateMemberPwd(MemberDTO changePwdMember) {
		
		SqlSession sqlSession = getSqlSession();
		
		int result = memberDAO.updateMemberPwd(sqlSession, changePwdMember);
		
		if(result > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		
		sqlSession.close();
		
		return result;
	}
}
