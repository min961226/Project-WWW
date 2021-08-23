package com.qs.www.board.model.service;

import java.util.List; 
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.board.model.dao.FormDAO;
import com.qs.www.board.model.dto.FormDTO;
import com.qs.www.common.paging.SelectCriteria;

import static com.qs.www.common.mybatis.Template.getSqlSession;

public class FormService {
	
	private final FormDAO formDAO;
	
	public FormService() {
		
		formDAO = new FormDAO();
		
	}
	//문서서식 게시판 조회
	public List<FormDTO> selectAllFormList(SelectCriteria selectCriteria) {
		
		SqlSession session = getSqlSession();
		
		List<FormDTO> formList = formDAO.selectAllFormList(session, selectCriteria);
		
		session.close();
		
		return formList;
		
	}
	//문서서식 게시판 상세조회
	public FormDTO selectFormDetail(int no) {
		SqlSession session = getSqlSession();
		FormDTO formDetail = new FormDTO();
		
		int result = formDAO.incrementFormCount(session, no);
		
		if(result > 0) {
			formDetail = formDAO.selectFormDetail(session, no);
			
			if(formDetail != null) {
				session.commit();
			} else {
				session.rollback();
			}
		} else {
			session.rollback();
		}
		
		session.close();
		
		return formDetail;
	}
	//문서서식게시글 수 조회
	public int selectAllCount(Map<String, String> searchMap) {
		SqlSession session = getSqlSession();
		
		int count = formDAO.selectAllCount(session,searchMap);
		
		session.close();
		
		return count;
	}

}
