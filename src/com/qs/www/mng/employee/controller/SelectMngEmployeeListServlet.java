package com.qs.www.mng.employee.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.mng.employee.model.service.MngEmployeeService;

@WebServlet("/mng/employee/list/select")
public class SelectMngEmployeeListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MngEmployeeService mngEmployeeService = new MngEmployeeService();
		
		List<MemberInfoDTO> memberInfoList = mngEmployeeService.selectMngEmployeeList();
		
		String path = "";
		if(memberInfoList != null) {
			path = "/WEB-INF/views/mngemployee/employeeList.jsp";
			
			request.setAttribute("memberInfoList", memberInfoList);
		} else {
			path = "/WEB-INF/views/common/error-500.jsp";
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}
}
