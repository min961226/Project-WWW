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

@WebServlet("/schedule/holiday/selectOne")
public class SelectOneHolidayScheduleServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		/* 요청함에서 선택한 게시물의 상세정보 가져오기 */
		ReportDTO selectedReport  = new ApprovalService().selectOneReportDetail(no);

		/* 파일 첨부 DTO 서비스 실행 reportNo로 갖고옴 */
		AttachmentDTO attachmentDTO = new AttachmentService().selectAttachmentByReportNo(selectedReport.getReportNo());		//reportNo로 값을 갖고옴
		
		/* 상신번호, 문서번호, 순번, 내용 을 담아온다 */
		List<WorkingDocumentItemDTO> itemList = new ApprovalService().selectReportItemList(no);
		
		//휴가신청인경우
		if(selectedReport.getDocumentNo() == 6) {
			request.setAttribute("holidayType", itemList.get(1).getItemContent());					//휴가코드
			request.setAttribute("startDate", itemList.get(2).getItemContent());					//시작일
			request.setAttribute("startDateAllDay", itemList.get(3).getItemContent());				//시작일 종일여부
			request.setAttribute("endDate", itemList.get(4).getItemContent());						//종료일
			request.setAttribute("endDateAllDay", itemList.get(5).getItemContent());				//종료일 종일여부
			request.setAttribute("reason", itemList.get(6).getItemContent());						//사유
			request.setAttribute("holidayDuring", itemList.get(7).getItemContent());				//기간일수
		}
		
		/* 등록날짜를 보존기간으로 바꾸기 */
		Date reportDate = selectedReport.getReportDate();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String str = format.format(reportDate);
		
		String[] arrayDate = str.split("-");   
		int yearPlusFive = Integer.parseInt(arrayDate[0]) + 5;
		String preservedDate = yearPlusFive + "-" + arrayDate[1]  + "-" + arrayDate[2];
				
		request.setAttribute("selectedReport", selectedReport);
		request.setAttribute("preservedDate", preservedDate);
		request.setAttribute("attachmentDTO", attachmentDTO);
		
		String path = "/WEB-INF/views/schedule/detailHoliday.jsp";
		request.getRequestDispatcher(path).forward(request, response);
		
	}

}
