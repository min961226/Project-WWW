package com.qs.www.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.qs.www.member.model.dto.MemberDTO;
import com.qs.www.member.model.service.MemberService;

@WebServlet("/member/pwd/update")
public class UpdateMemberPwdServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String changePwd = request.getParameter("changePwd");
		String changePwd2 = request.getParameter("changePwd2");
		
		MemberDTO changePwdMember = new MemberDTO();
		changePwdMember.setMemberId(memberId);
		changePwdMember.setPassword(changePwd);

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String path = "";
		
		
		if(changePwd.equals(changePwd2)) {
			MemberService memberService = new MemberService();
			System.out.println("changePwd : " + changePwd);
			System.out.println("changePwd2 : " + changePwd2);
			
			int result = memberService.updateMemberPwd(changePwdMember);
			
			if(result > 0) {
				path = "/WEB-INF/views/common/success.jsp";
				request.setAttribute("successCode", "updatePwd");
				
				request.getRequestDispatcher(path).forward(request, response);
			} else {
				path = "/WEB-INF/views/common/failed.jsp";
				request.setAttribute("failedCode", "updatePwd");
				
				request.getRequestDispatcher(path).forward(request, response);
			}
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "matchPwd");
			
			request.getRequestDispatcher(path).forward(request, response);
		}
	}
}
