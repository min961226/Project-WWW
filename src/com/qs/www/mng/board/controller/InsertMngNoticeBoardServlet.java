package com.qs.www.mng.board.controller;

import java.io.IOException; 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.board.model.dto.FreeDTO;
import com.qs.www.board.model.service.FreeService;
import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.mng.board.model.dto.MngNoticeDTO;
import com.qs.www.mng.board.model.service.MngNoticeService;

@WebServlet("/mng/board/notice/insert")
public class InsertMngNoticeBoardServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = "/WEB-INF/views/mngboard/insertNoticeBoard.jsp";
		
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		int memberNo = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
		String type = "공지";
		String title = (String)request.getParameter("title");
		String body = (String)request.getParameter("body");
		
		MngNoticeDTO newMngNotice = new MngNoticeDTO();
		newMngNotice.setTitle(title);
		newMngNotice.setBody(body);
		newMngNotice.setMember(memberNo);
		newMngNotice.setType(type);
		
		System.out.println(newMngNotice);

		MngNoticeService mngnoticeService = new MngNoticeService();
		int result = mngnoticeService.insertMngNotice(newMngNotice);
		
		System.out.println(result);
		
		String path = "";
		if(result > 0) {
			path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "insertMngNotice");
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("message", "공지사항 등록에 실패하셨습니다.");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}
}
