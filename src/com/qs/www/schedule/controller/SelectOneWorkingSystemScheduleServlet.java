package com.qs.www.schedule.controller;

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
import com.qs.www.common.attachment.model.dto.AttachmentDTO;
import com.qs.www.common.attachment.model.service.AttachmentService;
import com.qs.www.schedule.model.dto.ReportDTO;
import com.qs.www.schedule.model.dto.WorkingDocumentItemDTO;

@WebServlet("/schedule/workingSystem/selectOne")
public class SelectOneWorkingSystemScheduleServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("근무신청 상세보기"); 
		
		int no = Integer.parseInt(request.getParameter("no"));
		//요청함에서 선택한 게시물의 살세정보 가져오기
		//상신번호, 상신일자, 상신자 사번, 문서번호, 비고, 결재상태, 라인명, 상신명
		ReportDTO selectedReport  = new ApprovalService().selectOneReportDetail(no);

		/* 파일 첨부 DTO 서비스 실행 reportNo로 갖고옴 */
		AttachmentDTO attachmentDTO = new AttachmentService().selectAttachmentByReportNo(selectedReport.getReportNo());		//reportNo로 값을 갖고옴
		System.out.println(attachmentDTO);
		
		//상신번호, 문서번호, 순번, 내용
		List<WorkingDocumentItemDTO> itemList = new ApprovalService().selectReportItemList(no);
		System.out.println("itemList : " + itemList);
		
		//근무신청일경우
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
			request.setAttribute("startDateTime", itemList.get(5).getItemContent());		//시작시간
			request.setAttribute("endDateTime", itemList.get(6).getItemContent());			//종료시간
		}
		
		
		//등록날짜를 보존기간으로 바꾸기
		Date reportDate = selectedReport.getReportDate();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String str = format.format(reportDate);

		String[] arrayDate = str.split("-");   
		int yearPlusFive = Integer.parseInt(arrayDate[0]) + 5;
		String preservedDate = yearPlusFive + "-" + arrayDate[1]  + "-" + arrayDate[2];
		
		request.setAttribute("selectedReport", selectedReport);
		request.setAttribute("preservedDate", preservedDate);
		request.setAttribute("attachmentDTO", attachmentDTO);
		
		String path = "/WEB-INF/views/schedule/detailAppliedWorkingSystem.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
