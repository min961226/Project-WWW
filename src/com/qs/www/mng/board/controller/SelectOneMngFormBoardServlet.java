package com.qs.www.mng.board.controller;

import java.io.IOException; 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.mng.board.model.dto.MngFormDTO;
import com.qs.www.mng.board.model.dto.MngNoticeDTO;
import com.qs.www.mng.board.model.service.MngFormService;
import com.qs.www.mng.board.model.service.MngNoticeService;

@WebServlet("/mng/board/form/selectOne")
public class SelectOneMngFormBoardServlet extends HttpServlet {

	//게시판 상세보기
	System.out.println("확인");
	int no = Integer.parseInt(request.getParameter("no"));
	System.out.println(no);
	MngFormService mngformService = new MngFormService();
	MngFormDTO mngformDetail = mngformService.selectMngFormDetail(no);
	
	System.out.println("mngformDetail : " + mngformDetail);
	
	String path = "";
	if(mngformDetail != null) {
		path = "/WEB-INF/views/mngboard/detailFormBoard.jsp";
		request.setAttribute("mngform", mngformDetail);
	} else {
		path = "/WEB-INF/views/common/failed.jsp";
		request.setAttribute("message", "공지사항 상세보기 실패");
	}
	
	request.getRequestDispatcher(path).forward(request, response);
	
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session = request.getSession();
	int memberNo = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
	int no = Integer.parseInt(request.getParameter("no"));
	
	MngFormService mngformService = new MngFormService();
	MngFormDTO mngformDetail = mngformService.selectMngFormDetail(no);
	
	System.out.println("mngformDetail : " + mngformDetail);
	

	System.out.println(memberNo);

	System.out.println(no);
	
	MngFormDTO mngform = new MngFormDTO();

	mngform.setNo(no);

	mngform.setMember(memberNo);
	System.out.println(mngform);


//	int result = freeService.updateFree(free);

	String path = "";
//	if(result > 0) {
//		path = "/WEB-INF/views/common/success.jsp";
//		request.setAttribute("successCode", "updateFree");
//	} else {
//		path = "/WEB-INF/views/common/failed.jsp";
//		request.setAttribute("message", "자유게시판 수정에 실패");
//	}

//	request.getRequestDispatcher(path).forward(request, response);
}
}
