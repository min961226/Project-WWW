package com.qs.www.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.member.model.dto.MemberDTO;
import com.qs.www.member.model.service.MemberService;

@WebServlet("/member/id/check")
public class CheckMemberIdServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/checkMemberId.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		MemberDTO member = new MemberDTO();
		member.setName(name);
		member.setEmail(email);
		
		MemberService memberService = new MemberService();
		String findId = memberService.checkMemberId(member);
		
		String path="";
		if(findId != null) {
			path = "/WEB-INF/views/member/resultCheckMemberId.jsp";
			request.setAttribute("checkName", name);
			request.setAttribute("checkId", findId);
			
			request.getRequestDispatcher(path).forward(request, response);
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "checkId");
			
			request.getRequestDispatcher(path).forward(request, response);
		}
	}
}
