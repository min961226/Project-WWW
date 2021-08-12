package com.qs.www.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.board.model.dto.FreeDTO;
import com.qs.www.board.model.service.FreeService;

@WebServlet("/board/free/update")
public class UpdateFreeBoardServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		FreeService freeService = new FreeService();
		FreeDTO free = freeService.selectFreeDetail(no);
		
		String path = "";
		if(free != null) {
			path = "/WEB-INF/views/board/updateFreeBoard.jsp";
			request.setAttribute("free", free);
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("message", "자유게시판 수정용 조회하기 실패!");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
