package com.qs.www.mng.welfare.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.welfare.model.service.WelfareService;

@WebServlet("/mng/welfare/list/select")
public class SelectMngWelfareListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("시행 복지 관리");
		
		WelfareService welfareService = new WelfareService();
		List<String> welfareYn = welfareService.selectWelfareYn();
		
		request.setAttribute("welfareYn", welfareYn);
		String path = "/WEB-INF/views/mngwelfare/welfare.jsp"; 
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("nightTrans"));
	}
}
