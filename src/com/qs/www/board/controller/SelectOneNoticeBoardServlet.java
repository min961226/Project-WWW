package com.qs.www.board.controller;

import java.io.IOException; 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.qs.www.board.model.dto.NoticeDTO;

import com.qs.www.board.model.service.NoticeService;
import com.qs.www.member.model.dto.MemberInfoDTO;

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
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int memberNo = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
		int no = Integer.parseInt(request.getParameter("no"));
		
		NoticeService noticeService = new NoticeService();
		NoticeDTO noticeDetail = noticeService.selectNoticeDetail(no);
		
		System.out.println(memberNo);

		System.out.println(no);
		
		NoticeDTO notice = new NoticeDTO();

		notice.setNo(no);

		notice.setMember(memberNo);
		System.out.println(notice);


//		int result = freeService.updateFree(free);

		String path = "";
//		if(result > 0) {
//			path = "/WEB-INF/views/common/success.jsp";
//			request.setAttribute("successCode", "updateFree");
//		} else {
//			path = "/WEB-INF/views/common/failed.jsp";
//			request.setAttribute("message", "자유게시판 수정에 실패");
//		}

//		request.getRequestDispatcher(path).forward(request, response);
	}
}