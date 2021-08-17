package com.qs.www.welfare.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.welfare.model.dto.SeminarRoomReservDTO;
import com.qs.www.welfare.model.service.WelfareService;

/**
 * Servlet implementation class SelectAppliedSeminarRoolListServlet
 */
@WebServlet("/welfare/applied/list/seminarRoom/select")
public class SelectAppliedSeminarRoolListServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("회의실 예약신청 내역");
		
		HttpSession session = request.getSession();

		int no = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
		
		List<SeminarRoomReservDTO> seminarRoomList = new WelfareService().selectSeminarRoomByMemberNo(no);
		
		request.setAttribute("seminarRoomList", seminarRoomList);
		request.getRequestDispatcher("/WEB-INF/views/welfare/appliedSeminarRoomList.jsp").forward(request, response);
		
	}
}
