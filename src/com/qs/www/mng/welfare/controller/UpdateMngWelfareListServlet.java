package com.qs.www.mng.welfare.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.welfare.model.service.WelfareService;

@WebServlet("/mng/welfare/list/update")
public class UpdateMngWelfareListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		WelfareService welfareService = new WelfareService();
		
		System.out.println("시행복지 관리 수정 완료!");
		String nightTrans = request.getParameter("nightTrans");
		String familyEvent = request.getParameter("familyEvent");
		String selfDevYn = request.getParameter("selfDev");
		String domitory = request.getParameter("domitory");
		String seminarRoom = request.getParameter("seminarRoom");
		String lapTopRental = request.getParameter("rentalLaptop");
		
		System.out.println(nightTrans);
		System.out.println(familyEvent);
		System.out.println(selfDevYn);
		System.out.println(domitory);
		System.out.println(seminarRoom);
		System.out.println(lapTopRental);
		
		if(nightTrans.equals(null)) {
			welfareService.updateMngWelfare()z`11
		}
	}
}
