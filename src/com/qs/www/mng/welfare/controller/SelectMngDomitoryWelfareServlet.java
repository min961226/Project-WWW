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
		
		WelfareService welfareService = new WelfareService();

		String path = "";
		/* 	기숙사 리스트를 전부 가져옴 */
		List<DomitoryListDTO> domitoryList = welfareService.selectDomitory();												
		request.setAttribute("domitoryList", domitoryList);
		path = "/WEB-INF/views/mngwelfare/domitory.jsp";
		
		request.getRequestDispatcher(path).forward(request, response);

	}
}
