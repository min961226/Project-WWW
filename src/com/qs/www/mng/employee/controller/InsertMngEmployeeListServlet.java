package com.qs.www.mng.employee.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.member.model.dto.DepartmentDTO;
import com.qs.www.member.model.dto.JobDTO;
import com.qs.www.member.model.dto.RoleDTO;
import com.qs.www.mng.employee.model.service.MngEmployeeService;

@WebServlet("/mng/employee/list/insert")
public class InsertMngEmployeeListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MngEmployeeService mngEmployeeService = new MngEmployeeService();
		
		List<DepartmentDTO> deptList = mngEmployeeService.selectDeptList();
		List<JobDTO> jobList = mngEmployeeService.selectJobList();
		List<RoleDTO> roleList = mngEmployeeService.selectRoleList();
		
		request.setAttribute("deptList", deptList);
		request.setAttribute("jobList", jobList);
		request.setAttribute("roleList", roleList);
		
		request.getRequestDispatcher("/WEB-INF/views/mngemployee/insertEmployee.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String memberId = request.getParameter("memberId");
		String name = request.getParameter("name");
		
		
	}
}
