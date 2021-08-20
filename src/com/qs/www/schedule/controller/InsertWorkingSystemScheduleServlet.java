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
        location = "C:\\WWW\\Project-WWW\\web\\upload",								//임시저장 경로
        maxFileSize = 1024*1024*10,													//파일 허용 최대 크기
        maxRequestSize = 1024*1024*10*5,											//파일 허용 최대 갯수
        fileSizeThreshold = 1024)
@WebServlet("/schedule/workingSystem/insert")
public class InsertWorkingSystemScheduleServlet extends HttpServlet {
	
	//파일 저장공간
	private static final String ATTACHES_REPORT = "C:\\WWW\\Project-WWW\\web\\upload\\report";							//경로지정				

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("근무 신청");
		
		//로그인중인 유저가 생성자인 결재라인 가져오기
		HttpSession session = request.getSession();		
		int no = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
		
		List<ApprovalLineDTO> lineList = new ApprovalService().selectApprovalLine(no);
		request.setAttribute("lineList", lineList);
		session.setAttribute("lineList", lineList);
		
		//근무제목록 가져오기
		List<StandardWorkDTO> workTypeList = new ScheduleService().selectAllWorkType();
		System.out.println("workTypeList 목록 : " + workTypeList);
		
		request.setAttribute("workTypeList", workTypeList);
		session.setAttribute("workTypeList", workTypeList);
		
		String path = "/WEB-INF/views/schedule/insertApplyWorkingSystem.jsp";

		request.getRequestDispatcher(path).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("근무신청 서블렛으로 이동");
		
		AttachmentService attachmentService = new AttachmentService();		//--파일업로드
		
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
		if(workNo == 6) {				//workNo가 6이면 커스텀이다
			workType = "커스텀";
		} else if(workNo == 7){			//workNo가 7이면 초과근무이다
			workType = "초과";
		} else {						//그 이외는 표준근무제이다
			workType = "표준";
		}
		String title = memberName + " " + workType + "근무 신청서";		//title 미리 만들어둔다.
		
		int documentNo = 0;
		if(workNo == 7) {
			documentNo = 5;				//초과근무신청서의 문서번호는 5번이다.
		} else {
			documentNo = 4;				//통상근무신청서(커스텀,표준)의 문서번호는 4번이다.
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
		
		
		/* 2. 상신별문서항목작성내용(TBL_ITEM_CONTENT)에 insert */
		String getworkNo = request.getParameter("workNo"); //위에도 workNo가 있긴한데 String이 필요해서 다시 받아줬다.

		//초과근무신청과 통상근무신청(표준, 커스텀) 넣는 내용이 다르다
		int result2 = 0;
		int priority = 1;
		if(workType == "초과") {
			int overtimeStartHour = Integer.parseInt(request.getParameter("overtimeStartHour"));
			int overtimeEndHour = Integer.parseInt(request.getParameter("overtimeEndHour"));
			
			//날짜가 다르면... overtimeEndHour에 24를 더해줘서 계산...
			int overtimeduring = overtimeEndHour - overtimeStartHour;
			String overtimeduringStr = overtimeduring + "";
			
			List<String> workingDocumentItem = new ArrayList<>();
			workingDocumentItem.add(title);								//제목
			workingDocumentItem.add(request.getParameter("startDay"));	//시작일시
			workingDocumentItem.add(request.getParameter("endDay"));	//종료일시
			workingDocumentItem.add(overtimeduringStr);					//초과근무 기간시수
			workingDocumentItem.add(changeReason);						//사유
			workingDocumentItem.add(overtimeStartHour + "");			//시작시간
			workingDocumentItem.add(overtimeEndHour + "");				//종료시간
			System.out.println("초과 content : " + workingDocumentItem);
			
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
			workingDocumentItem.add(title);								//제목
			workingDocumentItem.add(getworkNo);							//근무제유형코드
			workingDocumentItem.add(request.getParameter("startDay"));	//시작일
			workingDocumentItem.add(request.getParameter("endDay"));	//종료일
			workingDocumentItem.add(changeReason);						//사유
			workingDocumentItem.add(workType);							//근무제유형
			
			String monStartTime = request.getParameter("monStartTimeHour") + ":" + request.getParameter("monStartTimeMin");
			String monEndTime = request.getParameter("monEndTimeHour") + ":" + request.getParameter("monEndTimeMin");
			String tueStartTime = request.getParameter("tueStartTimeHour") + ":" + request.getParameter("tueStartTimeMin");
			String tueEndTime = request.getParameter("tueEndTimeHour") + ":" + request.getParameter("tueEndTimeMin");
			String wedStartTime = request.getParameter("wedStartTimeHour") + ":" + request.getParameter("wedStartTimeMin");
			String wedEndTime = request.getParameter("wedEndTimeHour") + ":" + request.getParameter("wedEndTimeMin");
			String thuStartTime = request.getParameter("thuStartTimeHour") + ":" + request.getParameter("thuStartTimeMin");
			String thuEndTime = request.getParameter("monEndTimeHour") + ":" + request.getParameter("thuEndTimeMin");
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
			System.out.println("커스텀 content : " + workingDocumentItem);
			
			for(String item : workingDocumentItem) {
				WorkingDocumentItemDTO workingDocumentItemDTO = new WorkingDocumentItemDTO();
				workingDocumentItemDTO.setReportNo(reportNo);
				workingDocumentItemDTO.setDocumentNo(documentNo);
				workingDocumentItemDTO.setPriority(priority);
				workingDocumentItemDTO.setItemContent(item);			
				result2 = scheduleService.applyWorkingSystemItemContent(workingDocumentItemDTO);

				priority++;
			}
			
		} else {
			List<String> workingDocumentItem = new ArrayList<>();
			workingDocumentItem.add(title);								//제목
			workingDocumentItem.add(getworkNo);							//근무제유형코드
			workingDocumentItem.add(request.getParameter("startDay"));	//시작일
			workingDocumentItem.add(request.getParameter("endDay"));	//종료일
			workingDocumentItem.add(changeReason);						//사유
			workingDocumentItem.add(workType);							//근무제유형
			System.out.println("표준 content : " + workingDocumentItem);

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
		System.out.println(result2);		
		

		/* 3-1. 결재라인 선택한 번호로, 결재자들의 결재자사번과 우선순위를 DTO로 받아오기 */
		//선택한 결재 라인에 등록되있는 결재자들 가져오기				
		List<ApproverDTO> approverList = new ApprovalService().selectApprover(lineNo);
		System.out.println(approverList);
		
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
		
		/*---------------------------------------------------------------------------파일 업로드---------------------------------------------------------------------*/
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
        String contentType = request.getContentType();
        Map<String, Object> fileMap = new HashMap<>();
        int resultFileUpload = 0;
 
 
        if (contentType != null &&  contentType.toLowerCase().startsWith("multipart/")) {													//formdata를 받아오고 타입이 콘텐트 ㅇ타입인경우에만 진입
            // getParts()를 통해 Body에 넘어온 데이터들을 각각의  Part로 쪼개어 리턴
            Collection<Part> parts = request.getParts();
 
            for (Part part : parts) {
                System.out.printf("파라미터 명 : %s, contentType :  %s,  size : %d bytes \n", part.getName(),									//파트로 넘어온 값들 전부 출력
                        part.getContentType(), part.getSize());
 
 
                if  (part.getHeader("Content-Disposition").contains("filename=")) {							
                    String fileName =  extractFileName(part.getHeader("Content-Disposition"));
                    
                    if(fileName.length()>0) {																								//첨부한 파일이 존재하지 않을때(파일을 미첨부시, 파일 첨부한 값이 없을때)
                    System.out.println("fileName : "+fileName);
                    System.out.println(part.getHeader("Content-Disposition"));
                    
                    int dot = fileName.lastIndexOf(".");
					String ext = fileName.substring(dot);
					String randomFileName = UUID.randomUUID().toString().replace("-", "") + ext;											//파일 이름 랜덤 부여
					System.out.println(randomFileName);			
                    if (part.getSize() > 0) {																								// 업로드 할때 파일 크기가 0 보다 작을 수 없다.
                    	System.out.println(part.getHeaderNames());
                    	
                    	fileMap.put("reportNo", reportNo);
                    	fileMap.put("attachmentNo", 1);
                    	fileMap.put("originFileName", fileName);
                    	fileMap.put("savedFileName", randomFileName);
                    	fileMap.put("savePath", ATTACHES_REPORT);
                    	
                    	System.out.printf("업로드 파일 명 : %s  \n", randomFileName);
                    	System.out.println(ATTACHES_REPORT + File.separator  + fileName);
                        
                        part.write(ATTACHES_REPORT + File.separator  + randomFileName);													//파일 경로에 따른 파일 추가
                        part.delete();																										//임시 파일 삭제
                        
                        
                        System.out.println("map:" + fileMap);
                        System.out.println(resultFileUpload);
                        resultFileUpload = attachmentService.insertFileUpload(fileMap);
                    }
                    }else {
                    	resultFileUpload = -1;
                    }
                } else {
                    String formValue =  request.getParameter(part.getName());																//파트로 찢긴값들 파일이 아닐경우 처리하는 파트
                    System.out.printf("name : %s, value : %s  \n", part.getName(), formValue);
                }
            }
            System.out.println("<h1>업로드 완료</h1>");
        } else {
            System.out.println("<h1>enctype이 multipart/form-data가  아님</h1>");
        }

        /*---------------------------------------------------------------------------파일 업로드---------------------------------------------------------------------*/
	
		

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
	
	
/*------------------파일 업로드용 메소드----------------*/
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
/*------------------파일 업로드용 메소드----------------*/

	
	
	
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
	

