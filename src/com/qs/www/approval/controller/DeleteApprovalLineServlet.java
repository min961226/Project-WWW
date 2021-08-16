package com.qs.www.approval.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.approval.model.service.ApprovalService;

@WebServlet("/approval/line/delete")
public class DeleteApprovalLineServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("삭 제!");
		int lineNo = Integer.parseInt(request.getParameter("no"));

		int result =  new ApprovalService().deleteLine(lineNo);
		
		String path = "";
		if(result > 0) {
            path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "deleteLine");
			
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "deleteLine");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
