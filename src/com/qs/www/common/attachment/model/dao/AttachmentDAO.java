package com.qs.www.common.attachment.model.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.common.attachment.model.dto.AttachmentDTO;




public class AttachmentDAO {

	public int insertFileUpload(SqlSession session, Map<String, Object> fileMap) {
		return session.insert("AttachmentDAO.insertFileUpload", fileMap);
	}
	public AttachmentDTO selectAttachmentByReportNo(SqlSession session, int reportNo) {
		return session.selectOne("AttachmentDAO.selectAttachmentByReportNo", reportNo);
	}
}
