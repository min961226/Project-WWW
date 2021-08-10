package com.qs.www.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.board.model.dto.FreeBoardDTO;

public class FreeBoardDAO {


	public List<FreeBoardDTO> selectAllFreeList(SqlSession session) {
		
		return session.selectList("FreeBoardDAO.selectAllFreeList");
	}

}
