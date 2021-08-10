package com.qs.www.mypage.model.service;

import static com.qs.www.common.mybatis.Template.getSqlSession;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.mypage.model.dao.MypageDAO;
import com.qs.www.mypage.model.dto.MemberInfoDTO;

public class MypageService {
	
	private final MypageDAO mypageDAO;
	
	public MypageService() {
		mypageDAO = new MypageDAO();
	}

	public MemberInfoDTO selectInfo(String memberId) {
		
		SqlSession session = getSqlSession();
		
		MemberInfoDTO memberInfo = mypageDAO.selectInfo(session, memberId);
		
		session.close();
		
		return memberInfo;
	}
}
