package com.qs.www.schedule.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.main.model.dto.WorkInfoDTO;
import com.qs.www.main.model.dto.WorkingLogDTO;
import com.qs.www.main.model.service.MainService;
import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.mypage.model.dto.CommutingLogDTO;
import com.qs.www.schedule.model.dto.MonthlyWorkLogDTO;
import com.qs.www.schedule.model.dto.OvertimeLogDTO;
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
		
		
		//번거로우므로 날짜의 요일명도 미리 만들어둔다
		List<String> dayofWeekList = new ArrayList<>();
		
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
			case 1 : dayofWeekList.add("월"); thisMonthWorkDateNum++; break;
			case 2 : dayofWeekList.add("화"); thisMonthWorkDateNum++; break;
			case 3 : dayofWeekList.add("수"); thisMonthWorkDateNum++; break;
			case 4 : dayofWeekList.add("목"); thisMonthWorkDateNum++; break;
			case 5 : dayofWeekList.add("금"); thisMonthWorkDateNum++; break;
			case 6 : dayofWeekList.add("토"); break;
			case 7 : dayofWeekList.add("일"); break;
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
		
		//번거로우므로, 날짜들을 리스트로 만들어서 보낸다.
		List<String> dayList = new ArrayList<>();
		for(int inum = 1; inum < thisMonthLastDate + 1; inum++) {
			String i = "";
			
			if(inum < 10) {
			i = "0" + inum;
			} else {
			i = "" + inum;
			}
			
			dayList.add(i);
		}
		
		request.setAttribute("dayofWeekList", dayofWeekList);
		request.setAttribute("dayList", dayList);
		request.setAttribute("thisMonthLastDate", thisMonthLastDate);
		request.setAttribute("thisMonthWorkDateNum", thisMonthWorkDateNum);
		request.setAttribute("thisMonthWorkDateNum2", thisMonthWorkDateNum2);
		
		
		/* 1-2. 이번달 지각횟수 */
		java.sql.Date thisMonthFirst = Date.valueOf(LocalDate.of(todayYearInt, todayMonthInt, thisMonthFirstDate));
		java.sql.Date thisMonthLast = Date.valueOf(LocalDate.of(todayYearInt, todayMonthInt, thisMonthLastDate));
		String thisMonthFirstString = thisMonthFirst + "";
		String thisMonthLastString = thisMonthLast + "";
		
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
		// 오늘 날짜
		LocalDate currentDate = LocalDate.now(); //String todayDate는 위쪽에서 정의해둠
		String currentDateStr = currentDate + "";
		String[] currentDateArr = currentDateStr.split("-");
		request.setAttribute("thisYear", currentDateArr[0]);
		request.setAttribute("thisMonth", currentDateArr[1]);
		
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
		
		//그 주의 출퇴근번호(SEQ), 연도-월(yyyyMM), 일(dd), 출근시간, 퇴근시간
		List<CommutingLogDTO> commutingLogWeeklyList = mainService.selectCommutingLog(workInfo);
		System.out.println("commutingLogList : " + commutingLogWeeklyList);
		
		//현재보다 작은 날 중 가장 가까운 변경이력의 근무제변경이력번호(SEQ), 변경후근무제유형, 변경후근무제유형코드, 변경일자
		List<WorkingLogDTO> workingLogList = mainService.selectWorkingLog(workInfo);
		System.out.println("workingLogList : " + workingLogList);
		
		/* 2-3. 이번주 초과근무 시간과 잔여시간 */
		//정규근무시간 구하기
		
		
		//초과시간 검색
		OvertimeLogDTO overtimeLogDTO = new OvertimeLogDTO();
		overtimeLogDTO.setMemberNo(memberNo);
		overtimeLogDTO.setWeekStartDate(weekStartDate);
		overtimeLogDTO.setWeekEndDate(weekEndDate);
		List<OvertimeLogDTO> overTimeLogList = scheduleService.selectOverTimeLog(overtimeLogDTO);
		System.out.println("overTimeLogList : " + overTimeLogList);
		
		int overtimeSum = 0;
		if(overTimeLogList != null) {
			for(OvertimeLogDTO overtimeLog : overTimeLogList) {
				overtimeSum += overtimeLog.getOvertimeDuring();
			}
		}
		request.setAttribute("overtimeSum", overtimeSum);
		
		/* 2-4. 이번달 일수대로 출근시간, 퇴근시간,  */
		workInfo.setWeekStartDate(thisMonthFirstString); //주의 시작/끝이 아니라 월의 시작/끝을 넣어서 출력
		workInfo.setWeekEndDate(thisMonthLastString);
		List<CommutingLogDTO> commutingLogMontlyList = mainService.selectCommutingLog(workInfo);
				
		System.out.println("commutingLogMontlyList : " + commutingLogMontlyList);
		request.setAttribute("commutingLogMontlyList", commutingLogMontlyList);
		
				
		
		String path = "/WEB-INF/views/schedule/checkWorkingHours.jsp";

		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
