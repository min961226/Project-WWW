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

@WebServlet("/mng/welfare/domitory/delete")
public class DeleteMngDomitoryWelfareServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MngWelfareService mngWelfareService = new MngWelfareService();
		
		int domitoryManageNo = Integer.parseInt(request.getParameter("no"));																//기숙사 번호
		
		/* 기숙사 방번호로 해당 기숙사에 대한 입주정보를 가져온다*/
		List<DomitoryLogDTO> domitoryLogResult = mngWelfareService.selectDomitoryLogResult(domitoryManageNo);								
		
		request.setAttribute("domitoryLogResult", domitoryLogResult);
		String path = "/WEB-INF/views/mngwelfare/deleteDomitoryLeave.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}

}
