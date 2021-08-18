package com.qs.www.welfare.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
import com.qs.www.schedule.model.dto.ApproverPerReportDTO;
import com.qs.www.schedule.model.dto.WorkingDocumentItemDTO;
import com.qs.www.schedule.model.service.ScheduleService;
import com.qs.www.welfare.model.dto.FamilyEventDTO;
import com.qs.www.welfare.model.dto.WelfareListDTO;
import com.qs.www.welfare.model.service.WelfareService;

@MultipartConfig(
        location = "C:\\Users\\82102\\git\\Project-WWW\\upload",
        maxFileSize = 1024*1024*10,
        maxRequestSize = 1024*1024*10*5,
        fileSizeThreshold = 1024)
@WebServlet("/welfare/nightTransportation/insert")
public class InsertNightTransportationWelfareServlet extends HttpServlet {

	
	private static final String ATTACHES_WELFARE= "/welfare";
	
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
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
        PrintWriter out = response.getWriter();
        String contentType = request.getContentType();
 
 
        if (contentType != null &&  contentType.toLowerCase().startsWith("multipart/")) {
            // getParts()를 통해 Body에 넘어온 데이터들을 각각의  Part로 쪼개어 리턴
            Collection<Part> parts = request.getParts();
 
 
            for (Part part : parts) {
                System.out.printf("파라미터 명 : %s, contentType :  %s,  size : %d bytes \n", part.getName(),
                        part.getContentType(), part.getSize());
 
 
                if  (part.getHeader("Content-Disposition").contains("filename=")) {
                    String fileName =  extractFileName(part.getHeader("Content-Disposition"));
                    
                    int dot = fileName.lastIndexOf(".");
					String ext = fileName.substring(dot);
					String randomFileName = UUID.randomUUID().toString().replace("-", "") + ext;
					System.out.println(randomFileName);
                    if (part.getSize() > 0) {
                        System.out.printf("업로드 파일 명 : %s  \n", fileName);
                        part.write(ATTACHES_WELFARE + File.separator  + fileName);
                        part.delete();
                    }
                } else {
                    String formValue =  request.getParameter(part.getName());
                    System.out.printf("name : %s, value : %s  \n", part.getName(), formValue);
                }
            }
            
            System.out.println("<h1>업로드 완료</h1>");
        } else {
            System.out.println("<h1>enctype이 multipart/form-data가  아님</h1>");
        }


		
		
		
		
		
////////////////////////////////////////////////////////////////////////////////	
        		
		
		
//		
//		String welfareTitle = "야간 교통비 신청"; // 결재 제목
//		int documentNo = 7; // 야간 교통비 신청 문서 번호
//
//		WelfareService welfareService = new WelfareService();
//
//		String overTime = request.getParameter("overTimeLog");
//		int transBill = Integer.parseInt(request.getParameter("transBill"));
//		System.out.println(overTime);
//		String overTimeLogInfo = request.getParameter("overTimeLogInfo");
//
//		int lineNo = Integer.parseInt(request.getParameter("lineList"));
//		List<ApprovalLineDTO> lineList = new ApprovalService()
//				.selectApprovalLine(Integer.parseInt(request.getParameter("memberNo")));
//		System.out.println(lineNo);
//		System.out.println(lineList);
//
//		String lineName = "";
//
//		for (ApprovalLineDTO line : lineList) {
//			if (line.getLineNo() == lineNo) {
//				lineName = line.getLineName();
//			}
//		}
//
//		WelfareListDTO welfareListDTO = new WelfareListDTO();
//
//		welfareListDTO.setMemberNo(request.getParameter("memberNo"));
//		welfareListDTO.setDocumentNo(documentNo);
//		welfareListDTO.setReportNote(overTimeLogInfo);
//		welfareListDTO.setLineName(lineName);
//		welfareListDTO.setWelfareTitle(welfareTitle);
//		System.out.println(welfareListDTO);
//
//		int reportNo = welfareService.selectReportNum();
//		int result1 = welfareService.insertWelfareReport(welfareListDTO);
//
//		List<String> documentItem = new ArrayList<>();
//		documentItem.add(welfareTitle);
//		documentItem.add(overTime);
//		documentItem.add(String.valueOf(transBill));
//		documentItem.add(overTimeLogInfo);
//
//		int priority = 1;
//		int result2 = 0;
//
//		for (String item : documentItem) {
//			WorkingDocumentItemDTO documentItemDTO = new WorkingDocumentItemDTO();
//			documentItemDTO.setReportNo(reportNo);
//			documentItemDTO.setDocumentNo(documentNo);
//			documentItemDTO.setPriority(priority);
//			documentItemDTO.setItemContent(item);
//
//			result2 = welfareService.insertWelfareItemContent(documentItemDTO);
//
//			priority++;
//		}
//
//		List<ApproverDTO> approverList = new ApprovalService().selectApprover(lineNo);
//		System.out.println(approverList);
//
//		int result3 = 0;
//		for (ApproverDTO approver : approverList) {
//			ApproverPerReportDTO approverPerReportDTO = new ApproverPerReportDTO();
//			ScheduleService scheduleService = new ScheduleService();
//			if (approver.getApproverType().equals("결재")) {
//				approverPerReportDTO.setReportNo(reportNo);
//				approverPerReportDTO.setMemberNo(approver.getMemberNo());
//				approverPerReportDTO.setPriority(approver.getPriority());
//
//				result3 = scheduleService.applyWorkingSystemApprover(approverPerReportDTO);
//			} else {
//                approverPerReportDTO.setReportNo(reportNo);
//                approverPerReportDTO.setMemberNo(approver.getMemberNo());
//                approverPerReportDTO.setApproverType(approver.getApproverType());
//
//                result3 = scheduleService.applyWorkingSystemReferer(approverPerReportDTO);
//            }
//		}
//
//		String path = "";
//
//		if (result1 > 0 && result2 > 0 && result3 > 0) {
//			path = "/WEB-INF/views/common/success.jsp";
//			request.setAttribute("successCode", "insertNightTrans");
//		} else {
//			path = "/WEB-INF/views/common/failed.jsp";
//			request.setAttribute("failed", "insertNightTrans");
//		}
//		request.getRequestDispatcher(path).forward(request, response);
	}
}
