package com.qs.www.mypage.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.mypage.model.dto.MemberInfoDTO;

public class MypageDAO {

	public MemberInfoDTO selectInfo(SqlSession session, String memberId) {	
		return session.selectOne("selectInfo", memberId);
	}
}
