package com.qs.www.welfare.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.welfare.model.dto.SeminarRoomReservDTO;
import com.qs.www.welfare.model.service.WelfareService;

@WebServlet("/welfare/seminarRoom/insert")
public class InsertSeminarRoomWelfareServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		WelfareService welfareService = new WelfareService();
		SeminarRoomReservDTO seminarRoomReservDTO = new SeminarRoomReservDTO();
		List<SeminarRoomReservDTO> seminarRoomReserv = welfareService.selectSeminarRoomReserv(Integer.parseInt(request.getParameter("roomNo")));

		seminarRoomReservDTO.setMeetingRoomNo(Integer.parseInt(request.getParameter("roomNo")));
		seminarRoomReservDTO.setMemberNo(Integer.parseInt(request.getParameter("memberNo")));
		seminarRoomReservDTO.setUseDate(Date.valueOf(request.getParameter("useDate")));
		seminarRoomReservDTO.setReservNo(Integer.parseInt(request.getParameter("reservTime")));
		seminarRoomReservDTO.setSeminarInfo(request.getParameter("seminarInfo"));

		String path = "";

		int count = 0;
		for (int i = 0; i < seminarRoomReserv.size(); i++) {
			if (seminarRoomReserv.get(i).getMeetingRoomNo() == Integer.parseInt(request.getParameter("roomNo"))							//방번호와 시간, 날짜가 겹치는 일에대해서 예외처리
					&& seminarRoomReserv.get(i).getUseDate().equals(Date.valueOf(request.getParameter("useDate")))
					&& seminarRoomReserv.get(i).getReservNo() == Integer.parseInt(request.getParameter("reservTime"))) {
				count++;
			}
		}
		if (count == 0) {
			int insertSeminarRoom = welfareService.insertSeminarRoom(seminarRoomReservDTO);
			if (insertSeminarRoom > 0) {
				path = "/WEB-INF/views/common/success.jsp";
				request.setAttribute("successCode", "insertSeminarRoom");
			}
		}else {																															//중복되는 시간이 있을경우			
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "insertSeminarRoom");
		}


		request.getRequestDispatcher(path).forward(request, response);

	}
}
