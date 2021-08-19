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

@WebServlet("/mng/board/form/insert")
public class InsertMngFormBoardServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
String path = "/WEB-INF/views/mngboard/insertFormBoard.jsp";
		
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		int memberNo = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
		String type = "문서서식";
		String title = (String)request.getParameter("title");
		String body = (String)request.getParameter("body");
		
		MngFormDTO newMngForm = new MngFormDTO();
		newMngForm.setTitle(title);
		newMngForm.setBody(body);
		newMngForm.setMember(memberNo);
		newMngForm.setType(type);
		
		System.out.println(newMngForm);

		MngFormService mngformService = new MngFormService();
		int result = mngformService.insertMngForm(newMngForm);
		
		System.out.println(result);
		
		String path = "";
		if(result > 0) {
			path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "insertMngForm");
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("message", "문서서식 게시글 등록에 실패하셨습니다.");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}
}
