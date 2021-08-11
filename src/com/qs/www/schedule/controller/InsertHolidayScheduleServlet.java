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

@WebServlet("/schedule/holiday/insert")
public class InsertHolidayScheduleServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("휴가 신청");
		
		/* 1. 생성휴가, 사용휴가, 잔여휴가 일수 가져오기 */
		
		
		
		//로그인중인 유저가 생성자인 결재라인 가져오기
		HttpSession session = request.getSession();		
		int no = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
		List<ApprovalLineDTO> lineList = new ApprovalService().selectApprovalLine(no);
		request.setAttribute("lineList", lineList);
		session.setAttribute("lineList", lineList);
		
		
		
		
		String path = "/WEB-INF/views/schedule/insertHoliday.jsp";
		
		request.getRequestDispatcher(path).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
