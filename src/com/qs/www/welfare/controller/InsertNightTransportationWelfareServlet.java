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

/* 서블릿 3.0 파트 api 사용을 위한 multipartconfig 참조 선언 */
@MultipartConfig(
        location = "C:\\WWW\\Project-WWW\\web\\upload",								//임시저장 경로
        maxFileSize = 1024*1024*10,													//파일 허용 최대 크기
        maxRequestSize = 1024*1024*10*5,											//파일 허용 최대 갯수
        fileSizeThreshold = 1024)													//임시저장메모리 할당 크기
@WebServlet("/welfare/nightTransportation/insert")
public class InsertNightTransportationWelfareServlet extends HttpServlet {

	private static final String ATTACHES_REPORT = "C:\\WWW\\Project-WWW\\web\\upload\\report";							//경로지정				
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		WelfareService welfareService = new WelfareService();
		ApprovalService approvalService = new ApprovalService();
		AttachmentService attachmentService = new AttachmentService();													
		String welfareTitle = "야간 교통비 신청"; 																			// 결재 제목
		int documentNo = 7; 																							// 야간 교통비 신청 문서 번호
		
		String overTime = request.getParameter("overTimeLog");															//야근 기록
		int transBill = Integer.parseInt(request.getParameter("transBill"));											//교통비
		String overTimeLogInfo = request.getParameter("overTimeLogInfo");												//근무 내역
		
		int lineNo = Integer.parseInt(request.getParameter("lineList"));
		List<ApprovalLineDTO> lineList = new ApprovalService().selectApprovalLine(Integer.parseInt(request.getParameter("memberNo")));	//해당하는 결재라인 리스트
		
		String lineName = "";
		
		for (ApprovalLineDTO line : lineList) {
			if (line.getLineNo() == lineNo) {
				lineName = line.getLineName();
			}
		}
		
		WelfareListDTO welfareListDTO = new WelfareListDTO();
		
		welfareListDTO.setMemberNo(request.getParameter("memberNo"));
		welfareListDTO.setDocumentNo(documentNo);
		welfareListDTO.setReportNote(overTimeLogInfo);
		welfareListDTO.setLineName(lineName);
		welfareListDTO.setWelfareTitle(welfareTitle);
		
		int reportNo = welfareService.selectReportNum();
		int result1 = welfareService.insertWelfareReport(welfareListDTO);
		
		List<String> documentItem = new ArrayList<>();
		documentItem.add(welfareTitle);
		documentItem.add(overTime);
		documentItem.add(String.valueOf(transBill));
		documentItem.add(overTimeLogInfo);
		
		int priority = 1;
		int result2 = 0;
		
		for (String item : documentItem) {
			WorkingDocumentItemDTO documentItemDTO = new WorkingDocumentItemDTO();
			documentItemDTO.setReportNo(reportNo);
			documentItemDTO.setDocumentNo(documentNo);
			documentItemDTO.setPriority(priority);
			documentItemDTO.setItemContent(item);
			
			result2 = welfareService.insertWelfareItemContent(documentItemDTO);
			
			priority++;
		}
		
		List<ApproverDTO> approverList = approvalService.selectApprover(lineNo);
		
		int result3 = 0;
		for (ApproverDTO approver : approverList) {
			ApproverPerReportDTO approverPerReportDTO = new ApproverPerReportDTO();
			ScheduleService scheduleService = new ScheduleService();
			if (approver.getApproverType().equals("결재")) {
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
		
		response.setContentType("text/html; charset=UTF-8");																				//UTF-8로 한번 더 인코딩 해주어야함
		PrintWriter out = response.getWriter();
        String contentType = request.getContentType();
        Map<String, Object> fileMap = new HashMap<>();
        int resultFileUpload = 0;
 
 
        if (contentType != null &&  contentType.toLowerCase().startsWith("multipart/")) {													//formdata를 받아오고 타입이 콘텐트 ㅇ타입인경우에만 진입
            // getParts()를 통해 Body에 넘어온 데이터들을 각각의  Part로 쪼개어 리턴
            Collection<Part> parts = request.getParts();
 
            for (Part part : parts) {
 
 
                if  (part.getHeader("Content-Disposition").contains("filename=")) {							
                    String fileName =  extractFileName(part.getHeader("Content-Disposition"));
                    
                    if(fileName.length()>0) {																								//첨부한 파일이 존재하지 않을때(파일을 미첨부시, 파일 첨부한 값이 없을때)
                    
                    int dot = fileName.lastIndexOf(".");
					String ext = fileName.substring(dot);
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
                }
            }
        /* enctype이 multipart/form-data가 아닐경우*/
        } else {																															
        }
        
        

	

		String path = "";
		if(resultFileUpload == -1) {																										//파일첨부를 하지 않았을때는 result값을 더해주면안된다.
			if (result1 > 0 && result2 > 0 && result3 > 0) {
				path = "/WEB-INF/views/common/success.jsp";
				request.setAttribute("successCode", "insertNightTrans");
			} else {
				path = "/WEB-INF/views/common/failed.jsp";
				request.setAttribute("failedCode", "insertNightTrans");
			}			
		}else {
			if (result1 > 0 && result2 > 0 && result3 > 0 && resultFileUpload > 0) {														//파일을첨부하였고 그 파일첨부가 성공하였을경우 페이지로 이동한다.
				path = "/WEB-INF/views/common/success.jsp";
				request.setAttribute("successCode", "insertNightTrans");
			} else {
				path = "/WEB-INF/views/common/failed.jsp";
				request.setAttribute("failedCode", "insertNightTrans");
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
