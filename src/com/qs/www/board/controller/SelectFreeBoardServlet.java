package com.qs.www.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.board.model.dto.FreeBoardDTO;
import com.qs.www.board.model.service.FreeBoardService;

@WebServlet("/board/free/select")
public class SelectFreeBoardServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		List<FreeBoardDTO> freeboardList = new FreeBoardService().selectAllFreeList();
		
		
		
		String path = "";
		if(freeboardList != null) {
			path = "/WEB-INF/views/board/freeBoardList.jsp";
			request.setAttribute("freeboardList", freeboardList);
		} else {
			path = "/WEB-INF/views/common/error-404.jsp";
			request.setAttribute("message", "공지사항 조회 실패!");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}
}
