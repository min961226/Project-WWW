package com.qs.www.mng.welfare.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.mng.welfare.model.dto.WelfareYnDTO;

public class MngWelfareDAO {

	
	public List<WelfareYnDTO> selectWelfareYn(SqlSession session) {
		return session.selectList("WelfareDAO.selectWelfareYn");
	}
}
