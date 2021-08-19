package com.qs.www.mng.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.mng.board.model.dto.MngFormDTO;
import com.qs.www.mng.board.model.dto.MngNoticeDTO;
import com.qs.www.mng.board.model.service.MngFormService;
import com.qs.www.mng.board.model.service.MngNoticeService;

@WebServlet("/mng/board/form/update")
public class UpdateMngFormBoardServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println(no);
		
		MngFormService mngformService = new MngFormService();
		MngFormDTO mngform = mngformService.selectMngFormDetail(no);
		
		String path = "";
		
		System.out.println(mngform);
		System.out.println(path);
		System.out.println(no);
		if(mngform != null) {
			path = "/WEB-INF/views/mngboard/updateFormBoard.jsp";
			request.setAttribute("mngform", mngform);
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("message", "문서서식 게시판 수정 조회하기 실패!");
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
		
		MngFormDTO mngform = new MngFormDTO();

		mngform.setNo(no);
		mngform.setTitle(title);
		mngform.setBody(body);
		mngform.setMember(memberNo);
		System.out.println(mngform);

		MngFormService mngformService = new MngFormService();
		int result = mngformService.updateMngForm(mngform);

		String path = "";
		if(result > 0) {
			path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "updateMngForm");
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "updateMngForm");
		}

		request.getRequestDispatcher(path).forward(request, response);
	}
}

