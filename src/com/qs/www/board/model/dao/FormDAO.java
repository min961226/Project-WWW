package com.qs.www.board.model.dao;

import java.util.List; 
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.board.model.dto.FormDTO;
import com.qs.www.common.paging.SelectCriteria;

public class FormDAO {

	public List<FormDTO> selectAllFormList(SqlSession session, SelectCriteria selectCriteria) {
		
		
		return session.selectList("FormDAO.selectBoardList", selectCriteria);
	}

	public int incrementFormCount(SqlSession session, int no) {
		
		return session.update("FormDAO.incrementFormCount", no);
	}

	public FormDTO selectFormDetail(SqlSession session, int no) {
		
		return session.selectOne("FormDAO.selectFormDetail", no);
	}

	public int selectAllCount(SqlSession session, Map<String, String> searchMap) {
		return session.selectOne("FormDAO.selectAllCount", searchMap);
	}

}
