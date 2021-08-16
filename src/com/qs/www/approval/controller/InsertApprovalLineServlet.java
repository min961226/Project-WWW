package com.qs.www.approval.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.approval.model.dto.ApproverDTO;
import com.qs.www.approval.model.service.ApprovalService;
import com.qs.www.member.model.dto.MemberInfoDTO;

@WebServlet("/approval/line/insert")
public class InsertApprovalLineServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int memberNo = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
		List<MemberInfoDTO> memberList = new ApprovalService().selectMemberList();
		System.out.println(memberList);
//		memberList.get(0).getJob().getJobName()
		request.setAttribute("memberNo",memberNo);
		request.setAttribute("memberList", memberList);
		request.getRequestDispatcher("/WEB-INF/views/approval/insertApprovalLine.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("라인생성");
		String appSN1 = request.getParameter("app1");
		String appSN2 = request.getParameter("app2");
		String appSN3 = request.getParameter("app3");
		String appSN4 = request.getParameter("app4");
		int priority = 1;
		
		
		
		if(appSN1.equals(appSN2) || appSN1.equals(appSN3) || appSN1.equals(appSN4) || appSN2.equals(appSN1) || appSN2.equals(appSN3) || appSN3.equals(appSN4)) {
			System.out.println("겹치자너!!!!!!!!!!!!");
		}
		System.out.println(appSN1);
		System.out.println(appSN2);
		
		ApproverDTO app1 = new ApproverDTO();
		int appNo1 = Integer.parseInt(appSN1);
		app1.setMemberNo(appNo1);
		app1.setApproverType( request.getParameter("appType1"));
		app1.setPriority(priority);
		priority++;
		System.out.println(priority);
		List<ApproverDTO> appList = new ArrayList<ApproverDTO>();
		System.out.println(app1);
		
		
		
		if(!appSN2.equals("a0")) {
			System.out.println("2번결재자 안빔");
			ApproverDTO app2 = new ApproverDTO();
			int appNo2 = Integer.parseInt(appSN2);
			app2.setMemberNo(appNo2);
		}
	}
}
