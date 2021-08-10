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

import com.qs.www.member.model.dto.MemberDTO;
import com.qs.www.schedule.model.dto.MemberWorkLogDTO;
import com.qs.www.schedule.model.dto.ReportDTO;
import com.qs.www.schedule.model.dto.WorkingDocumentItemDTO;
import com.qs.www.schedule.model.service.ScheduleService;

@WebServlet("/schedule/workingSystem/insert")
public class InsertWorkingSystemScheduleServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("근무 신청");
		
		String path = "/WEB-INF/views/schedule/insertApplyWorkingSystem.jsp";
		
		request.getRequestDispatcher(path).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("InsertWorkingSystemScheduleServlet = 근무신청 서블렛으로 이동");
		
//		HttpSession session = request.getSession();
//		MemberDTO loginMember = (MemberDTO) session.getAttribute("loginMember");
		
		//근무신청서의 문서번호는 4번이다.
		int documentNo = 4;
		
		int workNo = Integer.parseInt(request.getParameter("workNo"));
		int approverLine = Integer.parseInt(request.getParameter("approverLine"));
		String changeReason = request.getParameter("changeReason");
		
		String workType = "";
		if(workNo > 5) {
			workType = "커스텀근무제";
		} else {
			workType = "표준근무제";
		}
		
		/* 1. 상신테이블(TBL_REPORT)에 상신문서 insert */
		ReportDTO reportDTO = new ReportDTO();
		//reportDTO.setMemberNo(loginMember.getMemberNo());
		reportDTO.setMemberNo(4);
		reportDTO.setDocumentNo(documentNo);
		reportDTO.setReportNote(changeReason);
		System.out.println("InsertWorkingSystemScheduleServlet의 reportDTO : " + reportDTO);
				
		ScheduleService scheduleService = new ScheduleService();		
//		int result = scheduleService.applyWorkingSystem(reportDTO);
//		System.out.println(result);
//		if(result > 0 ) { 여기서부터 다음거 시작이지
		
		
		/* 2-1. 방금 상신올린문서의 ReportNo를 가져와야 함 */
		//currval로 가져오면 되지않나?
		
		/* 2. 상신별문서항목작성내용(TBL_ITEM_CONTENT)에 insert*/
		//String memberName = (String) session.getAttribute("name");
		//String title = memberName + workType + " 신청서";
		String title = "조예슬" + workType + " 신청서";
		String getworkNo = request.getParameter("workNo");
		
		System.out.println("title : " + title);
		
		//ArrayList를 써야 하나?
		List<String> workingDocumentItem = new ArrayList<>();
		workingDocumentItem.add(title);
		workingDocumentItem.add(getworkNo);
		workingDocumentItem.add(request.getParameter("startDay"));
		workingDocumentItem.add(request.getParameter("endDay"));
		workingDocumentItem.add(changeReason);
		System.out.println("InsertWorkingSystemScheduleServlet의 List : " + workingDocumentItem);
		
		int priority = 1;
		int result = 0;
		for(String item : workingDocumentItem) {
			WorkingDocumentItemDTO workingDocumentItemDTO = new WorkingDocumentItemDTO();
			workingDocumentItemDTO.setReportNo(2); //일단 하드코딩
			workingDocumentItemDTO.setDocumentNo(documentNo);
			workingDocumentItemDTO.setPriority(priority);
			workingDocumentItemDTO.setItemContent(item);			
			
			result = scheduleService.applyWorkingSystemItemContent(workingDocumentItemDTO);
			
			priority++;
		}
		System.out.println(result);
		
//		workingDocumentItemDTO.setTitle(title);
//		workingDocumentItemDTO.setWorkNo(getworkNo);
//		workingDocumentItemDTO.setStartDate(request.getParameter("startDay"));
//		workingDocumentItemDTO.setEndDate(request.getParameter("endDay"));
//		workingDocumentItemDTO.setChangeReason(changeReason);
//		System.out.println("InsertWorkingSystemScheduleServlet의 workingDocumentItemDTO : " + workingDocumentItemDTO);
//		
//		int result = scheduleService.applyWorkingSystemItemContent(workingDocumentItemDTO);
//		System.out.println(result);
//		if(result > 0 ) { 여기서부터 다음거 시작이지
		
		
		/* 3-1. 결재라인선택한 번호로, 결재자들의 결재자사번을 받아오기 */
		List<String> approverList = new ArrayList<>();
		
		
		
		/* 3-2. 상신별결재자(TBL_APPROVER_PER_REPORT)에 insert */
		
		
		
		
		/* 4. 사원별근무제변경이력 확인 */
		java.sql.Date startDay = java.sql.Date.valueOf(request.getParameter("startDay"));
		java.sql.Date endDay = java.sql.Date.valueOf(request.getParameter("endDay"));
		//int memberNo = (Integer) session.getAttribute("memberNo");
		//System.out.println("memberNo : " + memberNo);
		
		MemberWorkLogDTO memberWorkLogDTO = new MemberWorkLogDTO();
		memberWorkLogDTO.setMemberNo(4);
		memberWorkLogDTO.setWorkType(workType);
		memberWorkLogDTO.setWorkNo(workNo);
		memberWorkLogDTO.setApproverLine(approverLine);
		memberWorkLogDTO.setStartDay(startDay);
		memberWorkLogDTO.setEndDay(endDay);
		memberWorkLogDTO.setChangeReason(changeReason);
		
		
		
		
		if(result > 0) {
			System.out.println("상신성공");
			String path = "";
			response.sendRedirect(request.getContextPath());
		} else {
			System.out.println("상신실패");
		}
		
	}
}
