package com.qs.www.mng.welfare.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.mng.welfare.model.dto.ItemDTO;
import com.qs.www.mng.welfare.model.service.MngWelfareService;
import com.qs.www.welfare.model.dto.LaptopDTO;

@WebServlet("/mng/welfare/laptopRental/selectOne")
public class SelectOneMngLaptopRentalWelfareServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		MngWelfareService mngWelfareService = new MngWelfareService();
		int itemNo =Integer.parseInt(request.getParameter("no"));
		
		List<ItemDTO> itemLog = mngWelfareService.selectAllItemLog(itemNo);				//예전 대여한기록, 반납된 값 가져오기
		ItemDTO item = mngWelfareService.selectOneItemLog(itemNo);						//현재 대여자 값 갖고오기
		System.out.println(itemLog);
		System.out.println(item);
		
		request.setAttribute("itemLog",itemLog);
		request.setAttribute("item", item);
		String path = "/WEB-INF/views/mngwelfare/detailLaptopRental.jsp";
		request.getRequestDispatcher(path).forward(request, response);	
	}
}
