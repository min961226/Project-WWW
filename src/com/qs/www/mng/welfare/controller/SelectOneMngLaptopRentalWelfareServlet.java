package com.qs.www.mng.welfare.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.mng.welfare.model.service.MngWelfareService;
import com.qs.www.welfare.model.dto.LaptopDTO;

@WebServlet("/mng/welfare/laptopRental/selectOne")
public class SelectOneMngLaptopRentalWelfareServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int itemNo =Integer.parseInt(request.getParameter("no"));
		
		LaptopDTO item = new MngWelfareService().selectOneItem(itemNo);
		System.out.println(item);
		String path = "/WEB-INF/views/mngwelfare/detailLaptopRental.jsp";
		request.getRequestDispatcher(path).forward(request, response);	
	}
}
