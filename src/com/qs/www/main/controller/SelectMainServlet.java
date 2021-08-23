package com.qs.www.main.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
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
import com.qs.www.schedule.model.dto.OvertimeLogDTO;
import com.qs.www.schedule.model.service.ScheduleService;
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
		
		WorkInfoDTO weeklyWorkInfo = new WorkInfoDTO();
		weeklyWorkInfo.setMemberNo(memberNo);
		weeklyWorkInfo.setWeekStartDate(weekStartDate);
		weeklyWorkInfo.setWeekEndDate(weekEndDate);
		
		List<WorkingLogDTO> workingLogList = mainService.selectWorkingLogList(todayWorkInfo);
		List<CommutingLogDTO> commutingLogList = mainService.selectCommutingLog(todayWorkInfo);
		
		// 일간 및 주간 근무시간 계산
		long workTimeSum = 0;
		if(workingLogList != null) {
			for(WorkingLogDTO workingLog : workingLogList) {
				Date selectedSqlDate = Date.valueOf(workingLog.getSelectedDate());
				workingLog.setSelectedSqlDate(selectedSqlDate);
				
				if(workingLog.getSelectedDayOfWeek() != "토" && workingLog.getSelectedDayOfWeek() != "일") {
					LocalTime checkInTime = LocalTime.parse(workingLog.getWorkingType().getCheckInTime());
					LocalTime checkOutTime = LocalTime.parse(workingLog.getWorkingType().getCheckOutTime());
					LocalTime breakStartTime = LocalTime.parse(workingLog.getWorkingType().getBreakStartTime());
					LocalTime breakEndTime = LocalTime.parse(workingLog.getWorkingType().getBreakEndTime());
					
					long beforeBreakTime = Duration.between(checkInTime, breakStartTime).toHours();
					long afterBreakTime = Duration.between(breakEndTime, checkOutTime).toHours();
					
					long dailyWorkTime = beforeBreakTime + afterBreakTime;
					workingLog.setDailyWorkTime(dailyWorkTime);
					
					LocalDate selectedDate = LocalDate.parse(workingLog.getSelectedDate());
					if(selectedDate.isBefore(currentDate.plusDays(1))) {
						workTimeSum += workingLog.getDailyWorkTime();
					}
				} else {
					workingLog.setDailyWorkTime(0);
				}
			}
		}
		//그래프로 표시할 퍼센티지를 구한다.
		long workTimeSumPercent = (workTimeSum * 100) / 40;
		request.setAttribute("workTimeSumPercent", workTimeSumPercent);
		
		List<NoticeDTO> noticeList= mainService.selectNoticeList();
		List<WelfareListDTO> welfareList = mainService.selectWelfareList(memberNo);
		
		/* 이번주 초과근무 시간과 잔여시간 */
		// 초과근무 결재시간이 있는지 검색
		OvertimeLogDTO overtimeLogDTO = new OvertimeLogDTO();
		overtimeLogDTO.setMemberNo(memberNo);
		overtimeLogDTO.setWeekStartDate(weekStartDate);
		overtimeLogDTO.setWeekEndDate(weekEndDate);
		ScheduleService scheduleService = new ScheduleService();
		List<OvertimeLogDTO> overTimeLogList = scheduleService.selectOverTimeLog(overtimeLogDTO);
		
		//결재된 초과근무를 모두 더한다.
		int overtimeSum = 0;
		if(overTimeLogList != null) {
			for(OvertimeLogDTO overtimeLog : overTimeLogList) {
				LocalDate selectedDate = new java.sql.Date(overtimeLog.getOvertimeStartDay().getTime()).toLocalDate();
				if(selectedDate.isBefore(currentDate.plusDays(1))) {
					overtimeSum += overtimeLog.getOvertimeDuring();
				}
			}
		}
		//그래프로 표시할 퍼센티지를 구한다.
		int overtimePercent = (overtimeSum * 100) / 12;
		request.setAttribute("overtimePercent", overtimePercent);
		
		String path = "/WEB-INF/views/main/main.jsp";
		
		request.setAttribute("welfareList", welfareList);
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("workInfo", todayWorkInfo);
		request.setAttribute("commutingLogList", commutingLogList);
		request.setAttribute("workingLogList", workingLogList);
		request.setAttribute("workTimeSum", workTimeSum);
		request.setAttribute("overTimeLogList", overTimeLogList);
		request.setAttribute("overtimeSum", overtimeSum);
			
		request.getRequestDispatcher(path).forward(request, response);
	}
}
