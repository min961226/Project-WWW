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
import com.qs.www.schedule.model.dto.ReportDTO;
import com.qs.www.schedule.model.service.ScheduleService;
import com.qs.www.welfare.model.dto.SeminarRoomDTO;
import com.qs.www.welfare.model.dto.SeminarRoomReservDTO;
import com.qs.www.welfare.model.service.WelfareService;

@WebServlet("/welfare/applied/list/select")
public class SelectAppliedWelfareListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		System.out.println("복지 신청 내역");
		HttpSession session = request.getSession();

		int no = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();									//세션에 저장된 멤버 번호를 가져온다

		List<ReportDTO> appliedWelfareList= new WelfareService().selectAppliedWelfareList(no);							//멤버 번호에 해당하는 신청 복지 목록을 가져온다.

		System.out.println(appliedWelfareList);																			//신청된 복지 목록 확인

		request.setAttribute("appliedWelfareList", appliedWelfareList);
		request.getRequestDispatcher("/WEB-INF/views/welfare/appliedWelfareList.jsp").forward(request, response);
	}
}
