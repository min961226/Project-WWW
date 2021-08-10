package com.qs.www.approval.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.approval.model.dao.ApprovalDAO;
import com.qs.www.approval.model.dto.ApprovalLineDTO;
import com.qs.www.approval.model.dto.ApproverDTO;

import static com.qs.www.common.mybatis.Template.getSqlSession;

public class ApprovalService {
	
	private final ApprovalDAO approvalDAO;
	
	public ApprovalService() {
		approvalDAO =new ApprovalDAO();
	}

	public List<ApprovalLineDTO> selectApprovalLine(int no) {
		SqlSession session = getSqlSession();

		List<ApprovalLineDTO> lineList = approvalDAO.selectApprovalLine(session, no);
		
		System.out.println(lineList);

		session.close();

		return lineList;
	}

	public List<ApproverDTO> selectApprover(int lineNo) {
		SqlSession session = getSqlSession();

		List<ApproverDTO> approverList = approvalDAO.selectApprover(session, lineNo);
		
		System.out.println(approverList);

		session.close();

		return approverList;
	}

}
