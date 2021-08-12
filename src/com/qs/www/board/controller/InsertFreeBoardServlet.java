package com.qs.www.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.board.model.dto.FreeDTO;
import com.qs.www.board.model.service.FreeService;
import com.qs.www.member.model.dto.MemberInfoDTO;

@WebServlet("/board/free/insert")
public class InsertFreeBoardServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = "/WEB-INF/views/board/freeBoard.jsp";
		
		request.getRequestDispatcher(path).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
//		int no = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
		String title = request.getParameter("title");
		int member = Integer.parseInt(request.getParameter("member"));
		String type = request.getParameter("type");
		int count = Integer.parseInt(request.getParameter("count"));
		String body = request.getParameter("body");
		String delete = request.getParameter("delete");
		java.sql.Date created = java.sql.Date.valueOf(request.getParameter("created"));
		java.sql.Date modified = java.sql.Date.valueOf(request.getParameter("modified"));
		
		
		FreeDTO newFree = new FreeDTO();
		newFree.setTitle(title);
		newFree.setBody(body);
		
		FreeService freeService = new FreeService();
		int result = freeService.insertFree(newFree);
		
		String path = "";
		if(result > 0) {
			path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "insertNotice");
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("message", "자유게시판 등록에 실패하셨습니다.");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}
}
