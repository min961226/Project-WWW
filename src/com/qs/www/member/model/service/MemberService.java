package com.qs.www.member.model.service;

import static com.qs.www.common.mybatis.Template.getSqlSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.qs.www.main.model.dao.MainDAO;
import com.qs.www.main.model.dto.WorkInfoDTO;
import com.qs.www.main.model.dto.WorkingLogDTO;
import com.qs.www.member.model.dao.MemberDAO;
import com.qs.www.member.model.dto.CheckPwdDTO;
import com.qs.www.member.model.dto.MemberDTO;
import com.qs.www.member.model.dto.MemberInfoDTO;

public class MemberService {
	
	private final MemberDAO memberDAO;
	private final MainDAO mainDAO;
	
	public MemberService() {
		memberDAO = new MemberDAO();
		mainDAO = new MainDAO();
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
		
		String questionBody = checkPwd.getQuestion().getQuestionBody();
		String questionCode = memberDAO.selectQuestionCode(sqlSession, questionBody);
		System.out.println("Code : " + questionCode);
		int result = 0;
		
		if(questionCode != null) {
			checkPwd.getQuestion().setQuestionCode(questionCode);

			CheckPwdDTO checkedMember = memberDAO.selectMemberPwd(sqlSession, checkPwd);
			
			if(checkedMember != null) {
				result = 1;
			}
		}
		
		sqlSession.close();
		
		return result;
	}

	public int updateMemberPwd(MemberDTO changePwdMember) {
		
		SqlSession sqlSession = getSqlSession();
		
		String memberId = changePwdMember.getMemberId();
		String changePwd = changePwdMember.getPassword();
		String encPwd = memberDAO.selectEncryptedPwd(sqlSession, memberId);
		System.out.println(changePwd);
		System.out.println(encPwd);
		
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
