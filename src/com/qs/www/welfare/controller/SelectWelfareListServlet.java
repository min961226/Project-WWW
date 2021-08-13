package com.qs.www.welfare.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.welfare.model.service.WelfareService;

@WebServlet("/welfare/list/select")
public class SelectWelfareListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("복지 신청");

		WelfareService welfareService = new WelfareService();
		List<String> welfareList = welfareService.checkWelfareList();
		System.out.println(welfareList);
		String path = "";
			if(welfareList != null) {
				path = "/WEB-INF/views/welfare/welfareList.jsp";
				request.setAttribute("welfareList", welfareList);
			} else {
				path = "/WEB-INF/views/common/error-500.jsp";
				request.setAttribute("message", "복지 목록조회 실패!");
			}
			request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
