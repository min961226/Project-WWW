package com.qs.www.welfare.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.approval.model.dto.ApprovalLineDTO;
import com.qs.www.approval.model.service.ApprovalService;
import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.welfare.model.service.WelfareService;

@WebServlet("/welfare/LaptopRental/select")
public class SelectLaptopRentalWelfareServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		WelfareService welfareService = new WelfareService();
		HttpSession session = request.getSession();
		
		int itemNo = Integer.parseInt(request.getParameter("no"));						//신청 품목 번호
		String laptopStatus = welfareService.selectOneLaptop(itemNo); 					//대여 상태
		String itemName = welfareService.selectItemNameByItemNo(itemNo);				//신청 품목명

		System.out.println(laptopStatus);
		
		String path="";
		if("대여가능".equals(laptopStatus)) {												//대여가능할때

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
			request.setAttribute("itemNo", itemNo);
			request.setAttribute("itemName", itemName);
			request.setAttribute("lineList", lineList);
			
			path = "/WEB-INF/views/welfare/insertLaptopRental.jsp";

		}else {																				//대여중일때
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "alreadyInsertedLaptop");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
		
	}
}
