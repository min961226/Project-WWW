package com.qs.www.mng.welfare.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.welfare.model.dto.LaptopDTO;
import com.qs.www.welfare.model.service.WelfareService;

@WebServlet("/mng/welfare/laptopRental/select")
public class SelectMngLaptopRentalWelfareServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		WelfareService welfareService = new WelfareService();
		
		List<LaptopDTO> laptopList = welfareService.selectLaptopList();
		request.setAttribute("laptopList", laptopList);
		System.out.println(laptopList);
		String path = "/WEB-INF/views/mngwelfare/laptopRental.jsp";
		request.getRequestDispatcher(path).forward(request, response);	

	}
}
