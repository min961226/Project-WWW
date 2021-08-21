package com.qs.www.schedule.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.schedule.model.dto.ReportDTO;
import com.qs.www.schedule.model.service.HolidayService;

@WebServlet("/schedule/holiday/select")
public class SelectHolidayScheduleServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("휴가 신청 현황"); 
		
		HolidayService holidayService = new HolidayService();
		
		//로그인 중인 사용자가 올린 결재(상신, 상신 별 항목> 가져오기
		HttpSession session = request.getSession();
		int memberNo = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
				
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
		
		//로그인 중인 사용자가 올린 결재중, documentNo가  6인 문서(휴가신청서)
		Map<String, String> searchMap = new HashMap<>();
		searchMap.put("searchCondition", searchCondition);
		searchMap.put("searchValue", searchValue);
		
		int documentNo = 6;
		HashMap<String, Object> countMap = new HashMap<>();
		countMap.put("memberNo", memberNo);
		countMap.put("documentNo", documentNo);
		countMap.put("searchMap", searchMap);
		
		Pagenation pagenation = new Pagenation();
		
		//totalCount 는 DB에 가서 조건에 해당하는 총 게시물 수를 세어와야 함. count(*) 중, where 삭제안된거.
		int totalCount = holidayService.selectAllHolidayReportCount(countMap);

		//limit는 한 페이지에서 보여지는 게시물 수
		int limit = 10;
				
		//buttonAmount는 한번에 보여줄 버튼 수
		int buttonAmount = 5;
		
		//검색하고싶으면 매개변수로 selectCriteria 써줄것
		SelectCriteria selectCriteria = null;
		
		if(searchCondition != null && !"".equals(searchCondition)) {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
		} else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		}
		System.out.println(selectCriteria);

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		//로그인한 사람의 사번과 문서번호를 넘겨준다.
		HashMap<String, Object> selectedInfoMap = new HashMap<>();
		selectedInfoMap.put("memberNo", memberNo);
		selectedInfoMap.put("documentNo", documentNo);
		selectedInfoMap.put("selectCriteria", selectCriteria);
		
		//조건 selectedInfoMap을 통해 검색한 reportList
		List<ReportDTO> holidayReportList = holidayService.selectMyholidayReport(selectedInfoMap);

		request.setAttribute("holidayReportList", holidayReportList);
		request.setAttribute("selectCriteria", selectCriteria);
		request.getRequestDispatcher("/WEB-INF/views/schedule/holiday.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
