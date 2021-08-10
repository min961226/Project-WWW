package com.qs.www.board.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.mng.board.model.dao.NoticeDAO;
import com.qs.www.mng.board.model.dto.NoticeDTO;

import static com.qs.www.common.mybatis.Template.getSqlSession;

public class NoticeService {
	
	private final NoticeDAO noticeDAO;
	
	public NoticeService() {
		
		noticeDAO = new NoticeDAO();
		
	}

	public List<NoticeDTO> selectAllNoticeList() {
		
		SqlSession session = getSqlSession();
		
		List<NoticeDTO> noticeList = noticeDAO.selectAllNoticeList(session);
		return noticeList;
		
	}

}
