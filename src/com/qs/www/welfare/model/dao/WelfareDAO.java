package com.qs.www.welfare.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.welfare.model.dto.WelfareListDTO;

public class WelfareDAO {

	public List<String> checkWelfareList(SqlSession session) {
		return session.selectList("WelfareDAO.selectWelfareList");
	}

}
