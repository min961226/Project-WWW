package com.qs.www.board.model.service;

import java.util.List; 

import org.apache.ibatis.session.SqlSession;

import com.qs.www.board.model.dao.NoticeDAO;
import com.qs.www.board.model.dto.FreeDTO;
import com.qs.www.board.model.dto.NoticeDTO;

import static com.qs.www.common.mybatis.Template.getSqlSession;

public class NoticeService {
	
	private final NoticeDAO noticeDAO;
	
	public NoticeService() {
		
		noticeDAO = new NoticeDAO();
		
	}
	//공지사항 목록조회
	public List<NoticeDTO> selectAllNoticeList() {
		
		SqlSession session = getSqlSession();
		
		List<NoticeDTO> noticeList = noticeDAO.selectAllNoticeList(session);
		
		session.close();
		
		return noticeList;
		

	}
	public NoticeDTO selectNoticeDetail(int no) {
		SqlSession session = getSqlSession();
		NoticeDTO noticeDetail = null;
		
		int result = noticeDAO.incrementNoticeCount(session, no);
		
		if(result > 0) {
			noticeDetail = noticeDAO.selectNoticeDetail(session, no);
			
			if(noticeDetail != null) {
				session.commit();
			} else {
				session.rollback();
			}
		} else {
			session.rollback();
		}
		
		session.close();
		
		return noticeDetail;
	}

}
