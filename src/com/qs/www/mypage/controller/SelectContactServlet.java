package com.qs.www.mypage.controller;

import java.io.IOException; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.board.model.dto.FreeDTO;
import com.qs.www.board.model.service.FreeService;
import com.qs.www.common.paging.Pagenation;
import com.qs.www.common.paging.SelectCriteria;
import com.qs.www.mypage.model.dto.ContactDTO;
import com.qs.www.mypage.model.service.ContactService;

@WebServlet("/mypage/contact/select")
public class SelectContactServlet extends HttpServlet {

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		int totalCount = new ContactService().selectAllCount(searchMap);

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
		System.out.println(selectCriteria);
		//자유게시판 목록
		//검색하고싶으면 매개변수로 selectCriteria 써줄것
		List<ContactDTO> contactList = new ContactService().selectAllContactList(selectCriteria);
		
		for(ContactDTO contact : contactList) {
			System.out.println(contact);
		}
		
		String path = "";
		if(contactList != null) {
			path = "/WEB-INF/views/mypage/address.jsp";
			request.setAttribute("selectCriteria", selectCriteria);
			request.setAttribute("contactList", contactList);
		} else {
			path = "/WEB-INF/views/common/error-404.jsp";
			request.setAttribute("message", "연락처 조회 실패!");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}
}
