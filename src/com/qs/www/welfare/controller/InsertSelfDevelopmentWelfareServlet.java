package com.qs.www.welfare.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/welfare/selfDevelopment/insert")
public class InsertSelfDevelopmentWelfareServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("자기개발비 신청!");
		String deptName = request.getParameter("selfDevObj");
		String selfDevInfo = request.getParameter("selfDevInfo");
		String approvalLine = request.getParameter("approvalLine");
		String date = request.getParameter("date");
		System.out.println(deptName);
		System.out.println(selfDevInfo);
		System.out.println(approvalLine);
		System.out.println(date);
		
	}
}
