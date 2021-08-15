package com.qs.www.board.controller;

import java.io.IOException; 
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.board.model.dto.FreeDTO;
import com.qs.www.board.model.service.FreeService;
import com.qs.www.member.model.dto.MemberInfoDTO;



@WebServlet("/board/free/insert")
public class InsertFreeBoardServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = "/WEB-INF/views/board/insertFreeBoard.jsp";
		
		request.getRequestDispatcher(path).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
//		int memberNo = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
		String type = "자유";
		String title = (String)request.getParameter("title");
		String body = (String)request.getParameter("body");

		FreeDTO newFree = new FreeDTO();
		newFree.setTitle(title);
		newFree.setBody(body);
//		newFree.setMember(memberNo);
		newFree.setType(type);
		
		System.out.println(newFree);

		FreeService freeService = new FreeService();
		int result = freeService.insertFree(newFree);
		
		System.out.println(result);
		
		String path = "";
		if(result > 0) {
			path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "insertFree");
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("message", "자유게시판 등록에 실패하셨습니다.");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}
}
