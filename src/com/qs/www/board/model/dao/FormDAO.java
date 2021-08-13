package com.qs.www.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.board.model.dto.FormDTO;

public class FormDAO {

	public List<FormDTO> selectAllFormList(SqlSession session) {
		
		
		return session.selectList("FormDAO.selectAllFormList");
	}

	public int incrementFormCount(SqlSession session, int no) {
		
		return session.update("FormDAO.incrementFormCount", no);
	}

	public FormDTO selectFormDetail(SqlSession session, int no) {
		
		return session.selectOne("FormDAO.selectFormDetail", no);
	}

}
