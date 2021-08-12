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

import com.qs.www.approval.model.service.ApprovalService;
import com.qs.www.schedule.model.dto.ReportDTO;
import com.qs.www.schedule.model.dto.WorkingDocumentItemDTO;

@WebServlet("/approval/applied/selectOne")
public class SelectOneAppliedApprovalServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("결재요청함 상세보기");

		int no = Integer.parseInt(request.getParameter("no"));

		ReportDTO selectedReport  = new ApprovalService().selectOneReportDetail(no);

		List<WorkingDocumentItemDTO> itemList = new ApprovalService().selectReportItemList(no);
		System.out.println(selectedReport);
		System.out.println(itemList);


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
		
		request.setAttribute("body", itemList.get(1).getItemContent());
		if(selectedReport.getDocumentNo() == 3) {
			request.setAttribute("contractDate", itemList.get(2).getItemContent());
			request.setAttribute("payDate", itemList.get(3).getItemContent());
			request.setAttribute("productNo", itemList.get(4).getItemContent());
		}
		request.getRequestDispatcher("/WEB-INF/views/approval/detailAppliedApproval.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
