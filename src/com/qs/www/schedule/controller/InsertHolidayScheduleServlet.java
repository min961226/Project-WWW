package com.qs.www.schedule.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
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
import com.qs.www.schedule.model.dto.HolidayTypeDTO;
import com.qs.www.schedule.model.dto.ReportDTO;
import com.qs.www.schedule.model.dto.WorkingDocumentItemDTO;
import com.qs.www.schedule.model.service.HolidayService;
import com.qs.www.schedule.model.service.ScheduleService;

/*--------------------서블릿 3.0 파트 api 사용을 위한 multipartconfig 참조 선언 --------------*/
@MultipartConfig(
        location = "C:\\WWW\\Project-WWW\\web\\upload",								//임시저장 경로
        maxFileSize = 1024*1024*10,													//파일 허용 최대 크기
        maxRequestSize = 1024*1024*10*5,											//파일 허용 최대 갯수
        fileSizeThreshold = 1024)
@WebServlet("/schedule/holiday/insert")
public class InsertHolidayScheduleServlet extends HttpServlet { 
	
	//파일 저장공간
	private static final String ATTACHES_REPORT = "C:\\WWW\\Project-WWW\\web\\upload\\report";							//경로지정				

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/* 1. 생성휴가, 사용휴가, 잔여휴가 일수 가져오기 */


		/* 2. 로그인중인 유저가 생성자인 결재라인 select */
		HttpSession session = request.getSession();		
		int no = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
		List<ApprovalLineDTO> lineList = new ApprovalService().selectApprovalLine(no);
		request.setAttribute("lineList", lineList);
		session.setAttribute("lineList", lineList);


		/* 3. 휴가종류를 모두 select */
		List<HolidayTypeDTO> holidayTypeList = new HolidayService().selectAllHolidayType();
		request.setAttribute("holidayTypeList", holidayTypeList);
		session.setAttribute("holidayTypeList", holidayTypeList);

		/* 4. 휴가신청페이지로 포워드 */
		String path = "/WEB-INF/views/schedule/insertHoliday.jsp";		
		request.getRequestDispatcher(path).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("휴가신청 서블렛으로 이동");
		
		AttachmentService attachmentService = new AttachmentService();		//--파일업로드

		HttpSession session = request.getSession();

		String memberName = ((MemberInfoDTO) session.getAttribute("memberInfo")).getName();
		int memberNo = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
		int documentNo = 6;
		String changeReason = request.getParameter("reason");
		System.out.println(changeReason);

		String startDayAllday = request.getParameter("startDayAllday"); 			//종일, 오전, 오후
		String endDayAllday = request.getParameter("endDayAllday");					//종일, 오전, 오후
		System.out.println(startDayAllday);
		System.out.println(endDayAllday);

		String startDayStr = request.getParameter("startDay");
		String endDayStr = request.getParameter("endDay");
		System.out.println(startDayStr);
		System.out.println(endDayStr);
		
		java.sql.Date startDay = java.sql.Date.valueOf(startDayStr);
		java.sql.Date endDay = java.sql.Date.valueOf(endDayStr);
		long longStartDay = startDay.getTime();
		long longendDay = endDay.getTime();

		//기간일수 계산... 일단 '종일'인 걸로 상정하고 계산.
		//기간일수 어떻게 할지 고민해봐야 함
		long during = longendDay - longStartDay;
		long duringDate = during / (24 * 60 * 60 * 1000) + 1;
		String duringDateString = duringDate + "";
		System.out.println(startDay + " 와 " + endDay + "의 기간일수 : " + duringDate);

		//session에서 가져온 결재라인들 중, 받아온 lineNo와 일치하는 DTO의 lineName을 따온다. 
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
		
		String startDayString = request.getParameter("startDay");
		String endDayString = request.getParameter("endDay");

		String title = memberName + " " + startDayString + " ~ " + endDayString + " " + holidayType + " 휴가신청서";

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
	
		

		/* 성공여부에 따라 success 혹은 fail로 넘겨줌 */
		String path = "";
		if(resultFileUpload == -1) {
			if(result1 > 0 && result3 > 0) {
				path = "/WEB-INF/views/common/success.jsp";
				request.setAttribute("successCode", "insertHoliday");
	
			} else {
				path = "/WEB-INF/views/common/failed.jsp";
				request.setAttribute("failedCode", "insertHoliday");
			}
		}else {
			if(result1 > 0 && result3 > 0 && resultFileUpload > 0) {
				path = "/WEB-INF/views/common/success.jsp";
				request.setAttribute("successCode", "insertHoliday");
	
			} else {
				path = "/WEB-INF/views/common/failed.jsp";
				request.setAttribute("failedCode", "insertHoliday");
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



}
