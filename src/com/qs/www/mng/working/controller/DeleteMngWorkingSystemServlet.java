package com.qs.www.mng.working.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.mng.working.model.service.MngWorkingSystemService;

@WebServlet("/mng/workingSystem/delete")
public class DeleteMngWorkingSystemServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("근무제도 삭제");
		
		int deleteWorkCode = Integer.parseInt(request.getParameter("workCode"));
		System.out.println(deleteWorkCode);
		
		/* TBL_STANDARD_WORK에 update*/
		MngWorkingSystemService mngWorkingSystemService = new MngWorkingSystemService();
		int result = mngWorkingSystemService.updateStandardMngWorkingSystem(deleteWorkCode);
		
		/* 성공여부에 따라 success 혹은 fail로 넘겨줌 */
		String path = "";
		if(result > 0 ) {
			System.out.println("근무제 삭제 성공");			
			path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "deleteWorkType");

		} else {
			System.out.println("근무제 삭제 실패");			
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "deleteWorkType");
		}

		request.getRequestDispatcher(path).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
