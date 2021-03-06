package com.qs.www.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.approval.model.service.ApprovalService;
import com.qs.www.board.model.service.FreeService;

@WebServlet("/board/free/delete")
public class DeleteFreeBoardServlet extends HttpServlet {
	// 자유게시판 삭제
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 공지사항 번호를 가져옴
		int No = Integer.parseInt(request.getParameter("no"));
		int result = new FreeService().deleteFree(No);

		// 화면 연결
		String path = "";
		if (result > 0) {
			path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "deleteFree");

		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "deleteFree");
		}

		request.getRequestDispatcher(path).forward(request, response);
	}
}
