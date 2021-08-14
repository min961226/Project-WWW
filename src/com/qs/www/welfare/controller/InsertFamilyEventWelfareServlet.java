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
import com.qs.www.schedule.model.service.ScheduleService;
import com.qs.www.welfare.model.dto.FamilyEventDTO;
import com.qs.www.welfare.model.dto.WelfareListDTO;
import com.qs.www.welfare.model.service.WelfareService;

@WebServlet("/welfare/familyEvent/insert")
public class InsertFamilyEventWelfareServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String welfareTitle = "경조금 신청"; // 결재 제목
		int documentNo = 8; // 자기개발비 신청 문서 번호
		
		WelfareService welfareService = new WelfareService();
		FamilyEventDTO familyEventDTO = new FamilyEventDTO();
		
		int eventNo = 0; // 신청목적 번호
		int supportFund = 0;
		familyEventDTO.setWreathStatus(request.getParameter("wreathStatus").charAt(0));
		System.out.println(request.getParameter("eventType"));
		switch (Integer.parseInt(request.getParameter("eventType"))) {
		case 1:
			familyEventDTO.setEventType("결혼");
			familyEventDTO.setRelation(request.getParameter("marrige"));
			break;
		case 2:
			familyEventDTO.setEventType("출산");
			familyEventDTO.setRelation(request.getParameter("childBirth"));
			break;
		case 3:
			familyEventDTO.setEventType("회갑");
			familyEventDTO.setRelation(request.getParameter("sixtyBirth"));
			break;
		case 4:
			familyEventDTO.setEventType("사망");
			familyEventDTO.setRelation(request.getParameter("death"));
			break;
		default:
			break;
		}
		eventNo = welfareService.selectEventNo(familyEventDTO);
		supportFund= welfareService.selectSupportFund(eventNo);
		System.out.println(supportFund);
		
		
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
		welfareListDTO.setReportNote(request.getParameter("eventInfo"));
		welfareListDTO.setLineName(lineName);
		welfareListDTO.setWelfareTitle(welfareTitle);
		System.out.println(welfareListDTO);
		
		int reportNo = welfareService.selectReportNum();
		int result1 = welfareService.insertWelfareReport(welfareListDTO);
		
		List<String> documentItem = new ArrayList<>();
		documentItem.add(welfareTitle);
		documentItem.add(String.valueOf(eventNo));
		documentItem.add(request.getParameter("eventName"));
		documentItem.add(String.valueOf(supportFund));
		documentItem.add(request.getParameter("date"));
		documentItem.add(request.getParameter("eventPlace"));
		documentItem.add(welfareListDTO.getReportNote());
		
		int priority = 1;
		int result2 = 0;
		
		for(String item : documentItem) {
			WorkingDocumentItemDTO documentItemDTO = new WorkingDocumentItemDTO();
			documentItemDTO.setReportNo(reportNo);
			documentItemDTO.setDocumentNo(documentNo);
			documentItemDTO.setPriority(priority);
			documentItemDTO.setItemContent(item);	

			result2 = welfareService.insertWelfareItemContent(documentItemDTO);

			priority++;
		}

		
		
		
		List<ApproverDTO> approverList = new ApprovalService().selectApprover(lineNo);
		System.out.println(approverList);
		
		int result3 = 0;
		for(ApproverDTO approver : approverList) {
			ApproverPerReportDTO approverPerReportDTO = new ApproverPerReportDTO();
            ScheduleService scheduleService =new ScheduleService();
            if(approver.getApproverType().equals("결재")) {
                approverPerReportDTO.setReportNo(reportNo);
                approverPerReportDTO.setMemberNo(approver.getMemberNo());
                approverPerReportDTO.setPriority(approver.getPriority());

                result3 = scheduleService.applyWorkingSystemApprover(approverPerReportDTO);}
//            } else {
//                approverPerReportDTO.setReportNo(reportNo);
//                approverPerReportDTO.setMemberNo(approver.getMemberNo());
//                approverPerReportDTO.setApproverType(approver.getApproverType());
//
//                result3 = scheduleService.applyWorkingSystemReferer(approverPerReportDTO);
//            }
		}
		
		String path = "";

		if(result1 > 0 && result2 > 0 && result3 > 0 ) {
			path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "insertFamilyEvent");
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failed", "insertFamilyEvent");
		}
		request.getRequestDispatcher(path).forward(request, response);
	}
}
