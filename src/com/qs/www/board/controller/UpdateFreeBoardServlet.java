package com.qs.www.board.controller;
 
import java.io.IOException;  
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.qs.www.board.model.dto.FreeDTO;
import com.qs.www.board.model.service.FreeService;
import com.qs.www.member.model.dto.MemberInfoDTO;
 
@WebServlet("/board/free/update")
public class UpdateFreeBoardServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		FreeService freeService = new FreeService();
		FreeDTO free = freeService.selectFreeDetail(no);
		
		String path = "";
		
		System.out.println(free);
		System.out.println(path);
		System.out.println(no);
		if(free != null) {
			path = "/WEB-INF/views/board/updateFreeBoard.jsp";
			request.setAttribute("free", free);
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("message", "자유게시판 수정 조회하기 실패!");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int no = Integer.parseInt(request.getParameter("no"));
		String title = (String)request.getParameter("title");
		String body = (String)request.getParameter("body");
		int memberNo = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
		System.out.println("출력테스트");
		System.out.println("no : " + no);
		System.out.println(title);
		System.out.println(body);
		
		FreeDTO free = new FreeDTO();

		free.setNo(no);
		free.setTitle(title);
		free.setBody(body);
		free.setMember(memberNo);
		System.out.println(free);

		FreeService freeService = new FreeService();
		int result = freeService.updateFree(free);

		String path = "";
		if(result > 0) {
			path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "updateFree");
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "updateFree");
		}

		request.getRequestDispatcher(path).forward(request, response);
	}
}

