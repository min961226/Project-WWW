package com.qs.www.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.qs.www.board.model.dto.NoticeDTO;

import com.qs.www.board.model.service.NoticeService;

@WebServlet("/board/notice/selectOne")
public class SelectOneNoticeBoardServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//게시판 상세보기
		int no = Integer.parseInt(request.getParameter("no"));
		
		NoticeService noticeService = new NoticeService();
		NoticeDTO noticeDetail = noticeService.selectNoticeDetail(no);
		
		System.out.println("noticeDetail : " + noticeDetail);
		
		String path = "";
		if(noticeDetail != null) {
			path = "/WEB-INF/views/board/detailNoticeBoard.jsp";
			request.setAttribute("notice", noticeDetail);
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("message", "공지사항 상세보기 실패");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
		
	}
}