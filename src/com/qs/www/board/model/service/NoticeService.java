package com.qs.www.board.model.service;

import java.util.List; 
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.board.model.dao.NoticeDAO;
import com.qs.www.board.model.dto.NoticeDTO;
import com.qs.www.common.paging.SelectCriteria;

import static com.qs.www.common.mybatis.Template.getSqlSession;

public class NoticeService {
	
	private final NoticeDAO noticeDAO;
	
	public NoticeService() {
		
		noticeDAO = new NoticeDAO();
		
	}
	//공지사항 목록조회
	public List<NoticeDTO> selectAllNoticeList(SelectCriteria selectCriteria) {
		
		SqlSession session = getSqlSession();
		
		List<NoticeDTO> noticeList = noticeDAO.selectAllNoticeList(session, selectCriteria);
		
		session.close();
		
		return noticeList;
		

	}
	public NoticeDTO selectNoticeDetail(int no) {
		SqlSession session = getSqlSession();
		NoticeDTO noticeDetail = new NoticeDTO();	
		
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
	public int selectAllCount(Map<String, String> searchMap) {
		SqlSession session = getSqlSession();
		
		int count = noticeDAO.selectAllCount(session,searchMap);
		
		session.close();
		
		return count;
	}

}
