package com.qs.www.approval.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.approval.model.dto.ApproverLogPerReportDTO;
import com.qs.www.approval.model.service.ApprovalService;
import com.qs.www.schedule.model.dto.ReportDTO;
import com.qs.www.schedule.model.dto.WorkingDocumentItemDTO;

@WebServlet("/approval/applied/selectOne")
public class SelectOneAppliedApprovalServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int no = Integer.parseInt(request.getParameter("no"));
		//요청함에서 선택한 게시물의 살세정보 가져오기
		ReportDTO selectedReport  = new ApprovalService().selectOneReportDetail(no);

		List<WorkingDocumentItemDTO> itemList = new ApprovalService().selectReportItemList(no);
		List<ApproverLogPerReportDTO>ALPRList = new ApprovalService().selectALPRList(no);

		//등록날짜를 보존기간으로 바꾸기
		Date reportDate = selectedReport.getReportDate();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String str = format.format(reportDate);

		String[] arrayDate = str.split("-");   
		int yearPlusFive = Integer.parseInt(arrayDate[0]) + 5;
		String endDate = yearPlusFive + "-" + arrayDate[1]  + "-" + arrayDate[2];

		request.setAttribute("endDate", endDate);
		request.setAttribute("selectedReport", selectedReport);
//		request.setAttribute("itemList", itemList);
		request.setAttribute("ALPRList", ALPRList);
		
		request.setAttribute("body", itemList.get(1).getItemContent());
		if(selectedReport.getDocumentNo() == 3) {
			request.setAttribute("contractDate", itemList.get(2).getItemContent());
			request.setAttribute("payDate", itemList.get(3).getItemContent());
			request.setAttribute("productNo", itemList.get(4).getItemContent());
		}
		request.getRequestDispatcher("/WEB-INF/views/approval/detailAppliedApproval.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		//상신 테이블에서 결재상태를 회수로 변경
		int result1 =  new ApprovalService().callbackSelectedReport(no);
		// 상신별 결재자들의 상태를 미처리로 변경
		int result2 =  new ApprovalService().callbackApproverPerReport(no);
		
		String path = "";
		if(result1 > 0 && result2 > 0) {
            path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "callbackApproval");
			
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "callbackApproval");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
		
	}
}
