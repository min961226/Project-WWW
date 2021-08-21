package com.qs.www.mng.working.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.approval.model.service.ApprovalService;
import com.qs.www.mng.working.model.service.MngWorkingSystemService;

/**
 * Servlet implementation class DeleteMngAppliedWorkingSystemServlet
 */
@WebServlet("/mng/workingSystem/applied/delete")
public class DeleteMngAppliedWorkingSystemServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("취소할거임");
		int reportNo = Integer.parseInt(request.getParameter("no"));
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));  //기안자의 회원번호
		String status = request.getParameter("status");
		System.out.println("memberNo : " +  memberNo);
		System.out.println("status : " + status);
		
		MngWorkingSystemService mngWorkingSystemService = new MngWorkingSystemService();
		//해당결재 상태를 취소로 변경
		int result1 = mngWorkingSystemService.cancelSelectedWorkReport(reportNo);
		
		// 상신별 결재자들의 상태를 미처리로 변경
		int result2 =  new ApprovalService().callbackApproverPerReport(reportNo);
		
		//결재상태가 '승인'이었을 경우, 적었던 Log내역 삭제
		if(status.equals("승인")) {
			int logNo = mngWorkingSystemService.selectWorkLogNum(reportNo);
			
			
			
			
		}
		
		
		
		
		
	}
	
	
	

}
