package com.qs.www.mng.welfare.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.mng.welfare.model.dto.ItemDTO;
import com.qs.www.mng.welfare.model.service.MngWelfareService;

@WebServlet("/mng/welfare/laptopRental/insertComplete")
public class InsertCompleteMngLaptopRentalWelfareServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ItemDTO itemDTO = new ItemDTO();
		
		String itemCategory = request.getParameter("itemCategory");
		String itemName = request.getParameter("itemName");
		
		itemDTO.setItemCategory(itemCategory);												//물품 카테고리
		itemDTO.setItemName(itemName);														//물품명
		
		/* 입력된 물품 카테고리와 물품 명을 삽입한다 */
		int result = new MngWelfareService().insertItem(itemDTO);															
		
		String path = "";
		if(result > 0 ) {																				
			path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "insertItem");
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "insertItem");
		}
		request.getRequestDispatcher(path).forward(request, response);
	}
}