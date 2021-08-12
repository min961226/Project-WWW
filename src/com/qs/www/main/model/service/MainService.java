package com.qs.www.main.model.service;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.main.model.dao.MainDTO;
import com.qs.www.main.model.dto.MainDAO;
import com.qs.www.member.model.dto.MemberInfoDTO;
import static com.qs.www.common.mybatis.Template.getSqlSession;
public class MainService {
	
	private final MainDAO mainDAO;
	
	public MainService() {
		mainDAO = new MainDAO();
	}

	public MainDTO selectMain(MemberInfoDTO memberInfo) {
		
		SqlSession sqlSession = getSqlSession();
		
		mainDAO.selectMain(sqlSession, memberInfo);
		
		sqlSession.close();
		
		return null;
	}
}
