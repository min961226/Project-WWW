package com.qs.www.welfare.controller;

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
import javax.servlet.http.Part;

import com.qs.www.approval.model.dto.ApprovalLineDTO;
import com.qs.www.approval.model.dto.ApproverDTO;
import com.qs.www.approval.model.service.ApprovalService;
import com.qs.www.common.attachment.model.service.AttachmentService;
import com.qs.www.schedule.model.dto.ApproverPerReportDTO;
import com.qs.www.schedule.model.dto.WorkingDocumentItemDTO;
import com.qs.www.schedule.model.service.ScheduleService;
import com.qs.www.welfare.model.dto.FamilyEventDTO;
import com.qs.www.welfare.model.dto.WelfareListDTO;
import com.qs.www.welfare.model.service.WelfareService;

	/*--------------------서블릿 3.0 파트 api 사용을 위한 multipartconfig 참조 선언 --------------*/
@MultipartConfig(
        location = "C:\\WWW\\Project-WWW\\web\\upload",																	//임시저장 경로
        maxFileSize = 1024*1024*10,																						//파일 허용 최대 크기
        maxRequestSize = 1024*1024*10*5,																				//파일 허용 최대 갯수
        fileSizeThreshold = 1024)																						//임시저장메모리 할당 크기
@WebServlet("/welfare/familyEvent/insert")
public class InsertFamilyEventWelfareServlet extends HttpServlet {
	
	/* ---------------------------------파일 업로드 서비스-----------------------------------------*/
	private static final String ATTACHES_REPORT = "C:\\WWW\\Project-WWW\\web\\upload\\report";							//경로지정				
	/* ---------------------------------파일 업로드 서비스-----------------------------------------*/

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String welfareTitle = "경조금 신청"; 																				// 결재 제목
		int documentNo = 8; 																							// 자기개발비 신청 문서 번호
		
		WelfareService welfareService = new WelfareService();
		FamilyEventDTO familyEventDTO = new FamilyEventDTO();
		
		/* ---------------------------------파일 업로드 서비스-----------------------------------------*/
		AttachmentService attachmentService = new AttachmentService();													//서비스 인스턴스 생성
		/* ---------------------------------파일 업로드 서비스-----------------------------------------*/

		
		int eventNo = 0; 																								// 신청목적 번호
		int supportFund = 0;																							// 지원금액
		familyEventDTO.setWreathStatus(request.getParameter("wreathStatus").charAt(0));									// 화환신청여부
		
		switch (Integer.parseInt(request.getParameter("eventType"))) {													//신청자의 목적
		case 1:
			familyEventDTO.setEventType("결혼");
			familyEventDTO.setRelation(request.getParameter("marrige"));												//결혼
			break;
		case 2:
			familyEventDTO.setEventType("출산");
			familyEventDTO.setRelation(request.getParameter("childBirth"));												//출산
			break;
		case 3:
			familyEventDTO.setEventType("회갑");
			familyEventDTO.setRelation(request.getParameter("sixtyBirth"));												//육갑
			break;
		case 4:
			familyEventDTO.setEventType("사망");
			familyEventDTO.setRelation(request.getParameter("death"));													//사망
			break;
		default:
			break;
		}
		
		eventNo = welfareService.selectEventNo(familyEventDTO);															//신청목적으로 신청목적 번호 추출
		supportFund= welfareService.selectSupportFund(eventNo);															//신청목적 번호로 지원금 추출
		
		int lineNo = Integer.parseInt(request.getParameter("lineList"));												//라인 번호
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));												//사번
		List<ApprovalLineDTO> lineList = new ApprovalService().selectApprovalLine(memberNo); 							//사번으로 라인명단  
		
		String lineName = "";					
		
		for (ApprovalLineDTO line : lineList) {
			if (line.getLineNo() == lineNo) {
				lineName = line.getLineName();
			}
		}

		WelfareListDTO welfareListDTO = new WelfareListDTO();															//결재데이터 값을 넣기 위한 dto 선언
		
		welfareListDTO.setMemberNo(request.getParameter("memberNo"));													
		welfareListDTO.setDocumentNo(documentNo);
		welfareListDTO.setReportNote(request.getParameter("eventInfo"));
		welfareListDTO.setLineName(lineName);
		welfareListDTO.setWelfareTitle(welfareTitle);
		
		int reportNo = welfareService.selectReportNum();																//결재 문서 번호					
		int result1 = welfareService.insertWelfareReport(welfareListDTO);												//결재 삽입
		
		List<String> documentItem = new ArrayList<>();																	//신청 세부 정보
		documentItem.add(welfareTitle);																					//신청 복지 제목
		documentItem.add(String.valueOf(eventNo));																		//신청 목적 번호
		documentItem.add(request.getParameter("eventName"));															//신청 목적 이름
		documentItem.add(String.valueOf(supportFund));																	//지원
		documentItem.add(request.getParameter("date"));																	//경조사 일자
		documentItem.add(request.getParameter("eventPlace"));															//경조사 장소
		documentItem.add(welfareListDTO.getReportNote());																//경조사 내용
		
		int priority = 1;																								//신청 세부 순번
		int result2 = 0;
		
		for(String item : documentItem) {																				//DTO에 담아서 한번에 보내기 위함
			WorkingDocumentItemDTO documentItemDTO = new WorkingDocumentItemDTO();
			documentItemDTO.setReportNo(reportNo);
			documentItemDTO.setDocumentNo(documentNo);
			documentItemDTO.setPriority(priority);
			documentItemDTO.setItemContent(item);	

			result2 = welfareService.insertWelfareItemContent(documentItemDTO);

			priority++;																									//DTO에 한번 값을 넣고 난 후 순번 증가
		}
		
		List<ApproverDTO> approverList = new ApprovalService().selectApprover(lineNo);									//결재자 명단
		
		int result3 = 0;
		for(ApproverDTO approver : approverList) {																		
			ApproverPerReportDTO approverPerReportDTO = new ApproverPerReportDTO();
            ScheduleService scheduleService =new ScheduleService();
            if(approver.getApproverType().equals("결재")) {																//결재문서의 상태가 최종결재인 결재일경우 승인으로 변경
                approverPerReportDTO.setReportNo(reportNo);
                approverPerReportDTO.setMemberNo(approver.getMemberNo());
                approverPerReportDTO.setPriority(approver.getPriority());

                result3 = scheduleService.applyWorkingSystemApprover(approverPerReportDTO);
            } else {																									//아닐경우 타입을 넘겨줌
                approverPerReportDTO.setReportNo(reportNo);
                approverPerReportDTO.setMemberNo(approver.getMemberNo());
                approverPerReportDTO.setApproverType(approver.getApproverType());

                result3 = scheduleService.applyWorkingSystemReferer(approverPerReportDTO);
            }
		}
		
		/*---------------------------------------------------------------------------파일 업로드---------------------------------------------------------------------*/
		response.setContentType("text/html; charset=UTF-8");																				//파일업로드를할경우 한번더 utf-8로 인코딩을해줘야함
		PrintWriter out = response.getWriter();																								//파일 생성 공간을위한 writer
        String contentType = request.getContentType();																						//파일을 구분할 수 있는 콘텐츠타입 선언
        Map<String, Object> fileMap = new HashMap<>();																						//키값으로 파일을 값을 저장하기 위한 해쉬맵선언
        int resultFileUpload = 0;
 
 
        if (contentType != null &&  contentType.toLowerCase().startsWith("multipart/")) {													//formdata를 받아오고 타입이 콘텐트 타입인경우에만 진입
            // getParts()를 통해 Body에 넘어온 데이터들을 각각의  Part로 쪼개어 리턴
            Collection<Part> parts = request.getParts();
 
            for (Part part : parts) {																										//파트로 넘어온 값들 전부 출력	
 
                if(part.getHeader("Content-Disposition").contains("filename=")) {							
                    String fileName =  extractFileName(part.getHeader("Content-Disposition"));												//fileName= 이포함된 파일의명을 갖고옴
                    
                    if(fileName.length()>0) {																								//첨부한 파일이 존재하지 않을때(파일을 미첨부시, 파일 첨부한 값이 없을때)
                    
                    int dot = fileName.lastIndexOf(".");																					//파일이름의 확장자를 구별할 수있는 인덱스 값 추출
					String ext = fileName.substring(dot);																					//그 뒤에 확장자명을 추출하기 위한 변수 선언
					String randomFileName = UUID.randomUUID().toString().replace("-", "") + ext;											//파일 이름 랜덤 부여
                    if (part.getSize() > 0) {																								// 업로드 할때 파일 크기가 0 보다 작을 수 없다.
                    	
                    	fileMap.put("reportNo", reportNo);
                    	fileMap.put("attachmentNo", 1);
                    	fileMap.put("originFileName", fileName);
                    	fileMap.put("savedFileName", randomFileName);
                    	fileMap.put("savePath", ATTACHES_REPORT);
                    	
                        part.write(ATTACHES_REPORT + File.separator  + randomFileName);														//파일 경로에 따른 파일 추가
                        part.delete();																										//임시 파일 삭제
                        
                        resultFileUpload = attachmentService.insertFileUpload(fileMap);
                    	}
                    }else {
                    	resultFileUpload = -1;
                    }
                } else {
                    String formValue =  request.getParameter(part.getName());																//파트로 찢긴값들 파일이 아닐경우 처리하는 파트
                }
            }																																//업로드 완료시
        } else {																															//전송데이터방식이 multipart formdata
        }
        
        

        /*---------------------------------------------------------------------------파일 업로드---------------------------------------------------------------------*/
		
		String path = "";

		if(resultFileUpload == -1) {																										//파일첨부를 하지 않았을때는 result값을 더해주면안된다.
			if (result1 > 0 && result2 > 0 && result3 > 0) {
				path = "/WEB-INF/views/common/success.jsp";
				request.setAttribute("successCode", "insertFamilyEvent");
			} else {
				path = "/WEB-INF/views/common/failed.jsp";
				request.setAttribute("failedCode", "insertFamilyEvent");
			}			
		}else {
			if(result1 > 0 && result2 > 0 && result3 > 0 ) {																				//파일을첨부하였고 그 파일첨부가 성공하였을경우 페이지로 이동한다.
				path = "/WEB-INF/views/common/success.jsp";
				request.setAttribute("successCode", "insertFamilyEvent");
			} else {
				path = "/WEB-INF/views/common/failed.jsp";
				request.setAttribute("failed", "insertFamilyEvent");
			}
		}
		request.getRequestDispatcher(path).forward(request, response);
	}
/*---------------------------------------------------------------------------파일 업로드---------------------------------------------------------------------*/
	
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

/*---------------------------------------------------------------------------파일 업로드---------------------------------------------------------------------*/
}
