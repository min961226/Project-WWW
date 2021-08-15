package com.qs.www.mypage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.mypage.model.dto.CommutingLogDTO;
import com.qs.www.mypage.model.service.MypageService;

@WebServlet("/mypage/commute/insert")
public class InsertCommuteToServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		int memberNo = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
		
		// 시간 정보 저장
		CommutingLogDTO commutingLog = new CommutingLogDTO();
		LocalDateTime currentDateTime = LocalDateTime.now();
		String currentYearMonth = currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM"));
		String currentDay = currentDateTime.format(DateTimeFormatter.ofPattern("dd"));
		String currentTime = currentDateTime.format(DateTimeFormatter.ofPattern("hh:mm"));
		
		commutingLog.setMemberNo(memberNo);
		commutingLog.setYearMonth(currentYearMonth);
		commutingLog.setDay(currentDay);
		commutingLog.setInTime(currentTime);
		
		MypageService mypageService = new MypageService();
		
		int result = mypageService.insertCommute(commutingLog);
		
		Gson gson = new GsonBuilder()
						.setPrettyPrinting()
						.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
						.serializeNulls()
						.disableHtmlEscaping()
						.create();
		
		response.setContentType("application/json, charset=UTF-8");

		String jsonString = "";
		
		jsonString = gson.toJson(commutingLog);
		
		PrintWriter out = response.getWriter();
		
		out.print(jsonString);
		
		out.flush();
		out.close();
	}
}
