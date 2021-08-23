package com.qs.www.main.controller;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.board.model.dto.NoticeDTO;
import com.qs.www.main.model.dto.WorkInfoDTO;
import com.qs.www.main.model.dto.WorkingLogDTO;
import com.qs.www.main.model.service.MainService;
import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.mypage.model.dto.CommutingLogDTO;
import com.qs.www.welfare.model.dto.WelfareListDTO;

@WebServlet("/main")
public class SelectMainServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		MemberInfoDTO memberInfo = (MemberInfoDTO) session.getAttribute("memberInfo");
		
		MainService mainService = new MainService();
		
		/* 근무제도 및 출퇴근 기록 조회 */
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
		
		WorkInfoDTO todayWorkInfo = new WorkInfoDTO();
		todayWorkInfo.setMemberNo(memberNo);
		todayWorkInfo.setToday(todayDate);
		todayWorkInfo.setWeekStartDate(weekStartDate);
		todayWorkInfo.setWeekEndDate(weekEndDate);
		todayWorkInfo.setSelectedLocalDate(selectedLocalDate);
		
		
		
		
		List<NoticeDTO> noticeList= mainService.selectNoticeList();
		List<WelfareListDTO> welfareList = mainService.selectWelfareList(memberNo);
		
		String path = "/WEB-INF/views/main/main.jsp";

		request.setAttribute("welfareList", welfareList);
		request.setAttribute("noticeList", noticeList);

		request.setAttribute("workInfo", todayWorkInfo);
		request.setAttribute("commutingLogList", commutingLogList);
		request.setAttribute("workingLogList", workingLogList);
			
		request.getRequestDispatcher(path).forward(request, response);
	}
}
