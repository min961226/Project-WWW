package com.qs.www.mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.mypage.model.service.MypageService;

@WebServlet("/mypage/info/update")
public class UpdateMypageServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/views/mypage/updateInfo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String gender = request.getParameter("gender");
		java.sql.Date birthday = java.sql.Date.valueOf(request.getParameter("birthday"));
		String phone = request.getParameter("phone").replace("-", "");
		String address = request.getParameter("zipCode")
						+ "$" + request.getParameter("address1")
						+ "$" + request.getParameter("address2");
		
		HttpSession session = request.getSession();
		MemberInfoDTO memberInfo = (MemberInfoDTO) session.getAttribute("memberInfo");
		memberInfo.setGender(gender);
		memberInfo.setBirthday(birthday);
		memberInfo.setPhone(phone);
		memberInfo.setAddress(address);
		
		MypageService mypageService = new MypageService();
		
		int result = mypageService.updateInfo(memberInfo);
		String path = "";
		
		if(result > 0) {
			path = "/WEB-INF/views/common/success.jsp";
			
			request.setAttribute("successCode", "updateInfo");
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			
			request.setAttribute("failedCode", "updateInfo");
		}
		request.getRequestDispatcher(path).forward(request, response);
	}
}
