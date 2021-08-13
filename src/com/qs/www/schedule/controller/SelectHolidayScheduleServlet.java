package com.qs.www.schedule.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.schedule.model.dto.ReportDTO;
import com.qs.www.schedule.model.service.HolidayService;

@WebServlet("/schedule/holiday/select")
public class SelectHolidayScheduleServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("휴가 신청 현황");
		
		HttpSession session = request.getSession();
		
		//로그인 중인 사용자가 올린 결재중, documentNo가  6인 문서(휴가신청서)
		int no = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();

		List<ReportDTO> holidayReportList = new HolidayService().selectMyholidayReport(no);

		System.out.println(holidayReportList);

		request.setAttribute("holidayReportList", holidayReportList);
		request.getRequestDispatcher("/WEB-INF/views/schedule/holiday.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
