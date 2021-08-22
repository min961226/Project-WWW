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
import com.qs.www.mng.employee.model.dto.MenuCategoryDTO;
import com.qs.www.mng.employee.model.service.MngEmployeeService;

@WebServlet("/mng/employee/role/select")
public class SelectMngEmployeeRoleServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MngEmployeeService mngEmployeeService = new MngEmployeeService();
		
		List<RoleDTO> roleList = mngEmployeeService.selectRoleList();
		List<DepartmentDTO> deptList = mngEmployeeService.selectDeptList();
		List<JobDTO> jobList = mngEmployeeService.selectJobList();
		List<AuthorityDTO> authorityList = mngEmployeeService.selectAuthorityList();
		for(AuthorityDTO authority : authorityList) {
			String menuCategoryUri = "/" + authority.getMenuUri().split("/")[1]
									+ "/" + authority.getMenuUri().split("/")[2]
									+ "/" + authority.getMenuUri().split("/")[3];
			String menuCode = authority.getMenuUri().split("/")[4];
			
			authority.setMenuCategoryUri(menuCategoryUri);
			authority.setCrudCode(menuCode);
		}
		List<MenuCategoryDTO> menuList = new ArrayList<>();
		menuList.add(new MenuCategoryDTO("/mng/employee/list", "직원 관리"));
		menuList.add(new MenuCategoryDTO("/mng/employee/role", "권한 관리"));
		menuList.add(new MenuCategoryDTO("/mng/workingSystem/type", "근무 제도 관리"));
		menuList.add(new MenuCategoryDTO("/mng/workingSystem/applied", "근무 신청 목록 관리"));
		menuList.add(new MenuCategoryDTO("/mng/workingSystem/commute", "출퇴근 기록 관리"));
		menuList.add(new MenuCategoryDTO("/mng/holiday/applied", "휴가 신청 관리"));
		menuList.add(new MenuCategoryDTO("/mng/holiday/category", "휴가 유형 관리"));
		menuList.add(new MenuCategoryDTO("/mng/holiday/rule", "휴가일수 발생규칙 관리"));
		menuList.add(new MenuCategoryDTO("/mng/board/notice", "공지사항 관리"));
		menuList.add(new MenuCategoryDTO("/mng/board/form", "문서서식 게시판 관리"));
		menuList.add(new MenuCategoryDTO("/mng/welfare/list", "시행 복지 관리"));
		menuList.add(new MenuCategoryDTO("/mng/welfare/applied", "복지 신청 목록"));
		menuList.add(new MenuCategoryDTO("/mng/welfare/laptopRental", "노트북 관리"));
		menuList.add(new MenuCategoryDTO("/mng/welfare/domitory", "기숙사 입주 관리"));

		String path = "";
		
		if(authorityList != null) {
			path = "/WEB-INF/views/mngemployee/roleList.jsp";
			
			request.setAttribute("roleList", roleList);
			request.setAttribute("deptList", deptList);
			request.setAttribute("jobList", jobList);
			request.setAttribute("authorityList", authorityList);
			request.setAttribute("menuList", menuList);
		} else {
			path = "/WEB-INF/views/common/error-500.jsp";
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
