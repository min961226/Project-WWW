package com.qs.www.mng.welfare.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.mng.welfare.model.dto.DomitoryLogDTO;
import com.qs.www.mng.welfare.model.service.MngWelfareService;

@WebServlet("/mng/welfare/domitory/delete/leave")
public class DeleteLeaveMngDomitoryWelfareServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MngWelfareService mngWelfareService = new MngWelfareService();
		
		int logNo = Integer.parseInt(request.getParameter("logNo"));																				//기숙사 로그번호
		int domitoryManageNo = Integer.parseInt(request.getParameter("domitoryNo"));																//기숙사 번호
		String outReason = request.getParameter("reason");																							//퇴거사유
		DomitoryLogDTO domitoryLogDTO = new DomitoryLogDTO();
		
		domitoryLogDTO.setLogNo(logNo);
		domitoryLogDTO.setOutReason(outReason);
		
		/* 기입한 퇴거사유를 삽입한다.*/
		int updateOutReason = new MngWelfareService().updateOutReason(domitoryLogDTO);																
		
		/* 기숙사 현재 거주 인원수에서 1명을 빼준다*/
		int minusDomitoryCapacity = mngWelfareService.minusDomitoryCapacity(domitoryManageNo);	
		
		String path = "";
		if(updateOutReason > 0 && minusDomitoryCapacity > 0 ) {
			path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "leaveDomitory");
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "leaveDomitory");
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

}
