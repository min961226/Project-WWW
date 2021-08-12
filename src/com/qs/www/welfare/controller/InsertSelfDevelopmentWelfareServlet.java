package com.qs.www.welfare.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.approval.model.dto.ApproverDTO;
import com.qs.www.approval.model.service.ApprovalService;
import com.qs.www.schedule.model.dto.ApproverPerReportDTO;
import com.qs.www.schedule.model.dto.WorkingDocumentItemDTO;
import com.qs.www.welfare.model.dto.WelfareListDTO;
import com.qs.www.welfare.model.service.WelfareService;

@WebServlet("/welfare/selfDevelopment/insert")
public class InsertSelfDevelopmentWelfareServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		WelfareService welfareService = new WelfareService();
		System.out.println("자기개발비 신청!");
		int documentNo = 9;		            //자기개발비 신청 문서 번호
		String welfareTitle = "자기개발비 신청"; // 결재 제목
		
		String lineList = request.getParameter("lineList");
		WelfareListDTO welfareListDTO = new WelfareListDTO();
		welfareListDTO.setMemberNo(request.getParameter("memberNo"));
		welfareListDTO.setDocumentNo(documentNo);
		welfareListDTO.setReportNote(request.getParameter("selfDevInfo"));
		welfareListDTO.setLineName(request.getParameter("selfDevList"));
		welfareListDTO.setWelfareTitle(welfareTitle);
		welfareListDTO.setDate(Date.valueOf(request.getParameter("date")));
		System.out.println(welfareListDTO);

		int developmentNo = welfareService.selectDevNo(welfareListDTO.getLineName());	//지원 목록
//		int limitCost = welfareService.selectLimitCost(developmentNo);					//지원 최대 금액

		
		int result1 = welfareService.insertSelfDevelopment(welfareListDTO);
		int reportNo = welfareService.selectReportNum();
		
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

			result3 = scheduleService.applyWorkingSystemApprover(approverPerReportDTO);
		}
		
		if(result1 > 0 && result2 > 0 && result3 > 0 ) {
			System.out.println("alert 상신성공");
			
		} else {
			System.out.println("alert 상신실패");
		}
	}
}
