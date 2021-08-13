package com.qs.www.board.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.board.model.dao.FormDAO;
import com.qs.www.board.model.dto.FormDTO;
import com.qs.www.board.model.dto.FreeDTO;

import static com.qs.www.common.mybatis.Template.getSqlSession;

public class FormService {
	
	private final FormDAO formDAO;
	
	public FormService() {
		
		formDAO = new FormDAO();
		
	}
	//문서서식 게시판 조회
	public List<FormDTO> selectAllFormList() {
		
		SqlSession session = getSqlSession();
		
		List<FormDTO> formList = formDAO.selectAllFormList(session);
		
		session.close();
		
		return formList;
		
	}
	public FormDTO selectFormDetail(int no) {
		SqlSession session = getSqlSession();
		FormDTO formDetail = null;
		
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

}
