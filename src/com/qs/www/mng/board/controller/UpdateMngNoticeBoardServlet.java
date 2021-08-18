package com.qs.www.mng.board.controller;

import java.io.IOException;  
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.mng.board.model.dto.MngNoticeDTO;
import com.qs.www.mng.board.model.service.MngNoticeService;

@WebServlet("/mng/board/notice/update")
public class UpdateMngNoticeBoardServlet extends HttpServlet {

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println(no);
		
		MngNoticeService mngnoticeService = new MngNoticeService();
		MngNoticeDTO mngnotice = mngnoticeService.selectMngNoticeDetail(no);
		
		String path = "";
		
		System.out.println(mngnotice);
		System.out.println(path);
		System.out.println(no);
		if(mngnotice != null) {
			path = "/WEB-INF/views/mngboard/updateNoticeBoard.jsp";
			request.setAttribute("mngnotice", mngnotice);
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("message", "공지사항 수정 조회하기 실패!");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int no = Integer.parseInt(request.getParameter("no"));
		String title = request.getParameter("title");
		String body =  request.getParameter("body");
		int memberNo = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
		System.out.println("출력테스트");
		System.out.println("no : " + no);
		System.out.println(title);
		System.out.println(body);
		
		MngNoticeDTO mngnotice = new MngNoticeDTO();

		mngnotice.setNo(no);
		mngnotice.setTitle(title);
		mngnotice.setBody(body);
		mngnotice.setMember(memberNo);
		System.out.println(mngnotice);

		MngNoticeService mngnoticeService = new MngNoticeService();
		int result = mngnoticeService.updateMngNotice(mngnotice);

		String path = "";
		if(result > 0) {
			path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "updateMngNotice");
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "updateMngNotice");
		}

		request.getRequestDispatcher(path).forward(request, response);
	}
}

