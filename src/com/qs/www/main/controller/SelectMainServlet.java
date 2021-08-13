package com.qs.www.main.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.main.model.dto.CommutingLogDTO;
import com.qs.www.main.model.dto.WorkInfoDTO;
import com.qs.www.main.model.dto.WorkingLogDTO;
import com.qs.www.main.model.service.MainService;
import com.qs.www.member.model.dto.MemberInfoDTO;

@WebServlet("/main")
public class SelectMainServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		MemberInfoDTO memberInfo = (MemberInfoDTO) session.getAttribute("memberInfo");
		
		int memberNo = memberInfo.getMemberNo();
		String appWorkType = memberInfo.getAppWorkType();
		int workCode = memberInfo.getWorkCode();
		String path = "";
		
		// 날짜 출력 양식
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 오늘 날짜
		Calendar date = new GregorianCalendar();
		String todayDate = sdf.format(date.getTime());
		// 이번주 월요일 날짜
		date.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		Calendar weekStartCalDate = date;
		String weekStartDate = sdf.format(weekStartCalDate.getTime());
		// 다음주 일요일 날짜
		date.add(Calendar.DATE, 6);
		Calendar weekEndCalDate = date;
		String weekEndDate = sdf.format(weekEndCalDate.getTime());
		// 요일별 날짜를 담을 변수
		date.add(Calendar.DATE, -6);
		Calendar selectedCalDate = date;
		String selectedDate = sdf.format(selectedCalDate.getTime());
		
		WorkInfoDTO workInfo = new WorkInfoDTO();
		workInfo.setMemberNo(memberNo);
		workInfo.setAppWorkType(appWorkType);
		workInfo.setWorkCode(workCode);
		workInfo.setToday(todayDate);
		workInfo.setWeekStartDate(weekStartDate);
		workInfo.setWeekEndDate(weekEndDate);
		workInfo.setSelectedDate(selectedDate);
		
		MainService mainService = new MainService();
		
		List<CommutingLogDTO> commutingLogList = mainService.selectCommutingLog(workInfo);
		List<WorkingLogDTO> workingLogList = mainService.selectWorkingLog(workInfo, selectedCalDate, sdf);
		
		request.setAttribute("workInfo", workInfo);
		request.setAttribute("commutingLogList", commutingLogList);
		request.setAttribute("workingLogList", workingLogList);
		path = "/WEB-INF/views/main/main.jsp";
			
		request.getRequestDispatcher(path).forward(request, response);
		
	}
}
