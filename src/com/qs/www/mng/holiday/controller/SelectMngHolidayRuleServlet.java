package com.qs.www.mng.holiday.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		String fullDate = holidayRule.get(0).getFiscalStateDate();
		String stDate = fullDate.substring(0, 2);
		int giveDate = Integer.parseInt(stDate);
		
		request.setAttribute("holidayRule", holidayRule);
		request.setAttribute("giveDate", giveDate);
		request.getRequestDispatcher("/WEB-INF/views/mngholiday/holidayRule.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MngHolidayService holidayService = new MngHolidayService();
		int gd = Integer.parseInt(request.getParameter("giveDate"));
		String giveDate = gd + "-01";
		if(gd <10) {
			giveDate = "0" + gd + "-01";
		}
		int result = 0;
		for(int i = 1; i <39; i++) {
			String codeName ="HR" + i;
			int dayNumber = Integer.parseInt(request.getParameter(codeName));
			Map<String , Object> ruleMap = new HashMap<>();
			ruleMap.put("codeName", codeName);
			ruleMap.put("dayNumber", dayNumber);
			ruleMap.put("giveDate", giveDate);

			result = holidayService.updateholidayRuleNumber(ruleMap);
		}
		
		
		String path = "";
		if(result > 0 ) {
            path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "updateholidayRule");
			
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "updateholidayRule");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
		
		
	}
}
