package com.qs.www.board.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.board.model.dao.FreeDAO;
import com.qs.www.board.model.dto.FreeDTO;
import com.qs.www.common.paging.SelectCriteria;

import static com.qs.www.common.mybatis.Template.getSqlSession;

public class FreeService {
	
	private final FreeDAO freeDAO;
	
	public FreeService() {
		
		freeDAO = new FreeDAO();
		
	}
	//자유게시판 목록조회
	public List<FreeDTO> selectAllFreeList(SelectCriteria selectCriteria) {
		
		SqlSession session = getSqlSession();
		
		List<FreeDTO> freeList = freeDAO.selectAllFreeList(session, selectCriteria);
		
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
		FreeDTO freeDetail = new FreeDTO();
		
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
	//자유게시판 수정
	public int updateFree(FreeDTO free) {
		
		SqlSession session = getSqlSession();

		int result = freeDAO.updateFree(session, free);

		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}

		session.close();

		return result;
	}
	//자유게시글 수 조회
	public int selectAllCount(Map<String, String> searchMap) {

		SqlSession session = getSqlSession();
		
		int count = freeDAO.selectAllCount(session,searchMap);
		
		session.close();
		
		return count;
	}
	//자유게시글 삭제
	public int deleteFree(int no) {
		SqlSession session = getSqlSession();

		int result1 = freeDAO.deleteFree(session,no);
		int result = 0;
		if(result1 > 0) {
			result = 1;
			session.commit();
		} else {
			session.rollback();
		}
		session.close();

		return result;
	}



}
