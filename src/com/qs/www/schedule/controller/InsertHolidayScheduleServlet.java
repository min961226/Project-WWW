package com.qs.www.schedule.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.approval.model.dto.ApprovalLineDTO;
import com.qs.www.approval.model.service.ApprovalService;
import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.schedule.model.dto.HolidayTypeDTO;
import com.qs.www.schedule.model.service.HolidayService;
import com.qs.www.schedule.model.service.ScheduleService;

@WebServlet("/schedule/holiday/insert")
public class InsertHolidayScheduleServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("휴가 신청");
		
		/* 1. 생성휴가, 사용휴가, 잔여휴가 일수 가져오기 */
		
		
		/* 2. 로그인중인 유저가 생성자인 결재라인 select */
		HttpSession session = request.getSession();		
		int no = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
		List<ApprovalLineDTO> lineList = new ApprovalService().selectApprovalLine(no);
		System.out.println("InsertHolidayScheduleServlet의 lineList : " + lineList);
		
		request.setAttribute("lineList", lineList);
		session.setAttribute("lineList", lineList);
		
		
		/* 3. 휴가종류를 모두 select */
		List<HolidayTypeDTO> holidayTypeList = new HolidayService().selectAllHolidayType();
		System.out.println("InsertHolidayScheduleServlet의 holidayTypeList : " + holidayTypeList);
		
		request.setAttribute("holidayTypeList", holidayTypeList);
		session.setAttribute("holidayTypeList", holidayTypeList);

		/* 4. 휴가신청페이지로 포워드 */
		String path = "/WEB-INF/views/schedule/insertHoliday.jsp";		
		request.getRequestDispatcher(path).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("휴가신청 서블렛으로 이동");
		
		
	}
}
