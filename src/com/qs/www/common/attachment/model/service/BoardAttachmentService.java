package com.qs.www.common.attachment.model.service;

import static com.qs.www.common.mybatis.Template.getSqlSession;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.common.attachment.model.dao.AttachmentDAO;
import com.qs.www.common.attachment.model.dao.BoardAttachmentDAO;
import com.qs.www.common.attachment.model.dto.AttachmentDTO;
import com.qs.www.common.attachment.model.dto.BoardAttachmentDTO;

public class BoardAttachmentService {

	private final BoardAttachmentDAO boardattachmentDAO;

	public BoardAttachmentService() {
		boardattachmentDAO =new BoardAttachmentDAO();
	}
	
	public int insertFileUpload(Map<String, Object> fileMap) {
		SqlSession session = getSqlSession();

		int result = boardattachmentDAO.insertFileUpload(session, fileMap);

		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();

		return result;
	}

	public BoardAttachmentDTO selectBoardAttachmentByBoardNo(int boardNo) {
		SqlSession session = getSqlSession();

		BoardAttachmentDTO boardattachmentDTO = boardattachmentDAO.selectBoardAttachmentByBoardNo(session, boardNo);

		session.close();

		return boardattachmentDTO;
	


	}
}
