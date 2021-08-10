package com.qs.www.board.model.service;

import java.util.List; 


import org.apache.ibatis.session.SqlSession;

import com.qs.www.board.model.dao.FreeBoardDAO;
import com.qs.www.board.model.dto.FreeBoardDTO;

import static com.qs.www.common.mybatis.Template.getSqlSession;

public class FreeBoardService {
	
	private final FreeBoardDAO freeboardDAO;
	
	public FreeBoardService() {
		freeboardDAO = new FreeBoardDAO();
	}

	public List<FreeBoardDTO> selectAllFreeList() {
		
		
		SqlSession session = getSqlSession();
		
		List<FreeBoardDTO> freeboardList = freeboardDAO.selectAllFreeList(session);
		
		System.out.println(freeboardList);
		
		session.close();

		return freeboardList;
	}

}
