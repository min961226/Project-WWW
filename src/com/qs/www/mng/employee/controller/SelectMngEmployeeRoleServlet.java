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
import com.qs.www.member.model.dto.DepartmentDTO;
import com.qs.www.member.model.dto.JobDTO;
import com.qs.www.member.model.dto.RoleDTO;
import com.qs.www.mng.employee.model.service.MngEmployeeService;

@WebServlet("/mng/employee/role/select")
public class SelectMngEmployeeRoleServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MngEmployeeService mngEmployeeService = new MngEmployeeService();
		
		List<RoleDTO> roleList = mngEmployeeService.selectRoleList();
		List<DepartmentDTO> deptList = mngEmployeeService.selectDeptList();
		List<JobDTO> jobList = mngEmployeeService.selectJobList();
		List<AuthorityDTO> authorityList = mngEmployeeService.selectAuthorityList();
		
		String path = "";
		if(authorityList != null) {
			path = "/WEB-INF/views/mngemployee/roleList.jsp";
			
			request.setAttribute("roleList", roleList);
			request.setAttribute("deptList", deptList);
			request.setAttribute("jobList", jobList);
			request.setAttribute("authorityList", authorityList);
		} else {
			path = "/WEB-INF/views/common/error-500.jsp";
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}
}
