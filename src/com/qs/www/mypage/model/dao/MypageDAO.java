package com.qs.www.mypage.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.member.model.dto.MemberInfoDTO;

public class MypageDAO {

	public int updateInfo(SqlSession sqlSession, MemberInfoDTO memberInfo) {
		return sqlSession.update("MypageDAO.updateInfo", memberInfo);
	}
}
