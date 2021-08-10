package com.qs.www.mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.member.model.dto.MemberDTO;
import com.qs.www.mypage.model.dto.MemberInfoDTO;
import com.qs.www.mypage.model.service.MypageService;

@WebServlet("/mypage/info/select")
public class SelectMypageServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String memberId = ((MemberDTO) session.getAttribute("loginMember")).getMemberId();
		
		MypageService mypageService = new MypageService();
		MemberInfoDTO memberInfo = mypageService.selectInfo(memberId);
		
		session.setAttribute("memberInfo", memberInfo);
		request.getRequestDispatcher("/WEB-INF/views/mypage/info.jsp").forward(request, response);
	}
}
