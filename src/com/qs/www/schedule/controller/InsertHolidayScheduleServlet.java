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
import com.qs.www.approval.model.dto.ApproverDTO;
import com.qs.www.approval.model.service.ApprovalService;
import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.schedule.model.dto.ApproverPerReportDTO;
import com.qs.www.schedule.model.dto.HolidayTypeDTO;
import com.qs.www.schedule.model.dto.ReportDTO;
import com.qs.www.schedule.model.dto.WorkingDocumentItemDTO;
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

		String startDayAllday = request.getParameter("startDayAllday"); 			//종일, 오전, 오후
		String endDayAllday = request.getParameter("endDayAllday");					//종일, 오전, 오후

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
		int holidayCodeInt = Integer.parseInt(request.getParameter("holidayCode"));
		List<HolidayTypeDTO> holidayTypeList = (List<HolidayTypeDTO>) session.getAttribute("holidayTypeList");
		String holidayType = "";
		for(HolidayTypeDTO type : holidayTypeList) {
			if(type.getHolidayCode() == holidayCodeInt) {
				holidayType = type.getHolidayType();
			}
		}

		String title = memberName + holidayType + " 휴가신청서";

		/* 1-0. 상신올릴 문서가 쓸 ReportNo 가져오기 */
		ApprovalService approvalService = new ApprovalService();
		int reportNo = approvalService.selectReportNum();

		System.out.println("reportNo : " + reportNo);

		/* 1. 상신테이블(TBL_REPORT)에 insert */
		ReportDTO reportDTO = new ReportDTO();
		reportDTO.setMemberNo(memberNo);
		reportDTO.setDocumentNo(documentNo);
		reportDTO.setReportNote(changeReason);
		reportDTO.setLineName(lineName);
		reportDTO.setReportTitle(title);
		System.out.println("InsertHolidayScheduleServlet의 reportDTO : " + reportDTO);

		ScheduleService scheduleService = new ScheduleService();		
		int result1 = scheduleService.applyWorkingSystem(reportDTO);
		int result3 = 0;
		if(result1 > 0) {


			/* 2. 상신별문서항목작성내용(TBL_ITEM_CONTENT)에 insert */			
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
			System.out.println("InsertHolidayScheduleServlet의 holidayDocumentItem" + holidayDocumentItem);

			int priority = 1;
			int result2 = 0;
			for(String item : holidayDocumentItem) {
				WorkingDocumentItemDTO workingDocumentItemDTO = new WorkingDocumentItemDTO(); //어차피 내용은 같으므로 재활용
				workingDocumentItemDTO.setReportNo(reportNo);
				workingDocumentItemDTO.setDocumentNo(documentNo);
				workingDocumentItemDTO.setPriority(priority);
				workingDocumentItemDTO.setItemContent(item);			

				result2 = scheduleService.applyWorkingSystemItemContent(workingDocumentItemDTO);

				priority++;
			}		

			System.out.println(result2);		
			if(result2 > 0) {

				/* 3-1. 결재라인 선택한 번호로, 결재자들의 결재자사번과 우선순위를 DTO로 받아오기 */
				List<ApproverDTO> approverList = new ApprovalService().selectApprover(lineNo);
				System.out.println("approverList : " + approverList);

				/* 3-2. 상신별결재자(TBL_APPROVER_PER_REPORT)에 insert */
				result3 = 0;
				for(ApproverDTO approver : approverList) {
					ApproverPerReportDTO approverPerReportDTO = new ApproverPerReportDTO();
					approverPerReportDTO.setReportNo(reportNo);
					approverPerReportDTO.setMemberNo(approver.getMemberNo());
					approverPerReportDTO.setPriority(approver.getPriority());

					result3 = scheduleService.applyWorkingSystemApprover(approverPerReportDTO);
				}
				System.out.println(result3);


			}

		}

		/* 성공여부에 따라 success 혹은 fail로 넘겨줌 */
		String path = "";
		if(result1 > 0 && result3 > 0) {
			System.out.println("alert 휴가신청 상신성공");			
			path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "insertHoliday");

		} else {
			System.out.println("alert 휴가신청 상신실패");			
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "insertHoliday");
		}

		request.getRequestDispatcher(path).forward(request, response);

	}



	/* 휴가신청이 승인된 후에 사용하면 될듯  */
	//	/* 4-1. 휴가부여사용내역에서 사용할 lastNum을 가져오기 */
	//	HolidayService holidayService = new HolidayService();
	//	int holidayLogNo = holidayService.selectHolidayLogNum();
	//	
	//	/* 4-2. 휴가부여사용내역(tbl_member_holiday_log)에 추가 */
	//	int holidayCodeInt = Integer.parseInt(holidayCode);					
	//	
	//	HolidayLogDTO holidayLogDTO = new HolidayLogDTO();
	//	holidayLogDTO.setMemberNo(memberNo);
	//	holidayLogDTO.setLogNote(changeReason);
	//	holidayLogDTO.setLogType("사용");
	//	holidayLogDTO.setHolidayCode(holidayCodeInt);
	//	holidayLogDTO.setHolidayDuringDate(duringDateString);
	//	
	//	System.out.println("holidayLogDTO : " + holidayLogDTO);
	//	
	//	int result4 = holidayService.insertHolidayLog(holidayLogDTO);
	//	System.out.println(result4);
	//	
	//	if(result4 > 0) {						
	//		
	//		/* 5. 휴가사용정보(TBL_HOLIDAY_USE_INFO)에 추가 */
	//		HolidayUseInfoDTO holidayUseInfoDTO = new HolidayUseInfoDTO();
	//		holidayUseInfoDTO.setHolidayLogNo(holidayLogNo);
	//		holidayUseInfoDTO.setHolidayStartDay(startDay);
	//		holidayUseInfoDTO.setHolidayEndDay(endDay);
	//		holidayUseInfoDTO.setHolidayReportNo(reportNo);
	//		System.out.println("holidayUseInfoDTO : " + holidayUseInfoDTO);
	//		
	//		result5 = holidayService.insertHolidayUseInfo(holidayUseInfoDTO);
	//		System.out.println(result5);
	//	}



}
