package com.qs.www.approval.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.approval.model.service.ApprovalService;
import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.schedule.model.dto.ReportDTO;

@WebServlet("/approval/applied/select")
public class SelectAppliedApprovalServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//로그인 중인 사용자가 올린 결재(상신, 상신 별 항목> 가져오기
		int no = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();

		List<ReportDTO> reportList = new ApprovalService().selectMyReport(no);

		System.out.println(reportList);

		List<ReportDTO> itemList = new ApprovalService().selectMyReport(no);

		System.out.println(reportList);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
