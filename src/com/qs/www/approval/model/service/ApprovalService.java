package com.qs.www.approval.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.approval.model.dao.ApprovalDAO;
import com.qs.www.approval.model.dto.ApprovalLineDTO;
import com.qs.www.approval.model.dto.ApproverDTO;
import com.qs.www.schedule.model.dto.ReportDTO;
import com.qs.www.schedule.model.dto.WorkingDocumentItemDTO;

import static com.qs.www.common.mybatis.Template.getSqlSession;

public class ApprovalService {
	
	private final ApprovalDAO approvalDAO;
	
	public ApprovalService() {
		approvalDAO =new ApprovalDAO();
	}

	public List<ApprovalLineDTO> selectApprovalLine(int no) {
		SqlSession session = getSqlSession();

		List<ApprovalLineDTO> lineList = approvalDAO.selectApprovalLine(session, no);

		session.close();

		return lineList;
	}

	public List<ApproverDTO> selectApprover(int lineNo) {
		SqlSession session = getSqlSession();

		List<ApproverDTO> approverList = approvalDAO.selectApprover(session, lineNo);

		session.close();

		return approverList;
	}

	public int selectReportNum() {
		SqlSession session = getSqlSession();

		int reportNum = approvalDAO.selectReportNum(session);
		
		if(reportNum > 0) {
            session.commit();
        } else {
            session.rollback();
        }
		session.close();

		return reportNum;
	}

	public List<ReportDTO> selectMyReport(int no) {
		SqlSession session = getSqlSession();

		List<ReportDTO> reportList = approvalDAO.selectMyReport(session, no);

		session.close();

		return reportList;
	}

	public ReportDTO selectOneReportDetail(int no) {
		SqlSession session = getSqlSession();

		ReportDTO reportList = approvalDAO.selectOneReportDetail(session, no);

		session.close();

		return reportList;
	}

	public List<WorkingDocumentItemDTO> selectReportItemList(int no) {
		SqlSession session = getSqlSession();

		List<WorkingDocumentItemDTO>  itemList = approvalDAO.selectReportItemList(session, no);

		session.close();

		return itemList;
	}

}
