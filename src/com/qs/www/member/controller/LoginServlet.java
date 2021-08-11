package com.qs.www.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.member.model.dto.MemberDTO;
import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.member.model.service.MemberService;

@WebServlet("/member/login")
public class LoginServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String memberId = request.getParameter("memberId");
		String memberPwd = request.getParameter("memberPwd");
		
		MemberDTO requestMember = new MemberDTO();
		requestMember.setMemberId(memberId);
		requestMember.setPassword(memberPwd);
		
		MemberService memberService = new MemberService();
		
		// ID, PWD가 일치하는 정보를 DB에서 가져오기
		MemberInfoDTO loginMember = memberService.checkMember(requestMember);
		
		// 일치하는 로그인 정보를 session에 저장
		if(loginMember != null) {
			HttpSession session = request.getSession();
			session.setAttribute("memberInfo", loginMember);
			
			response.sendRedirect(request.getContextPath());
		} else {
			
//			request.setAttribute("failedCode", "login");
//			request.getRequestDispatcher("/WEB-INF/views/common/failed.jsp").forward(request, response);
		}
	}
}
