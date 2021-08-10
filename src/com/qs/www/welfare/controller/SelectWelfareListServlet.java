package com.qs.www.welfare.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.welfare.model.service.WelfareService;

@WebServlet("/welfare/list/select")
public class SelectWelfareListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("복지 신청");

		WelfareService welfareService = new WelfareService();
		List<String> welfareList = welfareService.checkWelfareList();
		System.out.println(welfareList);
		if (welfareList.size() > 0) {
			for (int i = 0; i < welfareList.size(); i++) {
				switch (welfareList.get(i)) {
				case "야간교통비신청서":
					System.out.println("야간교통비신청서");
					request.getRequestDispatcher("/WEB-INF/views/welfare/welfareList.jsp").forward(request, response);
					break;
				case "경조사신청서":
					System.out.println("경조사신청서");
					request.getRequestDispatcher("/WEB-INF/views/welfare/welfareList.jsp").forward(request, response);

					break;
				case "자기개발비신청서":
					System.out.println("자기개발비신청서");
					break;
				case "기숙사입주신청서":
					System.out.println("기숙사입주신청서");

					break;
				case "회의실예약신청서":
					System.out.println("회의실예약신청서");
					break;
				case "노트북대여신청서":
					System.out.println("노트북대여신청서");
					break;
				default:
					break;
				}
			}
		} else {
			System.out.println("로그인 실패");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
