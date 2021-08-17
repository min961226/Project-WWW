package com.qs.www.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@WebServlet("/member/pwd/update")
public class UpdateMemberPwdServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String changePwd = request.getParameter("changePwd");
		String changePwd2 = request.getParameter("changePwd2");
		
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
//		if(passwordEncoder.matches(changePwd, changePwd2)) {
		String path = "";
		
		if(changePwd.equals(changePwd2)) {
			
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "matchPwd");
			
			request.getRequestDispatcher(path).forward(request, response);
		}
	}
}
