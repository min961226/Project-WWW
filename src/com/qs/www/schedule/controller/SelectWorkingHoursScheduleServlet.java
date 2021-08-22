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
import com.qs.www.schedule.model.dto.DailyCommuteDTO;
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

		/* 1. 이번 달 근태 통계 */
		/* 1-1. 이번달 근무일수가 몇일이고, 그 중 몇 일째인지 */
		//오늘이 속한 해, 달, 오늘날짜
		LocalDate currentDate = LocalDate.now(); //String todayDate는 위쪽에서 정의해둠
		String currentDateStr = currentDate + "";
		String[] currentDateArr = currentDateStr.split("-");
		request.setAttribute("thisYear", currentDateArr[0]);
		request.setAttribute("thisMonth", currentDateArr[1]);
		
		int todayYearInt = Integer.parseInt(currentDateArr[0]);
		int todayMonthInt = Integer.parseInt(currentDateArr[1]); 
		int todayDateInt = Integer.parseInt(currentDateArr[2]); 

		//해당 월의 시작일과 종료일
		Calendar date = new GregorianCalendar();
		int thisMonthFirstDate = date.getActualMinimum(Calendar.DATE);
		int thisMonthLastDate = date.getActualMaximum(Calendar.DATE);

		
		/* 1-2. 오늘까지 근무일수 */
		int forStartDate2 = 1;
		int thisMonthWorkDateNum2 = 0;
		for(int i = thisMonthFirstDate; i < todayDateInt + 1; i++) {
			
			String forStartDatestr = forStartDate2 + ""; 
			if(forStartDate2 < 10) {
				forStartDatestr = "0" + forStartDatestr;					
			}			
			
			String ForStartDate = currentDateArr[0] + "-" + currentDateArr[1] + "-" + forStartDatestr;
			
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

	


		/* 2. 근무시간 현황 */
		/* 2-1. 이름, 아이디, 오늘 근무제 정보는 위에서 해놨다. setAttribute만 하자 */	
		request.setAttribute("name", name);
		request.setAttribute("memberId", memberId);
		request.setAttribute("appWorkType", appWorkType);
		request.setAttribute("workCode", workCode);

		/* 2-2. 이번주 정규근무 시간과 잔여시간 */
		// 오늘 날짜는 위에서 선언&초기화 했음

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
		workInfo.setToday(currentDateStr);
		workInfo.setWeekStartDate(weekStartDate);
		workInfo.setWeekEndDate(weekEndDate);
		workInfo.setSelectedLocalDate(selectedLocalDate);

		MainService mainService = new MainService();

		//그 주의 출퇴근번호(SEQ), 연도-월(yyyyMM), 일(dd), 출근시간, 퇴근시간
		List<CommutingLogDTO> commutingLogWeeklyList = mainService.selectCommutingLog(workInfo);
		System.out.println("commutingLogList : " + commutingLogWeeklyList);

		//현재보다 작은 날 중 가장 가까운 변경이력의 근무제변경이력번호(SEQ), 변경후근무제유형, 변경후근무제유형코드, 변경일자
		List<WorkingLogDTO> workingLogList = mainService.selectWorkingLogList(workInfo);
		System.out.println("workingLogList : " + workingLogList);

		/* 2-3. 이번주 초과근무 시간과 잔여시간 */
		//정규근무시간 구하기


		//초과시간 검색
		OvertimeLogDTO overtimeLogDTO = new OvertimeLogDTO();
		overtimeLogDTO.setMemberNo(memberNo);
		overtimeLogDTO.setWeekStartDate(weekStartDate);
		overtimeLogDTO.setWeekEndDate(weekEndDate);
		List<OvertimeLogDTO> overTimeLogList = scheduleService.selectOverTimeLog(overtimeLogDTO);
		for(OvertimeLogDTO ot : overTimeLogList) {
			System.out.println(ot);
		}

		int overtimeSum = 0;
		if(overTimeLogList != null) {
			for(OvertimeLogDTO overtimeLog : overTimeLogList) {
				overtimeSum += overtimeLog.getOvertimeDuring();
			}
		}
		request.setAttribute("overtimeSum", overtimeSum);


		/* 2-4. 이번달 일수대로 출근시간, 퇴근시간,  */
		java.sql.Date thisMonthFirst = Date.valueOf(LocalDate.of(todayYearInt, todayMonthInt, thisMonthFirstDate));
		java.sql.Date thisMonthLast = Date.valueOf(LocalDate.of(todayYearInt, todayMonthInt, thisMonthLastDate));
		String thisMonthFirstString = thisMonthFirst + "";
		String thisMonthLastString = thisMonthLast + "";
		
		WorkInfoDTO workInfo3 = new WorkInfoDTO();
		workInfo3.setMemberNo(memberNo);	
		workInfo3.setWeekStartDate(thisMonthFirstString); //주의 시작/끝이 아니라 월의 시작/끝을 넣어서 출력
		workInfo3.setWeekEndDate(thisMonthLastString);
		List<CommutingLogDTO> commutingLogMontlyList = mainService.selectCommutingLog(workInfo3); 
		for(CommutingLogDTO dto : commutingLogMontlyList) {
			System.out.println(dto);
		}
		
		
		//view에 넘길 용도로 만든 한 사람의 List(재구축용)
		List<DailyCommuteDTO> dailyCommuteList = new ArrayList<>(); 
		
		int forStartDate = 1;			//1일부터 시작
		int thisMonthWorkDateNum = 0; 	//이번 달 총 근무일수
		for(int i = 0; i < thisMonthLastDate; i++) {
			
			String forStartDatestr = forStartDate + "";					//일단위를 String으로 바꾼다. 만약 10 이하라면 0을 붙여준다.
			if(forStartDate < 10) {
				forStartDatestr = "0" + forStartDatestr;					
			}			

			String ForStartDate = currentDateArr[0] + "-" + currentDateArr[1] + "-" + forStartDatestr; //연, 월, 일을 합쳐서 문자열로 표시한다.

			//view에 넘길 용도로 만든 DTO
			DailyCommuteDTO dailyCommuteDTO = new DailyCommuteDTO();
			dailyCommuteDTO.setDateNum(forStartDatestr); 					// 1.날짜 셋팅
			dailyCommuteList.add(dailyCommuteDTO);							//일단 날짜값만 넣어둔 DTO를 List에 add. 안 하면 NullPointerException
			
			//가져온 commuteLog DTO 중, 날짜(forStartDatestr)가 일치하는 것의 DTO를 가지고 온다. 
			CommutingLogDTO getDTO = new CommutingLogDTO();
			for(int j = 0; j < commutingLogMontlyList.size(); j++) {
				if(commutingLogMontlyList.get(j).getDay().equals(forStartDatestr)) {
					getDTO = commutingLogMontlyList.get(j);
				}
			}
			
			LocalDate fordate = LocalDate.parse(ForStartDate); 
			DayOfWeek dayOfWeek = fordate.getDayOfWeek();
			int w = dayOfWeek.getValue(); 								//from 1 (Monday) to 7 (Sunday)
			
			//토, 일, (6, 7)이 아니면 workdate에 1일씩 늘려준다. 
			switch(w) {
			case 1 : dailyCommuteDTO.setDayOfWeek("월");							// 2.요일셋팅
					dailyCommuteDTO.setCheckInTime(getDTO.getInTime());			// 3.출근시간셋팅
					dailyCommuteDTO.setCheckOutTime(getDTO.getOutTime()); 		// 4.퇴근시간셋팅
					dailyCommuteDTO.setLateYn(getDTO.getLateYn());				// 5.지각여부셋팅
					dailyCommuteDTO.setLeaveEarlyYn(getDTO.getLeaveEarlyYn());	// 6.조퇴여부셋팅
					thisMonthWorkDateNum++; 									// 이번 달 총 근무일수에 1을 더해준다
					break;
			case 2 : dailyCommuteDTO.setDayOfWeek("화");
					dailyCommuteDTO.setCheckInTime(getDTO.getInTime());			
					dailyCommuteDTO.setCheckOutTime(getDTO.getOutTime()); 		
					dailyCommuteDTO.setLateYn(getDTO.getLateYn());				
					dailyCommuteDTO.setLeaveEarlyYn(getDTO.getLeaveEarlyYn());	
					thisMonthWorkDateNum++; 
					break;
			case 3 : dailyCommuteDTO.setDayOfWeek("수");
					dailyCommuteDTO.setCheckInTime(getDTO.getInTime());			
					dailyCommuteDTO.setCheckOutTime(getDTO.getOutTime()); 		
					dailyCommuteDTO.setLateYn(getDTO.getLateYn());				
					dailyCommuteDTO.setLeaveEarlyYn(getDTO.getLeaveEarlyYn());	
					thisMonthWorkDateNum++; 
					break;
			case 4 : dailyCommuteList.get(i).setDayOfWeek("목");
					dailyCommuteDTO.setCheckInTime(getDTO.getInTime());			
					dailyCommuteDTO.setCheckOutTime(getDTO.getOutTime()); 		
					dailyCommuteDTO.setLateYn(getDTO.getLateYn());				
					dailyCommuteDTO.setLeaveEarlyYn(getDTO.getLeaveEarlyYn());	
					thisMonthWorkDateNum++; 
					break;
			case 5 : dailyCommuteList.get(i).setDayOfWeek("금");
					dailyCommuteDTO.setCheckInTime(getDTO.getInTime());			
					dailyCommuteDTO.setCheckOutTime(getDTO.getOutTime()); 		
					dailyCommuteDTO.setLateYn(getDTO.getLateYn());				
					dailyCommuteDTO.setLeaveEarlyYn(getDTO.getLeaveEarlyYn());	
					thisMonthWorkDateNum++; 
					break;
			case 6 : dailyCommuteList.get(i).setDayOfWeek("토"); 
					dailyCommuteDTO.setCheckInTime(getDTO.getInTime());			
					dailyCommuteDTO.setCheckOutTime(getDTO.getOutTime()); 		
					dailyCommuteDTO.setLateYn(getDTO.getLateYn());				
					dailyCommuteDTO.setLeaveEarlyYn(getDTO.getLeaveEarlyYn());	
					break;
			case 7 : dailyCommuteList.get(i).setDayOfWeek("일"); 
					break;
			}
			
			forStartDate++;		//for문에서 다음 날짜를 돌리기위해, 1 증가시킨다
		}		
		System.out.println(todayMonthInt + "월 의 총 근무일 수 : " + thisMonthWorkDateNum + "/ 오늘까지 근무일수 : " + thisMonthWorkDateNum2);
		
		request.setAttribute("thisMonthWorkDateNum", thisMonthWorkDateNum);
		request.setAttribute("thisMonthWorkDateNum2", thisMonthWorkDateNum2);
		request.setAttribute("dailyCommuteList", dailyCommuteList);
		
		
		/* 1-2. 이번달 지각횟수 */
		int thisMonthlateNum = 0;
		for(CommutingLogDTO dto : commutingLogMontlyList) {
			if(dto.getLateYn() != null && dto.getLateYn().equals("Y")) {
				thisMonthlateNum++;
			}
		}
		System.out.println("이번달 지각횟수 : " + thisMonthlateNum);
		request.setAttribute("thisMonthlateNum", thisMonthlateNum);
		
		/* 1-3. 이번달 조퇴횟수 */
		int thisMonthLeaveEarlyNum = 0;
		for(CommutingLogDTO dto : commutingLogMontlyList) {
			if(dto.getLeaveEarlyYn() != null && dto.getLeaveEarlyYn().equals("Y")) {
				thisMonthLeaveEarlyNum++;
			}
		}
		System.out.println("이번달 조퇴횟수 : " + thisMonthLeaveEarlyNum);
		request.setAttribute("thisMonthLeaveEarlyNum", thisMonthLeaveEarlyNum);
	
		/* 1-4. 이번달 출근 미체크 횟수 */
		int noCheckInTimeNum = 0;
		for(CommutingLogDTO dto : commutingLogMontlyList) {
			if(dto.getInTime() == null) {
				noCheckInTimeNum++;
			}
		}
		System.out.println("이번 달 출근미체크 : " + noCheckInTimeNum);
		request.setAttribute("noCheckInTimeNum", noCheckInTimeNum);
		
		/* 1-4. 이번달 퇴근 미체크 횟수 */
		int noCheckOutTimeNum = 0;
		for(CommutingLogDTO dto : commutingLogMontlyList) {
			if(dto.getInTime() == null) {
				noCheckOutTimeNum++;
			}
		}
		System.out.println("이번 달 퇴근미체크 : " + noCheckOutTimeNum);
		request.setAttribute("noCheckOutTimeNum", noCheckOutTimeNum);
		
		/* 1-5. 오늘 퇴근체크를 했는지 */
		int checkedOutToday = 0;
		for(CommutingLogDTO dto : commutingLogMontlyList) {
			if(dto.getDay().equals(currentDateArr[2])) {
				if(dto.getOutTime() != null) {
					checkedOutToday = 1;
				}
			}
		}
		System.out.println("오늘 퇴근체크를 안 했으면 0 : " + checkedOutToday);
		request.setAttribute("checkedOutToday", checkedOutToday);
		
		
		String path = "/WEB-INF/views/schedule/checkWorkingHours.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
