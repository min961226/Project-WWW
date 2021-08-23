package com.qs.www.mng.working.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.schedule.model.dto.StandardWorkDTO;
import com.qs.www.schedule.model.service.ScheduleService;

@WebServlet("/mng/workingSystem/select")
public class SelectMngWorkingSystemServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("근무 제도 관리"); 
		
		HttpSession session = request.getSession();	
		
		/* 근무제도 관련 기능들 가져오기 */
		List<StandardWorkDTO> workTypeList = new ScheduleService().selectAllWorkType();
				
		request.setAttribute("workTypeList", workTypeList);
		session.setAttribute("workTypeList", workTypeList);
		
		String path = "/WEB-INF/views/mngworkingsystem/workingSystem.jsp";		
		request.getRequestDispatcher(path).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
