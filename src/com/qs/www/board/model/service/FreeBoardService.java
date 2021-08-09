package com.qs.www.board.model.service;

import static com.qs.www.common.mybatis.Template.getSqlSession;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.board.model.dao.FreeBoardDAO;
import com.qs.www.board.model.dto.FreeBoardDTO;

public class FreeBoardService {
	
private final FreeBoardDAO freeboardDAO;
	
	public FreeBoardService() {
		freeboardDAO = new FreeBoardDAO();
	}
	
	public int selectTotalCount(Map<String, String> searchMap) {
		
		SqlSession session = getSqlSession();
		
		int totalCount = freeboardDAO.selectTotalCount(session, searchMap);
		
		session.close();
		
		return totalCount;
	}
	
	public List<FreeBoardDTO> selectBoardList(SelectCriteria selectCriteria) {
		
		SqlSession session = getSqlSession();
		
		List<FreeBoardDTO> boardList = freeboardDAO.selectBoardList(session, selectCriteria);
		
		session.close();
		
		return boardList;
	}
}
