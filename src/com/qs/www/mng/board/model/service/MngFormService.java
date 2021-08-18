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

	public List<MngFormDTO> selectAllMngFormList(SelectCriteria selectCriteria) {
		
		SqlSession session = getSqlSession();
		
		List<MngFormDTO> mngformList = mngformDAO.selectAllMngFormList(session, selectCriteria);
		
		session.close();
		
		return mngformList;
	}

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

}
