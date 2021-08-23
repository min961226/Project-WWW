package com.qs.www.mng.working.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.mng.working.model.dto.WorkingDTO;
import com.qs.www.mng.working.model.service.MngWorkingSystemService;

@WebServlet("/mng/workingSystem/insert")
public class InsertMngWorkingSystemServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();		
		String workName = request.getParameter("workName");
		
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
		int minimal = Integer.parseInt(request.getParameter("minimal"));
		
		String checkInTime = request.getParameter("checkInHour") 
				+ ":" + request.getParameter("checkInMin");
		String checkOutTime = request.getParameter("checkOutHour") 
				+ ":" + request.getParameter("checkOutMin");
		
		int memberNo = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
		
		WorkingDTO workingDTO = new WorkingDTO();
		workingDTO.setWorkName(workName);
		workingDTO.setBreakStartTime(breakStartTime);
		workingDTO.setBreakEndTime(breakEndTime);
		workingDTO.setMinimalWorkHour(minimal);
		workingDTO.setCheckInTime(checkInTime);
		workingDTO.setCheckOutTime(checkOutTime);
		workingDTO.setMemberNo(memberNo);
		System.out.println("workingDTO : " + workingDTO);
		
		/* TBL_STANDARD_WORK에 insert*/
		MngWorkingSystemService mngWorkingSystemService = new MngWorkingSystemService();
		int result = mngWorkingSystemService.InsertStandardMngWorkingSystem(workingDTO);
		
		/* 성공여부에 따라 success 혹은 fail로 넘겨줌 */
		String path = "";
		if(result > 0 ) {
			System.out.println("근무제추가 성공");			
			path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "inserWorkType");

		} else {
			System.out.println("근무제추가 실패");			
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "insertWorkType");
		}

		request.getRequestDispatcher(path).forward(request, response);
		
		
		
	}
}
