package com.qs.www.welfare.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;


public class WelfareDAO {

	public List<String> checkWelfareList(SqlSession session) {
		return session.selectList("WelfareDAO.selectWelfareList");
	}

	public List<String> selectApproverLine(SqlSession session, int memberNo) {
		return session.selectList("WelfareDAO.selectApproverLine", memberNo);
	}
	
	public List<String> checkSelfDevList(SqlSession session) {
		return session.selectList("WelfareDAO.checkSelfDevList");
	}


}
