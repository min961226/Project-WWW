package com.qs.www.board.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.board.model.dto.FreeDTO;
import com.qs.www.common.paging.SelectCriteria;

public class FreeDAO {
	
	//자유게시판 목록조회
	public List<FreeDTO> selectAllFreeList(SqlSession session, SelectCriteria selectCriteria) {
		
		return session.selectList("FreeDAO.selectBoardList", selectCriteria);
	}
	//자유게시판 작성
	public int insertFree(SqlSession session, FreeDTO newFree) {
		
		return session.insert("FreeDAO.insertFree", newFree);
	}
	//자유게시판 상세조회
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
	
}
