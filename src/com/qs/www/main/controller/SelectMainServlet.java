package com.qs.www.main.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.xml.internal.ws.client.RequestContext;

@WebServlet("/main")
public class SelectMainServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("연결 성공!!");
		
		System.out.println(request.getParameter("id"));
		
		request.getRequestDispatcher("/WEB-INF/views/main/main.jsp").forward(request, response);
		
	}
}
