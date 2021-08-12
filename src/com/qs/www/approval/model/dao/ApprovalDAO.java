package com.qs.www.approval.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.approval.model.dto.ApprovalLineDTO;
import com.qs.www.approval.model.dto.ApproverDTO;
import com.qs.www.schedule.model.dto.ReportDTO;
import com.qs.www.schedule.model.dto.WorkingDocumentItemDTO;

public class ApprovalDAO {

	public List<ApprovalLineDTO> selectApprovalLine(SqlSession session, int no) {
		
		return session.selectList("ApprovalDAO.selectApprovalLine", no);
	}

	public List<ApproverDTO> selectApprover(SqlSession session, int lineNo) {

		return session.selectList("ApprovalDAO.selectApprover", lineNo);
	}

	public int selectReportNum(SqlSession session) {
		
		return session.selectOne("ApprovalDAO.selectReportNum");
	}

	public List<ReportDTO> selectMyReport(SqlSession session, int no) {
		
		return session.selectList("ApprovalDAO.selectMyReport", no);
	}

	public ReportDTO selectOneReportDetail(SqlSession session, int no) {

		return session.selectOne("ApprovalDAO.selectOneReportDetail", no);
	}

	public List<WorkingDocumentItemDTO> selectReportItemList(SqlSession session, int no) {
		
		return session.selectList("ApprovalDAO.selectReportItemList", no);
	}

	public int callbackSelectedReport(SqlSession session, int no) {
		
		return session.update("ApprovalDAO.callbackSelectedReport", no);
	}

}
