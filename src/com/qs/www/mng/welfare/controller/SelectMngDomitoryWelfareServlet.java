package com.qs.www.mng.welfare.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.welfare.model.dto.DomitoryListDTO;
import com.qs.www.welfare.model.service.WelfareService;

@WebServlet("/mng/welfare/domitory/select")
public class SelectMngDomitoryWelfareServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("기숙사 입주 관리");
		
		WelfareService welfareService = new WelfareService();

		String path = "";

		List<DomitoryListDTO> domitoryList = welfareService.selectDomitory();													//domitory에 해당하는 리스트를 전부 가져옴
		request.setAttribute("domitoryList", domitoryList);
		path = "/WEB-INF/views/mngwelfare/domitory.jsp";
		
		request.getRequestDispatcher(path).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
