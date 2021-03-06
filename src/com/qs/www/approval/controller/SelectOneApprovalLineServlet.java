package com.qs.www.approval.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.approval.model.dto.ApprovalLineDTO;
import com.qs.www.approval.model.dto.ApproverDTO;
import com.qs.www.approval.model.service.ApprovalService;
import com.qs.www.member.model.dto.MemberInfoDTO;

@WebServlet("/approval/line/selectOne")
public class SelectOneApprovalLineServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        HttpSession session = request.getSession();
		
		int memberNo = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		ApprovalLineDTO line = new ApprovalService().selectApprovalOneLine(no);
		List<ApproverDTO> approverList = new ApprovalService().selectApprover(no);
		
		request.setAttribute("line", line);
		request.setAttribute("approverList", approverList);
		request.getRequestDispatcher("/WEB-INF/views/approval/detailApprovalLine.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
