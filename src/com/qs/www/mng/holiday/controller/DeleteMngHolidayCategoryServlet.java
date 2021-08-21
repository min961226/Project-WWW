package com.qs.www.mng.holiday.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.mng.holiday.model.service.MngHolidayService;
import com.qs.www.schedule.model.dto.HolidayTypeDTO;

@WebServlet("/mng/holiday/category/delete")
public class DeleteMngHolidayCategoryServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<HolidayTypeDTO> holidayTypeList = new MngHolidayService().selectHolidayType();

		System.out.println(holidayTypeList.get(0));

		request.setAttribute("holidayTypeList", holidayTypeList);
		request.getRequestDispatcher("/WEB-INF/views/mngholiday/deleteHolidayCategory.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] checkList = request.getParameterValues("deleteItemCheck"); 
	}
}
