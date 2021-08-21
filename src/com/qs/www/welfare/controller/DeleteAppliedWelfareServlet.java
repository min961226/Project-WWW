package com.qs.www.welfare.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.approval.model.service.ApprovalService;

@WebServlet("/welfare/applied/list/delete")
public class DeleteAppliedWelfareServlet extends HttpServlet {


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int no = Integer.parseInt(request.getParameter("no"));									//선택된 리포트 번호
		
		int result1 =  new ApprovalService().callbackSelectedReport(no);						// 상신별 결재자들의 상태를 미처리로 변경
		int result2 =  new ApprovalService().callbackApproverPerReport(no);						//상신 테이블에서 결재상태를 회수로 변경

		String path = "";
		
		if(result1 > 0 && result2 > 0) {
            path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "callbackWelfare");
			
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "callbackWelfare");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}
}
