package com.qs.www.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.board.model.dto.FreeDTO;

public class FreeDAO {

	public List<FreeDTO> selectAllFreeList(SqlSession session) {
		
		
		return session.selectList("FreeDAO.selectAllFreeList");
	}

	public int insertFree(SqlSession session, FreeDTO newFree) {
		
		return session.insert("FreeDAO.insertFree", newFree);
	}

}
