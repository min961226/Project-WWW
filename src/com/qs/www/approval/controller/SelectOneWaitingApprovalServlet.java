package com.qs.www.approval.controller;

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
import com.qs.www.schedule.model.dto.ApproverPerReportDTO;
import com.qs.www.schedule.model.dto.ReportDTO;
import com.qs.www.schedule.model.dto.WorkingDocumentItemDTO;

@WebServlet("/approval/waiting/selectOne")
public class SelectOneWaitingApprovalServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
			}
			System.out.println("성공당함 :"+ApproverStatus);
			
		}else {
			// 4.b 반려했으면 상신번호에 대한상신테이블의 상태를 '반려'로 변경
			result3 = new ApprovalService().finishAppReport(thisAPR);
			System.out.println("반려당함 :"+ApproverStatus);
		}
		
		//----------------근태, 휴가, 복지 내역 생성 삽입할 곳------------------
		
		
		
		//------------------------------------------------------------
		
		
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
