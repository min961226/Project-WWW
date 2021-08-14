package com.qs.www.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/free/delete")
public class DeleteFreeBoardServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  String path = "/WEB-INF/views/board/deleteFreeBoard.jsp";
		  
		  RequestDispatcher dipatcher = request.getRequestDispatcher(path);
		  
		  dipatcher.forward(request, response);	
	}
}
