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
		/* 입력받은 ID와 비밀번호를 DB에 저장 */
		String memberId = request.getParameter("memberId");
		String changePwd = request.getParameter("changePwd");
		
		MemberDTO changePwdMember = new MemberDTO();
		changePwdMember.setMemberId(memberId);
		changePwdMember.setPassword(changePwd);
		
		MemberService memberService = new MemberService();
		int result = memberService.updateMemberPwd(changePwdMember);
		
		String path = "";
		
		if(result > 0) {
			path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "updatePwd");
			
			request.getRequestDispatcher(path).forward(request, response);
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "updatePwd");
			
			request.getRequestDispatcher(path).forward(request, response);
		}
	}
}
