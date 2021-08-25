package com.qs.www.mng.welfare.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.mng.welfare.model.dto.DomitoryLogDTO;
import com.qs.www.mng.welfare.model.service.MngWelfareService;

@WebServlet("/mng/welfare/domitory/selectOne")
public class SelectOneMngDomitoryWelfareServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int domitoryNo = Integer.parseInt(request.getParameter("domitoryNo"));													//선택된 기숙사 번호
		int waitingNo = Integer.parseInt(request.getParameter("waitingNo"));													//대기자 명단 번호
		
		MngWelfareService mngWelfareService = new MngWelfareService();
		
		/* 선택된 no값으로 해당 인원의 멤버 번호 값을 갖고온다. */
		int memberNo = mngWelfareService.selectMemberNoByWaitingNo(waitingNo);													
		DomitoryLogDTO domitoryLogDTO = new  DomitoryLogDTO();																	
		
		domitoryLogDTO.setDomitoryManageNo(domitoryNo);																			//기숙사번호
		domitoryLogDTO.setMemberNo(memberNo);																					//사번
		
		/* 삽입된 값들을 로그에 기록한다 */
		int insertDomitoryLog = mngWelfareService.insertDomitoryLog(domitoryLogDTO);		
		/* 대기인원에서 해당인원을 삭제한다. */
		int deleteWaitList = mngWelfareService.deleteDomitoryWaitList(waitingNo);		
		/* 입주현황에 입주자를 한명추가한다. */
		int updateDomitory = mngWelfareService.updateDomitoryCapacity(domitoryNo);												
		
		String path = "";
		if(insertDomitoryLog > 0 && deleteWaitList > 0 && updateDomitory>0) {																					
			path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "insertDomitoryLog");
		} else {																												
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "insertDomitoryLog");
		}
		request.getRequestDispatcher(path).forward(request, response);
	}
}
