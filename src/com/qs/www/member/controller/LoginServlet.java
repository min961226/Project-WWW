package com.qs.www.member.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.main.model.dto.AuthorityDTO;
import com.qs.www.main.model.dto.WorkInfoDTO;
import com.qs.www.main.model.dto.WorkingLogDTO;
import com.qs.www.main.model.service.MainService;
import com.qs.www.member.model.dto.MemberDTO;
import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.member.model.service.MemberService;

@WebServlet("/member/login")
public class LoginServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 입력받은 ID와 비밀번호를 DB에 저장된 정보와 비교 */
		String memberId = request.getParameter("memberId");
		String memberPwd = request.getParameter("memberPwd");
		
		MemberDTO requestMember = new MemberDTO();
		requestMember.setMemberId(memberId);
		requestMember.setPassword(memberPwd);
		
		MemberService memberService = new MemberService();
		MainService mainService = new MainService();
		
		// ID, PWD가 일치하는 정보를 DB에서 가져오기
		MemberInfoDTO loginMember = memberService.checkMember(requestMember);
		
		// 일치하는 로그인 정보를 session에 저장
		String path = "";
		if(loginMember != null) {
			// 로그인 계정의 권한을 확인하여 session에 저장
			String roleCode = loginMember.getRole().getRoleCode();
			
			List<AuthorityDTO> roleAuthorityList = mainService.selectAccessAuthorityList(roleCode);
			
			// 로그인 한 날짜의 근무제도 확인하여 DB에 저장
			LocalDate today = LocalDate.now();
			String todayDate = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			
			WorkInfoDTO workInfo = new WorkInfoDTO();
			workInfo.setMemberNo(loginMember.getMemberNo());
			workInfo.setSelectedDate(todayDate);
			
			WorkingLogDTO workingLog = mainService.selectWorkingLog(workInfo);
			workInfo.setAppWorkType(workingLog.getWorkType());
			workInfo.setWorkCode(workingLog.getWorkNo());
			int result = mainService.insertCommute(workInfo);
			
			loginMember.setAppWorkType(workingLog.getWorkType());
			loginMember.setWorkCode(workingLog.getWorkNo());
			
			HttpSession session = request.getSession();
			session.setAttribute("memberInfo", loginMember);
			session.setAttribute("memberAuthority", roleAuthorityList);
			
			response.sendRedirect(request.getContextPath());
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "login");
			
			request.getRequestDispatcher(path).forward(request, response);
		}
	}
}

