package com.qs.www.common.attachment.model.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.common.attachment.model.dto.BoardAttachmentDTO;

public class BoardAttachmentDAO {

	public int insertFileUpload(SqlSession session, Map<String, Object> fileMap) {
		return session.insert("BoardAttachmentDAO.insertFileUpload", fileMap);
	}

	public BoardAttachmentDTO selectBoardAttachmentByBoardNo(SqlSession session, int boardNo) {
		return session.selectOne("BoardAttachmentDAO.selectBoardAttachmentByBoardNo", boardNo);
	}

}
