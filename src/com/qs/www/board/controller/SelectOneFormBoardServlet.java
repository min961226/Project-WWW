package com.qs.www.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.board.model.dto.FormDTO;
import com.qs.www.board.model.dto.FreeDTO;
import com.qs.www.board.model.service.FormService;
import com.qs.www.board.model.service.FreeService;

@WebServlet("/board/form/selectOne")
public class SelectOneFormBoardServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//게시판 상세보기
		int no = Integer.parseInt(request.getParameter("no"));
		
		FormService formService = new FormService();
		FormDTO formDetail = formService.selectFormDetail(no);
		
		System.out.println("formDetail : " + formDetail);
		
		String path = "";
		if(formDetail != null) {
			path = "/WEB-INF/views/board/detailFormBoard.jsp";
			request.setAttribute("form", formDetail);
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("message", "문서서식 게시판 상세보기 실패");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
		
	}
}
