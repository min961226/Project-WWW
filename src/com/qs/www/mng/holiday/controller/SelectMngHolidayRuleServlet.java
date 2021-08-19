package com.qs.www.mng.holiday.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.mng.holiday.model.dto.HolidayRuleDTO;
import com.qs.www.mng.holiday.model.service.MngHolidayService;

@WebServlet("/mng/holiday/rule/select")
public class SelectMngHolidayRuleServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<HolidayRuleDTO> holidayRule = new MngHolidayService().selectHolidayRule();
		
		System.out.println("휴가발생규칙 : " + holidayRule);
		request.getRequestDispatcher("/WEB-INF/views/mngholiday/holidayRule.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
