package com.qs.www.mng.holiday.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.approval.model.service.ApprovalService;
import com.qs.www.mng.holiday.model.service.MngHolidayService;

@WebServlet("/mng/holiday/applied/delete")
public class DeleteMngAppliedHolidayServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("취소할거임");
		int lineNo = Integer.parseInt(request.getParameter("no"));
		String status = request.getParameter("status");
		System.out.println("lineNo : " +  lineNo);
		System.out.println("status : " + status);
		
		//해당결재 상태를 취소로 변경
		int result1 =  new MngHolidayService().cancleSelectedReport(lineNo);
		
		// 상신별 결재자들의 상태를 미처리로 변경
		int result2 =  new ApprovalService().callbackApproverPerReport(lineNo);
		
		//결재상태가 '승인'일 시 내역삭제
		if(status.equals("승인")) {
			int logNo = new MngHolidayService().selectHolidayLogNum(lineNo);
			System.out.println("1번 삭제전 2번결과 : " + result2);
			result2 = new MngHolidayService().deleteHolidayLog(logNo);
			System.out.println("1번 삭제후 2번결과 : " + result2);
			result2 = new MngHolidayService().deleteHolidayUseInfo(logNo);
		}
		
		
		System.out.println("1번결과 : " + result1);
		System.out.println("2번 삭제후 2번결과 : " + result2);
		
		
		String path = "";
		if(result1 > 0 && result2 > 0) {
            path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "deleteHoliday");
			
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "deleteHoliday");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}
}
