package com.qs.www.approval.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/approval/insert")
public class InsertApprovalServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("결재 신청");
		request.getRequestDispatcher("/WEB-INF/views/approval/insertApproval.jsp").forward(request, response);
		
	}
}
