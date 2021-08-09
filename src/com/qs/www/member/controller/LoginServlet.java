package com.qs.www.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.member.model.dto.MemberDTO;
import com.qs.www.member.model.service.MemberService;

@WebServlet("/member/login")
public class LoginServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String memberId = request.getParameter("memberId");
		String memberPwd = request.getParameter("memberPwd");
		
		System.out.println(memberId);
		System.out.println(memberPwd);
		
		MemberDTO requestMember = new MemberDTO();
		requestMember.setId(memberId);
		requestMember.setPwd(memberPwd);
		
		MemberService memberService = new MemberService();
		
		MemberDTO loginMember = memberService.checkMember(requestMember);
		
		if(loginMember != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", loginMember);
			
			System.out.println(loginMember);
			
			response.sendRedirect(request.getContextPath());
		} else {
			System.out.println("로그인 실패");
		}
	}
}
