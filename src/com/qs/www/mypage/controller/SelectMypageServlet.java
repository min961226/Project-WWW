package com.qs.www.mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.member.model.dto.MemberInfoDTO;

@WebServlet("/mypage/info/select")
public class SelectMypageServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String rrn = ((MemberInfoDTO) session.getAttribute("memberInfo")).getRrn();
		String address = ((MemberInfoDTO) session.getAttribute("memberInfo")).getAddress();
		
		String firstRrn = "";
		String lastRrn = "";
		
		if(rrn != null) {
			lastRrn = rrn.split("-")[1];
			firstRrn= rrn.split("-")[0];
		}
		
		String zipCode = "";
		String address1 = "";
		String address2 = "";
		
		if(address != null) {
			zipCode = address.split("\\$")[0];
			address1 = address.split("\\$")[1];
			address2 = address.split("\\$")[2];
		}
		
		session.setAttribute("firstRrn", firstRrn);
		session.setAttribute("lastRrn", lastRrn);
		session.setAttribute("zipCode", zipCode);
		session.setAttribute("address1", address1);
		session.setAttribute("address2", address2);
		
		request.getRequestDispatcher("/WEB-INF/views/mypage/info.jsp").forward(request, response);
	}
}
