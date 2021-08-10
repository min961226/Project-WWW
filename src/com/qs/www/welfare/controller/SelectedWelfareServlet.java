package com.qs.www.welfare.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.member.model.dto.MemberDTO;
import com.qs.www.welfare.model.service.WelfareService;

@WebServlet("/welfare/list/selected")
public class SelectedWelfareServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		WelfareService welfareService = new WelfareService();
		
		HttpSession session = request.getSession();
		System.out.println("복지선택완료");

		int memberNo = ((MemberDTO) session.getAttribute("loginMember")).getMemberNo();
		String deptCode = ((MemberDTO) session.getAttribute("loginMember")).getDeptCode();
		String jobCode = ((MemberDTO) session.getAttribute("loginMember")).getJobCode();
		String name = ((MemberDTO) session.getAttribute("loginMember")).getName();
		System.out.println(deptCode);
		System.out.println(jobCode);
		String deptName = welfareService.selectDeptName(deptCode);
		String jobName = welfareService.selectJobName(jobCode);
		System.out.println(memberNo);
		System.out.println(name);
		
		System.out.println(deptName);
		System.out.println(jobName);
		request.setAttribute("memberNo", memberNo);
		request.setAttribute("deptName", deptName);
		request.setAttribute("jobName", jobName);
		request.setAttribute("name", name);
		
		String selectedWelfare = request.getParameter("selectedWelfare");
		System.out.println(selectedWelfare);
		
		String path = "";

		switch (selectedWelfare) {
		case "야간교통비신청서":
			path = "/WEB-INF/views/common/error-404.jsp";
			break;
		case "경조사신청서":
			path = "/WEB-INF/views/welfare/insertFamilyEvent.jsp";
			break;
		case "자기개발비신청서":
			path = "/WEB-INF/views/welfare/insertSelfDevelopment.jsp";
			break;
		case "기숙사입주신청서":
			path = "/WEB-INF/views/welfare/insertDomitory.jsp";
			break;
		case "회의실예약신청서":
			path = "/WEB-INF/views/welfare/detailSeminarRoom.jsp";
			break;
		case "노트북대여신청서":
			path = "/WEB-INF/views/welfare/insertLaptopRental.jsp";
			break;
		default:
			path = "/WEB-INF/views/common/error-404.jsp";
			break;
		}

		request.getRequestDispatcher(path).forward(request, response);
	}

}
