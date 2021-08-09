package com.qs.www.board.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.board.model.dto.FreeBoardDTO;

public class FreeBoardDAO {

	public int selectTotalCount(SqlSession session, Map<String, String> searchMap) {
		
		return session.selectOne("FreeBoardDAO.selectTotalCount", searchMap);
	}
	
	public List<FreeBoardDTO> selectBoardList(SqlSession session, SelectCriteria selectCriteria) {
		
		return session.selectList("BoardDAO.selectBoardList", selectCriteria);
	}

}
