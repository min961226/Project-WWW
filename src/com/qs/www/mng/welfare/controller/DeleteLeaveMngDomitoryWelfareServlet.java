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
		
		int logNo = Integer.parseInt(request.getParameter("logNo"));																				//로그 명단에 퇴거기록을 남기기 위한 로그 번호
		int domitoryManageNo = Integer.parseInt(request.getParameter("domitoryNo"));																//선택한 입주자를 퇴거시키기 위한 기숙사 번호
		String outReason = request.getParameter("reason");
		DomitoryLogDTO domitoryLogDTO = new DomitoryLogDTO();
		
		domitoryLogDTO.setLogNo(logNo);
		domitoryLogDTO.setOutReason(outReason);
		
		int updateOutReason = new MngWelfareService().updateOutReason(domitoryLogDTO);																//퇴거사유 기입
		
		int minusDomitoryCapacity = mngWelfareService.minusDomitoryCapacity(domitoryManageNo);														//기숙사 현재 거주 명단에서 1명을 빼준다.	
		
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
