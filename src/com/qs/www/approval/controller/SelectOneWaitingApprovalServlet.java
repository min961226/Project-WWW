package com.qs.www.approval.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import com.qs.www.schedule.model.dto.ApproverPerReportDTO;
import com.qs.www.schedule.model.dto.CustomWorkDTO;
import com.qs.www.schedule.model.dto.CustomWorkTimeDTO;
import com.qs.www.schedule.model.dto.MemberWorkLogDTO;
import com.qs.www.schedule.model.dto.ReportDTO;
import com.qs.www.schedule.model.dto.WorkingDocumentItemDTO;
import com.qs.www.schedule.model.service.ScheduleService;

@WebServlet("/approval/waiting/selectOne")
public class SelectOneWaitingApprovalServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		int memberNo = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
		
		int no = Integer.parseInt(request.getParameter("no"));
		//대기함에서 선택한 게시물의 살세정보 가져오기
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
		
		if(selectedReport.getDocumentNo() == 4) {
			request.setAttribute("body", itemList.get(1).getItemContent());
		}
		if(selectedReport.getDocumentNo() == 5) {
			request.setAttribute("body", itemList.get(1).getItemContent());
		}
		if(selectedReport.getDocumentNo() == 6) {
			request.setAttribute("body", itemList.get(1).getItemContent());
		}
		if(selectedReport.getDocumentNo() == 7) {
			request.setAttribute("body", itemList.get(1).getItemContent());
		}
		if(selectedReport.getDocumentNo() == 8) {
			request.setAttribute("body", itemList.get(1).getItemContent());
		}
		if(selectedReport.getDocumentNo() == 9) {
			request.setAttribute("body", itemList.get(1).getItemContent());
		}
		if(selectedReport.getDocumentNo() == 10) {
			request.setAttribute("body", itemList.get(1).getItemContent());
		}
		if(selectedReport.getDocumentNo() == 11) {
			request.setAttribute("body", itemList.get(1).getItemContent());
		}
		if(selectedReport.getDocumentNo() == 12) {
			request.setAttribute("body", itemList.get(1).getItemContent());
		}
		if(selectedReport.getDocumentNo() == 13) {
			request.setAttribute("body", itemList.get(1).getItemContent());
		}
		if(selectedReport.getDocumentNo() == 14) {
			request.setAttribute("body", itemList.get(1).getItemContent());
		}
		if(selectedReport.getDocumentNo() == 15) {
			request.setAttribute("body", itemList.get(1).getItemContent());
		}
		
		request.getRequestDispatcher("/WEB-INF/views/approval/detailWaitingApproval.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		int memberNo = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
		int reportNo = Integer.parseInt(request.getParameter("no"));
		String ApproverStatus = (String)request.getParameter("radio"); //'승인'인지 '반려'
		String opinion = (String)request.getParameter("opinion");
		
		ApproverPerReportDTO thisAPR = new ApproverPerReportDTO();
		thisAPR.setMemberNo(memberNo);
		thisAPR.setReportNo(reportNo);
		thisAPR.setApproverType(ApproverStatus);
		
		// 1.상신에대란 결재내역 추가
		ApproverLogPerReportDTO aLPR = new ApproverLogPerReportDTO();
		aLPR.setReportNo(reportNo);
		aLPR.setMemberNo(memberNo);
		aLPR.setAppNote(opinion);
		aLPR.setAppStatus(ApproverStatus);
		
		int result1 = new ApprovalService().insertALPR(aLPR);
		
		// 2. 결재한 상신번호에 대한 내 결재자사번의 상신별결재자가져오기
		ApproverPerReportDTO thisTurnAPR = new ApprovalService().selectThisTurnAPR(thisAPR);
		System.out.println(thisTurnAPR);
		
		int nextPriority = thisTurnAPR.getPriority() + 1; //다음 우선 순위 만들기
		System.out.println(nextPriority);
		
		// 3.  내 결재자사번의 상신별결재자 결재상태 '승인'혹은 '반려'로 바꾸기
		int result2 =  new ApprovalService().updateThisTurnAPR(thisAPR);
		
		int result3 = 0;
		if(ApproverStatus.equals("승인")) {
			// 4.a.1 '승인'했으면  내 결재자사번의 상신별결재에서 결재한 상신번호에 대한 다음 우선순위인 사람의 결재상태를 '대기'로 변경
			thisAPR.setPriority(nextPriority);
			result3 =  new ApprovalService().updateNextTurnAPR(thisAPR);
			// 4.a.2 변경하고 리턴값이 0(다음결재자가 존재하지 않음)일 시 결재한 상신번호에 대한 상신테이블의 상태를 '승인'으로 변경
			if(result3 == 0) {
				result3 = new ApprovalService().finishAppReport(thisAPR);
				
				
 				/* 5. 근무제쪽 테이블에도 insert 해주기 */
				//판단에 필요한 정보 가져오기
				ScheduleService scheduleService = new ScheduleService();
				ReportDTO selectedReport  = new ApprovalService().selectOneReportDetail(reportNo);
				List<WorkingDocumentItemDTO> itemList = new ApprovalService().selectReportItemList(reportNo);
				
				/* 5-1. 정규근무신청이라면 */
				if(selectedReport.getDocumentNo() == 4) {
					
					/* 5-1-a. 우선 사원별근무제변경이력 (TBL_MEMBER_WORK_LOG)에 insert한다 */
					String startDay = itemList.get(2).getItemContent();
					String endDay = itemList.get(3).getItemContent();
	 				
					String[] arrayEndDate = startDay.split("-");   
					int dayPlusOne = Integer.parseInt(arrayEndDate[2]) + 1;
					String endDate = arrayEndDate[0]  + "-" + arrayEndDate[1] + "-" + dayPlusOne;
					
	 				System.out.println(startDay + " 와" + endDate + "를 쓸 것이다.");
					
	 				/* 5-1-b. 첫번째 변경이력. startDay 사용 */
	 					//memberWorkLogDTO.setStartDay에 맞춰 sql.Date형식으로 바꿔준다
	 				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
	 				LocalDate startDayLoc = LocalDate.parse(startDay, formatter);
	 				java.sql.Date startDaySql = java.sql.Date.valueOf(startDayLoc);
	 				
	 				MemberWorkLogDTO memberWorkLogDTO = new MemberWorkLogDTO();
	 				memberWorkLogDTO.setMemberNo(selectedReport.getMemberNo());								//상신자사번
	 				memberWorkLogDTO.setWorkType(itemList.get(5).getItemContent()); 						//변경후 근무제유형
	 				memberWorkLogDTO.setWorkNo(Integer.parseInt(itemList.get(0).getItemContent()));			//변경후 근무제번호
	 				memberWorkLogDTO.setStartDay(startDaySql);												//변경일자
	 				memberWorkLogDTO.setChangeReason(itemList.get(4).getItemContent());						//변경사유
	 				
	 				System.out.println("memberWorkLogDTO : " + memberWorkLogDTO);	 				
	 				int result5 = scheduleService.applyWorkingSystemMemberWorkLog(memberWorkLogDTO);
	 				
	 				
	 				/* 5-2-c. 두번째 변경이력. endNextDate 사용. 다시 기본근태로 돌림 */
	 					//memberWorkLogDTO.setStartDay에 맞춰 sql.Date형식으로 바꿔준다
	 				LocalDate endDateLoc = LocalDate.parse(endDate, formatter);
	 				java.sql.Date endDateSql = java.sql.Date.valueOf(endDateLoc);
	 				
	 				MemberWorkLogDTO memberWorkLogDTO2 = new MemberWorkLogDTO();
	 				memberWorkLogDTO2.setMemberNo(selectedReport.getMemberNo());							//상신자사번
	 				memberWorkLogDTO2.setWorkType("표준"); 													//변경후 근무제유형
	 				memberWorkLogDTO2.setWorkNo(1);															//변경후 근무제번호
	 				memberWorkLogDTO2.setStartDay(endDateSql);												//변경일자
	 				memberWorkLogDTO2.setChangeReason("이전 근태신청의 기간만료");									//변경사유
	 				
	 				System.out.println("memberWorkLogDTO2 : " + memberWorkLogDTO2);
	 				int result6 = scheduleService.applyWorkingSystemMemberWorkLog(memberWorkLogDTO2);
	 				
	 				
					/* 5-1-d. 커스텀근무제라면 커스텀근무제에도 추가 */
	 				String workType = itemList.get(5).getItemContent(); //근무제가 뭔지 알아온다
	 				if(!workType.equals("표준")) {
	 					
	 					//생성할 workNo의 currval 가져오기
	 					
	 					//사원별커스텀근무제(TBL_CUSTOM_WORK) 에 insert
	 					CustomWorkDTO customWorkDTO = new CustomWorkDTO();
	 					itemList.get(6).getItemContent();
	 					
	 					
	 					//커스텀근무제요일별출퇴근시간에도 insert
	 					CustomWorkTimeDTO customWorkTimeDTO = new CustomWorkTimeDTO();
	 				}
	 				
	 				
				}
				
			
 				
 				
 				/* 5-2. 초과근무신청이라면 */
				if(selectedReport.getDocumentNo() == 5) {
					
				}
				
				
				

				
			}
			System.out.println("성공당함 :"+ApproverStatus);
			
		}else {
			// 4.b 반려했으면 상신번호에 대한상신테이블의 상태를 '반려'로 변경
			result3 = new ApprovalService().finishAppReport(thisAPR);
			System.out.println("반려당함 :"+ApproverStatus);
		}
		
		
		
		
		String path = "";
		if(result1 > 0 && result2 > 0 && result3 > 0) {
            path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "approvalProcess");
			
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "approvalProcess");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
		
	}
}
