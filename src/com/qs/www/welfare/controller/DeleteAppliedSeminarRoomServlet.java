package com.qs.www.welfare.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.approval.model.service.ApprovalService;
import com.qs.www.welfare.model.dto.SeminarRoomReservDTO;
import com.qs.www.welfare.model.service.WelfareService;

@WebServlet("/welfare/applied/list/seminarRoom/delete")
public class DeleteAppliedSeminarRoomServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		WelfareService welfareService = new WelfareService();
		SeminarRoomReservDTO seminarRoomReservDTO = new SeminarRoomReservDTO();
		
		int roomNo = Integer.parseInt(request.getParameter("roomNo"));							//데이터 값 삭제할 회의실
		String useDate = request.getParameter("useDate");										//회의실 사용일자
		int reservNo = Integer.parseInt(request.getParameter("reservNo"));						//회의실 예약 번호
		
		seminarRoomReservDTO.setMeetingRoomNo(roomNo);											// 삭제할 방번호 설정
		seminarRoomReservDTO.setUseDate(Date.valueOf(useDate));									// 해당하는 날짜설정
		seminarRoomReservDTO.setReservNo(reservNo);												// 해당하는 시간 설정
		
		
		int result = welfareService.deleteAppliedSeminarRoom(seminarRoomReservDTO);				//예약된 회의실 번호 삭제
		
		String path = "";
		if(result > 0 ) {
            path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "deleteSeminarRoom");
			
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "deleteSeminarRoom");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}
}
