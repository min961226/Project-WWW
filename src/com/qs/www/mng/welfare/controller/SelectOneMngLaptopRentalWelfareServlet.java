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
		
		/* 품목 번호로 대여기록 반납기록 가져오기 */
		List<ItemDTO> itemLog = mngWelfareService.selectAllItemLog(itemNo);				
		/* 현재 대여자 값 갖고오기 */
		ItemDTO item = mngWelfareService.selectOneItemLog(itemNo);						
		
		request.setAttribute("itemLog",itemLog);
		request.setAttribute("item", item);
		String path = "/WEB-INF/views/mngwelfare/detailLaptopRental.jsp";
		request.getRequestDispatcher(path).forward(request, response);	
	}
}
