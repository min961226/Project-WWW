package com.qs.www.welfare.controller;

import java.io.IOException;
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
import com.qs.www.schedule.model.service.ScheduleService;
import com.qs.www.welfare.model.dto.SeminarRoomDTO;
import com.qs.www.welfare.model.dto.SeminarRoomReservDTO;
import com.qs.www.welfare.model.service.WelfareService;

@WebServlet("/welfare/applied/list/select")
public class SelectAppliedWelfareListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		int memberNo = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo(); 							// 세션에 저장된 멤버 번호를 가져온다
		/*
		 * 목록보기를 눌렀을 시 가장 처음에 보여지는 페이지는 1페이지이다. 파라미터로 전달되는 페이지가 있는 경우 currentPage는 파라미터로
		 * 전달받은 페이지 수 이다.
		 */
		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;

		if (currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}

		
		if (pageNo <= 0) {																							//0보다 작은 숫자값을 입력해도 1페이지를 보여준다
			pageNo = 1;	
		}

		/* 검색에 사용할것 */
		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");

		Map<String, String> searchMap = new HashMap<>();
		searchMap.put("searchCondition", searchCondition);
		searchMap.put("searchValue", searchValue);
		/* 	검색값들에 해당하는 사번을 넣어준다 */
		HashMap<String, Object> countMap = new HashMap<>();									
		countMap.put("memberNo", memberNo);
		countMap.put("searchMap", searchMap);

		Pagenation pagenation = new Pagenation();

		int totalCount = new WelfareService().selectMyWelfareListCount(countMap); 				
		/* limit는 한 페이지에서 보여지는 게시물 수 */
		int limit = 10;																			
		/* buttonAmount는 한번에 보여줄 버튼 수 */
		int buttonAmount = 5;																	

		SelectCriteria selectCriteria = null;

		if (searchCondition != null && !"".equals(searchCondition)) {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition,searchValue);
		} else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		}

		HashMap<String, Object> selectedInfoMap = new HashMap<>();
		selectedInfoMap.put("memberNo", memberNo);
		selectedInfoMap.put("selectCriteria", selectCriteria);
		
		List<ReportDTO> appliedWelfareList = new WelfareService().selectAppliedWelfareList(selectedInfoMap); 		// 멤버 번호에 해당하는 신청
																													// 복지 목록을 가져온다.
		request.setAttribute("appliedWelfareList", appliedWelfareList);
		request.setAttribute("selectCriteria", selectCriteria);
		request.getRequestDispatcher("/WEB-INF/views/welfare/appliedWelfareList.jsp").forward(request, response);
	}
}
