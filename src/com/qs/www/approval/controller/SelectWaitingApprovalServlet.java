package com.qs.www.approval.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.approval.model.service.ApprovalService;
import com.qs.www.board.model.service.FreeService;
import com.qs.www.common.paging.Pagenation;
import com.qs.www.common.paging.SelectCriteria;
import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.schedule.model.dto.ApproverPerReportDTO;
import com.qs.www.schedule.model.dto.ReportDTO;

@WebServlet("/approval/waiting/select")
public class SelectWaitingApprovalServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		int no = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
		
		// 1. 상신별 결재자에서 회원번호 = '내회원번호', 결재상태 = '대기'인 상신별 결재자 테이블 리스트(MyTurnApproverPerReport) 가져오기
		
		List<ApproverPerReportDTO> APRList = new ApprovalService().selectMyTurnAPR(no);
		
		// 2. 상신별 결재자 테이블 리스트(MyTurnApproverPerReport)에 든 결재번호에 해당되는 상신 테이블리스트(MyWaitingApproval)가져오기
		List<Integer> reportNoList = new ArrayList<Integer>();
		int reportNo = 0;
		for(ApproverPerReportDTO APR : APRList) {
			reportNo = APR.getReportNo();	
			reportNoList.add(reportNo);
		}
		if(reportNoList.isEmpty()) {
			reportNoList.add(0);
		}
		//////////////////////////////////////////////////////////////////////////////////////
		/* 목록보기를 눌렀을 시 가장 처음에 보여지는 페이지는 1페이지이다.
		 * 파라미터로 전달되는 페이지가 있는 경우 currentPage는 파라미터로 전달받은 페이지 수 이다.
		 * */
		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;
		
		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}
		
		/* 0보다 작은 숫자값을 입력해도 1페이지를 보여준다 */
		if(pageNo <= 0) {
			pageNo = 1;
		}
		
		/* 검색에 사용할것*/
		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");
		
		Map<String, String> searchMap = new HashMap<>();
		searchMap.put("searchCondition", searchCondition);
		searchMap.put("searchValue", searchValue);
		
		HashMap<String, Object> countMap = new HashMap<>();
		countMap.put("reportNoList", reportNoList);
		countMap.put("searchMap", searchMap);
		
		
		Pagenation pagenation = new Pagenation();
		
		
		//totalCount 는 DB에 가서 총 게시물 수를 세어와야 함 count(*) 중, where 삭제안된거.
		int totalCount = new ApprovalService().selectWaitingAPPCount(countMap);

		//limit는 한 페이지에서 보여지는 게시물 수
		int limit = 10;
		
		//buttonAmount는 한번에 보여줄 버튼 수
		int buttonAmount = 5;
		
		SelectCriteria selectCriteria = null;
		
		if(searchCondition != null && !"".equals(searchCondition)) {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
		} else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		}
		System.out.println(selectCriteria);
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		

		HashMap<String, Object> selectedInfoMap = new HashMap<>();
		selectedInfoMap.put("reportNoList", reportNoList);
		selectedInfoMap.put("selectCriteria", selectCriteria);
		
		List<ReportDTO> reportList = new ApprovalService().selectReportDetail(selectedInfoMap);
		
		request.setAttribute("reportList", reportList);
		request.setAttribute("selectCriteria", selectCriteria);
		request.getRequestDispatcher("/WEB-INF/views/approval/waitingApproval.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
