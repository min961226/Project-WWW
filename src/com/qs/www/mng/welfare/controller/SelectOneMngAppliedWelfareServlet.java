package com.qs.www.mng.welfare.controller;

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
import com.qs.www.schedule.model.dto.ReportDTO;
import com.qs.www.schedule.model.dto.WorkingDocumentItemDTO;

@WebServlet("/mng/welfare/list/selectOne")
public class SelectOneMngAppliedWelfareServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("복지 신청 상세보기");
		
		int no = Integer.parseInt(request.getParameter("no"));
		// 요청함에서 선택한 게시물의 살세정보 가져오기
		// 상신번호, 상신일자, 상신자 사번, 문서번호, 비고, 결재상태, 라인명, 상신명
		ReportDTO selectedReport = new ApprovalService().selectOneReportDetail(no);

		// 상신번호, 문서번호, 순번, 내용
		List<WorkingDocumentItemDTO> itemList = new ApprovalService().selectReportItemList(no);
		System.out.println("itemList : " + itemList);

		// 야간교통비 신청서
		if (selectedReport.getDocumentNo() == 7) {

			request.setAttribute("overTimeWorkTime", itemList.get(1).getItemContent()); 	// 업무시간
			request.setAttribute("overTimeTransBill", itemList.get(2).getItemContent()); 	// 청구예상 교통비
			request.setAttribute("reason", itemList.get(3).getItemContent()); 				// 업무내용
		}

		// 경조사 신청서
		if (selectedReport.getDocumentNo() == 8) {
			String eventCodeName = "";
			int eventCode = Integer.parseInt(itemList.get(1).getItemContent()); 			// 분류코드
			if (eventCode < 5) {
				eventCodeName = "결혼"; 														// 분류코드명
			} else if (eventCode < 10) {
				eventCodeName = "회갑";
			} else if (eventCode < 15) {
				eventCodeName = "출산";
			} else {
				eventCodeName = "사망";
			}
			request.setAttribute("eventCodeName", eventCodeName); 							// 경조사 코드 이름
			request.setAttribute("eventName", itemList.get(2).getItemContent()); 			// 경조사명
			request.setAttribute("eventBill", itemList.get(3).getItemContent()); 			// 경조금
			request.setAttribute("eventDate", itemList.get(4).getItemContent()); 			// 일자
			request.setAttribute("eventPlace", itemList.get(5).getItemContent()); 			// 장소
			request.setAttribute("reason", itemList.get(6).getItemContent()); 				// 경조금 신청 내용
		}

		// 자기개발신청서
		if (selectedReport.getDocumentNo() == 9) {
			String selfDevCodeName = "";
			int selfDevCode = Integer.parseInt(itemList.get(1).getItemContent()); 			// 분류코드
			if (selfDevCode == 1) {
				selfDevCodeName = "시험"; 													// 분류코드명
			} else if (selfDevCode == 2) {
				selfDevCodeName = "도서매비";
			} else if (selfDevCode == 3) {
				selfDevCodeName = "학원비";
			} else {
				selfDevCodeName = "운동비";
			}
			request.setAttribute("selfDevCodeName", selfDevCodeName); 						// 자기개발비 코드 이름
			request.setAttribute("selfDevUseDate", itemList.get(2).getItemContent()); 		// 자기개발비 사용일자
			request.setAttribute("selfDevBill", itemList.get(3).getItemContent()); 			// 자기개발비 청구금액
			request.setAttribute("reason", itemList.get(4).getItemContent()); 				// 자기개발비 신청목적
		}

		// 기숙사 입주 신청서
		if (selectedReport.getDocumentNo() == 10) {
			request.setAttribute("domitoryAddress", itemList.get(1).getItemContent()); 		// 경조사명
			request.setAttribute("domitoryDate", itemList.get(2).getItemContent()); 		// 경조금
			request.setAttribute("reason", itemList.get(3).getItemContent()); 				// 기숙사 신청사유
		}
		
		// 노트북 대여 신청서
		if (selectedReport.getDocumentNo() == 12) {
			request.setAttribute("returnDate", itemList.get(1).getItemContent());			// 노트북 반납일
			request.setAttribute("itemNo", itemList.get(2).getItemContent()); 				// 노트북 품목 번호
			request.setAttribute("reason", itemList.get(3).getItemContent()); 				// 노트북 대여사유
		}

		// 등록날짜를 보존기간으로 바꾸기
		Date reportDate = selectedReport.getReportDate();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String str = format.format(reportDate);

		String[] arrayDate = str.split("-");
		int yearPlusFive = Integer.parseInt(arrayDate[0]) + 5;
		String preservedDate = yearPlusFive + "-" + arrayDate[1] + "-" + arrayDate[2];

		request.setAttribute("selectedReport", selectedReport);
		request.setAttribute("preservedDate", preservedDate);

		String path = "/WEB-INF/views/mngwelfare/appliedDetailWelfare.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}

}
