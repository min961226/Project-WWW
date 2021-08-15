package com.qs.www.approval.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.approval.model.dto.ApprovalLineDTO;
import com.qs.www.approval.model.service.ApprovalService;
import com.qs.www.member.model.dto.MemberInfoDTO;

@WebServlet("/approval/line/select")
public class SelectApprovalLineServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//로그인 중인 사용자가 올린 결재(상신, 상신 별 항목> 가져오기
		int no = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();

		List<ApprovalLineDTO> lineList = new ApprovalService().selectApprovalLine(no);
		Collections.reverse(lineList);
		System.out.println(lineList);
		request.setAttribute("lineList", lineList);
		request.getRequestDispatcher("/WEB-INF/views/approval/approvalLine.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
