package com.qs.www.common.attachment.model.service;

import static com.qs.www.common.mybatis.Template.getSqlSession;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.common.attachment.model.dao.AttachmentDAO;
import com.qs.www.common.attachment.model.dto.AttachmentDTO;


public class AttachmentService {

	
	private final AttachmentDAO attachmentDAO;

	public AttachmentService() {
		attachmentDAO =new AttachmentDAO();
	}
	
	public int insertFileUpload(Map<String, Object> fileMap) {
		SqlSession session = getSqlSession();

		int result = attachmentDAO.insertFileUpload(session, fileMap);

		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();

		return result;
	}

	public AttachmentDTO selectAttachmentByReportNo(int reportNo) {
		SqlSession session = getSqlSession();

		AttachmentDTO attachmentDTO = attachmentDAO.selectAttachmentByReportNo(session, reportNo);

		session.close();

		return attachmentDTO;
	}
}
