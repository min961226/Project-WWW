package com.qs.www.board.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.board.model.dto.NoticeDTO;
import com.qs.www.common.paging.SelectCriteria;



public class NoticeDAO {
	
	//공지사항 목록 조회
	public List<NoticeDTO> selectAllNoticeList(SqlSession session, SelectCriteria selectCriteria) {
		
		return session.selectList("NoticeDAO.selectBoardList", selectCriteria);
	}

	public NoticeDTO selectNoticeDetail(SqlSession session, int no) {
		return session.selectOne("NoticeDAO.selectNoticeDetail", no);
	}

	public int incrementNoticeCount(SqlSession session, int no) {
		return session.update("NoticeDAO.incrementNoticeCount", no);
	}

	public int selectAllCount(SqlSession session, Map<String, String> searchMap) {
		return session.selectOne("NoticeDAO.selectAllCount", searchMap);

	}
}


