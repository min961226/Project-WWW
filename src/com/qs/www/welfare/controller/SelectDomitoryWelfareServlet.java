package com.qs.www.welfare.controller;

import java.io.IOException;
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
import com.qs.www.welfare.model.service.WelfareService;

@WebServlet("/welfare/domitory/select")
public class SelectDomitoryWelfareServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		WelfareService welfareService = new WelfareService();
		HttpSession session = request.getSession();
		
		int memberNo = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();							//사번
		String name = ((MemberInfoDTO) session.getAttribute("memberInfo")).getName();								//이름
		String deptName = ((MemberInfoDTO) session.getAttribute("memberInfo")).getDepartment().getDeptName();		//부서
		String jobName = ((MemberInfoDTO) session.getAttribute("memberInfo")).getJob().getJobName();				//직책
		String addressAll = ((MemberInfoDTO) session.getAttribute("memberInfo")).getAddress();						//주소
		String address = addressAll.replace("$", " ");
		List<ApprovalLineDTO> lineList = new ApprovalService().selectApprovalLine(memberNo);
		
		String manageNo = request.getParameter("no");																//기숙사 번호
		
		request.setAttribute("memberNo", memberNo);
		request.setAttribute("deptName", deptName);
		request.setAttribute("jobName", jobName);
		request.setAttribute("name", name);
		request.setAttribute("lineList", lineList);
		request.setAttribute("address", address);
		request.setAttribute("manageNo", manageNo);
		
		String path="";
		path = "/WEB-INF/views/welfare/insertDomitory.jsp";
		
		request.getRequestDispatcher(path).forward(request, response);
		
	}
}
