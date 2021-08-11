package com.qs.www.welfare.controller;

import java.io.IOException;
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
import com.qs.www.welfare.model.service.WelfareService;

@WebServlet("/welfare/list/selected")
public class SelectedWelfareServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String selectedWelfare = request.getParameter("selectedWelfare");
		System.out.println(selectedWelfare);
		
		WelfareService welfareService = new WelfareService();

		HttpSession session = request.getSession();
		System.out.println("복지선택완료");

		int memberNo = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
		String name = ((MemberInfoDTO) session.getAttribute("memberInfo")).getName();
		String deptName = ((MemberInfoDTO) session.getAttribute("memberInfo")).getDepartment().getDeptName();
		String jobName = ((MemberInfoDTO) session.getAttribute("memberInfo")).getJob().getJobName();
		List<String> approverLine = welfareService.selectApproverLine(memberNo);
		List<ApprovalLineDTO> lineList = new ApprovalService().selectApprovalLine(memberNo);
		
		System.out.println(memberNo);
		System.out.println(name);
		System.out.println(deptName);
		System.out.println(jobName);
		System.out.println(approverLine);
		
		request.setAttribute("memberNo", memberNo);
		request.setAttribute("deptName", deptName);
		request.setAttribute("jobName", jobName);
		request.setAttribute("name", name);
		request.setAttribute("approverLine", approverLine);
		request.setAttribute("lineList", lineList);
		
		System.out.println(lineList);
		
		String path = "";
		switch (selectedWelfare) {
		case "야간교통비신청서":
			path = "/WEB-INF/views/welfare/insertNightTransportation.jsp";
			break;
		case "경조사신청서":
			path = "/WEB-INF/views/welfare/insertFamilyEvent.jsp";
			break;
		case "자기개발비신청서":
			List<String> selfDevList = welfareService.checkSelfDevList();
			System.out.println(selfDevList);
				if(selfDevList != null) {
					path = "/WEB-INF/views/welfare/insertSelfDevelopment.jsp";
					request.setAttribute("selfDevList", selfDevList);
				} else {
					path = "/WEB-INF/views/common/error-404.jsp";
					request.setAttribute("message", "복지 목록조회 실패!");
				}
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
