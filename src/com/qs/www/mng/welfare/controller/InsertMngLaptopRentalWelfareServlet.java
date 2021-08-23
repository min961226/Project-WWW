package com.qs.www.mng.welfare.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.mng.welfare.model.service.MngWelfareService;

@WebServlet("/mng/welfare/laptopRental/insert")
public class InsertMngLaptopRentalWelfareServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		MngWelfareService mngWelfareService = new MngWelfareService();
		
		int nextItemNo = mngWelfareService.selectNextItemNo();													//물품값을 추가해주기 위한 다음 번호값 가져오기
		
		String path = "/WEB-INF/views/mngwelfare/insertLaptopRental.jsp";
		request.setAttribute("nextItemNo", nextItemNo);
		request.getRequestDispatcher(path).forward(request, response);	
	}
}
