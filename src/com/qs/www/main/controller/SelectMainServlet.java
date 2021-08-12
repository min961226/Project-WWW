package com.qs.www.main.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.main.model.dto.MainDTO;
import com.qs.www.main.model.dto.MainInfoDTO;
import com.qs.www.main.model.service.MainService;
import com.qs.www.member.model.dto.MemberInfoDTO;

@WebServlet("/main")
public class SelectMainServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		MemberInfoDTO memberInfo = (MemberInfoDTO) session.getAttribute("memberInfo");
		
		int memberNo = memberInfo.getMemberNo();
		
		// 날짜 출력 양식
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 오늘 날짜
		Calendar today = new GregorianCalendar();
		String todayDate = sdf.format(today.getTime());
		// 이번주 월요일 날짜
		today.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		String weekStartDate = sdf.format(today.getTime());
		// 다음주 일요일 날짜
		today.add(Calendar.DATE, 6);
		String weekEndDate = sdf.format(today.getTime());
		
		MainDTO mainDTO = new MainDTO();
		mainDTO.setMemberNo(memberNo);
		mainDTO.setToday(todayDate);
		mainDTO.setWeekStartDate(weekStartDate);
		mainDTO.setWeekEndDate(weekEndDate);
		
		MainService mainService = new MainService();
		
		MainInfoDTO mainInfoDTO = mainService.selectMain(mainDTO);
		
		request.getRequestDispatcher("/WEB-INF/views/main/main.jsp").forward(request, response);
		
	}
}
