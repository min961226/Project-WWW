package com.qs.www.mng.welfare.controller;

import java.io.IOException;
import java.util.ArrayList;
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
		System.out.println("시행복지 관리 수정 완료!");
		
		WelfareService welfareService = new WelfareService();
		
		String nightTransNo = request.getParameter("nightTransNo");
		String nightTransYn = request.getParameter("nightTransYn");
		String eventNo = request.getParameter("eventNo");
		String eventYn = request.getParameter("eventYn");
		String selfDevNo = request.getParameter("selfDevNo");
		String selfDevYn = request.getParameter("selfDevYn");
		String domitoryNo = request.getParameter("domitory");
		String domitoryYn = request.getParameter("domitoryYn");
		String seminarNo = request.getParameter("seminarNo");
		String seminarYn = request.getParameter("seminarYn");
		String laptopNo = request.getParameter("laptopNo");
		String laptopYn = request.getParameter("laptopYn");
		
		
		System.out.println(nightTransNo);
		System.out.println(nightTransYn);
		System.out.println(eventNo);
		System.out.println(eventYn);
		System.out.println(selfDevNo);
		System.out.println(selfDevYn);
		System.out.println(domitoryNo);
		System.out.println(domitoryYn);
		System.out.println(seminarNo);
		System.out.println(seminarYn);
		System.out.println(laptopNo);
		System.out.println(laptopYn);
		List<String> welfareYnList = new ArrayList<>();
		
		
	}
}
