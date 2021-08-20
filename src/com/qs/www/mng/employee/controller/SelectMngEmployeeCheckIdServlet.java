package com.qs.www.mng.employee.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.mng.employee.model.service.MngEmployeeService;

@WebServlet("/mng/employee/checkId/select")
public class SelectMngEmployeeCheckIdServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String memberId = request.getParameter("memberId");
		
		MngEmployeeService mngEmployeeService = new MngEmployeeService();
		int result = mngEmployeeService.checkMemberId(memberId);
		if(memberId.equals("")) {
			result = -1;
		}
		
		PrintWriter out = response.getWriter();
		
		out.print(result);
		
		out.flush();
		out.close();
	}
}
