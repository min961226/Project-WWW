package com.qs.www.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.mng.board.model.dto.NoticeDTO;

public class NoticeDAO {
	
	//공지사항 목록 조회
	public List<NoticeDTO> selectAllNoticeList(SqlSession session) {
		
		return session.selectList("NoticeDAO.selectAllNoticeList");
	}

}
