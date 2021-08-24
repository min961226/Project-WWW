package com.qs.www.schedule.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.member.model.dto.DepartmentDTO;
import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.schedule.model.dto.HolidayLogDTO;
import com.qs.www.schedule.model.dto.TeamWorkingHourDTO;
import com.qs.www.schedule.model.service.ScheduleService;

@WebServlet("/schedule/workingHours/team/select")
public class SelectTeamWorkingHoursScheduleServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ScheduleService scheduleService = new ScheduleService();
		
		/* 기본정보 */
		HttpSession session = request.getSession();	
		MemberInfoDTO memberInfo = (MemberInfoDTO) session.getAttribute("memberInfo");
		String name = memberInfo.getName();
		String memberId = memberInfo.getMemberId();
		int memberNo = memberInfo.getMemberNo();
		String appWorkType = memberInfo.getAppWorkType();
		int workCode = memberInfo.getWorkCode();
		DepartmentDTO myDept = memberInfo.getDepartment();
		
		/* 오늘 날짜 */
		LocalDate currentDate = LocalDate.now();
		Date currentDateSql = Date.valueOf(currentDate);
		request.setAttribute("date", currentDate);
		
		/* 나의 부서와 오늘날짜 기준으로 검색해야 하므로 HashMap을 만들어준다 */
		HashMap<String, Object> deptAndDay = new HashMap<>();
		deptAndDay.put("myDeptCode", myDept.getDeptCode());
		deptAndDay.put("searchDate", currentDateSql);
		
		/* 같은부서 사람들의 명단을 뽑는다. 줄 수를 정하기위해 필요함 */
		List<MemberInfoDTO> teamMemberInfoList = scheduleService.selectAllTeamMember(deptAndDay);
		request.setAttribute("teamMemberInfoList", teamMemberInfoList);
		
		/* 같은부서 사람들의 근무 정보를 DTO로 담는다 */
		List<TeamWorkingHourDTO> teamWorkingHourList = scheduleService.selectteamWorkingHourList(deptAndDay);
		request.setAttribute("teamWorkingHourList", teamWorkingHourList);
		
		/* 같은부서 사람들 중 휴가가 있는 사람의 정보를 DTO로 담는다 */ 
		List<HolidayLogDTO> teamHolidayLogList = scheduleService.selectteamHolidayLogList(deptAndDay);
		request.setAttribute("teamHolidayLogList", teamHolidayLogList);
		
		String path = "/WEB-INF/views/schedule/checkWoringHoursOfTeam.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}

}
