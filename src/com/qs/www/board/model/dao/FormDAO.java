package com.qs.www.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.board.model.dto.FormDTO;

public class FormDAO {

	public List<FormDTO> selectAllFormList(SqlSession session) {
		
		
		return session.selectList("FormDAO.selectAllFormList");
	}

}
