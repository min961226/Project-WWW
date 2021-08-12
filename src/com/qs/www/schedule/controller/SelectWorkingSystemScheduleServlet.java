package com.qs.www.schedule.controller;

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
import com.qs.www.schedule.model.service.ScheduleService;

@WebServlet("/schedule/workingSystem/select")
public class SelectWorkingSystemScheduleServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("근무 신청 현황");
		
		HttpSession session = request.getSession();
		
		//로그인 중인 사용자가 올린 결재중, documentNo가  4인 문서(정규근무신청)
		int no = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();

		List<ReportDTO> reportList = new ApprovalService().selectMyReport(no); //수정해야함

		System.out.println(reportList);

		request.setAttribute("reportList", reportList);
		request.getRequestDispatcher("/WEB-INF/views/schedule/appliedWorkingSystem.jsp").forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
