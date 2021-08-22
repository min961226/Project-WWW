package com.qs.www.mng.holiday.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.approval.model.service.ApprovalService;
import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.mng.holiday.model.service.MngHolidayService;
import com.qs.www.schedule.model.service.HolidayService;

@WebServlet("/mng/holiday/applied/delete")
public class DeleteMngAppliedHolidayServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("취소할거임");
		int reportNo = Integer.parseInt(request.getParameter("no"));
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));  //기안자의 회원번호
		String status = request.getParameter("status");

		MngHolidayService mngHolidayService = new MngHolidayService();
		//해당결재 상태를 취소로 변경
		int result1 =  mngHolidayService.cancleSelectedReport(reportNo);
		
		// 상신별 결재자들의 상태를 미처리로 변경
		int result2 =  new ApprovalService().callbackApproverPerReport(reportNo);
		
		//결재상태가 '승인'일 시 휴가사용내역삭제
		int result3 = 1;
		if(status.equals("승인")) {
			int logNo = new MngHolidayService().selectHolidayLogNum(reportNo);
			
			String duringDate = mngHolidayService.selectDuringDate(logNo);
			result2 =mngHolidayService.deleteHolidayLog(logNo);
			result2 = mngHolidayService.deleteHolidayUseInfo(logNo);
			
			HolidayService holidayService = new HolidayService();
			
			int useHoliday = Integer.parseInt(duringDate);    //사용휴가일수
			int havingHoliday = holidayService.selectHavingHoliday(memberNo);    //기존보유휴가일수
			
			int modifiedHoliday = havingHoliday + useHoliday;    //새 보유휴가일수
			
			MemberInfoDTO memberInfoDTO = new MemberInfoDTO();
			memberInfoDTO.setMemberNo(memberNo);
			memberInfoDTO.setRemainingHoliday(modifiedHoliday);
			
			result3 = holidayService.updateHavingHoliday(memberInfoDTO);
		}
		
		
		String path = "";
		if(result1 > 0 && result2 > 0 && result3 > 0) {
            path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "deleteHoliday");
			
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "deleteHoliday");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}
}
