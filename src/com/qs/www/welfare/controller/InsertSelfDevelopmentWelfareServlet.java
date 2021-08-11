package com.qs.www.welfare.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.approval.model.service.ApprovalService;
import com.qs.www.schedule.model.dto.WorkingDocumentItemDTO;
import com.qs.www.welfare.model.dto.WelfareListDTO;
import com.qs.www.welfare.model.service.WelfareService;
import com.sun.java.swing.plaf.windows.resources.windows;

@WebServlet("/welfare/selfDevelopment/insert")
public class InsertSelfDevelopmentWelfareServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		WelfareService welfareService = new WelfareService();
		System.out.println("자기개발비 신청!");
		int documentNo = 9;		            //자기개발비 신청 문서 번호
		String welfareTitle = "자기개발비 신청"; // 결재 제목
		
		WelfareListDTO welfareListDTO = new WelfareListDTO();
		welfareListDTO.setMemberNo(request.getParameter("memberNo"));
		welfareListDTO.setDocumentNo(documentNo);
		welfareListDTO.setReportNote(request.getParameter("selfDevInfo"));
		welfareListDTO.setLineName(request.getParameter("selfDevList"));
		welfareListDTO.setWelfareTitle(welfareTitle);
		welfareListDTO.setDate(Date.valueOf(request.getParameter("date")));
		System.out.println(welfareListDTO);

//		int developmentNo = welfareService.selectDevNo(welfareListDTO.getLineName());	//지원 목록
//		int limitCost = welfareService.selectLimitCost(developmentNo);					//지원 최대 금액

		
		int result1 = welfareService.insertSelfDevelopment(welfareListDTO);
		int reportNo = welfareService.selectReportNum();
		
		List<String> documentItem = new ArrayList<>();
		documentItem.add(welfareListDTO.getWelfareTitle());
		documentItem.add(String.valueOf(documentNo));
		documentItem.add(request.getParameter("date"));
		
		
		
		int priority = 1;
		int result2 = 0;
		for(int i = 0; i < 5; i++) {
			welfareListDTO.setReportNo(reportNo);
//			welfareListDTO.setItemContent(item);	
//			
//			result2 = welfareService.insertSelfDevelopmentItemContent(documentItemDTO);
			
			priority++;			
		}
		
//		if(result1 > 0 && result2 > 0 && result3 > 0 ) {
//			PrintWriter out = response.getWriter();
//			
//			out.println("<script language='javascript'>");
//			out.println("alert('자기개발비 복지 신청 완료.');");
//			out.println("document.location.href=\"/alert/jsp/log_in/login.jsp\" ;");            
//			out.println("</script>");
//			out.flush();
//		} else {
//			System.out.println("alert 상신실패");
//		}
	}
}
