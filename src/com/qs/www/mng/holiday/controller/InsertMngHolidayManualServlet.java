package com.qs.www.mng.holiday.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.mng.holiday.model.service.MngHolidayService;
import com.qs.www.schedule.model.dto.HolidayLogDTO;
import com.qs.www.schedule.model.service.HolidayService;

@WebServlet("/mng/holiday/manual/insert")
public class InsertMngHolidayManualServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));    //휴가를 부여받을 사원번호
		
		MemberInfoDTO memberInfo = new MngHolidayService().selectMemberInfo(memberNo);
		
		request.setAttribute("memberInfo", memberInfo);
		request.getRequestDispatcher("/WEB-INF/views/mngholiday/insertHolidayManual.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));    //휴가를 부여받을 사원번호
		int dayNumber = Integer.parseInt(request.getParameter("dayNumber"));
		
		HolidayService holidayService = new HolidayService();
		int havingHoliday = holidayService.selectHavingHoliday(memberNo);
		
		int remainHoliday = havingHoliday + dayNumber;
		MemberInfoDTO memberInfo = new MemberInfoDTO();
		memberInfo.setMemberNo(memberNo);
		memberInfo.setRemainingHoliday(remainHoliday);
		
		int result1 =  holidayService.updateHavingHoliday(memberInfo);
		
		HolidayLogDTO holidayLogDTO = new HolidayLogDTO();
		holidayLogDTO.setMemberNo(memberNo); 				//사번
		holidayLogDTO.setLogNote(request.getParameter("note")); 			//비고
		holidayLogDTO.setHolidayCode(0); //휴가코드
		holidayLogDTO.setHolidayDuringDate(request.getParameter("dayNumber")); 	//기간일수
		
		int result2 =  new MngHolidayService().insertManualHolidayLog(holidayLogDTO);
		
		String path = "";
		if(result1 > 0 && result2 > 0) {
            path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "insertManualHolidayLog");
			
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "insertManualHolidayLog");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}
}
