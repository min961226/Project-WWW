package com.qs.www.approval.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.approval.model.dto.ApprovalLineDTO;
import com.qs.www.approval.model.dto.ApproverDTO;
import com.qs.www.approval.model.service.ApprovalService;

@WebServlet("/approval/line/selectOne")
public class SelectOneApprovalLineServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		
		ApprovalLineDTO line = new ApprovalService().selectApprovalOneLine(no);
		List<ApproverDTO> approverList = new ApprovalService().selectApprover(no);
		System.out.println(approverList);
		
		request.setAttribute("line", line);
		request.setAttribute("approverList", approverList);
		request.getRequestDispatcher("/WEB-INF/views/approval/detailApprovalLine.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
