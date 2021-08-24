package com.qs.www.approval.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.approval.model.dto.ApprovalLineDTO;
import com.qs.www.approval.model.dto.ApproverDTO;
import com.qs.www.approval.model.service.ApprovalService;
import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.schedule.model.dto.WorkingDocumentItemDTO;

@WebServlet("/approval/line/insert")
public class InsertApprovalLineServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int memberNo = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
		List<MemberInfoDTO> memberList = new ApprovalService().selectMemberList();
		request.setAttribute("memberNo",memberNo);
		request.setAttribute("memberList", memberList);
		request.getRequestDispatcher("/WEB-INF/views/approval/insertApprovalLine.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* "결재자번호/결재자이름" 형태로 저장되있는 문자열, 스플릿으로 나눠 사용 */
		String appNN1 = request.getParameter("app1");                                                                         
		String appNN2 = request.getParameter("app2");
		String appNN3 = request.getParameter("app3");
		String appNN4 = request.getParameter("app4");
		String[] appList1 = appNN1.split("/");
		String[] appList2 = appNN2.split("/");
		String[] appList3 = appNN3.split("/");
		String[] appList4 = appNN4.split("/");
		String appSN1 = appList1[0];
		String appSN2 = appList2[0];
		String appSN3 = appList3[0];
		String appSN4 = appList4[0];
		int priority = 1;
		int lineNo = new ApprovalService().selectLineNum();
		
		String path = "";
		if(appSN1.equals(appSN2) || appSN1.equals(appSN3) || appSN1.equals(appSN4) 
			|| appSN2.equals(appSN3) || appSN2.equals(appSN4) || appSN3.equals(appSN4)) {
			System.out.println("겹치자너!!!!!!!!!!!!");
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "insertAppLine");
			
			request.getRequestDispatcher(path).forward(request, response);
		}else {
			int appNo1 = Integer.parseInt(appSN1);                                                                               //결재자번호 int형으로 형변환
			ApproverDTO app1 = new ApproverDTO();
			
			app1.setMemberNo(appNo1);
			app1.setApproverName(appList1[1]);                                                                                   //appList1[1]은 결재자 이름부분
			app1.setPriority(priority);
			app1.setLineNo(lineNo);
			app1.setApproverType("결재");
			priority++;
				
			System.out.println(priority);
			List<ApproverDTO> approverList = new ArrayList<ApproverDTO>();
			List<ApproverDTO> referrerList = new ArrayList<ApproverDTO>();
			
			approverList.add(app1);
			
			/* a0, a1, a2라는 건 각각2,3,4번 결재자는 선택 안 되었다는것을 의미, 즉 선택되었다면 if문 안에서 결재자리스트에 add */
			if(!appSN2.equals("a0")) {    
				ApproverDTO app2 = new ApproverDTO();
				int appNo2 = Integer.parseInt(appSN2);
				app2.setMemberNo(appNo2);
				app2.setLineNo(lineNo);
				app2.setApproverName(appList2[1]);
				if(request.getParameter("appType2").equals("결재")) {                                                             //결재방법이 '결재'면 approverList에 
					app2.setPriority(priority);
					app2.setApproverType("결재");
					priority++;
					approverList.add(app2);
				} else {                                                                                                         // 결재방법이 '참조'면 referrerList에 저장
					app2.setApproverType("참조");
					referrerList.add(app2);
				}
			}
			if(!appSN3.equals("a1")) {
				ApproverDTO app3 = new ApproverDTO();
				int appNo3 = Integer.parseInt(appSN3);
				app3.setMemberNo(appNo3);
				app3.setApproverName(appList3[1]);
				app3.setLineNo(lineNo);
				if(request.getParameter("appType3").equals("결재")) {
					app3.setPriority(priority);
					app3.setApproverType("결재");
					priority++;
					approverList.add(app3);
				} else {
					app3.setApproverType("참조");
					referrerList.add(app3);
				}
			}
			if(!appSN4.equals("a2")) {
				ApproverDTO app4 = new ApproverDTO();
				int appNo4 = Integer.parseInt(appSN4);
				app4.setMemberNo(appNo4);
				app4.setApproverName(appList4[1]);
				app4.setLineNo(lineNo);
				if(request.getParameter("appType4").equals("결재")) {
					app4.setPriority(priority);
					app4.setApproverType("결재");
					priority++;
					approverList.add(app4);
				} else {
					app4.setApproverType("참조");
					referrerList.add(app4);
				}
			}
			
			HttpSession session = request.getSession();
			int memberNo = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
			String workType = request.getParameter("workType");
			String lineName = request.getParameter("lineName");
			
			ApprovalLineDTO line = new ApprovalLineDTO ();
			line.setMemberNo(memberNo);
			line.setWorkType(workType);
			line.setLineName(lineName);
			
			System.out.println(approverList);
			System.out.println(referrerList);
			System.out.println(line);
			
			
			int result1 = new ApprovalService().insertLine(line);
			int result2 = 0;
			for(ApproverDTO approver : approverList) {
				result2 = new ApprovalService().insertApprover(approver);	
			}
			if(!referrerList.isEmpty()) {
				for(ApproverDTO approver : referrerList) {
					result2 = new ApprovalService().insertApprover(approver);
				}
			}
			
			if(result1 > 0 && result2 > 0) {
	            path = "/WEB-INF/views/common/success.jsp";
				request.setAttribute("successCode", "insertAppLine");
				
			} else {
				path = "/WEB-INF/views/common/failed.jsp";
				request.setAttribute("failedCode", "insertAppLine");
			}
			
			request.getRequestDispatcher(path).forward(request, response);
			
			
		}
	}
}
