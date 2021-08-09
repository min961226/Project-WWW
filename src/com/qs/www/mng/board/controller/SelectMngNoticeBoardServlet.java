package com.qs.www.mng.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.mng.board.model.dto.NoticeDTO;
import com.qs.www.mng.board.model.service.NoticeService;

@WebServlet("/mng/board/notice/select")
public class SelectMngNoticeBoardServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<NoticeDTO> noticeList = new NoticeService().selectAllNoticeList();
		
		String path = "";
		if(noticeList != null) {
			path = "/WEB-INF/views/mngboard/noticeBoard.jsp";
			request.setAttribute("noticeList", noticeList);
		} else {
			path = "/WEB-INF/views/common/error-404.jsp";
			request.setAttribute("message", "공지사항 조회 실패!");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
		
	}
}
