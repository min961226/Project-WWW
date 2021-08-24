package com.qs.www.approval.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.qs.www.approval.model.dto.ApprovalLineDTO;
import com.qs.www.approval.model.dto.ApproverDTO;
import com.qs.www.approval.model.service.ApprovalService;
import com.qs.www.common.attachment.model.service.AttachmentService;
import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.schedule.model.dto.ApproverPerReportDTO;
import com.qs.www.schedule.model.dto.ReportDTO;
import com.qs.www.schedule.model.dto.WorkingDocumentItemDTO;
import com.qs.www.schedule.model.service.ScheduleService;
/*--------------------서블릿 3.0 파트 api 사용을 위한 multipartconfig 참조 선언 --------------*/
@MultipartConfig(
		location = "C:\\WWW\\Project-WWW\\web\\upload",								                                      //임시저장 경로
		maxFileSize = 1024*1024*10,													                                      //파일 허용 최대 크기
		maxRequestSize = 1024*1024*10*5,											                                      //파일 허용 최대 갯수
		fileSizeThreshold = 1024)													                                      //임시저장메모리 할당 크기
@WebServlet("/approval/insert")
public class InsertApprovalServlet extends HttpServlet {

	private static final String ATTACHES_REPORT = "C:\\WWW\\Project-WWW\\web\\upload\\report";							  //파일 업로드경로지정				

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		int no = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();                                      //로그인중인 유저가 생성자인 결재라인 가져오기

		List<ApprovalLineDTO> lineList = new ApprovalService().selectApprovalLine(no);
		
		//현재날짜 + 5년뒤로 보존날짜 설정
		Date now  = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String str = format.format(now);

		String[] arrayDate = str.split("-");   
		int yearPlusFive = Integer.parseInt(arrayDate[0]) + 5;
		String endDate = yearPlusFive + "-" + arrayDate[1]  + "-" + arrayDate[2];

		request.setAttribute("endDate", endDate);
		request.setAttribute("lineList", lineList);
		session.setAttribute("lineList", lineList);
		request.getRequestDispatcher("/WEB-INF/views/approval/insertApproval.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		AttachmentService attachmentService = new AttachmentService();													   //파일업로드 서비스 인스턴스 생성

		HttpSession session = request.getSession();

		int lineNo = Integer.parseInt(request.getParameter("line"));

		int documentNo = Integer.parseInt(request.getParameter("documentNo"));
		int memberNo = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
		String note = request.getParameter("note");
		
		/* 신청창에서 선택한 라인 번호를 통해 라인목록에서 해당하는 라인면 가져오기 */
		List<ApprovalLineDTO> lineList = (List<ApprovalLineDTO>) session.getAttribute("lineList");

		String lineName = "";
		for(ApprovalLineDTO line: lineList) {
			if(line.getLineNo() == lineNo) {
				lineName = line.getLineName();
			}
		}
		
		String title = request.getParameter("title");
		int reportNo = new ApprovalService().selectReportNum();                                                             //현재 등록할 차례의 결재번호 받아오기

		ReportDTO report = new ReportDTO();
		report.setDocumentNo(documentNo);
		report.setMemberNo(memberNo);
		report.setReportNote(note);
		report.setLineName(lineName);
		report.setReportTitle(title);


		/* REPORT(상신) 추가 */
		ScheduleService scheduleService = new ScheduleService();	
		int result1 = scheduleService.applyWorkingSystem(report);


		String body = request.getParameter("body");

		List<String> documentItem = new ArrayList<>();
		documentItem.add(title);
		documentItem.add(body);

		if(documentNo == 3) {
			String contractDate = request.getParameter("contractDate");
			String payDate = request.getParameter("payDate");
			String productNo = request.getParameter("productNo");

			documentItem.add(contractDate);
			documentItem.add(payDate);
			documentItem.add(productNo);		
		}

		int priority = 1;
		int result2 = 0;
		/* 상신별 항목작성내용 등록 */
		for(String item : documentItem) {
			WorkingDocumentItemDTO documentItemDTO = new WorkingDocumentItemDTO();
			documentItemDTO.setReportNo(reportNo);
			documentItemDTO.setDocumentNo(documentNo);
			documentItemDTO.setPriority(priority);
			documentItemDTO.setItemContent(item);	

			result2 = scheduleService.applyWorkingSystemItemContent(documentItemDTO);

			priority++;
		}




		/* 선택한 결재 라인에 등록되있는 결재자들 가져오기 */
		List<ApproverDTO> approverList = new ApprovalService().selectApprover(lineNo);

		/* 상신별 결재자 등록 */
		int result3 = 0;
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

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String contentType = request.getContentType();
		Map<String, Object> fileMap = new HashMap<>();
		int resultFileUpload = 0;


		if (contentType != null &&  contentType.toLowerCase().startsWith("multipart/")) {													     //formdata를 받아오고 타입이 콘텐트 ㅇ타입인경우에만 진입
			// getParts()를 통해 Body에 넘어온 데이터들을 각각의  Part로 쪼개어 리턴
			Collection<Part> parts = request.getParts();

			for (Part part : parts) {
				System.out.printf("파라미터 명 : %s, contentType :  %s,  size : %d bytes \n", part.getName(),									     //파트로 넘어온 값들 전부 출력
						part.getContentType(), part.getSize());


				if  (part.getHeader("Content-Disposition").contains("filename=")) {							
					String fileName =  extractFileName(part.getHeader("Content-Disposition"));

					if(fileName.length()>0) {																								     //첨부한 파일이 존재하지 않을때(파일을 미첨부시, 파일 첨부한 값이 없을때)

						int dot = fileName.lastIndexOf(".");
						String ext = fileName.substring(dot);
						String randomFileName = UUID.randomUUID().toString().replace("-", "") + ext;											//파일 이름 랜덤 부여
						
						if (part.getSize() > 0) {																								// 업로드 할때 파일 크기가 0 보다 작을 수 없다.
							System.out.println(part.getHeaderNames());

							fileMap.put("reportNo", reportNo);
							fileMap.put("attachmentNo", 1);
							fileMap.put("originFileName", fileName);
							fileMap.put("savedFileName", randomFileName);
							fileMap.put("savePath", ATTACHES_REPORT);
							
							part.write(ATTACHES_REPORT + File.separator  + randomFileName);													     //파일 경로에 따른 파일 추가
							part.delete();																										 //임시 파일 삭제
							
							resultFileUpload = attachmentService.insertFileUpload(fileMap);
						}
					}else {
						resultFileUpload = -1;
					}
				} else {
					String formValue =  request.getParameter(part.getName());																     //파트로 찢긴값들 파일이 아닐경우 처리하는 파트
				}
			}
		} 


		String path = "";
		if(resultFileUpload == -1) {
			if(result1 > 0 && result2 > 0 && result3 > 0 ) {
				path = "/WEB-INF/views/common/success.jsp";
				request.setAttribute("successCode", "insertApproval");

			} else {
				path = "/WEB-INF/views/common/failed.jsp";
				request.setAttribute("failedCode", "insertApproval");
			}
		}else {
			if(result1 > 0 && result2 > 0 && result3 > 0  && resultFileUpload > 0) {
				path = "/WEB-INF/views/common/success.jsp";
				request.setAttribute("successCode", "insertApproval");

			} else {
				path = "/WEB-INF/views/common/failed.jsp";
				request.setAttribute("failedCode", "insertApproval");
			}
		}


		request.getRequestDispatcher(path).forward(request, response);
	}

	private String extractFileName(String partHeader) {
		for (String cd : partHeader.split(";")) {
			if (cd.trim().startsWith("filename")) {
				String fileName = cd.substring(cd.indexOf("=") +  1).trim().replace("\"", "");
				int index = fileName.lastIndexOf(File.separator);
				return fileName.substring(index + 1);
			}
		}
		return null;
	}

}
