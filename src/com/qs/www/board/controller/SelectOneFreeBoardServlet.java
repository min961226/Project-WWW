package com.qs.www.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.board.model.dto.FreeDTO;
import com.qs.www.board.model.service.FreeService;

@WebServlet("/board/free/selectOne")
public class SelectOneFreeBoardServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//게시판 상세보기
		int no = Integer.parseInt(request.getParameter("no"));
		
		FreeService freeService = new FreeService();
		FreeDTO freeDetail = freeService.selectFreeDetail(no);
		
		System.out.println("freeDetail : " + freeDetail);
		
		String path = "";
		if(freeDetail != null) {
			path = "/WEB-INF/views/board/formBoard.jsp";
			request.setAttribute("free", freeDetail);
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("message", "자유게시판 상세보기 실패");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
		
	}
}
