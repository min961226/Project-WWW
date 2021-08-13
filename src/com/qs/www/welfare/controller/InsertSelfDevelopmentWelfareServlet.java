package com.qs.www.welfare.controller;

import java.io.IOException;
import java.sql.Date;
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

@WebServlet("/welfare/selfDevelopment/insert")
public class InsertSelfDevelopmentWelfareServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		WelfareService welfareService = new WelfareService();
		System.out.println("자기개발비 신청!");
		int documentNo = 9; 													// 자기개발비 신청 문서 번호
		int developmentNo = 0;													// 신청목적 번호
		String welfareTitle = "자기개발비 신청"; 									// 결재 제목
		int lineNo = Integer.parseInt(request.getParameter("lineList"));

		switch (request.getParameter("selfDevList")) {						//신청목적=> 번호 도출
		case "시험":
			developmentNo=1;
			break;
		case "도서구매비":
			developmentNo=2;
			break;
		case "학원비":
			developmentNo=3;
			break;
		case "운동비":
			developmentNo=4;
			break;

		default:
			break;
		}
		
		List<ApprovalLineDTO> lineList = new ApprovalService().selectApprovalLine(Integer.parseInt(request.getParameter("memberNo")));
		
		String lineName = "";					
		
		for (ApprovalLineDTO line : lineList) {
			if (line.getLineNo() == lineNo) {
				lineName = line.getLineName();
			}
		}

		WelfareListDTO welfareListDTO = new WelfareListDTO();
		
		welfareListDTO.setMemberNo(request.getParameter("memberNo"));
		welfareListDTO.setLineName(request.getParameter("lineList"));
		welfareListDTO.setDocumentNo(documentNo);
		welfareListDTO.setReportNote(request.getParameter("selfDevInfo"));
		welfareListDTO.setLineName(request.getParameter("selfDevList"));
		welfareListDTO.setWelfareTitle(welfareTitle);
		welfareListDTO.setDate(Date.valueOf(request.getParameter("date")));
		System.out.println(welfareListDTO);
		System.out.println(documentNo);
		
//		int developmentNo = welfareService.selectDevNo(welfareListDTO.getLineName());		//지원 목록
////		int limitCost = welfareService.selectLimitCost(developmentNo);					//지원 최대 금액

		int reportNo = welfareService.selectReportNum();
		int result1 = welfareService.insertWelfareReport(welfareListDTO);
		
		List<String> documentItem = new ArrayList<>();
		documentItem.add(welfareListDTO.getWelfareTitle());
		documentItem.add(String.valueOf(developmentNo));
		documentItem.add(request.getParameter("date"));
		documentItem.add(request.getParameter("selfDevCost"));
		documentItem.add(welfareListDTO.getReportNote());
		
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
			request.setAttribute("successCode", "insertSelfDev");
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failed", "insertSelfDev");
		}
		request.getRequestDispatcher(path).forward(request, response);
	}
}
