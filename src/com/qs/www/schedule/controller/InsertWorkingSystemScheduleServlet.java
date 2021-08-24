package com.qs.www.schedule.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
import com.qs.www.schedule.model.dto.MemberWorkLogDTO;
import com.qs.www.schedule.model.dto.ReportDTO;
import com.qs.www.schedule.model.dto.StandardWorkDTO;
import com.qs.www.schedule.model.dto.WorkingDocumentItemDTO;
import com.qs.www.schedule.model.service.ScheduleService;

/*--------------------서블릿 3.0 파트 api 사용을 위한 multipartconfig 참조 선언 --------------*/
@MultipartConfig(
        location = "C:\\WWW\\Project-WWW\\web\\upload",																	//임시저장 경로
        maxFileSize = 1024*1024*10,																						//파일 허용 최대 크기
        maxRequestSize = 1024*1024*10*5,																				//파일 허용 최대 갯수
        fileSizeThreshold = 1024)
@WebServlet("/schedule/workingSystem/insert")
public class InsertWorkingSystemScheduleServlet extends HttpServlet { 
	
	private static final String ATTACHES_REPORT = "C:\\WWW\\Project-WWW\\web\\upload\\report";							//파일저장 경로지정				

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* 로그인중인 유저가 생성자인 결재라인 가져오기 */
		HttpSession session = request.getSession();		
		int no = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
		
		List<ApprovalLineDTO> lineList = new ApprovalService().selectApprovalLine(no);
		request.setAttribute("lineList", lineList);
		session.setAttribute("lineList", lineList);
		
		/* 근무제목록 가져오기 */
		List<StandardWorkDTO> workTypeList = new ScheduleService().selectAllWorkType();
		request.setAttribute("workTypeList", workTypeList);
		session.setAttribute("workTypeList", workTypeList);
		
		String path = "/WEB-INF/views/schedule/insertApplyWorkingSystem.jsp";
		request.getRequestDispatcher(path).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		AttachmentService attachmentService = new AttachmentService();								//파일업로드 용
		
		HttpSession session = request.getSession();
		int lineNo = Integer.parseInt(request.getParameter("line"));
		
		/* session에서 가져온 결재라인들 중, 받아온 lineNo와 일치하는 DTO의 lineName을 따온다 */ 
		List<ApprovalLineDTO> lineList = (List<ApprovalLineDTO>) session.getAttribute("lineList");
        String lineName = "";
        for(ApprovalLineDTO line: lineList) {
            if(line.getLineNo() == lineNo) {
                lineName = line.getLineName();
            }
        }
        			
		int workNo = Integer.parseInt(request.getParameter("workNo"));								//근무제번호
		int approverLine = Integer.parseInt(request.getParameter("line"));							//결재라인번호
		String changeReason = request.getParameter("changeReason");									//신청사유
		String memberName = ((MemberInfoDTO) session.getAttribute("memberInfo")).getName();			//신청자이름
		
		/* workNo로 workType을 정한다 */
		String workType = ""; 			
		if(workNo == 6) {																			//6이면 커스텀
			workType = "커스텀";
		} else if(workNo == 7){																		//7이면 초과
			workType = "초과";
		} else {																					//그 이외는 표준근
			workType = "표준";
		}
		
		String title = memberName + " " + workType + " 근무 신청서";
		
		/* documentNo로 workNo를 정한다 */
		int documentNo = 0;
		if(workNo == 7) {
			documentNo = 5;																			//초과근무신청서의 문서번호는 5번
		} else {
			documentNo = 4;																			//통상근무신청서(커스텀,표준)의 문서번호는 4번
		}				
		
		
		/* 1-1. 상신올릴 문서가 쓸 ReportNo 가져오기 */
		ApprovalService approvalService = new ApprovalService();
		int reportNo = approvalService.selectReportNum();
		
		/* 1-2. 상신테이블(TBL_REPORT)에 insert */
		ReportDTO reportDTO = new ReportDTO();
		int memberNo = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
		reportDTO.setMemberNo(memberNo);
		reportDTO.setDocumentNo(documentNo);
		reportDTO.setReportNote(changeReason);
		reportDTO.setLineName(lineName);
		reportDTO.setReportTitle(title);

		ScheduleService scheduleService = new ScheduleService();		
		int result1 = scheduleService.applyWorkingSystem(reportDTO);
		
		
		/* 2. 상신별문서항목작성내용(TBL_ITEM_CONTENT)에 insert */
		String getworkNo = request.getParameter("workNo");							

		/* 2-1. 초과근무신청과 통상근무신청(표준, 커스텀) 넣는 내용이 다르다 */
		int result2 = 0;
		int priority = 1;
		if(workType == "초과") {
			int overtimeStartHour = Integer.parseInt(request.getParameter("overtimeStartHour"));
			int overtimeEndHour = Integer.parseInt(request.getParameter("overtimeEndHour"));
			
			/* 초과근무 시수를 계산한다 */
			int overtimeduring = overtimeEndHour - overtimeStartHour;
			String overtimeduringStr = overtimeduring + "";
			
			List<String> workingDocumentItem = new ArrayList<>();
			workingDocumentItem.add(title);																//제목
			workingDocumentItem.add(request.getParameter("startDay"));									//시작일시
			workingDocumentItem.add(request.getParameter("endDay"));									//종료일시
			workingDocumentItem.add(overtimeduringStr);													//초과근무 기간시수
			workingDocumentItem.add(changeReason);														//사유
			workingDocumentItem.add(overtimeStartHour + "");											//시작시간
			workingDocumentItem.add(overtimeEndHour + "");												//종료시간
			
			for(String item : workingDocumentItem) {
				WorkingDocumentItemDTO workingDocumentItemDTO = new WorkingDocumentItemDTO();
				workingDocumentItemDTO.setReportNo(reportNo);
				workingDocumentItemDTO.setDocumentNo(documentNo);
				workingDocumentItemDTO.setPriority(priority);
				workingDocumentItemDTO.setItemContent(item);			
				result2 = scheduleService.applyWorkingSystemItemContent(workingDocumentItemDTO);

				priority++;
			}
		
		} else if (workType == "커스텀") {
			List<String> workingDocumentItem = new ArrayList<>();
			workingDocumentItem.add(title);																//제목
			workingDocumentItem.add(getworkNo);															//근무제유형코드
			workingDocumentItem.add(request.getParameter("startDay"));									//시작일
			workingDocumentItem.add(request.getParameter("endDay"));									//종료일
			workingDocumentItem.add(changeReason);														//사유
			workingDocumentItem.add(workType);															//근무제유형
			
			String monStartTime = request.getParameter("monStartTimeHour") + ":" + request.getParameter("monStartTimeMin");
			String monEndTime = request.getParameter("monEndTimeHour") + ":" + request.getParameter("monEndTimeMin");
			String tueStartTime = request.getParameter("tueStartTimeHour") + ":" + request.getParameter("tueStartTimeMin");
			String tueEndTime = request.getParameter("tueEndTimeHour") + ":" + request.getParameter("tueEndTimeMin");
			String wedStartTime = request.getParameter("wedStartTimeHour") + ":" + request.getParameter("wedStartTimeMin");
			String wedEndTime = request.getParameter("wedEndTimeHour") + ":" + request.getParameter("wedEndTimeMin");
			String thuStartTime = request.getParameter("thuStartTimeHour") + ":" + request.getParameter("thuStartTimeMin");
			String thuEndTime = request.getParameter("thuEndTimeHour") + ":" + request.getParameter("thuEndTimeMin");
			String friStartTime = request.getParameter("friStartTimeHour") + ":" + request.getParameter("friStartTimeMin");
			String friEndTime = request.getParameter("friEndTimeHour") + ":" + request.getParameter("friEndTimeMin");
			workingDocumentItem.add(monStartTime);
			workingDocumentItem.add(monEndTime);
			workingDocumentItem.add(tueStartTime);
			workingDocumentItem.add(tueEndTime);
			workingDocumentItem.add(wedStartTime);
			workingDocumentItem.add(wedEndTime);
			workingDocumentItem.add(thuStartTime);
			workingDocumentItem.add(thuEndTime);
			workingDocumentItem.add(friStartTime);
			workingDocumentItem.add(friEndTime);
			
			for(String item : workingDocumentItem) {
				WorkingDocumentItemDTO workingDocumentItemDTO = new WorkingDocumentItemDTO();
				workingDocumentItemDTO.setReportNo(reportNo);
				workingDocumentItemDTO.setDocumentNo(documentNo);
				workingDocumentItemDTO.setPriority(priority);
				workingDocumentItemDTO.setItemContent(item);			
				result2 = scheduleService.applyWorkingSystemItemContent(workingDocumentItemDTO);

				priority++;
			}
			
		/* '초과'도 '커스텀'도 아닌경우는 '표준'근무제 신청의 경우이다 */
		} else {
			List<String> workingDocumentItem = new ArrayList<>();
			workingDocumentItem.add(title);								                                                   //제목
			workingDocumentItem.add(getworkNo);							                                                   //근무제유형코드
			workingDocumentItem.add(request.getParameter("startDay"));	                                                   //시작일
			workingDocumentItem.add(request.getParameter("endDay"));	                                                   //종료일
			workingDocumentItem.add(changeReason);																		   //사유
			workingDocumentItem.add(workType);																			   //근무제유형

			for(String item : workingDocumentItem) {
				WorkingDocumentItemDTO workingDocumentItemDTO = new WorkingDocumentItemDTO();
				workingDocumentItemDTO.setReportNo(reportNo);
				workingDocumentItemDTO.setDocumentNo(documentNo);
				workingDocumentItemDTO.setPriority(priority);
				workingDocumentItemDTO.setItemContent(item);			
				result2 = scheduleService.applyWorkingSystemItemContent(workingDocumentItemDTO);

				priority++;
			}		
		}
		

		/* 3-1. 결재라인 선택한 번호로, 결재자들의 결재자사번과 우선순위를 DTO로 받아오기 */
		List<ApproverDTO> approverList = new ApprovalService().selectApprover(lineNo);
		
		/* 3-2. 상신별결재자(TBL_APPROVER_PER_REPORT)에 insert */
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
		
		/* 파일 업로드 */
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
        String contentType = request.getContentType();
        Map<String, Object> fileMap = new HashMap<>();
        int resultFileUpload = 0;
 
        /* formdata를 받아오고 타입이 콘텐트 타입인경우에만 진입
         * enctype이 multipart/form-data가  아닌 경우에는 들어올 수 없다. 
         * */
        if (contentType != null &&  contentType.toLowerCase().startsWith("multipart/")) {
        	
            /* getParts()를 통해 Body에 넘어온 데이터들을 각각의  Part로 쪼개어 리턴 
             * 파라미터명, contentType, size, bytes, Name, ContentType, size를 넘겨받았다 */
            Collection<Part> parts = request.getParts();
 
            for (Part part : parts) {
 
                if  (part.getHeader("Content-Disposition").contains("filename=")) {							
                    String fileName =  extractFileName(part.getHeader("Content-Disposition"));
                    
                    /* 첨부한 파일이 존재하지 않을때(파일을 미첨부시, 파일 첨부한 값이 없을때) */
                    if(fileName.length() > 0) {
                    
	                    int dot = fileName.lastIndexOf(".");
						String ext = fileName.substring(dot);
						String randomFileName = UUID.randomUUID().toString().replace("-", "") + ext;											//파일 이름 랜덤 부여
						
						/* 업로드 할때 파일 크기가 0 보다 작을 수 없다. */
	                    if (part.getSize() > 0) {																								
	                    	fileMap.put("reportNo", reportNo);
	                    	fileMap.put("attachmentNo", 1);
	                    	fileMap.put("originFileName", fileName);
	                    	fileMap.put("savedFileName", randomFileName);
	                    	fileMap.put("savePath", ATTACHES_REPORT);
	                    	
	                        part.write(ATTACHES_REPORT + File.separator  + randomFileName);														//파일 경로에 따른 파일 추가
	                        part.delete();																										//임시 파일 삭제
	                        
	                        resultFileUpload = attachmentService.insertFileUpload(fileMap);
	                    }
                    
                    } else {
                    	resultFileUpload = -1;
                    }
                    
                /* 파트로 찢긴값들 파일이 아닐경우 처리하는 파트 */
                } else {
                    String formValue =  request.getParameter(part.getName());
                }
                
            }
            
        } 

		/* 성공여부에 따라 success 혹은 fail로 넘겨줌 (+파일업로드도 포함) */
		String path = "";
		if(resultFileUpload == -1) {
			
			if(result1 > 0 && result2 > 0 && result3 > 0) {
				path = "/WEB-INF/views/common/success.jsp";
				request.setAttribute("successCode", "insertWork");
			} else {
				path = "/WEB-INF/views/common/failed.jsp";
				request.setAttribute("failedCode", "insertWork");
			}
			
		} else {
			
			if(result1 > 0 && result2 > 0 && result3 > 0 && resultFileUpload > 0) {
				path = "/WEB-INF/views/common/success.jsp";
				request.setAttribute("successCode", "insertWork");
			} else {
				path = "/WEB-INF/views/common/failed.jsp";
				request.setAttribute("failedCode", "insertWork");
			}
		}

		request.getRequestDispatcher(path).forward(request, response);

	}
	
	
	/* 파일 업로드용 메소드 */
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
	

