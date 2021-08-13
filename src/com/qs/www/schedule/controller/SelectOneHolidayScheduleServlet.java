package com.qs.www.schedule.controller;

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

@WebServlet("/schedule/holiday/selectOne")
public class SelectOneHolidayScheduleServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("휴가신청 상세보기");
		
		int no = Integer.parseInt(request.getParameter("no"));
		//요청함에서 선택한 게시물의 살세정보 가져오기
		//상신번호, 상신일자, 상신자 사번, 문서번호, 비고, 결재상태, 라인명, 상신명
		ReportDTO selectedReport  = new ApprovalService().selectOneReportDetail(no);

		//상신번호, 문서번호, 순번, 내용
		List<WorkingDocumentItemDTO> itemList = new ApprovalService().selectReportItemList(no);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
