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
		System.out.println("memberNo : " +  memberNo);
		System.out.println("status : " + status);
		
		MngHolidayService mngHolidayService = new MngHolidayService();
		//해당결재 상태를 취소로 변경
		int result1 =  mngHolidayService.cancleSelectedReport(reportNo);
		
		// 상신별 결재자들의 상태를 미처리로 변경
		int result2 =  new ApprovalService().callbackApproverPerReport(reportNo);
		
		//결재상태가 '승인'일 시 내역삭제
		if(status.equals("승인")) {
			int logNo = new MngHolidayService().selectHolidayLogNum(reportNo);
			
			String duringDate = mngHolidayService.selectDuringDate(logNo);
			System.out.println("받을 휴가일수 : "+ duringDate);
			System.out.println("1번 삭제전 2번결과 : " + result2);
			result2 =mngHolidayService.deleteHolidayLog(logNo);
			System.out.println("1번 삭제후 2번결과 : " + result2);
			result2 = mngHolidayService.deleteHolidayUseInfo(logNo);
			
			HolidayService holidayService = new HolidayService();
			
			int useHoliday = Integer.parseInt(duringDate);
			int havingHoliday = holidayService.selectHavingHoliday(memberNo);
			
			int modifiedHoliday = havingHoliday + useHoliday;
			
			MemberInfoDTO memberInfoDTO = new MemberInfoDTO();
			memberInfoDTO.setMemberNo(memberNo);
			memberInfoDTO.setRemainingHoliday(modifiedHoliday);
			int result6 = holidayService.updateHavingHoliday(memberInfoDTO);
			System.out.println("사용휴가일수 : " + useHoliday);
			System.out.println("기존보유휴가일수 : " + havingHoliday);
			System.out.println("새 보유휴가일수 : " + modifiedHoliday);
			System.out.println("휴가 회수 성공 여부 : "+ result6);
		}
		
		
		System.out.println("1번결과 : " + result1);
		System.out.println("2번 삭제후 2번결과 : " + result2);
		
		
		String path = "";
		if(result1 > 0 && result2 > 0) {
            path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "deleteHoliday");
			
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "deleteHoliday");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}
}
