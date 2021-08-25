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
		
		int itemNo = Integer.parseInt(request.getParameter("no"));													//신청 품목 번호
		/* 해당 품목의 대여상태를가져온다 */
		String laptopStatus = welfareService.selectOneLaptop(itemNo);
		/* 해당 품목의 품목명을가져온다*/
		String itemName = welfareService.selectItemNameByItemNo(itemNo);											

		String path="";
		/* 대여가능할때 */
		if("대여가능".equals(laptopStatus)) {																			

			int memberNo = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();						//사번
			String name = ((MemberInfoDTO) session.getAttribute("memberInfo")).getName();							//이름				
			String deptName = ((MemberInfoDTO) session.getAttribute("memberInfo")).getDepartment().getDeptName();	//부서이름
			String jobName = ((MemberInfoDTO) session.getAttribute("memberInfo")).getJob().getJobName();			//직위
			List<ApprovalLineDTO> lineList = new ApprovalService().selectApprovalLine(memberNo);
			
			request.setAttribute("memberNo", memberNo);
			request.setAttribute("deptName", deptName);
			request.setAttribute("jobName", jobName);
			request.setAttribute("name", name);
			request.setAttribute("itemNo", itemNo);
			request.setAttribute("itemName", itemName);
			request.setAttribute("lineList", lineList);
			
			path = "/WEB-INF/views/welfare/insertLaptopRental.jsp";

		/* 대여중일 때 */
		}else {																										
			request.setAttribute("failedCode", "alreadyInsertedLaptop");
			path = "/WEB-INF/views/common/failed.jsp";
		}
		
		request.getRequestDispatcher(path).forward(request, response);
		
	}
}
