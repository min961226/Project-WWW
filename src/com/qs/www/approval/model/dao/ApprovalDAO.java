package com.qs.www.approval.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.approval.model.dto.ApprovalLineDTO;
import com.qs.www.approval.model.dto.ApproverDTO;
import com.qs.www.member.model.dto.MemberDTO;
import com.qs.www.schedule.model.dto.ApproverPerReportDTO;
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

	public ReportDTO selectOneReportDetail(SqlSession session, int reportNo) {

		return session.selectOne("ApprovalDAO.selectOneReportDetail", reportNo);
	}

	public List<WorkingDocumentItemDTO> selectReportItemList(SqlSession session, int no) {
		
		return session.selectList("ApprovalDAO.selectReportItemList", no);
	}

	public int callbackSelectedReport(SqlSession session, int no) {
		
		return session.update("ApprovalDAO.callbackSelectedReport", no);
	}

	public int callbackApproverPerReport(SqlSession session, int no) {
		
		return session.update("ApprovalDAO.callbackApproverPerReport", no);
	}

	public List<ApproverPerReportDTO> selectMyTurnAPR(SqlSession session, int no) {
		
		return session.selectList("ApprovalDAO.selectMyTurnAPR", no);
	}

	public MemberDTO selectMemberName(SqlSession session, int membertNo) {
		
		return session.selectOne("ApprovalDAO.selectMemberName", membertNo);
	}

}
