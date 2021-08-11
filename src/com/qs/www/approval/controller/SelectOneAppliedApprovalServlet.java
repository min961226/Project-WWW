package com.qs.www.approval.controller;

import java.io.IOException;
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
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
