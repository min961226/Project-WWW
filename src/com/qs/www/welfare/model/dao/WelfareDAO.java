package com.qs.www.welfare.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.welfare.model.dto.WelfareListDTO;

public class WelfareDAO {

	public List<String> checkWelfareList(SqlSession session) {
		return session.selectList("WelfareDAO.selectWelfareList");
	}

	public String selectDeptName(SqlSession session, String deptCode) {
		return session.selectOne("WelfareDAO.selecDeptName",deptCode);
	}

	public String selectJobName(SqlSession session, String jobCode) {
		return session.selectOne("WelfareDAO.selectJobName",jobCode);
	}

}
