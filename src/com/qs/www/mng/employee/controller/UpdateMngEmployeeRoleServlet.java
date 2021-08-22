package com.qs.www.mng.employee.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.main.model.dto.AuthorityDTO;
import com.qs.www.mng.employee.model.service.MngEmployeeService;

@WebServlet("/mng/employee/role/update")
public class UpdateMngEmployeeRoleServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String roleCode = request.getParameter("role");
		List<AuthorityDTO> roleAuthorityList = new ArrayList<>();
		
		MngEmployeeService mngEmployeeService = new MngEmployeeService();
		
		roleAuthorityList = mngEmployeeService.selectRoleAuthorityList(roleCode);
		
		request.setAttribute("roleCode", roleCode);
		request.setAttribute("roleAuthorityList", roleAuthorityList);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
