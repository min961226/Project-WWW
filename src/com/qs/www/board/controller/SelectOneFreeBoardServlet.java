package com.qs.www.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.board.model.dto.FreeDTO;
import com.qs.www.board.model.service.FreeService;
import com.qs.www.common.attachment.model.dto.BoardAttachmentDTO;
import com.qs.www.common.attachment.model.service.BoardAttachmentService;
import com.qs.www.member.model.dto.MemberInfoDTO;

@WebServlet("/board/free/selectOne")
public class SelectOneFreeBoardServlet extends HttpServlet {
	/* 자유게시판 상세보기 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/* 상세보기 희망하는 게시글 번호를 가져옴 */
		int no = Integer.parseInt(request.getParameter("no"));

		FreeService freeService = new FreeService();
		FreeDTO freeDetail = freeService.selectFreeDetail(no);

		/* 파일 첨부 DTO 서비스 실행 reportNo로 갖고옴 */
		BoardAttachmentDTO boardattachmentDTO = new BoardAttachmentService()
				.selectBoardAttachmentByBoardNo(freeDetail.getNo()); // boardNo로 값을 갖고옴
		
		request.setAttribute("boardattachmentDTO", boardattachmentDTO);
		
		/* 화면 연결 */
		String path = "";
		if (freeDetail != null) {
			path = "/WEB-INF/views/board/detailFreeBoard.jsp";
			request.setAttribute("free", freeDetail);
		} else {
			path = "/WEB-INF/views/common/error-404.jsp";
			request.setAttribute("message", "문서서식 게시판 조회 실패!");
		}

		request.getRequestDispatcher(path).forward(request, response);

	}
}

