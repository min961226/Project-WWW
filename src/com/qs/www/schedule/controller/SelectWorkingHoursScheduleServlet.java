package com.qs.www.schedule.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.member.model.dto.MemberInfoDTO;

@WebServlet("/schedule/workingHours/select")
public class SelectWorkingHoursScheduleServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("근무시간 조회");
		
		HttpSession session = request.getSession();	
		
		/* 1. 이번 달 근태 통계 */
		/* 1-1. 이번달 근무일수가 몇일이고, 그 중 몇일째인지 */
		/* 1-2. 이번달 지각횟수 */
		/* 1-3. 이번달 출/퇴근 미체크 횟수가 몇번인지 */
		/* 1-4. 오늘 퇴근체크를 했는지 */
		
		
		/* 2. 근무시간 현황 */
		/* 2-1. 이름, 아이디, 오늘 근무제 정보 */
		int no = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
		String memberName = ((MemberInfoDTO) session.getAttribute("memberInfo")).getName();
		int workCode = ((MemberInfoDTO) session.getAttribute("memberInfo")).getWorkCode();
		
		/* 2-2. 이번주 정규근무 시간과 잔여시간 */
		/* 2-3. 이번주 초과근무 시간과 잔여시간 */
		/* 2-4. 이번달 일수대로 출근시간, 퇴근시간,  */
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		String path = "/WEB-INF/views/schedule/checkWorkingHours.jsp";

		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
