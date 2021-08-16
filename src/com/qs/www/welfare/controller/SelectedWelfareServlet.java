package com.qs.www.welfare.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.approval.model.dto.ApprovalLineDTO;
import com.qs.www.approval.model.service.ApprovalService;
import com.qs.www.board.model.service.NoticeService;
import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.welfare.model.dto.DomitoryListDTO;
import com.qs.www.welfare.model.dto.LaptopDTO;
import com.qs.www.welfare.model.dto.MemberOverTimeLogDTO;
import com.qs.www.welfare.model.dto.SeminarRoomDTO;
import com.qs.www.welfare.model.service.WelfareService;

import jdk.internal.misc.FileSystemOption;

@WebServlet("/welfare/list/selected")
public class SelectedWelfareServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String selectedWelfare = request.getParameter("selectedWelfare");
		System.out.println(selectedWelfare);
		System.out.println("복지선택완료");
		
		WelfareService welfareService = new WelfareService();
		HttpSession session = request.getSession();

		int memberNo = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
		String name = ((MemberInfoDTO) session.getAttribute("memberInfo")).getName();
		String deptName = ((MemberInfoDTO) session.getAttribute("memberInfo")).getDepartment().getDeptName();
		String jobName = ((MemberInfoDTO) session.getAttribute("memberInfo")).getJob().getJobName();
		List<ApprovalLineDTO> lineList = new ApprovalService().selectApprovalLine(memberNo);
		
		System.out.println(memberNo);
		System.out.println(name);
		System.out.println(deptName);
		System.out.println(jobName);
		System.out.println(lineList);
		
		request.setAttribute("memberNo", memberNo);
		request.setAttribute("deptName", deptName);
		request.setAttribute("jobName", jobName);
		request.setAttribute("name", name);
		request.setAttribute("lineList", lineList);
		
		
		String path = "";
		switch (selectedWelfare) {
		case "야간교통비신청":
			List<MemberOverTimeLogDTO> memberOverTimeLog = welfareService.checkNightTrans(memberNo);
			request.setAttribute("memberOverTimeLog", memberOverTimeLog);
			path = "/WEB-INF/views/welfare/insertNightTransportation.jsp";
			break;
		case "경조사신청":
			path = "/WEB-INF/views/welfare/insertFamilyEvent.jsp";
			break;
		case "자기개발비신청":
			List<String> selfDevList = welfareService.checkSelfDevList();
			System.out.println(selfDevList);
				if(selfDevList != null) {
					request.setAttribute("selfDevList", selfDevList);
					path = "/WEB-INF/views/welfare/insertSelfDevelopment.jsp";
				} else {
					request.setAttribute("message", "복지 목록조회 실패!");
					path = "/WEB-INF/views/common/error-500.jsp";
				}
			break;
		case "기숙사입주신청":
			List<DomitoryListDTO> domitoryList = welfareService.selectDomitory();
			request.setAttribute("domitoryList", domitoryList);
			path = "/WEB-INF/views/welfare/domitory.jsp";
			break;
		case "회의실예약신청":
			List<SeminarRoomDTO> seminarRoomList = welfareService.selectSeminarRoom();
			request.setAttribute("seminarRoomList", seminarRoomList);
			path = "/WEB-INF/views/welfare/seminarRoom.jsp";
			break;
		case "노트북대여신청":
			List<LaptopDTO> laptopList = welfareService.selectLaptopList();
			request.setAttribute("laptopList", laptopList);
			path = "/WEB-INF/views/welfare/laptopRental.jsp";
			break;
		default:
			path = "/welfare/list/select";
			break;
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

}
