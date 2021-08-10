package com.qs.www.approval.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.approval.model.dto.ApprovalLineDTO;
import com.qs.www.approval.model.service.ApprovalService;
import com.qs.www.member.model.dto.MemberDTO;

@WebServlet("/approval/insert")
public class InsertApprovalServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int no = ((MemberDTO) request.getSession().getAttribute("loginMember")).getMemberNo();
	
		List<ApprovalLineDTO> lineList = new ApprovalService().selectApprovalLine(no);
		
		request.setAttribute("lineList", lineList);
		request.getRequestDispatcher("/WEB-INF/views/approval/insertApproval.jsp").forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = ((MemberDTO) request.getSession().getAttribute("loginMember")).getName();
		System.out.println(name);
		
		int lineNo = Integer.parseInt(request.getParameter("line"));
		System.out.println(lineNo);
		
//		List<ApprovalLineDTO> lineList = new ApprovalService().selectApprovalLine(no);
	}
	
	
}
