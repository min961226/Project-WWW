package com.qs.www.board.controller;

import java.io.IOException; 
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.board.model.dto.FreeDTO;
import com.qs.www.board.model.service.FreeService;




@WebServlet("/board/free/select")
public class SelectFreeBoardServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//자유게시판 목록
		List<FreeDTO> freeList = new FreeService().selectAllFreeList();
		
		String path = "";
		if(freeList != null) {
			path = "/WEB-INF/views/board/freeBoard.jsp";
			request.setAttribute("freeList", freeList);
		} else {
			path = "/WEB-INF/views/common/error-404.jsp";
			request.setAttribute("message", "공지사항 조회 실패!");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}
}
