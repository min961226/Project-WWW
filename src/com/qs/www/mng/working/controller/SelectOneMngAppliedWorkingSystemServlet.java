package com.qs.www.mng.working.controller;

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
import com.qs.www.common.attachment.model.dto.AttachmentDTO;
import com.qs.www.common.attachment.model.service.AttachmentService;
import com.qs.www.schedule.model.dto.ReportDTO;
import com.qs.www.schedule.model.dto.WorkingDocumentItemDTO;

@WebServlet("/mng/workingSystem/applied/selectOne")
public class SelectOneMngAppliedWorkingSystemServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("근무 신청 목록 상세"); 
		
		//윤서님 SelectOneMngAppliedHolidayServlet 참조함
		//선택한 근무신청결재의 결재번호를 넘겨받음
		int no = Integer.parseInt(request.getParameter("no"));
		
		// 참조함에서 선택한 게시물의 살세정보 가져오기
		ReportDTO selectedReport = new ApprovalService().selectOneReportDetail(no);

		/* 파일 첨부 DTO 서비스 실행 reportNo로 가져오기 */
		AttachmentDTO attachmentDTO = new AttachmentService().selectAttachmentByReportNo(selectedReport.getReportNo()); 
		System.out.println(attachmentDTO);

		List<WorkingDocumentItemDTO> itemList = new ApprovalService().selectReportItemList(no);
		List<ApproverLogPerReportDTO> ALPRList = new ApprovalService().selectALPRList(no);

		// 등록날짜를 보존기간으로 바꾸기
		Date reportDate = selectedReport.getReportDate();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String str = format.format(reportDate);

		String[] arrayDate = str.split("-");
		int yearPlusFive = Integer.parseInt(arrayDate[0]) + 5;
		String endDate = yearPlusFive + "-" + arrayDate[1] + "-" + arrayDate[2];

		request.setAttribute("endDate", endDate);
		request.setAttribute("selectedReport", selectedReport);
		request.setAttribute("itemList", itemList);
		request.setAttribute("ALPRList", ALPRList);
		request.setAttribute("attachmentDTO", attachmentDTO);
		
		//결재문서의 종류에 따라 항목명들을 키값으로 지정해서 request에 넣기
		if(selectedReport.getDocumentNo() == 4) {
			request.setAttribute("workCode", itemList.get(1).getItemContent());			//근무제 유형코드
			request.setAttribute("startDate", itemList.get(2).getItemContent());		//시작일
			request.setAttribute("endDate", itemList.get(3).getItemContent());			//종료일
			request.setAttribute("reason", itemList.get(4).getItemContent());			//사유
			request.setAttribute("worktype", itemList.get(5).getItemContent());			//근무제 유형
		}
		
		//초과근무신청일경우
		if(selectedReport.getDocumentNo() == 5) {
			request.setAttribute("startDate", itemList.get(1).getItemContent());		//시작일시
			request.setAttribute("endDate", itemList.get(2).getItemContent());			//종료일시
			request.setAttribute("overtimeDuring", itemList.get(3).getItemContent());	//초과근무 기간시수
			request.setAttribute("reason", itemList.get(4).getItemContent());			//사유
			request.setAttribute("startDateTime", itemList.get(5).getItemContent());	//시작시간
			request.setAttribute("endDateTime", itemList.get(6).getItemContent());		//종료시간
		}
		
		String path = "/WEB-INF/views/mngworkingsystem/detailAppliedWorkingSysytem.jsp";
		request.getRequestDispatcher(path).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
