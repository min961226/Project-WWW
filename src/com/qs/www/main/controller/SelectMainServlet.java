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
		int remainingHoliday = memberInfo.getRemainingHoliday();
		
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
		
		WorkInfoDTO workInfo = new WorkInfoDTO();
		workInfo.setMemberNo(memberNo);
		workInfo.setAppWorkType(appWorkType);
		workInfo.setWorkCode(workCode);
		workInfo.setRemainingHoliday(remainingHoliday);
		workInfo.setToday(todayDate);
		workInfo.setWeekStartDate(weekStartDate);
		workInfo.setWeekEndDate(weekEndDate);
		
		MainService mainService = new MainService();
		
		List<CommutingLogDTO> commutingLog = mainService.selectCommutingLog(workInfo);
		List<WorkingLogDTO> workingLog = mainService.selectWorkingLog(workInfo);
		
		System.out.println(commutingLog);
		System.out.println(workingLog);
		
		request.getRequestDispatcher("/WEB-INF/views/main/main.jsp").forward(request, response);
		
	}
}
