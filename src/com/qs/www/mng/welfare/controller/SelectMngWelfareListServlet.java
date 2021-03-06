package com.qs.www.mng.welfare.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.mng.welfare.model.dto.WelfareYnDTO;
import com.qs.www.mng.welfare.model.service.MngWelfareService;

@WebServlet("/mng/welfare/list/select")
public class SelectMngWelfareListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MngWelfareService mngWelfareService = new MngWelfareService();
		/* 전체 복지 목록의 시행 상태를 가져옴*/
		List<WelfareYnDTO> welfareYn = mngWelfareService.selectWelfareYn();						
		
		request.setAttribute("welfareYn", welfareYn);

		String path = "/WEB-INF/views/mngwelfare/welfare.jsp"; 
		request.getRequestDispatcher(path).forward(request, response);
	}
}
