package com.qs.www.mng.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.qs.www.common.attachment.model.service.BoardAttachmentService;
import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.mng.board.model.dto.MngFormDTO;
import com.qs.www.mng.board.model.dto.MngNoticeDTO;
import com.qs.www.mng.board.model.service.MngFormService;
import com.qs.www.mng.board.model.service.MngNoticeService;

/*--------------------서블릿 3.0 파트 api 사용을 위한 multipartconfig 참조 선언 --------------*/
@MultipartConfig(location = "C:\\WWW\\Project-WWW\\web\\upload", // 임시저장 경로
		maxFileSize = 1024 * 1024 * 10, // 파일 허용 최대 크기
		maxRequestSize = 1024 * 1024 * 10 * 5, // 파일 허용 최대 갯수
		fileSizeThreshold = 1024)
@WebServlet("/mng/board/form/update")
public class UpdateMngFormBoardServlet extends HttpServlet {

	private static final String ATTACHES_BOARD = "C:\\WWW\\Project-WWW\\web\\upload\\board"; // 경로지정

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println(no);

		MngFormService mngformService = new MngFormService();
		MngFormDTO mngform = mngformService.selectMngFormDetail(no);

		String path = "";

		System.out.println(mngform);
		System.out.println(path);
		System.out.println(no);
		if (mngform != null) {
			path = "/WEB-INF/views/mngboard/updateFormBoard.jsp";
			request.setAttribute("mngform", mngform);
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("message", "문서서식 게시판 수정 조회하기 실패!");
		}

		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BoardAttachmentService boardattachmentService = new BoardAttachmentService();
		HttpSession session = request.getSession();
		int no = Integer.parseInt(request.getParameter("no"));
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		int memberNo = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
		System.out.println("출력테스트");
		System.out.println("no : " + no);
		System.out.println(title);
		System.out.println(body);

		MngFormDTO mngform = new MngFormDTO();

		mngform.setNo(no);
		mngform.setTitle(title);
		mngform.setBody(body);
		mngform.setMember(memberNo);
		System.out.println(mngform);

		MngFormService mngformService = new MngFormService();
		int result = mngformService.updateMngForm(mngform);

		/*---------------------------------------------------------------------------파일 업로드---------------------------------------------------------------------*/
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String contentType = request.getContentType();
		Map<String, Object> fileMap = new HashMap<>();
		int resultFileUpload = 0;

		if (contentType != null && contentType.toLowerCase().startsWith("multipart/")) { // formdata를 받아오고 타입이 콘텐트
																							// ㅇ타입인경우에만 진입
			// getParts()를 통해 Body에 넘어온 데이터들을 각각의 Part로 쪼개어 리턴
			Collection<Part> parts = request.getParts();

			for (Part part : parts) {
				System.out.printf("파라미터 명 : %s, contentType :  %s,  size : %d bytes \n", part.getName(), // 파트로 넘어온 값들
																											// 전부 출력
						part.getContentType(), part.getSize());

				if (part.getHeader("Content-Disposition").contains("filename=")) {
					String fileName = extractFileName(part.getHeader("Content-Disposition"));

					if (fileName.length() > 0) { // 첨부한 파일이 존재하지 않을때(파일을 미첨부시, 파일 첨부한 값이 없을때)
						System.out.println("fileName : " + fileName);
						System.out.println(part.getHeader("Content-Disposition"));

						int dot = fileName.lastIndexOf(".");
						String ext = fileName.substring(dot);
						String randomFileName = UUID.randomUUID().toString().replace("-", "") + ext; // 파일 이름 랜덤 부여
						System.out.println(randomFileName);
						if (part.getSize() > 0) { // 업로드 할때 파일 크기가 0 보다 작을 수 없다.
							System.out.println(part.getHeaderNames());
							System.out.println(no);
							fileMap.put("boardNo", no);
							fileMap.put("attachmentNo", 1);
							fileMap.put("originFileName", fileName);
							fileMap.put("savedFileName", randomFileName);
							fileMap.put("thumbnailPath", ATTACHES_BOARD);
							fileMap.put("savePath", ATTACHES_BOARD);

							System.out.printf("업로드 파일 명 : %s  \n", randomFileName);
							System.out.println(ATTACHES_BOARD + File.separator + fileName);

							part.write(ATTACHES_BOARD + File.separator + randomFileName); // 파일 경로에 따른 파일 추가
							part.delete(); // 임시 파일 삭제

							System.out.println("map:" + fileMap);
							System.out.println(resultFileUpload);
							resultFileUpload = boardattachmentService.insertFileUpload(fileMap);
						}
					} else {
						resultFileUpload = -1;
					}
				} else {
					String formValue = request.getParameter(part.getName()); // 파트로 찢긴값들 파일이 아닐경우 처리하는 파트
					System.out.printf("name : %s, value : %s  \n", part.getName(), formValue);
				}
			}
			System.out.println("<h1>업로드 완료</h1>");
		} else {
			System.out.println("<h1>enctype이 multipart/form-data가  아님</h1>");
		}

		String path = "";
		if (resultFileUpload == -1) { // 파일첨부를 하지 않았을때는 result값을 더해주면안된다.
			if (result > 0) {
				path = "/WEB-INF/views/common/success.jsp";
				request.setAttribute("successCode", "updateMngForm");
			} else {
				path = "/WEB-INF/views/common/failed.jsp";
				request.setAttribute("message", "문서서식  게시글 등록에 실패하셨습니다.");
			}
		} else {
			if (result > 0 && resultFileUpload > 0) { // 파일을첨부하였고 그 파일첨부가 성공하였을경우 페이지로 이동한다.
				path = "/WEB-INF/views/common/success.jsp";
				request.setAttribute("successCode", "updateMngForm");
			} else {
				path = "/WEB-INF/views/common/failed.jsp";
				request.setAttribute("message", "문서서식 게시글 등록에 실패하셨습니다.");
			}
		}

		request.getRequestDispatcher(path).forward(request, response);

	}
	/*---------------------------------------------------------------------------파일 업로드---------------------------------------------------------------------*/

	private String extractFileName(String partHeader) {
		for (String cd : partHeader.split(";")) {
			if (cd.trim().startsWith("filename")) {
				String fileName = cd.substring(cd.indexOf("=") + 1).trim().replace("\"", "");
				int index = fileName.lastIndexOf(File.separator);
				return fileName.substring(index + 1);
			}
		}
		return null;
	}

	/*---------------------------------------------------------------------------파일 업로드---------------------------------------------------------------------*/

}
