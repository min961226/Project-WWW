package com.qs.www.schedule.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.approval.model.dto.ApprovalLineDTO;
import com.qs.www.approval.model.service.ApprovalService;
import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.schedule.model.dto.HolidayTypeDTO;
import com.qs.www.schedule.model.dto.ReportDTO;
import com.qs.www.schedule.model.service.HolidayService;
import com.qs.www.schedule.model.service.ScheduleService;

@WebServlet("/schedule/holiday/insert")
public class InsertHolidayScheduleServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("휴가 신청");
		
		/* 1. 생성휴가, 사용휴가, 잔여휴가 일수 가져오기 */
		
		
		/* 2. 로그인중인 유저가 생성자인 결재라인 select */
		HttpSession session = request.getSession();		
		int no = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
		List<ApprovalLineDTO> lineList = new ApprovalService().selectApprovalLine(no);
		System.out.println("InsertHolidayScheduleServlet의 lineList : " + lineList);
		
		request.setAttribute("lineList", lineList);
		session.setAttribute("lineList", lineList);
		
		
		/* 3. 휴가종류를 모두 select */
		List<HolidayTypeDTO> holidayTypeList = new HolidayService().selectAllHolidayType();
		System.out.println("InsertHolidayScheduleServlet의 holidayTypeList : " + holidayTypeList);
		
		request.setAttribute("holidayTypeList", holidayTypeList);
		session.setAttribute("holidayTypeList", holidayTypeList);

		/* 4. 휴가신청페이지로 포워드 */
		String path = "/WEB-INF/views/schedule/insertHoliday.jsp";		
		request.getRequestDispatcher(path).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("휴가신청 서블렛으로 이동");
		
		HttpSession session = request.getSession();
		
		String memberName = ((MemberInfoDTO) session.getAttribute("memberInfo")).getName();
		int memberNo = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
		int documentNo = 6;
		String changeReason = request.getParameter("reason");
		
		String startDayAllday = request.getParameter("startDayAllday");
		String endDayAllday = request.getParameter("endDayAllday");
		
		java.sql.Date startDay = java.sql.Date.valueOf(request.getParameter("startDay"));
		java.sql.Date endDay = java.sql.Date.valueOf(request.getParameter("endDay"));
		long longStartDay = startDay.getTime();
		long longendDay = endDay.getTime();
		
		//기간일수 계산... 일단 '종일'인 걸로 상정하고 계산.
		long during = longendDay - longStartDay;
		long duringDate = during / (24 * 60 * 60 * 1000) + 1;
		String duringDateString = duringDate + "";
		System.out.println(startDay + " 와 " + endDay + "의 기간일수 : " + duringDate);
		
		int lineNo = Integer.parseInt(request.getParameter("line"));
		List<ApprovalLineDTO> lineList = (List<ApprovalLineDTO>) session.getAttribute("lineList");
		String lineName = "";
        for(ApprovalLineDTO line: lineList) {
            if(line.getLineNo() == lineNo) {
                lineName = line.getLineName();
            }
        }
        
        String holidayCode = request.getParameter("holidayCode");
        List<HolidayTypeDTO> holidayTypeList = (List<HolidayTypeDTO>) session.getAttribute("holidayTypeList");
        String holidayType = "";
        for(HolidayTypeDTO type : holidayTypeList) {
        	if(type.getHolidayType().equals(holidayCode)) {
        		holidayType = type.getHolidayType();
        	}
        }
        
        String title = memberName + " " + holidayType + " 휴가신청서";
        
        /* 1. 상신테이블(TBL_REPORT)에 insert */
		ReportDTO reportDTO = new ReportDTO();
		reportDTO.setMemberNo(memberNo);
		reportDTO.setDocumentNo(documentNo);
		reportDTO.setReportNote(changeReason);
		reportDTO.setLineName(lineName);
		reportDTO.setReportTitle(title);
		System.out.println("InsertHolidayScheduleServlet의 reportDTO : " + reportDTO);

//		ScheduleService scheduleService = new ScheduleService();		
//		int result1 = scheduleService.applyWorkingSystem(reportDTO);
//		
//		if(result1 > 0) {
			
			/* 2-1. 방금 상신올린 문서의 ReportNo 가져오기 */
			ApprovalService approvalService = new ApprovalService();
			int reportNo = approvalService.selectReportNum();
			
			System.out.println("reportNo : " + reportNo);
			
			/* 2-2. 상신별문서항목작성내용(TBL_ITEM_CONTENT)에 insert */
			//제목, 휴가코드, 시작일, 시작일 종일여부, 종료일, 종료일 종일여부, 사유, 기간일수
			
			String startDayString = request.getParameter("startDay");
			String endDayString = request.getParameter("endDay");	
			
			List<String> holidayDocumentItem = new ArrayList<>();
			holidayDocumentItem.add(title);					//제목
			holidayDocumentItem.add(holidayCode);			//휴가코드
			holidayDocumentItem.add(startDayString);		//시작일
			holidayDocumentItem.add(startDayAllday);		//시작일 종일여부
			holidayDocumentItem.add(endDayString);			//종료일
			holidayDocumentItem.add(endDayAllday);			//종료일 종일여부
			holidayDocumentItem.add(changeReason);			//사유
			holidayDocumentItem.add(duringDateString);		//기간일수
			System.out.println(holidayDocumentItem);
			
//		}
		

    	
    	
	}
}
