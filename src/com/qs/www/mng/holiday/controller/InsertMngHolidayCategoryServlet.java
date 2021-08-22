package com.qs.www.mng.holiday.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.mng.holiday.model.service.MngHolidayService;
import com.qs.www.schedule.model.dto.HolidayTypeDTO;

@WebServlet("/mng/holiday/category/insert")
public class InsertMngHolidayCategoryServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/mngholiday/insertHolidayCategory.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String holidayType = request.getParameter("holidayType");
		String holidayName = request.getParameter("holidayName");
		String useYn = request.getParameter("useYn");
		String holidayNote = request.getParameter("holidayNote");
		
		HolidayTypeDTO holidayCategory = new HolidayTypeDTO();
		holidayCategory.setHolidayType(holidayType);
		holidayCategory.setHolidayName(holidayName);
		holidayCategory.setUseYn(useYn);
		holidayCategory.setHolidayNote(holidayNote);
		
		
		int result = new MngHolidayService().insertHolidayType(holidayCategory);
		
		String path = "";
		if(result > 0) {
            path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "insertHolidayType");
			
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "insertHolidayType");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}
}
