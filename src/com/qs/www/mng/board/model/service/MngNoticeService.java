package com.qs.www.mng.board.model.service;

import static com.qs.www.common.mybatis.Template.getSqlSession;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.board.model.dao.NoticeDAO;
import com.qs.www.board.model.dto.FreeDTO;
import com.qs.www.board.model.dto.NoticeDTO;
import com.qs.www.common.paging.SelectCriteria;
import com.qs.www.mng.board.model.dao.MngNoticeDAO;
import com.qs.www.mng.board.model.dto.MngNoticeDTO;

public class MngNoticeService {
	
	private final MngNoticeDAO mngnoticeDAO;
	
	public MngNoticeService() {
		
		mngnoticeDAO = new MngNoticeDAO();
		
	}

	public int selectAllCount(Map<String, String> searchMap) {
		
		SqlSession session = getSqlSession();
		
		int count = mngnoticeDAO.selectAllCount(session,searchMap);
		
		session.close();
		
		return count;
	}

	public List<MngNoticeDTO> selectAllMngNoticeList(SelectCriteria selectCriteria) {
		
		SqlSession session = getSqlSession();
		
		List<MngNoticeDTO> mngnoticeList = mngnoticeDAO.selectAllMngNoticeList(session, selectCriteria);
		
		session.close();
		
		return mngnoticeList;
	}

	public int insertMngNotice(MngNoticeDTO newMngNotice) {
		SqlSession session = getSqlSession();
		
		int result = mngnoticeDAO.insertMngNotice(session, newMngNotice);
		
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		
		session.close();
		
		System.out.println(result);
		
		return result;
	}

	public MngNoticeDTO selectMngNoticeDetail(int no) {
		
		SqlSession session = getSqlSession();
		MngNoticeDTO mngnoticeDetail = new MngNoticeDTO();
		
		int result = mngnoticeDAO.incrementMngNoticeCount(session, no);
		
		if(result > 0) {
			mngnoticeDetail = mngnoticeDAO.selectMngNoticeDetail(session, no);
			
			if(mngnoticeDetail != null) {
				session.commit();
			} else {
				session.rollback();
			}
		} else {
			session.rollback();
		}
		
		session.close();
		
		return mngnoticeDetail;
	}

	public int updateMngNotice(MngNoticeDTO mngnotice) {
		SqlSession session = getSqlSession();

		int result = mngnoticeDAO.updateMngNotice(session, mngnotice);

		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}

		session.close();

		return result;
	}

	public int deleteMngNotice(int no) {
		SqlSession session = getSqlSession();

		int result1 = mngnoticeDAO.deleteMngNotice(session,no);
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

	public int selectBoardNum() {
		SqlSession session = getSqlSession();

		int boardNum = mngnoticeDAO.selectBoardNum(session);

		session.close();

		return boardNum;
	}

}
