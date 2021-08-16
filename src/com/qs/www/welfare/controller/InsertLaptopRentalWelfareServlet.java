package com.qs.www.welfare.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTML;

import com.qs.www.approval.model.dto.ApprovalLineDTO;
import com.qs.www.approval.model.dto.ApproverDTO;
import com.qs.www.approval.model.service.ApprovalService;
import com.qs.www.schedule.model.dto.ApproverPerReportDTO;
import com.qs.www.schedule.model.dto.WorkingDocumentItemDTO;
import com.qs.www.schedule.model.service.ScheduleService;
import com.qs.www.welfare.model.dto.WelfareListDTO;
import com.qs.www.welfare.model.service.WelfareService;

@WebServlet("/welfare/laptopRental/insert")
public class InsertLaptopRentalWelfareServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String welfareTitle = "노트북 대여 신청서"; // 결재 제목
		int documentNo = 10; // 야간 교통비 신청 문서 번호
		
		WelfareService welfareService = new WelfareService();
		int lineNo = Integer.parseInt(request.getParameter("lineList"));
		LocalDate sysDateAfterWeek = LocalDate.now().plusWeeks(2);
		LocalDate pickDate = LocalDate.parse(request.getParameter("date"));

		String path = "";
		if(sysDateAfterWeek.isAfter(pickDate)) {
			
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
			welfareListDTO.setReportNote(request.getParameter("laptopInfo"));
			welfareListDTO.setLineName(lineName);
			welfareListDTO.setWelfareTitle(welfareTitle);
			
			int reportNo = welfareService.selectReportNum();
			int result1 = welfareService.insertWelfareReport(welfareListDTO);
			
			List<String> documentItem = new ArrayList<>();												//document_item에 들어가는 값 설정
			documentItem.add(welfareTitle);
			documentItem.add(request.getParameter("date"));
			documentItem.add(request.getParameter("itemNo"));
			documentItem.add(request.getParameter("laptopInfo"));
			
			int priority = 1;
			int result2 = 0;
			
			for(String item : documentItem) {															//for문 지정후 itemcontent에 값 넣기
				WorkingDocumentItemDTO documentItemDTO = new WorkingDocumentItemDTO();
				documentItemDTO.setReportNo(reportNo);
				documentItemDTO.setDocumentNo(documentNo);
				documentItemDTO.setPriority(priority);
				documentItemDTO.setItemContent(item);	
				
				result2 = welfareService.insertWelfareItemContent(documentItemDTO);
				
				priority++;
			}
			
			List<ApproverDTO> approverList = new ApprovalService().selectApprover(lineNo);				//라인번호로 결재 참조자 목록 가져오기
			
			int result3 = 0;
			for(ApproverDTO approver : approverList) {													//결재자 역활구분
				ApproverPerReportDTO approverPerReportDTO = new ApproverPerReportDTO();
				ScheduleService scheduleService =new ScheduleService();
				if(approver.getApproverType().equals("결재")) {
					approverPerReportDTO.setReportNo(reportNo);
					approverPerReportDTO.setMemberNo(approver.getMemberNo());
					approverPerReportDTO.setPriority(approver.getPriority());
					
					result3 = scheduleService.applyWorkingSystemApprover(approverPerReportDTO);
				} else {
					approverPerReportDTO.setReportNo(reportNo);
					approverPerReportDTO.setMemberNo(approver.getMemberNo());
					approverPerReportDTO.setApproverType(approver.getApproverType());
					
					result3 = scheduleService.applyWorkingSystemReferer(approverPerReportDTO);
				}
				
			}
			
			
			if(result1 > 0 && result2 > 0 && result3 > 0 ) {
				path = "/WEB-INF/views/common/success.jsp";
				request.setAttribute("successCode", "insertLaptop");
			} else {
				path = "/WEB-INF/views/common/failed.jsp";
				request.setAttribute("failedCode", "insertLaptop");
			}
		}else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "insertLaptopDateError");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}
}
