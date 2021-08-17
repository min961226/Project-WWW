package com.qs.www.mng.board.model.service;

import static com.qs.www.common.mybatis.Template.getSqlSession;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.board.model.dao.NoticeDAO;
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

}
