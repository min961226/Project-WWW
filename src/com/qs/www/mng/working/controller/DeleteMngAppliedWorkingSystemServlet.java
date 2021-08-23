package com.qs.www.mng.working.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.approval.model.service.ApprovalService;
import com.qs.www.mng.holiday.model.service.MngHolidayService;
import com.qs.www.mng.working.model.service.MngAppliedWorkingService;

/**
 * Servlet implementation class DeleteMngAppliedWorkingSystemServlet
 */
@WebServlet("/mng/workingSystem/applied/delete")
public class DeleteMngAppliedWorkingSystemServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int reportNo = Integer.parseInt(request.getParameter("no"));
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));  //기안자의 회원번호
		int documentNo = Integer.parseInt(request.getParameter("documentNo"));
		String workType = request.getParameter("workType");
		String status = request.getParameter("status");
		
		MngAppliedWorkingService mngAppliedWorkingService = new MngAppliedWorkingService();
		MngHolidayService mngHolidayService = new MngHolidayService();
		
		//해당결재 상태를 취소로 변경
		int result1 =  mngHolidayService.cancleSelectedReport(reportNo);
		
		// 상신별 결재자들의 상태를 미처리로 변경
		int result2 =  new ApprovalService().callbackApproverPerReport(reportNo);
		
		int result3 = 1;
		int result4 = 1;
		int result5 = 1;
		//결재상태가 '승인'이었을 경우, 적었던 Log내역 삭제
		if(status.equals("승인")) {
			
			//reportNo를 활용하여, TBL_MEMBER_WORK_LOG상 log의 로그를 delete
			result3 = mngAppliedWorkingService.deleteWorkLog(reportNo);
			
			//커스텀근무라면, TBL_CUSTOM_WORK의 기록을 delete
			if(workType.equals("커스텀")) {
				//TBL_CUSTOM_WORK에서 커스텀근무제번호를 select
				int customWorkNo = mngAppliedWorkingService.selectCustomWorkNo(reportNo);
				
				//TBL_CUSTOM_WORKTIME에 가서 로그를 삭제(근무제가 FK이기 때문에 이쪽을 먼저 지워야 한다)
				result4 = mngAppliedWorkingService.deleteCustomWorktime(customWorkNo);
				
				//TBL_CUSTOM_WORK로 가서 로그를 삭제
				result5 = mngAppliedWorkingService.deleteCustomWork(reportNo);
			}
			
			//초과근무라면, TBL_MEMBER_OVERTIME_LOG의 기록을 delete해야 하는데.. 야간교통비에서도 쓰는데..?
			if(documentNo == 5) {
				
			} 
			
		}
		
		String path = "";
		if(result1 > 0 && result2 > 0 && result3 > 0 && result4 > 0 && result5 > 0     ) {
			path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "deleteWork");
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "deletework");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
		
		
	}
	
	
	

}
