package com.qs.www.mng.employee.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.mng.employee.model.service.MngEmployeeService;

@WebServlet("/mng/employee/list/update")
public class UpdateMngEmployeeListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int memberNo = Integer.parseInt(request.getParameter("no"));
		
		MngEmployeeService mngEmployeeService = new MngEmployeeService();
		MemberInfoDTO memberInfo = mngEmployeeService.selectOneMngEmployee(memberNo);
		String rrn = memberInfo.getRrn();
		String address = memberInfo.getAddress();
		
		String firstRrn = "";
		String lastRrn = "";
		
		if(rrn != null) {
			firstRrn = rrn.split("-")[0];
			lastRrn = rrn.split("-")[1];
		}
		
		String zipCode = "";
		String address1 = "";
		String address2 = "";
		
		if(address != null) {
			zipCode = address.split("\\$")[0];
			address1 = address.split("\\$")[1];
			address2 = address.split("\\$")[2];
		}
		
		String path = "/WEB-INF/views/mngemployee/updateEmployee.jsp";
			
		request.setAttribute("memberInfo", memberInfo);
		request.setAttribute("firstRrn", firstRrn);
		request.setAttribute("lastRrn", lastRrn);
		request.setAttribute("zipCode", zipCode);
		request.setAttribute("address1", address1);
		request.setAttribute("address2", address2);
		
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
