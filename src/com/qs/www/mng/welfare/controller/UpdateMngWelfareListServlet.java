package com.qs.www.mng.welfare.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.mng.welfare.model.dto.WelfareYnDTO;
import com.qs.www.mng.welfare.model.service.MngWelfareService;

@WebServlet("/mng/welfare/list/update")
public class UpdateMngWelfareListServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MngWelfareService mngWelfareService = new MngWelfareService();
	
		
		int nightTransNo = Integer.parseInt(request.getParameter("nightTransNo"));								//문서번호
		String nightTransYn = request.getParameter("nightTransYn");												//체크박스 선택 여부
		int eventNo = Integer.parseInt(request.getParameter("eventNo"));
		String eventYn = request.getParameter("eventYn");
		int selfDevNo = Integer.parseInt(request.getParameter("selfDevNo"));
		String selfDevYn = request.getParameter("selfDevYn");
		int domitoryNo = Integer.parseInt(request.getParameter("domitoryNo"));
		String domitoryYn = request.getParameter("domitoryYn");
		int seminarNo = Integer.parseInt(request.getParameter("seminarNo"));
		String seminarYn = request.getParameter("seminarYn");
		int laptopNo = Integer.parseInt(request.getParameter("laptopNo"));
		String laptopYn = request.getParameter("laptopYn");
		
		/* 시행복지를 off 할경우 null값으로 뜬다. */
		if (nightTransYn == null) {														
			nightTransYn ="N";
		}else {
			nightTransYn ="Y";
		}
		if (eventYn == null) {
			eventYn ="N";
		}else {
			eventYn ="Y";
		}
		if (selfDevYn == null) {
			selfDevYn ="N";
		}else {
			selfDevYn ="Y";
		}
		if (domitoryYn == null) {
			domitoryYn ="N";
		}else {
			domitoryYn ="Y";
		}
		if (seminarYn == null) {
			seminarYn ="N";
		}else {
			seminarYn ="Y";
		}
		if (laptopYn == null) {
			laptopYn ="N";
		}else {
			laptopYn ="Y";
		}
		
		WelfareYnDTO nightTransYnDTO = new WelfareYnDTO();
		WelfareYnDTO eventYnDTO = new WelfareYnDTO();
		WelfareYnDTO selfDevYnDTO = new WelfareYnDTO();
		WelfareYnDTO domitoryYnDTO = new WelfareYnDTO();
		WelfareYnDTO seminarYnDTO = new WelfareYnDTO();
		WelfareYnDTO laptopYnDTO = new WelfareYnDTO();
		
		nightTransYnDTO.setDocumentNo(nightTransNo);
		nightTransYnDTO.setUseYnChar(nightTransYn.charAt(0));
		eventYnDTO.setDocumentNo(eventNo);
		eventYnDTO.setUseYnChar(eventYn.charAt(0));
		selfDevYnDTO.setDocumentNo(selfDevNo);
		selfDevYnDTO.setUseYnChar(selfDevYn.charAt(0));
		domitoryYnDTO.setDocumentNo(domitoryNo);
		domitoryYnDTO.setUseYnChar(domitoryYn.charAt(0));
		seminarYnDTO.setDocumentNo(seminarNo);
		seminarYnDTO.setUseYnChar(seminarYn.charAt(0));
		laptopYnDTO.setDocumentNo(laptopNo);
		laptopYnDTO.setUseYnChar(laptopYn.charAt(0));
		
		int result1 = mngWelfareService.updateWelfare(nightTransYnDTO);
		int result2 = mngWelfareService.updateWelfare(eventYnDTO);
		int result3 = mngWelfareService.updateWelfare(selfDevYnDTO);
		int result4 = mngWelfareService.updateWelfare(domitoryYnDTO);
		int result5 = mngWelfareService.updateWelfare(seminarYnDTO);
		int result6 = mngWelfareService.updateWelfare(laptopYnDTO);
		
		/* 모든 데이터들이 성공한 값 */
		int result = result1 + result2 + result3 + result4 + result5 + result6;
		
		String path = "";
		/* 모든 업데이트가 성공하였을경우 */
		if(result > 5 ) {																					
			path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "updateWelfare");
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failed", "updateWelfare");
		}
		request.getRequestDispatcher(path).forward(request, response);
		
	}
}
