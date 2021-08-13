package com.qs.www.approval.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.approval.model.dto.ApprovalLineDTO;
import com.qs.www.approval.model.dto.ApproverDTO;
import com.qs.www.approval.model.service.ApprovalService;
import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.schedule.model.dto.ApproverPerReportDTO;
import com.qs.www.schedule.model.dto.ReportDTO;
import com.qs.www.schedule.model.dto.WorkingDocumentItemDTO;
import com.qs.www.schedule.model.service.ScheduleService;

@WebServlet("/approval/insert")
public class InsertApprovalServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		//로그인중인 유저가 생성자인 결재라인 가져오기
		int no = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();

		List<ApprovalLineDTO> lineList = new ApprovalService().selectApprovalLine(no);
		
		Date now  = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String str = format.format(now);
		
		String[] arrayDate = str.split("-");   
		int yearPlusFive = Integer.parseInt(arrayDate[0]) + 5;
		String endDate = yearPlusFive + "-" + arrayDate[1]  + "-" + arrayDate[2];
		
		request.setAttribute("endDate", endDate);
		request.setAttribute("lineList", lineList);
		session.setAttribute("lineList", lineList);
		request.getRequestDispatcher("/WEB-INF/views/approval/insertApproval.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		int lineNo = Integer.parseInt(request.getParameter("line"));
		
		int documentNo = Integer.parseInt(request.getParameter("documentNo"));
		int memberNo = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
		String note = request.getParameter("note");
		
		List<ApprovalLineDTO> lineList = (List<ApprovalLineDTO>) session.getAttribute("lineList");
		
		String lineName = "";
		for(ApprovalLineDTO line: lineList) {
			if(line.getLineNo() == lineNo) {
				lineName = line.getLineName();
			}
		}
		System.out.println("lineName : " + lineName);
		
		String title = request.getParameter("title");
		//현재 등록할 차례의 결재번호 받아오기
		int reportNo = new ApprovalService().selectReportNum();
		
		ReportDTO report = new ReportDTO();
		report.setDocumentNo(documentNo);
		report.setMemberNo(memberNo);
		report.setReportNote(note);
		report.setLineName(lineName);
		report.setReportTitle(title);
		
		
		//REPORT(상신) 추가
		ScheduleService scheduleService = new ScheduleService();	
		int result1 = scheduleService.applyWorkingSystem(report);
		
		
		
		
		
		
		
		
		String body = request.getParameter("body");

		List<String> documentItem = new ArrayList<>();
		documentItem.add(title);
		documentItem.add(body);

		if(documentNo == 3) {
			String contractDate = request.getParameter("contractDate");
			String payDate = request.getParameter("payDate");
			String productNo = request.getParameter("productNo");

			documentItem.add(contractDate);
			documentItem.add(payDate);
			documentItem.add(productNo);		
		}

		int priority = 1;
		int result2 = 0;
		//상신별 항목작성내용 등록
		for(String item : documentItem) {
			WorkingDocumentItemDTO documentItemDTO = new WorkingDocumentItemDTO();
			documentItemDTO.setReportNo(reportNo);
			documentItemDTO.setDocumentNo(documentNo);
			documentItemDTO.setPriority(priority);
			documentItemDTO.setItemContent(item);	

			result2 = scheduleService.applyWorkingSystemItemContent(documentItemDTO);

			priority++;
		}



		
		//선택한 결재 라인에 등록되있는 결재자들 가져오기
		List<ApproverDTO> approverList = new ApprovalService().selectApprover(lineNo);
		
		//상신별 결재자 등록
		int result3 = 0;
		for(ApproverDTO approver : approverList) {
			ApproverPerReportDTO approverPerReportDTO = new ApproverPerReportDTO();
			approverPerReportDTO.setReportNo(reportNo);
			approverPerReportDTO.setMemberNo(approver.getMemberNo());
			approverPerReportDTO.setPriority(approver.getPriority());

			result3 = scheduleService.applyWorkingSystemApprover(approverPerReportDTO);
		}
		
		
		String path = "";
		if(result1 > 0 && result2 > 0 && result3 > 0 ) {
            path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "insertApproval");
			
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "insertApproval");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}


}
