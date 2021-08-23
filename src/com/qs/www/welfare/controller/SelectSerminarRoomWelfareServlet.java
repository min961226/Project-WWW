package com.qs.www.welfare.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
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
import com.qs.www.welfare.model.dto.SeminarReservTimeDTO;
import com.qs.www.welfare.model.dto.SeminarRoomDTO;
import com.qs.www.welfare.model.dto.SeminarRoomReservDTO;
import com.qs.www.welfare.model.service.WelfareService;

@WebServlet("/welfare/seminarRoom/select")
public class SelectSerminarRoomWelfareServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		WelfareService welfareService = new WelfareService();
		HttpSession session = request.getSession();
		
		int roomNo = Integer.parseInt(request.getParameter("no"));												//선택한 세미나실 번호
		List<SeminarRoomReservDTO> seminarRoomReserv = welfareService.selectSeminarRoomReserv(roomNo); 			//번호로 예약 내역 가져옴
		List<SeminarReservTimeDTO> seminarReservTime = welfareService.selectSeminarReservTime();				//사용할 시간 가져옴
		
		int memberNo = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
		String name = ((MemberInfoDTO) session.getAttribute("memberInfo")).getName();
		String deptName = ((MemberInfoDTO) session.getAttribute("memberInfo")).getDepartment().getDeptName();
		String jobName = ((MemberInfoDTO) session.getAttribute("memberInfo")).getJob().getJobName();
		LocalDate sysDate = LocalDate.now();																	//시스템 날짜
		LocalDate sysNextDate = sysDate.plusDays(1);															//시스템 날짜로부터 1일후
		LocalDate sysTwiceNextDate = sysDate.plusDays(2);														//시스템 날짜로부터 2일후
		
		request.setAttribute("memberNo", memberNo);
		request.setAttribute("deptName", deptName);
		request.setAttribute("jobName", jobName);
		request.setAttribute("name", name);
		request.setAttribute("seminarRoomReserv", seminarRoomReserv);
		request.setAttribute("roomNo", roomNo);
		request.setAttribute("seminarReservTime", seminarReservTime);
		request.setAttribute("sysDate", sysDate);
		request.setAttribute("sysNextDate", sysNextDate);
		request.setAttribute("sysTwiceNextDate", sysTwiceNextDate);
		
		String path="";
		path = "/WEB-INF/views/welfare/insertSeminarRoom.jsp";
		
		request.getRequestDispatcher(path).forward(request, response);
	}
}
