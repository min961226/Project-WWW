package com.qs.www.welfare.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.approval.model.dto.ApprovalLineDTO;
import com.qs.www.approval.model.dto.ApproverDTO;
import com.qs.www.approval.model.service.ApprovalService;
import com.qs.www.schedule.model.dto.ApproverPerReportDTO;
import com.qs.www.schedule.model.dto.WorkingDocumentItemDTO;
import com.qs.www.schedule.model.service.ScheduleService;
import com.qs.www.welfare.model.dto.WelfareListDTO;
import com.qs.www.welfare.model.service.WelfareService;

@WebServlet("/welfare/domitory/insert")
public class InsertDomitoryWelfareServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String welfareTitle = "기숙사 입주 신청"; 														// 결재 제목
		int documentNo = 10; 																		// 기숙사 입주 신청 문서 번호

		WelfareService welfareService = new WelfareService();

		String address = request.getParameter("address");											//현 거주지
		String hopeDate = request.getParameter("hopeDate");											//입주 희망일
		String memberNo = request.getParameter("memberNo");											//사용자 번호
		String domitoryInfo = request.getParameter("domitoryInfo");									//신청사유

		int lineNo = Integer.parseInt(request.getParameter("lineList"));							//결재 라인 번호
		List<ApprovalLineDTO> lineList = new ApprovalService().selectApprovalLine(Integer.parseInt(memberNo));

		String lineName = "";

		for (ApprovalLineDTO line : lineList) {														//라인리스트로부터 라인이름을가져온다.
			if (line.getLineNo() == lineNo) {
				lineName = line.getLineName();
			}
		}

		WelfareListDTO welfareListDTO = new WelfareListDTO();

		welfareListDTO.setMemberNo(memberNo);														//사번
		welfareListDTO.setDocumentNo(documentNo);													//문서번호
		welfareListDTO.setReportNote(domitoryInfo);													//신청사유
		welfareListDTO.setLineName(lineName);														//라인명
		welfareListDTO.setWelfareTitle(welfareTitle);												//신청제목
		
		int reportNo = welfareService.selectReportNum();											//결재 문서 번호
		int result1 = welfareService.insertWelfareReport(welfareListDTO);							//결재 상신

		List<String> documentItem = new ArrayList<>();												//결재 세부 내용 넣기 위한 리스트 생성
		documentItem.add(welfareTitle);																//제목
		documentItem.add(address);																	//주소
		documentItem.add(hopeDate);																	//입주희망일
		documentItem.add(domitoryInfo);																//신청 사유

		int priority = 1;																			//결재 세부 내용 순번
		int result2 = 0;

		for (String item : documentItem) {															//결재 세부 내용 itemcontent에 넣기 위에 dto에 담기
			WorkingDocumentItemDTO documentItemDTO = new WorkingDocumentItemDTO();
			documentItemDTO.setReportNo(reportNo);
			documentItemDTO.setDocumentNo(documentNo);
			documentItemDTO.setPriority(priority);
			documentItemDTO.setItemContent(item);

			result2 = welfareService.insertWelfareItemContent(documentItemDTO);

			priority++;																				//아이템을 한번 넣고나면 priority 증가로 다른 값을 넣는다
		}

		List<ApproverDTO> approverList = new ApprovalService().selectApprover(lineNo);				//라인번호로 결재자명단을 갖고온다.

		int result3 = 0;
		for(ApproverDTO approver : approverList) {													
            ApproverPerReportDTO approverPerReportDTO = new ApproverPerReportDTO();
            ScheduleService scheduleService = new ScheduleService();
            if(approver.getApproverType().equals("결재")) {											//결재 상태가 최종결재일경우 상태를 결재 처리로 바꿔준다.
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

		String path = "";

		if (result1 > 0 && result2 > 0 && result3 > 0) {
			path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "insertDomitory");
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failed", "insertDomitory");
		}
		request.getRequestDispatcher(path).forward(request, response);
	}
}
