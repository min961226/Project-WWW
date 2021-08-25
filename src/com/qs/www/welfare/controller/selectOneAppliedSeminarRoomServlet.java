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
		
		int no = Integer.parseInt(request.getParameter("no"));
		String useDate = request.getParameter("useDate");										//사용일자
		int reservNo =Integer.parseInt(request.getParameter("reservNo"));						//사용 시간 번호

		SeminarRoomReservDTO seminarRoomReservDTO = new SeminarRoomReservDTO();
		seminarRoomReservDTO.setMeetingRoomNo(no);												//세미나실 번호
		seminarRoomReservDTO.setUseDate(Date.valueOf(useDate));									//사용일자
		seminarRoomReservDTO.setReservNo(reservNo);												//예약시간
		
		/* 본인이 예약한 회의실 리스트를 가져옴*/
		SeminarRoomReservDTO selectSeminarRoom = new WelfareService().selectAppliedSeminarRoom(seminarRoomReservDTO);

		LocalDate sysDate = LocalDate.now();
		LocalDate seminarDate = LocalDate.parse(useDate);
		/* 세미나실 예약일자가 지났는가? */ 
		Boolean isDatePassed = sysDate.isBefore(seminarDate);									
		
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
