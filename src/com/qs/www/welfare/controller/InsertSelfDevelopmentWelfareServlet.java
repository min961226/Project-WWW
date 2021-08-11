package com.qs.www.welfare.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.welfare.model.dto.WelfareListDTO;

@WebServlet("/welfare/selfDevelopment/insert")
public class InsertSelfDevelopmentWelfareServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		
		int result1 = welfareService.insertSelfDevelopment
	}
}
