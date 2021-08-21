package com.qs.www.mng.employee.controller;

import java.io.IOException;
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
import com.qs.www.member.model.dto.MemberDTO;
import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.member.model.dto.RoleDTO;
import com.qs.www.mng.employee.model.service.MngEmployeeService;

@WebServlet("/mng/employee/list/update")
public class UpdateMngEmployeeListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 선택한 사원의 정보 조회
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
		
		// 부서, 직급, 역할(권한) 전체 목록 조회
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
		
		String path = "/WEB-INF/views/mngemployee/updateEmployee.jsp";
		
		request.setAttribute("memberInfo", memberInfo);
		request.setAttribute("firstRrn", firstRrn);
		request.setAttribute("lastRrn", lastRrn);
		request.setAttribute("zipCode", zipCode);
		request.setAttribute("address1", address1);
		request.setAttribute("address2", address2);
		
		request.setAttribute("deptList", deptList);
		request.setAttribute("deptListJson", jsonString);
		request.setAttribute("jobList", jobList);
		request.setAttribute("roleList", roleList);
		
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		String name = request.getParameter("name");
		String deptCode = request.getParameter("department").split(" ")[0];
		String jobCode = request.getParameter("job").split(" ")[0];
		String email = request.getParameter("email");
		String entYn = request.getParameter("entYn");
		String roleCode = request.getParameter("role").split(" ")[0];
		String rrn = request.getParameter("firstRrn") + "-" + request.getParameter("lastRrn");
		
		MemberDTO member = new MemberDTO();
		member.setMemberNo(memberNo);
		member.setName(name);
		member.setDeptCode(deptCode);
		member.setJobCode(jobCode);
		member.setEmail(email);
		member.setEntYn(entYn);
		member.setRoleCode(roleCode);
		member.setRrn(rrn);
		
		MngEmployeeService mngEmployeeService = new MngEmployeeService();
		int result = mngEmployeeService.updateMngEmployee(member);
		
		String path = "";
		if(result > 0) {
			path = "/WEB-INF/views/common/success.jsp";
			
			request.setAttribute("memberNo", memberNo);
			request.setAttribute("successCode", "updateMngEmployee");
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			
			request.setAttribute("failedCode", "updateMngEmployee");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}
}
