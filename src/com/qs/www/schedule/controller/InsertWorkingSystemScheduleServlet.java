package com.qs.www.schedule.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.schedule.model.dto.MemberWorkLogDTO;
import com.qs.www.schedule.model.dto.ReportDTO;
import com.qs.www.schedule.model.service.ScheduleService;

@WebServlet("/schedule/workingSystem/insert")
public class InsertWorkingSystemScheduleServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("근무 신청");
		
		String path = "/WEB-INF/views/schedule/insertApplyWorkingSystem.jsp";
		
		request.getRequestDispatcher(path).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("InsertWorkingSystemScheduleServlet = 근무신청 서블렛으로 이동");
		
		int workNo = Integer.parseInt(request.getParameter("workNo"));
		int approverLine = Integer.parseInt(request.getParameter("approverLine"));
		java.sql.Date startDay = java.sql.Date.valueOf(request.getParameter("startDay"));
		java.sql.Date endDay = java.sql.Date.valueOf(request.getParameter("endDay"));
		String changeReason = request.getParameter("changeReason");
		
		String workType = "";
		if(workNo > 5) {
			workType = "표준근무제";
		} else {
			workType = "커스텀근무제";
		}
		
		System.out.println(workNo);
		System.out.println(workType);
		System.out.println(approverLine);
		System.out.println(startDay);
		System.out.println(endDay);
		System.out.println(changeReason);
		
//		HttpSession session = request.getSession();
//		int memberNo = (Integer) session.getAttribute("memberNo");
//		System.out.println(memberNo);
		
		MemberWorkLogDTO memberWorkLogDTO = new MemberWorkLogDTO();
		memberWorkLogDTO.setWorkNo(workNo);
		memberWorkLogDTO.setWorkType(workType);
		memberWorkLogDTO.setApproverLine(approverLine);
		memberWorkLogDTO.setStartDay(startDay);
		memberWorkLogDTO.setEndDay(endDay);
		memberWorkLogDTO.setChangeReason(changeReason);
		
		//일단 하드코딩
		ReportDTO reportDTO = new ReportDTO();
		reportDTO.setMemberNo(4);
		reportDTO.setDocumentNo(4);
		reportDTO.setReportNote(changeReason);
		
		ScheduleService scheduleService = new ScheduleService();
		
		int result = scheduleService.applyWorkingSystem(memberWorkLogDTO);
		
		System.out.println(result);
		
		
		
		
	}
}
