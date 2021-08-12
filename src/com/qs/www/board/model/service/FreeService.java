package com.qs.www.board.model.service;

import java.util.List; 

import org.apache.ibatis.session.SqlSession;

import com.qs.www.board.model.dao.FreeDAO;
import com.qs.www.board.model.dto.FreeDTO;

import static com.qs.www.common.mybatis.Template.getSqlSession;

public class FreeService {
	
	private final FreeDAO freeDAO;
	
	public FreeService() {
		
		freeDAO = new FreeDAO();
		
	}
	//자유게시판 목록조회
	public List<FreeDTO> selectAllFreeList() {
		
		SqlSession session = getSqlSession();
		
		List<FreeDTO> freeList = freeDAO.selectAllFreeList(session);
		
		session.close();
		
		return freeList;
		
	}
	//자유게시판 작성
	public int insertFree(FreeDTO newFree) {
		
		SqlSession session = getSqlSession();
		
		int result = freeDAO.insertFree(session, newFree);
		
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		
		session.close();
		
		System.out.println(result);
		
		return result;
	}
	//자유게시판 상세조회
	public FreeDTO selectFreeDetail(int no) {
		
		SqlSession session = getSqlSession();
		FreeDTO freeDetail = null;
		
		int result = freeDAO.incrementFreeCount(session, no);
		
		if(result > 0) {
			freeDetail = freeDAO.selectFreeDetail(session, no);
			
			if(freeDetail != null) {
				session.commit();
			} else {
				session.rollback();
			}
		} else {
			session.rollback();
		}
		
		session.close();
		
		return freeDetail;
	}

}
