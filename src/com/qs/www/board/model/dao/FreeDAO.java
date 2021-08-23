package com.qs.www.board.model.dao;

import java.util.List;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.board.model.dto.FreeDTO;
import com.qs.www.common.paging.SelectCriteria;

public class FreeDAO {
	
	public List<FreeDTO> selectAllFreeList(SqlSession session, SelectCriteria selectCriteria) {
		
		return session.selectList("FreeDAO.selectBoardList", selectCriteria);
	}
	public int insertFree(SqlSession session, FreeDTO newFree) {
		
		return session.insert("FreeDAO.insertFree", newFree);
	}
	public FreeDTO selectFreeDetail(SqlSession session, int no) {
		
		return session.selectOne("FreeDAO.selectFreeDetail", no);	
	}
	public int incrementFreeCount(SqlSession session, int no) {
		
		return session.update("FreeDAO.incrementFreeCount", no);
	}
	public int updateFree(SqlSession session, FreeDTO free) {
		
		return session.update("FreeDAO.updateFree", free);
	}
	public int selectAllCount(SqlSession session, Map<String, String> searchMap) {
		return session.selectOne("FreeDAO.selectAllCount", searchMap);
	}
	public int deleteFree(SqlSession session, int no) {
		return session.delete("FreeDAO.deleteFree",no);
	}
	
}
