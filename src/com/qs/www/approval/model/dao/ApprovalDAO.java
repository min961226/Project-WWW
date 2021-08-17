package com.qs.www.approval.model.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.approval.model.dto.ApprovalLineDTO;
import com.qs.www.approval.model.dto.ApproverDTO;
import com.qs.www.approval.model.dto.ApproverLogPerReportDTO;
import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.schedule.model.dto.ApproverPerReportDTO;
import com.qs.www.schedule.model.dto.ReportDTO;
import com.qs.www.schedule.model.dto.WorkingDocumentItemDTO;

public class ApprovalDAO {

	public List<ApprovalLineDTO> selectApprovalLine(SqlSession session, int no) {
		
		return session.selectList("ApprovalDAO.selectApprovalLine", no);
	}
    public List<ApprovalLineDTO> selectApprovalLineByMap(SqlSession session, HashMap<String, Object> selectedInfoMap) {
		
		return session.selectList("ApprovalDAO.selectApprovalLineByMap", selectedInfoMap);
	}
	
	public ApprovalLineDTO selectApprovalOneLine(SqlSession session, int no) {
		
		return session.selectOne("ApprovalDAO.selectApprovalOneLine", no);
	}

	public List<ApproverDTO> selectApprover(SqlSession session, int lineNo) {

		return session.selectList("ApprovalDAO.selectApprover", lineNo);
	}

	public int selectReportNum(SqlSession session) {
		
		return session.selectOne("ApprovalDAO.selectReportNum");
	}

	public List<ReportDTO> selectMyReport(SqlSession session, HashMap<String, Object> selectedInfoMap) {
		
		return session.selectList("ApprovalDAO.selectMyReport", selectedInfoMap);
	}
	
	public ReportDTO selectOneReportDetail(SqlSession session, int reportNo) {

		return session.selectOne("ApprovalDAO.selectOneReportDetail", reportNo);
	}
	
	public List<ReportDTO> selectReportDetail(SqlSession session, HashMap<String, Object> selectedInfoMap) {

		return session.selectList("ApprovalDAO.selectReportDetail", selectedInfoMap);
	}

	public List<WorkingDocumentItemDTO> selectReportItemList(SqlSession session, int no) {
		
		return session.selectList("ApprovalDAO.selectReportItemList", no);
	}
	
	public List<ApproverLogPerReportDTO> selectALPRList(SqlSession session, int no) {
		
		return session.selectList("ApprovalDAO.selectALPRList", no);
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
	
	public int insertALPR(SqlSession session, ApproverLogPerReportDTO aLPR) {
		
		return session.insert("ApprovalDAO.insertALPR", aLPR);
	}

	public ApproverPerReportDTO selectThisTurnAPR(SqlSession session, ApproverPerReportDTO thisAPR) {
		
		return session.selectOne("ApprovalDAO.selectThisTurnAPR", thisAPR);
	}

	public int updateThisTurnAPR(SqlSession session, ApproverPerReportDTO thisAPR) {
		
		return session.update("ApprovalDAO.updateThisTurnAPR", thisAPR);
	}

	public int updateNextTurnAPR(SqlSession session, ApproverPerReportDTO thisAPR) {
		
		return session.update("ApprovalDAO.updateNextTurnAPR", thisAPR);
	}

	public int finishAppReport(SqlSession session, ApproverPerReportDTO thisAPR) {
		
		return session.update("ApprovalDAO.finishAppReport", thisAPR);
	}

	public List<ApproverPerReportDTO> selecReceivedAPR(SqlSession session, int no) {
		
		return session.selectList("ApprovalDAO.selecReceivedAPR", no);
	}

	public List<MemberInfoDTO> selectMemberList(SqlSession session) {
		
		return session.selectList("ApprovalDAO.selectMemberList");
	}

	public int selectLineNum(SqlSession session) {
		
		return session.selectOne("ApprovalDAO.selectLineNum");
	}

	public int insertLine(SqlSession session, ApprovalLineDTO line) {
		
		return session.insert("ApprovalDAO.insertLine", line);
	}

	public int insertApprover(SqlSession session, ApproverDTO approver) {
		
		return session.insert("ApprovalDAO.insertApprover", approver);
	}

	public int deleteLine(SqlSession session, int lineNo) {
		
		return session.delete("ApprovalDAO.deleteLine",lineNo);
	}

	public int deleteApprover(SqlSession session, int lineNo) {
		
		return session.delete("ApprovalDAO.deleteApprover",lineNo);
	}

	public int updateLine(SqlSession session, ApprovalLineDTO line) {
		
		return session.update("ApprovalDAO.updateLine", line);
	}
	public int selectWaitingAPPCount(SqlSession session, HashMap<String, Object> countMap) {
		
		return session.selectOne("ApprovalDAO.selectWaitingAPPCount", countMap);
	}
	public int selectAppliedAPPCount(SqlSession session, HashMap<String, Object> countMap) {
		
		return session.selectOne("ApprovalDAO.selectAppliedAPPCount", countMap);
	}
	public int selectAPPLineCount(SqlSession session, HashMap<String, Object> countMap) {
		
		return session.selectOne("ApprovalDAO.selectAPPLineCount", countMap);
	}


	


	

}
