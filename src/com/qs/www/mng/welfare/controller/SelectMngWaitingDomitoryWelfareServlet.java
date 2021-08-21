package com.qs.www.mng.welfare.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.mng.welfare.model.service.MngWelfareService;
import com.qs.www.welfare.model.dto.DomitoryListDTO;
import com.qs.www.welfare.model.dto.DomitoryWaitListDTO;
import com.qs.www.welfare.model.service.WelfareService;

@WebServlet("/mng/welfare/domitory/waiting/select")
public class SelectMngWaitingDomitoryWelfareServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		WelfareService welfareService = new WelfareService();
		
		int domitoryNo = Integer.parseInt(request.getParameter("no"));												// 입주 처리할 기숙사 번호
		String path = "";																							// 결과값에 따른 경로
		
		DomitoryListDTO domitoryDTO = new MngWelfareService().selectDomitory(domitoryNo);							//현재 거주 인원이 최대거주인원을 넘어서는데 처리를 하면 안되기때문에 예외처리
		System.out.println(domitoryDTO);
		
		if (domitoryDTO.getCurrCapacity() >= domitoryDTO.getMaxCapacity()) {										//현재 거주인원이 최대인원과 같거나 더 많을경우 신청 불가
			path = "/WEB-INF/views/common/failed.jsp";																//실패 페이지로 이동
			request.setAttribute("failedCode", "maxCapacity");
		}else {
			
			List<DomitoryWaitListDTO> domitoryWaitList = welfareService.selectDomitoryWaitList();					// 대기자 명단 전부 불러온다.
			
			path="/WEB-INF/views/mngwelfare/domitoryWaiting.jsp";
			
			request.setAttribute("domitoryNo", domitoryNo);
			request.setAttribute("domitoryWaitList", domitoryWaitList);
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}

}
