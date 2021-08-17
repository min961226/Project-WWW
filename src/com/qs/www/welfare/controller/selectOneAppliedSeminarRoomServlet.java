package com.qs.www.welfare.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.approval.model.service.ApprovalService;
import com.qs.www.schedule.model.dto.ReportDTO;
import com.qs.www.schedule.model.dto.WorkingDocumentItemDTO;
import com.qs.www.welfare.model.dto.SeminarRoomReservDTO;
import com.qs.www.welfare.model.service.WelfareService;

/**
 * Servlet implementation class selectOneAppliedWelfareServlet
 */
@WebServlet("/welfare/applied/seminarRoom/selectOne")
public class selectOneAppliedSeminarRoomServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("복지 신청 상세보기");
		
		int no = Integer.parseInt(request.getParameter("no"));
		String useDate = request.getParameter("useDate");
		int reservNo =Integer.parseInt(request.getParameter("reservNo"));

		System.out.println(no);
		System.out.println(useDate);
		System.out.println(reservNo);
		
		SeminarRoomReservDTO seminarRoomReservDTO = new SeminarRoomReservDTO();
		seminarRoomReservDTO.setMeetingRoomNo(no);
		seminarRoomReservDTO.setUseDate(Date.valueOf(useDate));
		seminarRoomReservDTO.setReservNo(reservNo);
		
		SeminarRoomReservDTO selectSeminarRoom = new WelfareService().selectAppliedSeminarRoom(seminarRoomReservDTO);
		System.out.println(selectSeminarRoom);

		LocalDate sysDate = LocalDate.now();
		LocalDate seminarDate = LocalDate.parse(useDate);
		Boolean isDatePassed = sysDate.isBefore(seminarDate);							//세미나실 예약일자가 지났는가?
		
		request.setAttribute("roomNo", selectSeminarRoom.getMeetingRoomNo());
		request.setAttribute("memberNo", selectSeminarRoom.getMemberNo());
		request.setAttribute("useDate", selectSeminarRoom.getUseDate());
		request.setAttribute("reservNo", selectSeminarRoom.getReservNo());
		request.setAttribute("seminarInfo", selectSeminarRoom.getSeminarInfo());
		request.setAttribute("isDatePassed", isDatePassed);
		
		
		String path = "/WEB-INF/views/welfare/appliedDetailSeminarRoom.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}

}
