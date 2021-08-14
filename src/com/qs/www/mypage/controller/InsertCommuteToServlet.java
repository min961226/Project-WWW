package com.qs.www.mypage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.member.model.dto.MemberInfoDTO;

@WebServlet("/mypage/commute/insert")
public class InsertCommuteToServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String address = ((MemberInfoDTO) session.getAttribute("memberInfo")).getAddress();
		
	}
}
