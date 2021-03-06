package com.qs.www.mng.employee.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qs.www.member.model.dto.DepartmentDTO;
import com.qs.www.member.model.dto.JobDTO;
import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.member.model.dto.RoleDTO;
import com.qs.www.mng.employee.model.service.MngEmployeeService;

@WebServlet("/mng/employee/list/insert")
public class InsertMngEmployeeListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MngEmployeeService mngEmployeeService = new MngEmployeeService();
		
		int memberNo = mngEmployeeService.selectMemberNo();
		List<DepartmentDTO> deptList = mngEmployeeService.selectDeptList();
		List<JobDTO> jobList = mngEmployeeService.selectJobList();
		List<RoleDTO> roleList = mngEmployeeService.selectRoleList();
		
		Gson gson = new GsonBuilder()
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls()
				.disableHtmlEscaping()
				.create();
		String jsonString = gson.toJson(deptList);
		
		request.setAttribute("memberNo", memberNo);
		request.setAttribute("deptList", deptList);
		request.setAttribute("deptListJson", jsonString);
		request.setAttribute("jobList", jobList);
		request.setAttribute("roleList", roleList);
		
		request.getRequestDispatcher("/WEB-INF/views/mngemployee/insertEmployee.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("pwd1");
		String name = request.getParameter("name");
		String rrn = request.getParameter("firstRrn") + "-" + request.getParameter("lastRrn");
		String deptCode = request.getParameter("department").split(" ")[0];
		String jobCode = request.getParameter("job").split(" ")[0];
		String email = request.getParameter("email");
		Date enrollDate = Date.valueOf(request.getParameter("enrollDate"));
		String roleCode = request.getParameter("role").split(" ")[0];
		
		MemberInfoDTO memberInfo = new MemberInfoDTO();
		memberInfo.setMemberNo(memberNo);
		memberInfo.setMemberId(memberId);
		memberInfo.setPassword(password);
		memberInfo.setName(name);
		memberInfo.setRrn(rrn);
		memberInfo.setDepartment(new DepartmentDTO());
		memberInfo.getDepartment().setDeptCode(deptCode);
		memberInfo.setJob(new JobDTO());
		memberInfo.getJob().setJobCode(jobCode);
		memberInfo.setEmail(email);
		memberInfo.setEnrollDate(enrollDate);
		memberInfo.setRole(new RoleDTO());
		memberInfo.getRole().setRoleCode(roleCode);
		
		MngEmployeeService mngEmployeeService = new MngEmployeeService();
		int result1 = mngEmployeeService.insertMngEmployee(memberInfo);
		int result2 = mngEmployeeService.insertWorkingLog(memberInfo);
		
		String path = "";
		if(result1 > 0 && result2 > 0) {
			path = "/WEB-INF/views/common/success.jsp";
			
			request.setAttribute("successCode", "insertMngEmployee");
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			
			request.setAttribute("failedCode", "insertMngEmployee");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}
}
