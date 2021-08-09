package com.qs.www.mypage.model.service;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.member.model.dto.MemberDTO;
import com.qs.www.mypage.model.dao.MypageDAO;

import static com.qs.www.common.mybatis.Template.getSqlSession;

public class MypageService {
	
	private final MypageDAO mypageDAO;
	
	public MypageService() {
		mypageDAO = new MypageDAO();
	}

	public MemberDTO selectInfo() {
		
		SqlSession session = getSqlSession();
		
		MemberDTO memberInfo = mypageDAO.selectInfo(session);
		
		session.close();
		
		return memberInfo;
	}
}
