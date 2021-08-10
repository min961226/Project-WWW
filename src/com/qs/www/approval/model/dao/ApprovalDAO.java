package com.qs.www.approval.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.approval.model.dto.ApprovalLineDTO;
import com.qs.www.approval.model.dto.ApproverDTO;

public class ApprovalDAO {

	public List<ApprovalLineDTO> selectApprovalLine(SqlSession session, int no) {
		
		return session.selectList("ApprovalDAO.selectApprovalLine", no);
	}

	public List<ApproverDTO> selectApprover(SqlSession session, int lineNo) {

		return session.selectList("ApprovalDAO.selectApprover", lineNo);
	}

	public String selectReportNum(SqlSession session) {
		
		return session.selectOne("ApprovalDAO.selectReportNum");
	}

}
