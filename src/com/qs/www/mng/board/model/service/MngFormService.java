package com.qs.www.mng.board.model.service;

import static com.qs.www.common.mybatis.Template.getSqlSession;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.common.paging.SelectCriteria;
import com.qs.www.mng.board.model.dao.MngFormDAO;
import com.qs.www.mng.board.model.dto.MngFormDTO;
import com.qs.www.mng.board.model.dto.MngNoticeDTO;

public class MngFormService {
	
	private final MngFormDAO mngformDAO;
	
	public MngFormService() {
		
		mngformDAO = new MngFormDAO();
		
	}
	//문서서식 게시글 조회
	public List<MngFormDTO> selectAllMngFormList(SelectCriteria selectCriteria) {
		
		SqlSession session = getSqlSession();
		
		List<MngFormDTO> mngformList = mngformDAO.selectAllMngFormList(session, selectCriteria);
		
		session.close();
		
		return mngformList;
	}
	//문서서식 게시글 상세조회
	public MngFormDTO selectMngFormDetail(int no) {
		SqlSession session = getSqlSession();
		MngFormDTO mngformDetail = new MngFormDTO();
		
		int result = mngformDAO.incrementMngFormCount(session, no);
		
		if(result > 0) {
			mngformDetail = mngformDAO.selectMngFormDetail(session, no);
			
			if(mngformDetail != null) {
				session.commit();
			} else {
				session.rollback();
			}
		} else {
			session.rollback();
		}
		
		session.close();
		
		return mngformDetail;
	}
	//문서서식 게시글 작성
	public int insertMngForm(MngFormDTO newMngForm) {
		SqlSession session = getSqlSession();
		
		int result = mngformDAO.insertMngForm(session, newMngForm);
		
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		
		session.close();
		
		System.out.println(result);
		
		return result;
	}
	//문서서식 게시글 수정
	public int updateMngForm(MngFormDTO mngform) {
		SqlSession session = getSqlSession();

		int result = mngformDAO.updateMngForm(session, mngform);

		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}

		session.close();

		return result;
	}
	//문서서식 게시글 삭제
	public int deleteMngForm(int no) {
		SqlSession session = getSqlSession();

		int result1 = mngformDAO.deleteMngForm(session,no);
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
	//게시글 번호 검색
	public int selectBoardNum() {
		SqlSession session = getSqlSession();

		int boardNum = mngformDAO.selectBoardNum(session);

		session.close();

		return boardNum;
	}

}
