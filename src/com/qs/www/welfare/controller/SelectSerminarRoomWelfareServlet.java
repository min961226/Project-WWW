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

@WebServlet("/welfare/seminarRoom/select")
public class SelectSerminarRoomWelfareServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("회의실 예약");
		
		WelfareService welfareService = new WelfareService();
		HttpSession session = request.getSession();

		int memberNo = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
		String name = ((MemberInfoDTO) session.getAttribute("memberInfo")).getName();
		String deptName = ((MemberInfoDTO) session.getAttribute("memberInfo")).getDepartment().getDeptName();
		String jobName = ((MemberInfoDTO) session.getAttribute("memberInfo")).getJob().getJobName();
		String addressAll = ((MemberInfoDTO) session.getAttribute("memberInfo")).getAddress();
		List<ApprovalLineDTO> lineList = new ApprovalService().selectApprovalLine(memberNo);
		
		String manageNo = request.getParameter("no");
		System.out.println(manageNo);
		
		request.setAttribute("memberNo", memberNo);
		request.setAttribute("deptName", deptName);
		request.setAttribute("jobName", jobName);
		request.setAttribute("name", name);
		request.setAttribute("lineList", lineList);
		request.setAttribute("manageNo", manageNo);

		String path="";
		path = "/WEB-INF/views/welfare/insertDomitory.jsp";
		
		request.getRequestDispatcher(path).forward(request, response);
	}
}
