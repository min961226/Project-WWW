package com.qs.www.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.member.model.dto.CheckPwdDTO;
import com.qs.www.member.model.dto.CheckQuestionDTO;
import com.qs.www.member.model.service.MemberService;
import com.qs.www.mypage.model.service.MypageService;

@WebServlet("/member/pwd/check")
public class CheckMemberPwdServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 본인 확인 질문 출력 */
		MypageService mypageService = new MypageService();
		List<CheckQuestionDTO> questionList = mypageService.selectQuestionList();
		
		request.setAttribute("questionList", questionList);
		
		request.getRequestDispatcher("/WEB-INF/views/member/checkMemberPwd.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 입력받은 정보를 DB와 비교 */
		String memberId = request.getParameter("id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");
		
		CheckPwdDTO checkPwd = new CheckPwdDTO();
		checkPwd.setMemberId(memberId);
		checkPwd.setName(name);
		checkPwd.setEmail(email);
		checkPwd.setQuestion(new CheckQuestionDTO());
		checkPwd.getQuestion().setQuestionCode(question);
		checkPwd.setQuestionAnswer(answer);
		
		MemberService memberService = new MemberService();
		int result = memberService.checkMemberPwd(checkPwd);
		String path = "";
		
		if(result > 0) {
			path = "/WEB-INF/views/member/updateMemberPwd.jsp";
			request.setAttribute("memberId", memberId);
			
			request.getRequestDispatcher(path).forward(request, response);
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "checkPwd");
			
			request.getRequestDispatcher(path).forward(request, response);
		}
	}
}
