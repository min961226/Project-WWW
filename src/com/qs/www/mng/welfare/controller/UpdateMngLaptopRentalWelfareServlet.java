package com.qs.www.mng.welfare.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.mng.welfare.model.dto.ItemDTO;
import com.qs.www.mng.welfare.model.service.MngWelfareService;

@WebServlet("/mng/welfare/laptopRental/update")
public class UpdateMngLaptopRentalWelfareServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		MngWelfareService mngWelfareService = new MngWelfareService();
		ItemDTO itemDTO =  new ItemDTO();

		int logNo = Integer.parseInt(request.getParameter("logNo"));								//반납하는 기록번호
		
		LocalDate sysDate = LocalDate.now();														//반납하는 기록날짜
		
		
		itemDTO.setLogNo(logNo);																	//기록번호를 담아준다
		itemDTO.setReturnDate(Date.valueOf(sysDate));												//오늘 날짜를 담아준다
		
		int returnItem = mngWelfareService.updateReturnItem(itemDTO);																		//dto를 통해 반납일자 등록 서비스 실행
		int changeStatus = mngWelfareService.updateChangeStatus(Integer.parseInt(request.getParameter("itemNo")));							//dto를 통해 대여 상태를 대여중으로 변환 등록 서비스 실행
		
		String path = "";
		if(returnItem > 0 && changeStatus > 0 ) {
			path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "returnItem");
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "returnItem");
		}
		request.getRequestDispatcher(path).forward(request, response);
	}
}
