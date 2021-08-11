package com.qs.www.mypage.model.service;

import static com.qs.www.common.mybatis.Template.getSqlSession;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.mypage.model.dao.MypageDAO;

public class MypageService {
	
	private final MypageDAO mypageDAO;
	
	public MypageService() {
		mypageDAO = new MypageDAO();
	}

	public int updateInfo(MemberInfoDTO memberInfo) {
		
		SqlSession sqlSession = getSqlSession();
		
		int result = mypageDAO.updateInfo(sqlSession, memberInfo);
		
		if(result > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		
		sqlSession.close();
		
		return result;
	}
}
