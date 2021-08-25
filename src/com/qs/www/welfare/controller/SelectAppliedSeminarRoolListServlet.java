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
import com.qs.www.welfare.model.dto.SeminarRoomReservDTO;
import com.qs.www.welfare.model.service.WelfareService;

/**
 * Servlet implementation class SelectAppliedSeminarRoolListServlet
 */
@WebServlet("/welfare/applied/list/seminarRoom/select")
public class SelectAppliedSeminarRoolListServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();

		int memberNo = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();						//사번
		
		/*
		 * 목록보기를 눌렀을 시 가장 처음에 보여지는 페이지는 1페이지이다. 파라미터로 전달되는 페이지가 있는 경우 currentPage는 파라미터로
		 * 전달받은 페이지 수 이다.
		 */
		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;

		if (currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}

		/* 페이지는 1보다 작을 수 없음*/
		if (pageNo <= 0) {																			
			pageNo = 1;	
		}

		/* 검색에 사용할것 */
		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");

		Map<String, String> searchMap = new HashMap<>();
		searchMap.put("searchCondition", searchCondition);
		searchMap.put("searchValue", searchValue);

		HashMap<String, Object> countMap = new HashMap<>();
		countMap.put("memberNo", memberNo);
		countMap.put("searchMap", searchMap);

		Pagenation pagenation = new Pagenation();

		/* 회의실이 몇개 있는지 갯수를 리턴한다*/
		int totalCount = new WelfareService().selectMySeminarRoomListCount(countMap);				
		
		/* 한페이지에서 최대 게시물수*/
		int limit = 5;																				
		/* 최대 버튼수 */
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
		
		/* 회의실 예약내역 */
		List<SeminarRoomReservDTO> seminarRoomList = new WelfareService().selectSeminarRoomByMemberNo(selectedInfoMap);		
		
		request.setAttribute("seminarRoomList", seminarRoomList);
		request.setAttribute("selectCriteria", selectCriteria);
		request.getRequestDispatcher("/WEB-INF/views/welfare/appliedSeminarRoomList.jsp").forward(request, response);
		
	}
}
