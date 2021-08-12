package com.qs.www.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.board.model.dto.FormDTO;
import com.qs.www.board.model.service.FormService;
import com.qs.www.board.model.service.NoticeService;
import com.qs.www.mng.board.model.dto.NoticeDTO;

@WebServlet("/board/form/select")
public class SelectFormBoardServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//문서서식 게시판 목록
		List<FormDTO> formList = new FormService().selectAllFormList();
		
		String path = "";
		if(formList != null) {
			path = "/WEB-INF/views/board/formBoard.jsp";
			request.setAttribute("formList", formList);
		} else {
			path = "/WEB-INF/views/common/error-404.jsp";
			request.setAttribute("message", "문서서식 게시판 조회 실패!");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
		
	}
}
