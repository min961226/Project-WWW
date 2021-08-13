package com.qs.www.welfare.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.approval.model.dto.ApprovalLineDTO;
import com.qs.www.approval.model.dto.ApproverDTO;
import com.qs.www.approval.model.service.ApprovalService;
import com.qs.www.schedule.model.dto.ApproverPerReportDTO;
import com.qs.www.schedule.model.dto.WorkingDocumentItemDTO;
import com.qs.www.welfare.model.dto.FamilyEventDTO;
import com.qs.www.welfare.model.dto.WelfareListDTO;
import com.qs.www.welfare.model.service.WelfareService;

@WebServlet("/welfare/nightTransportation/insert")
public class InsertNightTransportationWelfareServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String welfareTitle = "야간 교통비 신청"; // 결재 제목
		int documentNo = 7; // 야간 교통비 신청 문서 번호
		
		WelfareService welfareService = new WelfareService();

		String overTime = request.getParameter("overTimeLog");
		int transBill = Integer.parseInt(request.getParameter("transBill"));
		System.out.println(overTime);
		String overTimeLogInfo = request.getParameter("overTimeLogInfo");

		int lineNo = Integer.parseInt(request.getParameter("lineList"));
		List<ApprovalLineDTO> lineList = new ApprovalService().selectApprovalLine(Integer.parseInt(request.getParameter("memberNo")));
		System.out.println(lineNo);
		System.out.println(lineList);
		
		String lineName = "";					
		
		for (ApprovalLineDTO line : lineList) {
			if (line.getLineNo() == lineNo) {
				lineName = line.getLineName();
			}
		}

		WelfareListDTO welfareListDTO = new WelfareListDTO();
		
		welfareListDTO.setMemberNo(request.getParameter("memberNo"));
		welfareListDTO.setDocumentNo(documentNo);
		welfareListDTO.setReportNote(overTimeLogInfo);
		welfareListDTO.setLineName(lineName);
		welfareListDTO.setWelfareTitle(welfareTitle);
		System.out.println(welfareListDTO);
		
		int reportNo = welfareService.selectReportNum();
		int result1 = welfareService.insertWelfareReport(welfareListDTO);
		
		List<String> documentItem = new ArrayList<>();
		documentItem.add(welfareTitle);
		documentItem.add(overTime);
		documentItem.add(String.valueOf(transBill));
		documentItem.add(overTimeLogInfo);
		
		int priority = 1;
		int result2 = 0;
		
		for(String item : documentItem) {
			WorkingDocumentItemDTO documentItemDTO = new WorkingDocumentItemDTO();
			documentItemDTO.setReportNo(reportNo);
			documentItemDTO.setDocumentNo(documentNo);
			documentItemDTO.setPriority(priority);
			documentItemDTO.setItemContent(item);	

			result2 = welfareService.insertSelfDevelopmentItemContent(documentItemDTO);

			priority++;
		}

		
		
		
		List<ApproverDTO> approverList = new ApprovalService().selectApprover(lineNo);
		System.out.println(approverList);
		
		int result3 = 0;
		for(ApproverDTO approver : approverList) {
			ApproverPerReportDTO approverPerReportDTO = new ApproverPerReportDTO();
			approverPerReportDTO.setReportNo(reportNo);
			approverPerReportDTO.setMemberNo(approver.getMemberNo());
			approverPerReportDTO.setPriority(approver.getPriority());

			result3 = welfareService.insertSelfDevelopmentApprover(approverPerReportDTO);
		}
		
		String path = "";

		if(result1 > 0 && result2 > 0 && result3 > 0 ) {
			path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "insertNightTrans");
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failed", "insertNightTrans");
		}
		request.getRequestDispatcher(path).forward(request, response);
	}
}
