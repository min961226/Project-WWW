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
import com.qs.www.welfare.model.service.WelfareService;

@WebServlet("/welfare/applied/list/select")
public class SelectAppliedWelfareListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		System.out.println("복지 신청 내역");
		HttpSession session = request.getSession();

		// 로그인 중인 사용자가 올린 결재중, documentNo가 4, 5인 문서(근무신청, 초과근무신청)
		int no = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();

		List<ReportDTO> appliedWelfareList= new WelfareService().selectAppliedWelfareList(no);

		System.out.println(appliedWelfareList);

		request.setAttribute("appliedWelfareList", appliedWelfareList);
		request.getRequestDispatcher("/WEB-INF/views/welfare/appliedWelfareList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
