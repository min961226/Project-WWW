package com.qs.www.main.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.DateFormatter;

import com.qs.www.main.model.dto.WorkInfoDTO;
import com.qs.www.main.model.dto.WorkingLogDTO;
import com.qs.www.main.model.service.MainService;
import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.mypage.model.dto.CommutingLogDTO;

@WebServlet("/main")
public class SelectMainServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		MemberInfoDTO memberInfo = (MemberInfoDTO) session.getAttribute("memberInfo");
		
		int memberNo = memberInfo.getMemberNo();
		
		// 오늘 날짜
		LocalDate currentDate = LocalDate.now();
		String todayDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		// 이번주 월요일 날짜
		String weekStartDate = currentDate
							.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
							.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		// 다음주 일요일 날짜
		String weekEndDate = currentDate
							.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))
							.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		// 요일별 날짜를 담을 변수(월요일 ~ 일요일)
		LocalDate selectedLocalDate = currentDate
								.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
		
		WorkInfoDTO workInfo = new WorkInfoDTO();
		workInfo.setMemberNo(memberNo);
		workInfo.setToday(todayDate);
		workInfo.setWeekStartDate(weekStartDate);
		workInfo.setWeekEndDate(weekEndDate);
		workInfo.setSelectedLocalDate(selectedLocalDate);
		
		MainService mainService = new MainService();
		
		List<CommutingLogDTO> commutingLogList = mainService.selectCommutingLog(workInfo);
		List<WorkingLogDTO> workingLogList = mainService.selectWorkingLog(workInfo);

		String path = "";

		request.setAttribute("workInfo", workInfo);
		request.setAttribute("commutingLogList", commutingLogList);
		request.setAttribute("workingLogList", workingLogList);
		path = "/WEB-INF/views/main/main.jsp";
			
		request.getRequestDispatcher(path).forward(request, response);
	}
}
