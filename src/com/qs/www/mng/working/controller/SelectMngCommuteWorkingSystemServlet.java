package com.qs.www.mng.working.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.common.paging.Pagenation;
import com.qs.www.common.paging.SelectCriteria;
import com.qs.www.main.model.dto.WorkInfoDTO;
import com.qs.www.main.model.service.MainService;
import com.qs.www.mng.working.model.service.MngCommuteService;
import com.qs.www.mypage.model.dto.CommutingLogDTO;
import com.qs.www.schedule.model.dto.DailyCommuteDTO;
import com.qs.www.schedule.model.dto.MemberCommuteLogDTO;

@WebServlet("/mng/workingSystem/commute/select")
public class SelectMngCommuteWorkingSystemServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("직원 출퇴근 기록");

		//모든 직원의 출퇴근기록을 받아와야 함
		MngCommuteService mngCommuteService = new MngCommuteService();

		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		/* 목록보기를 눌렀을 시 가장 처음에 보여지는 페이지는 1페이지이다.
		 * 파라미터로 전달되는 페이지가 있는 경우 currentPage는 파라미터로 전달받은 페이지 수 이다.
		 * */
		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;

		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}

		/* 0보다 작은 숫자값을 입력해도 1페이지를 보여준다 */
		if(pageNo <= 0) {
			pageNo = 1;
		}

		/* 검색에 사용할것*/
		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");
				
		//검색의 내용(name, deptCode, jobCode)
		Map<String, String> searchMap = new HashMap<>();
		searchMap.put("searchCondition", searchCondition);
		searchMap.put("searchValue", searchValue);
		
		//퇴직하지 않은 모든 직원의 수를 세와야 한다.(조건이 있다면 조건을 따라서)
		HashMap<String, Object> countMap = new HashMap<>();
		countMap.put("searchMap", searchMap);
		
		Pagenation pagenation = new Pagenation();
		
		//totalCount 는 DB에 가서 조건에 해당하는 총 게시물 수를 세어와야 함. count(*) 중, where 삭제안된거.
		//연월(yearMonth)로 검색한다면, 모든 직원을 totalCount로 세게 될 것이다.
		int totalCount = mngCommuteService.selectAllMemberCount(countMap);
		
		//limit는 한 페이지에서 보여지는 게시물 수
		int limit = 5;
						
		//buttonAmount는 한번에 보여줄 버튼 수
		int buttonAmount = 5;
				
		//검색하고싶으면 매개변수로 selectCriteria 써줄것
		SelectCriteria selectCriteria = null;
		
		if(searchCondition != null && !"".equals(searchCondition)) {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
		} else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		}

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		/* 1. 이번 달 근태 통계 */
		//오늘이 속한 해, 달, 오늘날짜
		LocalDate currentDate = LocalDate.now(); 
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
				/* 이번달 일수대로 출근시간, 퇴근시간,  */
		java.sql.Date thisMonthFirst = Date.valueOf(LocalDate.of(todayYearInt, todayMonthInt, thisMonthFirstDate));
		java.sql.Date thisMonthLast = Date.valueOf(LocalDate.of(todayYearInt, todayMonthInt, thisMonthLastDate));
		String thisMonthFirstString = thisMonthFirst + "";
		String thisMonthLastString = thisMonthLast + "";
		
		//검색조건으로 연,월을 선택한 경우, 넣은 searchValue로 검색되게 하는 기능
		if(searchCondition != null) {
			if(searchCondition.equals("yearMonth")) {
				String getYear = searchValue.substring(0, 4);
				String getMonth = searchValue.substring(4, 6);
				request.setAttribute("thisYear", getYear);
				request.setAttribute("thisMonth", getMonth);
				
				String getYearMonthDateFirst = getYear + "-" + getMonth + "-" + "01";
				
				//YearMonth 타입으로 변환
				YearMonth targetYearMonth = YearMonth.from(LocalDate.parse(getYearMonthDateFirst, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				//해당월의 마지막 날
				LocalDate getYearMonthDateLast = targetYearMonth.atEndOfMonth();
				
				thisMonthFirst = Date.valueOf(getYearMonthDateFirst);
				thisMonthLast = Date.valueOf(getYearMonthDateLast);
				thisMonthFirstString = thisMonthFirst + "";
				thisMonthLastString = thisMonthLast + "";
			}
			
		}
		
		//우선 페이징으로 보여줄 member들 부터 뽑아와야 한다
		List<Integer> memberNo = new ArrayList<>();
		HashMap<String, Object> selectedInfoMap = new HashMap<>();
		selectedInfoMap.put("selectCriteria", selectCriteria);
		
		//조건 selectedInfoMap을 통해 검색한 member
		//사번, 이름, 부서명, 직급을 가져온다. 
		//List<DailyCommuteDTO> dailyCommuteList 필드는 NULL인 DTO를 받아오게 된다.
		List<MemberCommuteLogDTO> memberList = mngCommuteService.selectCriteriaMemberList(selectedInfoMap);
		
		
		//조건과 달을 입력하여 출근시간, 퇴근시간들을 가져와야 함
		
		for(int j = 0; j < memberList.size(); j++) {
			
			//한 사람의 1달치 출퇴근기록을 가져온다
			WorkInfoDTO workInfo = new WorkInfoDTO();
			workInfo.setMemberNo(memberList.get(j).getMemberNo());		//검색할 사번
			workInfo.setWeekStartDate(thisMonthFirstString); 			//주의 시작/끝이 아니라 월의 시작/끝을 넣어서 출력
			workInfo.setWeekEndDate(thisMonthLastString);
			//출퇴근 번호, 연도-월(yyyyMM), 일(dd), 출근시간, 퇴근시간, 지각여부, 퇴근여부 (사번은 없음)
			List<CommutingLogDTO> commutingLogMontlyList = new MainService().selectCommutingLog(workInfo); 
			for(CommutingLogDTO dto : commutingLogMontlyList) {
				System.out.println(dto);
			}
			
			//view에 넘길 용도로 만든 한 사람의 List(재구축용)
			List<DailyCommuteDTO> dailyCommuteList = new ArrayList<>(); 
			
			int forStartDate = 1;			//1일부터 시작
			for(int i = 0; i < thisMonthLastDate; i++) {
				
				String forStartDatestr = forStartDate + "";					//일단위를 String으로 바꾼다. 만약 10 이하라면 0을 붙여준다.
				if(forStartDate < 10) {
					forStartDatestr = "0" + forStartDatestr;					
				}			

				String ForStartDate = currentDateArr[0] + "-" + currentDateArr[1] + "-" + forStartDatestr; //연, 월, 일을 합쳐서 문자열로 표시한다.

				//view에 넘길 용도로 만든 DTO
				DailyCommuteDTO dailyCommuteDTO = new DailyCommuteDTO();
				dailyCommuteDTO.setDateNum(forStartDatestr); 					// 1.날짜 셋팅
				
				//가져온 commuteLog DTO 중, 날짜(forStartDatestr)가 일치하는 것의 DTO를 가지고 온다. 
				CommutingLogDTO getDTO = new CommutingLogDTO();
				for(int h = 0; h < commutingLogMontlyList.size(); h++) {
					if(commutingLogMontlyList.get(h).getDay().equals(forStartDatestr)) {
						getDTO = commutingLogMontlyList.get(h);
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
						break;
				case 2 : dailyCommuteDTO.setDayOfWeek("화");
						dailyCommuteDTO.setCheckInTime(getDTO.getInTime());			
						dailyCommuteDTO.setCheckOutTime(getDTO.getOutTime()); 		
						dailyCommuteDTO.setLateYn(getDTO.getLateYn());				
						dailyCommuteDTO.setLeaveEarlyYn(getDTO.getLeaveEarlyYn());	
						break;
				case 3 : dailyCommuteDTO.setDayOfWeek("수");
						dailyCommuteDTO.setCheckInTime(getDTO.getInTime());			
						dailyCommuteDTO.setCheckOutTime(getDTO.getOutTime()); 		
						dailyCommuteDTO.setLateYn(getDTO.getLateYn());				
						dailyCommuteDTO.setLeaveEarlyYn(getDTO.getLeaveEarlyYn());	
						break;
				case 4 : dailyCommuteDTO.setDayOfWeek("목");
						dailyCommuteDTO.setCheckInTime(getDTO.getInTime());			
						dailyCommuteDTO.setCheckOutTime(getDTO.getOutTime()); 		
						dailyCommuteDTO.setLateYn(getDTO.getLateYn());				
						dailyCommuteDTO.setLeaveEarlyYn(getDTO.getLeaveEarlyYn());	
						break;
				case 5 : dailyCommuteDTO.setDayOfWeek("금");
						dailyCommuteDTO.setCheckInTime(getDTO.getInTime());			
						dailyCommuteDTO.setCheckOutTime(getDTO.getOutTime()); 		
						dailyCommuteDTO.setLateYn(getDTO.getLateYn());				
						dailyCommuteDTO.setLeaveEarlyYn(getDTO.getLeaveEarlyYn());	
						break;
				case 6 : dailyCommuteDTO.setDayOfWeek("토"); 
						break;
				case 7 : dailyCommuteDTO.setDayOfWeek("일"); 
						break;
				}
				
				forStartDate++;		//for문에서 다음 날짜를 돌리기위해, 1 증가시킨다
				
				dailyCommuteList.add(dailyCommuteDTO);				//만들어진 한줄의 DTO를 List에 add
			}
			
			//확인용 1인 출퇴근기록
			for(DailyCommuteDTO commute : dailyCommuteList) {
				System.out.println("한 사람의 commute 기록들");
				System.out.println(commute);
			}
			
			memberList.get(j).setDailyCommuteList(dailyCommuteList);
		}
		
		request.setAttribute("memberList", memberList);
		request.setAttribute("selectCriteria", selectCriteria);
		
		String path = "/WEB-INF/views/mngworkingsystem/commuteList.jsp";
		request.getRequestDispatcher(path).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
