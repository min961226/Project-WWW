package com.qs.www.mng.welfare.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.mng.welfare.model.service.MngWelfareService;
import com.qs.www.schedule.model.dto.ReportDTO;
import com.qs.www.welfare.model.service.WelfareService;

@WebServlet("/mng/welfare/applied/select")
public class SelectMngAppliedWelfareServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("복지 신청 내역");
		
		MngWelfareService mngWelfareService = new MngWelfareService();
		
		List<ReportDTO> allAppliedWelfareList= mngWelfareService.selectAllAppliedWelfareList();							//멤버 번호에 해당하는 신청 복지 목록을 가져온다.
		
		System.out.println(allAppliedWelfareList);																			//신청된 복지 목록 확인
		
		request.setAttribute("allAppliedWelfareList", allAppliedWelfareList);
		request.getRequestDispatcher("/WEB-INF/views/mngwelfare/appliedWelfare.jsp").forward(request, response);
	}

}
