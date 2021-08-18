package com.qs.www.mng.holiday.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.approval.model.dto.ApproverLogPerReportDTO;
import com.qs.www.approval.model.service.ApprovalService;
import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.schedule.model.dto.ReportDTO;
import com.qs.www.schedule.model.dto.WorkingDocumentItemDTO;

@WebServlet("/mng/holiday/applied/selectOne")
public class SelectOneMngAppliedHolidayServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		int memberNo = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
		int no = Integer.parseInt(request.getParameter("no"));
		//참조함에서 선택한 게시물의 살세정보 가져오기
		ReportDTO selectedReport  = new ApprovalService().selectOneReportDetail(no);

		List<WorkingDocumentItemDTO> itemList = new ApprovalService().selectReportItemList(no);
		List<ApproverLogPerReportDTO>ALPRList = new ApprovalService().selectALPRList(no);

		//등록날짜를 보존기간으로 바꾸기
		Date reportDate = selectedReport.getReportDate();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String str = format.format(reportDate);

		String[] arrayDate = str.split("-");   
		int yearPlusFive = Integer.parseInt(arrayDate[0]) + 5;
		String endDate = yearPlusFive + "-" + arrayDate[1]  + "-" + arrayDate[2];

		request.setAttribute("endDate", endDate);
		request.setAttribute("selectedReport", selectedReport);
		request.setAttribute("itemList", itemList);
		request.setAttribute("ALPRList", ALPRList);

		//결재의 문서종류에 따라 항목명들을 키값으로 지정해서 request에 넣기
		if(selectedReport.getDocumentNo() < 4) {
			request.setAttribute("body", itemList.get(1).getItemContent());
			if(selectedReport.getDocumentNo() == 3) {
				request.setAttribute("contractDate", itemList.get(2).getItemContent());
				request.setAttribute("payDate", itemList.get(3).getItemContent());
				request.setAttribute("productNo", itemList.get(4).getItemContent());
			}

		}

		if(selectedReport.getDocumentNo() == 6) {
			request.setAttribute("body", itemList.get(1).getItemContent());
		}

		request.getRequestDispatcher("/WEB-INF/views/mngholiday/detailHolidayList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
