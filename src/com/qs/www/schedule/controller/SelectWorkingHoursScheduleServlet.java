package com.qs.www.schedule.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

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
import com.qs.www.schedule.model.dto.MonthlyWorkLogDTO;
import com.qs.www.schedule.model.service.ScheduleService;

@WebServlet("/schedule/workingHours/select")
public class SelectWorkingHoursScheduleServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("근무시간 조회");
		
		ScheduleService scheduleService = new ScheduleService();
		
		//기본정보
		HttpSession session = request.getSession();	
		MemberInfoDTO memberInfo = (MemberInfoDTO) session.getAttribute("memberInfo");
		String name = memberInfo.getName();
		String memberId = memberInfo.getMemberId();
		int memberNo = memberInfo.getMemberNo();
		String appWorkType = memberInfo.getAppWorkType();
		int workCode = memberInfo.getWorkCode();
		
		// 날짜 출력 양식
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 오늘 날짜(todayDate)
		Calendar date = new GregorianCalendar();
		String todayDate = sdf.format(date.getTime()); //밀리세컨단위 getTime을 sdf으로 변환
		System.out.println("String 형식 todayDate : " + todayDate);
		
		// 이번주 월요일 날짜(weekStartDate)
		date.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		Calendar weekStartCalDate = date;
		String weekStartDate = sdf.format(weekStartCalDate.getTime());
		System.out.println("monday를 sdf으로 변환한 날짜 : " + weekStartDate);
		
		// 다음주 일요일 날짜(weekEndDate)
		date.add(Calendar.DATE, 6);
		Calendar weekEndCalDate = date;
		String weekEndDate = sdf.format(weekEndCalDate.getTime());
		System.out.println("sunday를 sdf으로 변환한 날짜 : " + weekEndDate);
		
		// 요일별 날짜를 담을 변수
		date.add(Calendar.DATE, -6);
		Calendar selectedCalDate = date;
		String selectedDate = sdf.format(selectedCalDate.getTime());
		System.out.println("monday부터 시작할 것이므로, date(지금은 일요일로 설정되어있음)에서 -6 : " + selectedCalDate);
		
				
		/* 1. 이번 달 근태 통계 */
		/* 1-1. 이번달 근무일수가 몇일이고, 그 중 몇 일째인지 */
		//오늘이 속한 해, 달, 오늘날짜
		String[] arrayToday = todayDate.split("-");
		int todayYearInt = Integer.parseInt(arrayToday[0]);
		int todayMonthInt = Integer.parseInt(arrayToday[1]); 
		int todayDateInt = Integer.parseInt(arrayToday[2]); 
				
		//해당 월의 시작일과 종료일
		int thisMonthFirstDate = date.getActualMinimum(Calendar.DATE);
		int thisMonthLastDate = date.getActualMaximum(Calendar.DATE);
		
		
		int forStartDate = 1;
		int thisMonthWorkDateNum = 0;
		
		for(int i = thisMonthFirstDate; i < thisMonthLastDate + 1; i++) {
			
			String forStartDatestr = forStartDate + "";					//일단위를 String으로 바꾼다. 만약 10 이하라면 0을 붙여준다.
			if(forStartDate < 10) {
				forStartDatestr = "0" + forStartDatestr;					
			}			
			
			String ForStartDate = arrayToday[0] + "-" + arrayToday[1] + "-" + forStartDatestr; //연, 월, 일을 합쳐서 문자열로 표시한다.
			
			LocalDate fordate = LocalDate.parse(ForStartDate); 
			DayOfWeek dayOfWeek = fordate.getDayOfWeek();
			int w = dayOfWeek.getValue(); 								//from 1 (Monday) to 7 (Sunday)
			
			//if문으로 정리하는게 더 간편할듯
			//토, 일, (6, 7)이 아니면 workdate에 1일씩 늘려준다. 
			switch(w) {
			case 1 : thisMonthWorkDateNum++; break;
			case 2 : thisMonthWorkDateNum++; break;
			case 3 : thisMonthWorkDateNum++; break;
			case 4 : thisMonthWorkDateNum++; break;
			case 5 : thisMonthWorkDateNum++; break;
			case 6 : break;
			case 7 : break;
			}
			
			forStartDate++;												//for문을 다시 돌리기위해 날짜를 1 증가시킨다
		}		
		System.out.println(todayMonthInt + "월 의 근무일 수 : " + thisMonthWorkDateNum);

		
		/* 오늘까지 근무일수 */
		int forStartDate2 = 1;
		int thisMonthWorkDateNum2 = 0;
		for(int i = thisMonthFirstDate; i < todayDateInt + 1; i++) {
			
			String forStartDatestr = forStartDate2 + ""; 
			if(forStartDate2 < 10) {
				forStartDatestr = "0" + forStartDatestr;					
			}			
			
			String ForStartDate = arrayToday[0] + "-" + arrayToday[1] + "-" + forStartDatestr;
			
			LocalDate fordate = LocalDate.parse(ForStartDate); 
			DayOfWeek dayOfWeek = fordate.getDayOfWeek();
			int w = dayOfWeek.getValue(); //from 1 (Monday) to 7 (Sunday)
			
			//if문으로 정리하는게 더 간편할듯
			switch(w) {
			case 1 : thisMonthWorkDateNum2++; break;
			case 2 : thisMonthWorkDateNum2++; break;
			case 3 : thisMonthWorkDateNum2++; break;
			case 4 : thisMonthWorkDateNum2++; break;
			case 5 : thisMonthWorkDateNum2++; break;
			case 6 : break;
			case 7 : break;
			}
			
			forStartDate2++;
		}		
		System.out.println(todayMonthInt + "월 의 오늘까지 근무일 수 : " + thisMonthWorkDateNum2);
		
		request.setAttribute("thisMonthWorkDateNum", thisMonthWorkDateNum);
		request.setAttribute("thisMonthWorkDateNum2", thisMonthWorkDateNum2);
		
		
		/* 1-2. 이번달 지각횟수 */
		java.sql.Date thisMonthFirst = Date.valueOf(LocalDate.of(todayYearInt, todayMonthInt, thisMonthFirstDate));
		java.sql.Date thisMonthLast = Date.valueOf(LocalDate.of(todayYearInt, todayMonthInt, thisMonthLastDate));
		System.out.println(thisMonthFirst);
		System.out.println(thisMonthLast);
		
		MonthlyWorkLogDTO monthlyWorkLogDTO = new MonthlyWorkLogDTO();
		monthlyWorkLogDTO.setMemberNo(memberNo);
		monthlyWorkLogDTO.setMonthStartDate(thisMonthFirst);
		monthlyWorkLogDTO.setMonthEndDate(thisMonthLast);
		
		int thisMonthlateNum = scheduleService.countLateTimeNum(monthlyWorkLogDTO);
		System.out.println("이번달 지각횟수 : " + thisMonthlateNum);
		request.setAttribute("thisMonthlateNum", thisMonthlateNum);
		
		
		/* 1-3. 이번달 출/퇴근 미체크 횟수가 몇번인지 */
		String monthAndYear = arrayToday[0] + "-" + arrayToday[1];
		monthlyWorkLogDTO.setMonthAndYear(monthAndYear);
		int noCheckInTimeNum = scheduleService.countNoCheckInTimeNum(monthlyWorkLogDTO);
		System.out.println("이번달 출근미체크 횟수 : " + noCheckInTimeNum);
		request.setAttribute("noCheckInTimeNum", noCheckInTimeNum);
		
		int noCheckOutTimeNum = scheduleService.countNoCheckOutTimeNum(monthlyWorkLogDTO);
		System.out.println("이번달 퇴근미체크 횟수 : " + noCheckOutTimeNum);
		request.setAttribute("noCheckOutTimeNum", noCheckOutTimeNum);
		
		/* 1-4. 오늘 퇴근체크를 했는지 */
		String checkedOutToday = "";
		monthlyWorkLogDTO.setSelectedDate(todayDate);
		int isCheckedOut = scheduleService.checkedOutToday(monthlyWorkLogDTO);
		if(isCheckedOut >= 1) {
			checkedOutToday = "퇴근체크 완료";
		} else if(isCheckedOut == 0) {
			checkedOutToday = "퇴근체크 대상";
		}
		System.out.println("퇴근체크 여부 : " + checkedOutToday);
		request.setAttribute("checkedOutToday", checkedOutToday);
		
		
		/* 2. 근무시간 현황 */
		/* 2-1. 이름, 아이디, 오늘 근무제 정보는 위에서 해놨다. setAttribute만 하자 */	
		request.setAttribute("name", name);
		request.setAttribute("memberId", memberId);
		request.setAttribute("appWorkType", appWorkType);
		request.setAttribute("workCode", workCode);
		
		/* 2-2. 이번주 정규근무 시간과 잔여시간 */
				
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
				
		
		/* 2-3. 이번주 초과근무 시간과 잔여시간 */
		/* 2-4. 이번달 일수대로 출근시간, 퇴근시간,  */
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		String path = "/WEB-INF/views/schedule/checkWorkingHours.jsp";

		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
