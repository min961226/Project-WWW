package com.qs.www.mng.holiday.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.mng.holiday.model.service.MngHolidayService;
import com.qs.www.mng.welfare.model.dto.ItemDTO;
import com.qs.www.schedule.model.dto.HolidayTypeDTO;

@WebServlet("/mng/holiday/category/delete")
public class DeleteMngHolidayCategoryServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<HolidayTypeDTO> holidayTypeList = new MngHolidayService().selectHolidayType();

		request.setAttribute("holidayTypeList", holidayTypeList);
		request.getRequestDispatcher("/WEB-INF/views/mngholiday/deleteHolidayCategory.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] checkList = request.getParameterValues("deleteItemCheck"); 
		List<Integer> itemList = new ArrayList<>();
		for (int i = 0; i < checkList.length; i++) { // check 박스로 받아오게 될시 String[]로 받아와야함으로 변환이 필요하다.
			itemList.add(Integer.parseInt(checkList[i]));
		}

		int result = new MngHolidayService().deleteHolidayType(itemList);

		String path = "";
		if(result > 0) {
			path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "deleteItemCheck");

		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "deleteItemCheck");
		}

		request.getRequestDispatcher(path).forward(request, response);
	}
}
