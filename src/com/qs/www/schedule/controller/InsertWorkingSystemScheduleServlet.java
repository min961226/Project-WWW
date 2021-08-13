package com.qs.www.schedule.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
import com.qs.www.schedule.model.dto.MemberWorkLogDTO;
import com.qs.www.schedule.model.dto.ReportDTO;
import com.qs.www.schedule.model.dto.WorkingDocumentItemDTO;
import com.qs.www.schedule.model.service.ScheduleService;

@WebServlet("/schedule/workingSystem/insert")
public class InsertWorkingSystemScheduleServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("근무 신청");
		
		//로그인중인 유저가 생성자인 결재라인 가져오기
		HttpSession session = request.getSession();		
		int no = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
		List<ApprovalLineDTO> lineList = new ApprovalService().selectApprovalLine(no);
		request.setAttribute("lineList", lineList);
		session.setAttribute("lineList", lineList);
		
		String path = "/WEB-INF/views/schedule/insertApplyWorkingSystem.jsp";

		request.getRequestDispatcher(path).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("근무신청 서블렛으로 이동");

		HttpSession session = request.getSession();
		int lineNo = Integer.parseInt(request.getParameter("line")); 		//위쪽에서 미리 받는다.
		

		//session에서 가져온 결재라인들 중, 받아온 lineNo와 일치하는 DTO의 lineName을 따온다. 
		List<ApprovalLineDTO> lineList = (List<ApprovalLineDTO>) session.getAttribute("lineList");
        String lineName = "";
        for(ApprovalLineDTO line: lineList) {
            if(line.getLineNo() == lineNo) {
                lineName = line.getLineName();
            }
        }
        System.out.println("lineName : " + lineName);
        
        
				
		int workNo = Integer.parseInt(request.getParameter("workNo"));
		int approverLine = Integer.parseInt(request.getParameter("line"));
		String changeReason = request.getParameter("changeReason");
		String memberName = ((MemberInfoDTO) session.getAttribute("memberInfo")).getName();

		String workType = ""; 			//workNo로 workType을 정한다.
		if(workNo == 6) {
			workType = "커스텀";
		} else if(workNo == 7){
			workType = "표준";
		} else {
			workType = "초과";
		}
		String title = memberName + " " + workType + "근무 신청서";		//title 미리 만들어둔다.
		
		int documentNo = 0;
		if(workNo == 7) {
			documentNo = 5;				//초과근무신청서의 문서번호는 5번이다.
		} else {
			documentNo = 4;				//근무신청서의 문서번호는 4번이다.
		}				
		
		
		/* 1-1. 상신올릴 문서가 쓸 ReportNo 가져오기 */
		ApprovalService approvalService = new ApprovalService();
		int reportNo = approvalService.selectReportNum();

		System.out.println("reportNo : " + reportNo);
		
		
		/* 1-2. 상신테이블(TBL_REPORT)에 insert */
		ReportDTO reportDTO = new ReportDTO();
		int memberNo = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
		reportDTO.setMemberNo(memberNo);
		reportDTO.setDocumentNo(documentNo);
		reportDTO.setReportNote(changeReason);
		reportDTO.setLineName(lineName);
		reportDTO.setReportTitle(title);
		System.out.println("InsertWorkingSystemScheduleServlet의 reportDTO : " + reportDTO);

		ScheduleService scheduleService = new ScheduleService();		
		int result1 = scheduleService.applyWorkingSystem(reportDTO);
		
		int result3 = 0; 		//if문 밖인 제일 마지막에서도 판단할 수 있도록 바깥에 빼서 선언해준다		
		if(result1 > 0 ) { 

			
			/* 2. 상신별문서항목작성내용(TBL_ITEM_CONTENT)에 insert */
			String getworkNo = request.getParameter("workNo");

			List<String> workingDocumentItem = new ArrayList<>();
			workingDocumentItem.add(title);
			workingDocumentItem.add(getworkNo);
			workingDocumentItem.add(request.getParameter("startDay"));
			workingDocumentItem.add(request.getParameter("endDay"));
			workingDocumentItem.add(changeReason);
			workingDocumentItem.add(workType);
			System.out.println("InsertWorkingSystemScheduleServlet의 List : " + workingDocumentItem);

			int priority = 1;
			int result2 = 0;
			for(String item : workingDocumentItem) {
				WorkingDocumentItemDTO workingDocumentItemDTO = new WorkingDocumentItemDTO();
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
				//선택한 결재 라인에 등록되있는 결재자들 가져오기				
				List<ApproverDTO> approverList = new ApprovalService().selectApprover(lineNo);
				System.out.println(approverList);

				/* 3-2. 상신별결재자(TBL_APPROVER_PER_REPORT)에 insert */
				for(ApproverDTO approver : approverList) {
		            ApproverPerReportDTO approverPerReportDTO = new ApproverPerReportDTO();

		            if(approver.getApproverType().equals("결재")) {
		                approverPerReportDTO.setReportNo(reportNo);
		                approverPerReportDTO.setMemberNo(approver.getMemberNo());
		                approverPerReportDTO.setPriority(approver.getPriority());

		                result3 = scheduleService.applyWorkingSystemApprover(approverPerReportDTO);
		            } else {
		                approverPerReportDTO.setReportNo(reportNo);
		                approverPerReportDTO.setMemberNo(approver.getMemberNo());
		                approverPerReportDTO.setApproverType(approver.getApproverType());

		                result3 = scheduleService.applyWorkingSystemReferer(approverPerReportDTO);
		            }

		        }
				System.out.println(result3);
				

			}
		}

		/* 성공여부에 따라 success 혹은 fail로 넘겨줌 */
		String path = "";
		if(result1 > 0 && result3 > 0) {
			System.out.println("alert 근무신청 상신성공");			
			path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "insertWork");

		} else {
			System.out.println("alert 근무신청 상신실패");			
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "insertWork");
		}

		request.getRequestDispatcher(path).forward(request, response);

	}
	
	
	//나중에 근무신청이 결재승인 된 이후에 해야 함
	
	/* 4. 커스텀근무제라면 커스텀근무제에도 추가 */
//	if(true) {

		
//		/* 5. 사원별근무제변경이력(TBL_MEMBER_WORK_LOG)에 insert */
//		java.sql.Date startDay = java.sql.Date.valueOf(request.getParameter("startDay"));
//		java.sql.Date endDay = java.sql.Date.valueOf(request.getParameter("endDay"));
//		java.util.Date endDate = endDay;
//		long longendDate = endDate.getTime();								//밀리세컨으로 변경
//		long longendNextDate = longendDate + (1 * 24 * 60 * 60 * 1000);		//하루를 추가
//		java.sql.Date endNextDate = new java.sql.Date(longendNextDate);		//다시 sql.Date 형태로 변경
//		System.out.println(endDay + " 와" + endNextDate);
//		//혹은 split한 다음에 날짜에 +1하고 다시 합치는 방법도 있다. 
//
//		if(workType.equals("표준근무제")) {
//			workType = "표준";
//		} else {
//			workType = "커스텀";
//		}
//
//		/* 5-1. 첫번째 변경이력. startDay 사용 */
//		MemberWorkLogDTO memberWorkLogDTO = new MemberWorkLogDTO();
//		memberWorkLogDTO.setMemberNo(((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo());
//		memberWorkLogDTO.setWorkType(workType);
//		memberWorkLogDTO.setWorkNo(workNo);
//		memberWorkLogDTO.setStartDay(startDay);
//		memberWorkLogDTO.setChangeReason(changeReason);
//		System.out.println("memberWorkLogDTO : " + memberWorkLogDTO);
//
//		/* 5-2. 두번째 변경이력. endNextDate 사용. 다시 기본근태로 돌림 */
//		MemberWorkLogDTO memberWorkLogDTO2 = new MemberWorkLogDTO();
//		memberWorkLogDTO2.setMemberNo(((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo());
//		memberWorkLogDTO2.setWorkType("표준");
//		memberWorkLogDTO2.setWorkNo(1);
//		memberWorkLogDTO2.setStartDay(endNextDate);
//		memberWorkLogDTO2.setChangeReason("이전 근태신청의 기간만료");
//		System.out.println("memberWorkLogDTO2 : " + memberWorkLogDTO2);
//
//		result5 = scheduleService.applyWorkingSystemMemberWorkLog(memberWorkLogDTO);
//		result6 = scheduleService.applyWorkingSystemMemberWorkLog(memberWorkLogDTO2);
//
//		System.out.println(result5);
//		System.out.println(result6);

	
	
}
