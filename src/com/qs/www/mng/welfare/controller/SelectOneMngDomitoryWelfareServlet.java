package com.qs.www.mng.welfare.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.mng.welfare.model.dto.DomitoryLogDTO;
import com.qs.www.mng.welfare.model.service.MngWelfareService;
import com.qs.www.welfare.model.dto.DomitoryWaitListDTO;
import com.qs.www.welfare.model.service.WelfareService;

@WebServlet("/mng/welfare/domitory/selectOne")
public class SelectOneMngDomitoryWelfareServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int domitoryNo = Integer.parseInt(request.getParameter("domitoryNo"));													//선택된 기숙사 번호
		int waitingNo = Integer.parseInt(request.getParameter("waitingNo"));													//대기자 명단 번호
		
		MngWelfareService mngWelfareService = new MngWelfareService();

		int memberNo = mngWelfareService.selectMemberNoByWaitingNo(waitingNo);													//선택된 no값으로 해당 인원의 멤버 번호 값을 갖고온다.
		DomitoryLogDTO domitoryLogDTO = new  DomitoryLogDTO();																	//데이터 값을 옮겨 닮기위한 DTO 선언 결합성 낮춤
		
		domitoryLogDTO.setDomitoryManageNo(domitoryNo);
		domitoryLogDTO.setMemberNo(memberNo);
		
		int insertDomitoryLog = mngWelfareService.insertDomitoryLog(domitoryLogDTO);											//LOG 값에 입주처리를 한다.
		int deleteWaitList = mngWelfareService.deleteDomitoryWaitList(waitingNo);												//대기인원에서 해당인원을 삭제한다.
		int updateDomitory = mngWelfareService.updateDomitoryCapacity(domitoryNo);												//입주현황에 입주자를 한명추가한다.
		
		String path = "";
		if(insertDomitoryLog > 0 && deleteWaitList > 0 && updateDomitory>0) {													//모든 서비스 성공시										
			path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "insertDomitoryLog");
		} else {																												//한개의서비스라도 실패시
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "insertDomitoryLog");
		}
		request.getRequestDispatcher(path).forward(request, response);
	}
}
