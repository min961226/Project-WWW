package com.qs.www.mng.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.board.model.service.FreeService;
import com.qs.www.mng.board.model.service.MngFormService;
import com.qs.www.mng.board.model.service.MngNoticeService;

@WebServlet("/mng/board/notice/delete")
public class DeleteMngNoticeBoardServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/* 삭제 하고자 하는 게시글 번호를 가져옴 */
		int no = Integer.parseInt(request.getParameter("no"));

		int result = new MngNoticeService().deleteMngNotice(no);

		/* 화면 연결 */
		String path = "";
		if (result > 0) {
			path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "deleteMngNotice");

		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "deleteMngNotice");
		}

		request.getRequestDispatcher(path).forward(request, response);
	}

}
