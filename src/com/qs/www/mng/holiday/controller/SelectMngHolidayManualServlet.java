package com.qs.www.mng.holiday.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.common.paging.Pagenation;
import com.qs.www.common.paging.SelectCriteria;
import com.qs.www.mng.holiday.model.dto.HolidayRuleDTO;
import com.qs.www.mng.holiday.model.dto.MemberHolidayInfoDTO;
import com.qs.www.mng.holiday.model.service.MngHolidayService;
import com.qs.www.schedule.model.dto.HolidayLogDTO;

@WebServlet("/mng/holiday/manual/select")
public class SelectMngHolidayManualServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("휴가일수 수동 발생");

		
		//////////////////////////////////////////////////////////////////////////////////////
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
		
		Map<String, String> searchMap = new HashMap<>();
		searchMap.put("searchCondition", searchCondition);
		searchMap.put("searchValue", searchValue);
		
		Pagenation pagenation = new Pagenation();
		
		
		//totalCount 는 DB에 가서 총 게시물 수를 세어와야 함 count(*) 중, where 삭제안된거.
		int totalCount = new MngHolidayService().selectMemberHolidayInfoCount(searchMap);

		//limit는 한 페이지에서 보여지는 게시물 수
		int limit = 10;
		
		//buttonAmount는 한번에 보여줄 버튼 수
		int buttonAmount = 5;
		
		SelectCriteria selectCriteria = null;
		
		if(searchCondition != null && !"".equals(searchCondition)) {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
		} else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		}
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		MngHolidayService mngHolidayService = new MngHolidayService();
		List<MemberHolidayInfoDTO> memberHolidayInfoIList = new MngHolidayService().selectMemberHolidayInfoList(selectCriteria);
		List<HolidayRuleDTO> holidayRule = mngHolidayService.selectHolidayRule();
		
		//현재날짜에서 년도만 추출
		Date now  = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String str = format.format(now);
		
		String[] arrayDate = str.split("-");   
		int currYear = Integer.parseInt(arrayDate[0]);

		
		for(MemberHolidayInfoDTO info : memberHolidayInfoIList) {
			//직원의 입사날짜에서 년도만 추출
			String enrStr = format.format(info.getEnrollDate());
			String[] enryDate = str.split("-");
			
			int enrYear = Integer.parseInt(arrayDate[0]);
			
			System.out.println("연차 : " + (currYear - enrYear + 1));
			
			if(currYear == enrYear) {
				info.setAutoDayNumber(15);
			} else {
				for(HolidayRuleDTO rule : holidayRule) {
					if(rule.getWorkPeriod() == (currYear - enrYear + 1)) {    //직원의 연차와 규칙에서의 연차가 일치한만큼 휴가 자동방생일 지정
						info.setAutoDayNumber(rule.getDayNumber());
					}
				}
			}
			
			//수동발생일
			
			 List<HolidayLogDTO> holidayLogList = mngHolidayService.selectHolidayLogList(info.getMemberNo());
			int passiveday = 0;
			for(HolidayLogDTO log : holidayLogList ) {
				passiveday += Integer.parseInt(log.getHolidayDuringDate());
			}
			
			info.setPassivedayNumber(passiveday);
		}
		System.out.println(memberHolidayInfoIList);
		request.setAttribute("MemberHolidayInfoIList", memberHolidayInfoIList);
		request.setAttribute("selectCriteria", selectCriteria);
		request.getRequestDispatcher("/WEB-INF/views/mngholiday/holidayManual.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
