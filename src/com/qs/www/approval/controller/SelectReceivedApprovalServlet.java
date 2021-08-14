package com.qs.www.approval.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.approval.model.service.ApprovalService;
import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.schedule.model.dto.ApproverPerReportDTO;
import com.qs.www.schedule.model.dto.ReportDTO;

@WebServlet("/approval/received/select")
public class SelectReceivedApprovalServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		int no = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
		
		// 1. 상신별 결재자에서 회원번호 = '내회원번호', 결재상태 not in ('대기', '미처리') 인 상신별 결재자 테이블 리스트(recievedApproverPerReport) 가져오기
		
		List<ApproverPerReportDTO> APRList = new ApprovalService().selecReceivedAPR(no);
		
		// 2. 상신별 결재자 테이블 리스트(recievedApproverPerReport)에 든 결재번호에 해당되는 상신 테이블리스트(MyWaitingApproval)가져오기
		List<ReportDTO> reportList = new ArrayList<ReportDTO>();
		int reportNo = 0;
		for(ApproverPerReportDTO APR : APRList) {
			reportNo = APR.getReportNo();
			ReportDTO report = new ApprovalService().selectOneReportDetail(reportNo);
			reportList.add(report);
		}
		
		
		request.setAttribute("reportList", reportList);
		request.getRequestDispatcher("/WEB-INF/views/approval/receivedApproval.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
