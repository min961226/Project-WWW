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

@WebServlet("/mng/holiday/category/update")
public class UpdateMngHolidayCategoryServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));  //선택한 휴가유형 번호
		HolidayTypeDTO holidayType = new MngHolidayService().selectOneHolidayType(no);
		
		System.out.println(holidayType );
		
		request.setAttribute("holidayType", holidayType);
		request.getRequestDispatcher("/WEB-INF/views/mngholiday/updateHolidayCategory.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int holidayCode = Integer.parseInt(request.getParameter("holidayCode"));
		String holidayType = request.getParameter("holidayType");
		String holidayName = request.getParameter("holidayName");
		String useYn = request.getParameter("useYn");
		String holidayNote = request.getParameter("holidayNote");
		
		HolidayTypeDTO holidayCategory = new HolidayTypeDTO();
		holidayCategory.setHolidayCode(holidayCode);
		holidayCategory.setHolidayType(holidayType);
		holidayCategory.setHolidayName(holidayName);
		holidayCategory.setUseYn(useYn);
		holidayCategory.setHolidayNote(holidayNote);
		System.out.println("holidayCategory : " + holidayCategory);
		
		int result = new MngHolidayService().updateHolidayType(holidayCategory);
		
		String path = "";
		if(result > 0) {
            path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "updateHolidayType");
			
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "updateHolidayType");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}
}
