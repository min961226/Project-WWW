package com.qs.www.welfare.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.approval.model.dto.ApprovalLineDTO;
import com.qs.www.approval.model.service.ApprovalService;
import com.qs.www.board.model.service.NoticeService;
import com.qs.www.common.paging.Pagenation;
import com.qs.www.common.paging.SelectCriteria;
import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.welfare.model.dto.DomitoryListDTO;
import com.qs.www.welfare.model.dto.LaptopDTO;
import com.qs.www.welfare.model.dto.MemberOverTimeLogDTO;
import com.qs.www.welfare.model.dto.SeminarRoomDTO;
import com.qs.www.welfare.model.service.WelfareService;


@WebServlet("/welfare/list/selected")
public class SelectedWelfareServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String selectedWelfare = request.getParameter("selectedWelfare");
		WelfareService welfareService = new WelfareService();
		HttpSession session = request.getSession();
		
		int memberNo = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();										//사번
		String name = ((MemberInfoDTO) session.getAttribute("memberInfo")).getName();											//이름
		String deptName = ((MemberInfoDTO) session.getAttribute("memberInfo")).getDepartment().getDeptName();					//부서번호
		String jobName = ((MemberInfoDTO) session.getAttribute("memberInfo")).getJob().getJobName();							//직위
		List<ApprovalLineDTO> lineList = new ApprovalService().selectApprovalLine(memberNo);									//라인명
		
		request.setAttribute("memberNo", memberNo);
		request.setAttribute("deptName", deptName);
		request.setAttribute("jobName", jobName);
		request.setAttribute("name", name);
		request.setAttribute("lineList", lineList);
		
		String path = "";
		switch (selectedWelfare) {
		case "야간교통비신청":
			/* 추가근무 결재 내역을 불러온다. */
			List<MemberOverTimeLogDTO> memberOverTimeLog = welfareService.checkNightTrans(memberNo);
			request.setAttribute("memberOverTimeLog", memberOverTimeLog);
			path = "/WEB-INF/views/welfare/insertNightTransportation.jsp";
			break;
		case "경조사신청":
			path = "/WEB-INF/views/welfare/insertFamilyEvent.jsp";
			break;
		case "자기개발비신청":
			/* 자기개발비 유형목록을 불러온다.*/
			List<String> selfDevList = welfareService.checkSelfDevList();
			request.setAttribute("selfDevList", selfDevList);
			path = "/WEB-INF/views/welfare/insertSelfDevelopment.jsp";
			break;
		case "기숙사입주신청":
			/* 기숙시 목록을 불러온다 */
			List<DomitoryListDTO> domitoryList = welfareService.selectDomitory();
			request.setAttribute("domitoryList", domitoryList);
			path = "/WEB-INF/views/welfare/domitory.jsp";
			break;
		case "회의실예약신청":
			/* 세미나실 예약현황을 불러온다 */
			List<SeminarRoomDTO> seminarRoomList = welfareService.selectSeminarRoom();
			request.setAttribute("seminarRoomList", seminarRoomList);
			path = "/WEB-INF/views/welfare/seminarRoom.jsp";
			break;
		case "복지물품대여신청":
			/* 복지물품 리스트를 불런온다. */
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
