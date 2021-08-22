package com.qs.www.mypage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qs.www.main.model.dto.WorkingLogDTO;
import com.qs.www.main.model.dto.WorkingTypeDTO;
import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.mypage.model.dto.CommutingLogDTO;
import com.qs.www.mypage.model.service.MypageService;

@WebServlet("/mypage/commute/update")
public class UpdateCommuteFromServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		MemberInfoDTO memberInfo = (MemberInfoDTO) session.getAttribute("memberInfo");
		int memberNo = memberInfo.getMemberNo();
		int workCode = memberInfo.getWorkCode();
		String appWorkType = memberInfo.getAppWorkType();
		
		// 시간 정보 저장
		CommutingLogDTO commutingLog = new CommutingLogDTO();
		WorkingLogDTO workingLog = new WorkingLogDTO();
		LocalDateTime currentDateTime = LocalDateTime.now();
		String currentYearMonth = currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM"));
		String currentDay = currentDateTime.format(DateTimeFormatter.ofPattern("dd"));
		String currentTime = currentDateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
		String currentDayOfWeek = currentDateTime.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.KOREAN);
		
		workingLog.setWorkNo(workCode);
		workingLog.setWorkType(appWorkType);
		workingLog.setSelectedDate(currentDay);
		workingLog.setSelectedDayOfWeek(currentDayOfWeek);
		
		commutingLog.setMemberNo(memberNo);
		commutingLog.setYearMonth(currentYearMonth);
		commutingLog.setDay(currentDay);
		commutingLog.setOutTime(currentTime);
		commutingLog.setWorkingLog(workingLog);

		MypageService mypageService = new MypageService();
		
		WorkingTypeDTO workingType = mypageService.selectWorkingType(workingLog);
		workingLog.setWorkingType(workingType);
		
		int result = mypageService.updateCommuteFrom(commutingLog, currentDateTime);
		
		Gson gson = new GsonBuilder()
						.setPrettyPrinting()
						.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
						.serializeNulls()
						.disableHtmlEscaping()
						.create();
		
		response.setContentType("application/json, charset=UTF-8");
		
		String jsonString = "";

		if(result > 0) {
			jsonString = gson.toJson(commutingLog);
		} else {
			jsonString = gson.toJson("failed");
		}
		
		PrintWriter out = response.getWriter();
		
		out.print(jsonString);
		
		out.flush();
		out.close();
	}
}
