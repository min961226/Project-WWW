package com.qs.www.mng.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.board.model.dto.FreeDTO;
import com.qs.www.board.model.service.FreeService;
import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.mng.board.model.dto.MngNoticeDTO;
import com.qs.www.mng.board.model.service.MngNoticeService;

@WebServlet("/mng/board/notice/selectOne")
public class SelectOneMngNoticeBoardServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//게시판 상세보기
				System.out.println("확인");
				int no = Integer.parseInt(request.getParameter("no"));
				System.out.println(no);
				MngNoticeService mngnoticeService = new MngNoticeService();
				MngNoticeDTO mngnoticeDetail = mngnoticeService.selectMngNoticeDetail(no);
				
				System.out.println("freeDetail : " + mngnoticeDetail);
				
				String path = "";
				if(mngnoticeDetail != null) {
					path = "/WEB-INF/views/mngboard/detailNoticeBoard.jsp";
					request.setAttribute("mngnotice", mngnoticeDetail);
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
				
				MngNoticeService mngnoticeService = new MngNoticeService();
				MngNoticeDTO mngnoticeDetail = mngnoticeService.selectMngNoticeDetail(no);
				
				System.out.println("mngnoticeDetail : " + mngnoticeDetail);
				

				System.out.println(memberNo);

				System.out.println(no);
				
				MngNoticeDTO mngnotice = new MngNoticeDTO();

				mngnotice.setNo(no);

				mngnotice.setMember(memberNo);
				System.out.println(mngnotice);


//				int result = freeService.updateFree(free);

				String path = "";
//				if(result > 0) {
//					path = "/WEB-INF/views/common/success.jsp";
//					request.setAttribute("successCode", "updateFree");
//				} else {
//					path = "/WEB-INF/views/common/failed.jsp";
//					request.setAttribute("message", "자유게시판 수정에 실패");
//				}

//				request.getRequestDispatcher(path).forward(request, response);
			}
		}
