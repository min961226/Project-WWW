package com.qs.www.mng.working.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.mng.working.model.dto.WorkingDTO;

@WebServlet("/mng/workingSystem/insert")
public class InsertMngWorkingSystemServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String workName = request.getParameter("workName");
		
		int minimal = Integer.parseInt(request.getParameter("minimal"));
		
		int monCheckInHour = Integer.parseInt(request.getParameter("monCheckInHour"));
		
		/* workOutFreeType이 2면 '출퇴근시간 설정'이므로, 그 때 설정해준다. */
		String breakStartTime = null;
		String breakEndTime = null;
		
		int workOutFreeType = Integer.parseInt(request.getParameter("workOutFreeType"));
		if(workOutFreeType == 2) {
			breakStartTime = request.getParameter("breakStartHour") 
					+ ":" + request.getParameter("breakStartMin");
			breakEndTime = request.getParameter("breakEndHour") 
					+ ":" + request.getParameter("breakEndMin");
		}
		
		String checkInTime = request.getParameter("checkInHour") 
				+ ":" + request.getParameter("checkInMin");
		String checkOutTime = request.getParameter("checkOutHour") 
				+ ":" + request.getParameter("checkOutMin");
		
		WorkingDTO workingDTO = new WorkingDTO();
		workingDTO.setWorkName(workName);
		workingDTO.setBreakStartTime(breakStartTime);
		workingDTO.setBreakEndTime(breakEndTime);
		workingDTO.setMinimalWorkHour(minimal);
		
		
		
		
	}
}
