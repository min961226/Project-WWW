package com.qs.www.main.model.dto;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.member.model.dto.MemberInfoDTO;

public class MainDAO {

	public void selectMain(SqlSession sqlSession, MemberInfoDTO memberInfo) {
		//sqlSession.selectList("selectMain", memberInfo);
	}
}
