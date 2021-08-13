package com.qs.www.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.board.model.dto.FreeDTO;

public class FreeDAO {
	
	//자유게시판 목록조회
	public List<FreeDTO> selectAllFreeList(SqlSession session) {
		
		return session.selectList("FreeDAO.selectAllFreeList");
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
}
