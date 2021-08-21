package com.qs.www.approval.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.approval.model.dto.ApproverLogPerReportDTO;
import com.qs.www.approval.model.service.ApprovalService;
import com.qs.www.common.attachment.model.dto.AttachmentDTO;
import com.qs.www.common.attachment.model.service.AttachmentService;
import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.mng.welfare.model.dto.ItemDTO;
import com.qs.www.schedule.model.dto.ApproverPerReportDTO;
import com.qs.www.schedule.model.dto.CustomWorkDTO;
import com.qs.www.schedule.model.dto.CustomWorkTimeDTO;
import com.qs.www.schedule.model.dto.HolidayLogDTO;
import com.qs.www.schedule.model.dto.HolidayUseInfoDTO;
import com.qs.www.schedule.model.dto.MemberWorkLogDTO;
import com.qs.www.schedule.model.dto.OvertimeLogDTO;
import com.qs.www.schedule.model.dto.ReportDTO;
import com.qs.www.schedule.model.dto.WorkingDocumentItemDTO;
import com.qs.www.schedule.model.service.HolidayService;
import com.qs.www.schedule.model.service.ScheduleService;
import com.qs.www.welfare.model.dto.DomitoryWaitListDTO;
import com.qs.www.welfare.model.service.WelfareService;

@WebServlet("/approval/waiting/selectOne")
public class SelectOneWaitingApprovalServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		int memberNo = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();

		int no = Integer.parseInt(request.getParameter("no"));
		// 대기함에서 선택한 게시물의 살세정보 가져오기
		ReportDTO selectedReport = new ApprovalService().selectOneReportDetail(no);

		/* 파일 첨부 DTO 서비스 실행 reportNo로 갖고옴 */
		AttachmentDTO attachmentDTO = new AttachmentService().selectAttachmentByReportNo(selectedReport.getReportNo()); // reportNo로
																														// 값을
																														// 갖고옴
		System.out.println(attachmentDTO);

		List<WorkingDocumentItemDTO> itemList = new ApprovalService().selectReportItemList(no);
		List<ApproverLogPerReportDTO> ALPRList = new ApprovalService().selectALPRList(no);

		// 등록날짜를 보존기간으로 바꾸기
		Date reportDate = selectedReport.getReportDate();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String str = format.format(reportDate);

		String[] arrayDate = str.split("-");
		int yearPlusFive = Integer.parseInt(arrayDate[0]) + 5;
		String endDate = yearPlusFive + "-" + arrayDate[1] + "-" + arrayDate[2];

		request.setAttribute("endDate", endDate);
		request.setAttribute("selectedReport", selectedReport);
		request.setAttribute("itemList", itemList);
		request.setAttribute("ALPRList", ALPRList);
		request.setAttribute("attachmentDTO", attachmentDTO);
		// 결재의 문서종류에 따라 항목명들을 키값으로 지정해서 request에 넣기
		if (selectedReport.getDocumentNo() < 4) {
			request.setAttribute("body", itemList.get(1).getItemContent());
			if (selectedReport.getDocumentNo() == 3) {
				request.setAttribute("contractDate", itemList.get(2).getItemContent());
				request.setAttribute("payDate", itemList.get(3).getItemContent());
				request.setAttribute("productNo", itemList.get(4).getItemContent());
			}

		}
		request.getRequestDispatcher("/WEB-INF/views/approval/detailWaitingApproval.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		WelfareService welfareService = new WelfareService();
		
		int memberNo = ((MemberInfoDTO) session.getAttribute("memberInfo")).getMemberNo();
		int reportNo = Integer.parseInt(request.getParameter("no"));
		String ApproverStatus = (String)request.getParameter("radio"); //'승인'인지 '반려'
		String opinion = (String)request.getParameter("opinion");
		
		ApproverPerReportDTO thisAPR = new ApproverPerReportDTO();
		thisAPR.setMemberNo(memberNo);
		thisAPR.setReportNo(reportNo);
		thisAPR.setApproverType(ApproverStatus);
		
		// 1.상신에대란 결재내역 추가
		ApproverLogPerReportDTO aLPR = new ApproverLogPerReportDTO();
		aLPR.setReportNo(reportNo);
		aLPR.setMemberNo(memberNo);
		aLPR.setAppNote(opinion);
		aLPR.setAppStatus(ApproverStatus);
		
		ApprovalService approvalService = new ApprovalService();
		int result1 = approvalService.insertALPR(aLPR);
		
		// 2. 결재한 상신번호에 대한 내 결재자사번의 상신별결재자가져오기
		ApproverPerReportDTO thisTurnAPR = approvalService.selectThisTurnAPR(thisAPR);
		System.out.println(thisTurnAPR);
		
		int nextPriority = thisTurnAPR.getPriority() + 1; //다음 우선 순위 만들기
		System.out.println(nextPriority);
		
		// 3.  내 결재자사번의 상신별결재자 결재상태 '승인'혹은 '반려'로 바꾸기
		int result2 =  approvalService.updateThisTurnAPR(thisAPR);
		
		int result3 = 0;
		if(ApproverStatus.equals("승인")) {
			// 4.a.1 '승인'했으면  내 결재자사번의 상신별결재에서 결재한 상신번호에 대한 다음 우선순위인 사람의 결재상태를 '대기'로 변경
			thisAPR.setPriority(nextPriority);
			result3 =  approvalService.updateNextTurnAPR(thisAPR);
			// 4.a.2 변경하고 리턴값이 0(다음결재자가 존재하지 않음)일 시 결재한 상신번호에 대한 상신테이블의 상태를 '승인'으로 변경
			if(result3 == 0) {
				result3 = approvalService.finishAppReport(thisAPR);
				
				
				//판단에 필요한 정보 가져오기
				ReportDTO selectedReport  = approvalService.selectOneReportDetail(reportNo);
				List<WorkingDocumentItemDTO> itemList = approvalService.selectReportItemList(reportNo);
				for(WorkingDocumentItemDTO dto : itemList) {
					System.out.println("순서대로 나오나...?");
					System.out.println("문서의 item 리스트 : " + dto);
				}
				
 				/* 5. 근무제쪽 테이블에도 insert 해주기 */
				if(selectedReport.getDocumentNo() == 4 || selectedReport.getDocumentNo() == 5) {
					ScheduleService scheduleService = new ScheduleService();
				
					/* 5-1. 정규근무신청이라면 */
					if(selectedReport.getDocumentNo() == 4) {
						System.out.println("------(정규)근무신청서");
						
						/* 5-1-a. TBL_MEMBER_WORK_LOG에 사용할 값을 가져온다 */
						String startDay = itemList.get(2).getItemContent();
						String endDay = itemList.get(3).getItemContent();
		 				
						String[] arrayEndDate = startDay.split("-");   
						int dayPlusOne = Integer.parseInt(arrayEndDate[2]) + 1;
						String endDate = arrayEndDate[0]  + "-" + arrayEndDate[1] + "-" + dayPlusOne;
						
		 				/* 5-1-b. 첫번째 변경이력. startDay 사용 */
		 					//memberWorkLogDTO.setStartDay에 맞춰 sql.Date형식으로 바꿔준다
		 				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
		 				LocalDate startDayLoc = LocalDate.parse(startDay, formatter);
		 				java.sql.Date startDaySql = java.sql.Date.valueOf(startDayLoc);
		 				
		 				MemberWorkLogDTO memberWorkLogDTO = new MemberWorkLogDTO();
		 				memberWorkLogDTO.setMemberNo(selectedReport.getMemberNo());								//상신자사번
		 				memberWorkLogDTO.setWorkType(itemList.get(5).getItemContent()); 						//변경후 근무제유형
		 				memberWorkLogDTO.setWorkNo(Integer.parseInt(itemList.get(1).getItemContent()));			//변경후 근무제번호
		 				memberWorkLogDTO.setStartDay(startDaySql);												//변경일자
		 				memberWorkLogDTO.setChangeReason(itemList.get(4).getItemContent());						//변경사유
		 				
		 				System.out.println("memberWorkLogDTO : " + memberWorkLogDTO);	 				
		 				int result5 = scheduleService.applyWorkingSystemMemberWorkLog(memberWorkLogDTO);
		 				
		 				
		 				/* 5-2-c. 두번째 변경이력. endNextDate 사용. 다시 기본근태로 돌림 */
		 					//memberWorkLogDTO.setStartDay에 맞춰 sql.Date형식으로 바꿔준다
		 				LocalDate endDateLoc = LocalDate.parse(endDate, formatter);
		 				java.sql.Date endDateSql = java.sql.Date.valueOf(endDateLoc);
		 				
		 				MemberWorkLogDTO memberWorkLogDTO2 = new MemberWorkLogDTO();
		 				memberWorkLogDTO2.setMemberNo(selectedReport.getMemberNo());							//상신자사번
		 				memberWorkLogDTO2.setWorkType("표준"); 													//변경후 근무제유형
		 				memberWorkLogDTO2.setWorkNo(1);															//변경후 근무제번호
		 				memberWorkLogDTO2.setStartDay(endDateSql);												//변경일자
		 				memberWorkLogDTO2.setChangeReason("이전 근태신청의 기간만료");									//변경사유
		 				
		 				System.out.println("memberWorkLogDTO2 : " + memberWorkLogDTO2);
		 				int result6 = scheduleService.applyWorkingSystemMemberWorkLog(memberWorkLogDTO2);
		 				
		 				
						/* 5-1-d. 커스텀근무제라면 커스텀근무제에도 추가 */
		 				String workType = itemList.get(5).getItemContent(); //근무제가 뭔지 알아온다
		 				if(workType.equals("커스텀")) {
		 					System.out.println("------커스텀근무신청서");
		 					
		 					//생성할 workNo의 LastNum 가져오기
		 					int customWorkNum = scheduleService.selectCustomWorkNum();
		 					
		 					//사원별커스텀근무제(TBL_CUSTOM_WORK) 에 insert
		 					CustomWorkDTO customWorkDTO = new CustomWorkDTO(); //휴식시간을 넣을지말지 몰라서.. 일단 DTO로 만들었다.
		 					customWorkDTO.setMemberNo(selectedReport.getMemberNo());
		 					int result7 = scheduleService.insertCustomWork(customWorkDTO);
		 					
		 					//커스텀근무제요일별출퇴근시간(TBL_CUSTOM_WORKTIME)에도 insert
		 					CustomWorkTimeDTO customWorkTimeDTO = new CustomWorkTimeDTO();
		 					customWorkTimeDTO.setWorkNo(customWorkNum);
		 					String[] dayOfWeekArr = new String[]{"월", "화", "수", "목", "금"};
		 					for(int i = 0; i < dayOfWeekArr.length; i++ ) {
		 						customWorkTimeDTO.setDayOfWeek(dayOfWeekArr[i]);
		 						customWorkTimeDTO.setCheckInTime(itemList.get(i * 2 + 6).getItemContent());
		 						customWorkTimeDTO.setCheckOutTime(itemList.get(i * 2 + 7).getItemContent());
		 						int result8 = scheduleService.insertCustomWorktime(customWorkTimeDTO);
		 						System.out.println(result8);
		 					}
		 				}
		 				
					}
	 				
	 				
	 				/* 5-2. 초과근무신청이라면 */
					if(selectedReport.getDocumentNo() == 5) {
						System.out.println("------초과근무신청서");
						
						//초과근무제내역 (TBL_MEMBER_OVERTIME_LOG)에  insert 
						OvertimeLogDTO overtimeLogDTO = new OvertimeLogDTO();
						overtimeLogDTO.setOvertimeReportNo(reportNo);
						overtimeLogDTO.setMemberNo(memberNo);
						overtimeLogDTO.setOvertimeStartDay(java.sql.Date.valueOf(itemList.get(1).getItemContent()));
						overtimeLogDTO.setOvertimeEndDay(java.sql.Date.valueOf(itemList.get(2).getItemContent()));
						overtimeLogDTO.setOvertimeDuring(Integer.parseInt(itemList.get(3).getItemContent()));
						overtimeLogDTO.setOvertimeStartTime(itemList.get(5).getItemContent());
						overtimeLogDTO.setOvertimeEndTime(itemList.get(6).getItemContent());
						int result = scheduleService.insertOvertimeLog(overtimeLogDTO);
						
					}
				
				}
				
				
				/* 6. 휴가신청이라면 */
				if(selectedReport.getDocumentNo() == 6) {
					System.out.println("------휴가신청서");
					
					HolidayService holidayService = new HolidayService();
					
					/* 6-1. 휴가부여사용내역에서 사용할 log Num의 lastNum을 미리 가져오기 */
					int holidayLogNo = holidayService.selectHolidayLogNum();
					
					/* 6-2. 휴가부여사용내역 (TBL_MEMBER_HOLIDAY_LOG)에 추가 */					
					HolidayLogDTO holidayLogDTO = new HolidayLogDTO();
					holidayLogDTO.setMemberNo(selectedReport.getMemberNo()); 				//사번
					holidayLogDTO.setLogOccurDate(selectedReport.getReportDate()); 			//휴가내역발생일자=상신일
					holidayLogDTO.setLogNote(itemList.get(6).getItemContent()); 			//비고
					holidayLogDTO.setLogType("사용"); 										//내역구분
					holidayLogDTO.setHolidayCode(Integer.parseInt(itemList.get(1).getItemContent())); //휴가코드
					holidayLogDTO.setHolidayDuringDate(itemList.get(7).getItemContent()); 	//기간일수
					System.out.println("holidayLogDTO : " + holidayLogDTO);
					
					int result4 = holidayService.insertHolidayLog(holidayLogDTO);
					System.out.println(result4);
					
					/* 6-3. 휴가부여사용내역 (TBL_HOLIDAY_USE_INFO)에 추가 */
					HolidayUseInfoDTO holidayUseInfoDTO = new HolidayUseInfoDTO();
					holidayUseInfoDTO.setHolidayLogNo(holidayLogNo);						//휴가내역번호(6-1에서 가져옴)
					holidayUseInfoDTO.setHolidayStartDay(java.sql.Date.valueOf(itemList.get(2).getItemContent()));
					holidayUseInfoDTO.setHolidayStartDayAllday(itemList.get(3).getItemContent());
					holidayUseInfoDTO.setHolidayEndDay(java.sql.Date.valueOf(itemList.get(4).getItemContent()));
					holidayUseInfoDTO.setHolidayEndDayAllday(itemList.get(5).getItemContent());
					holidayUseInfoDTO.setHolidayReportNo(reportNo);
					System.out.println("holidayUseInfoDTO : " + holidayUseInfoDTO);
					
					int result5 = holidayService.insertHolidayUseInfo(holidayUseInfoDTO);
					System.out.println(result5);
					
					
					/* 6-4. 사용한 휴가일수 보유휴가일수에서 차감 */
					int useHoliday = Integer.parseInt(holidayLogDTO.getHolidayDuringDate());
					int havingHoliday = holidayService.selectHavingHoliday(selectedReport.getMemberNo());
					
					int modifiedHoliday = havingHoliday - useHoliday;
					
					MemberInfoDTO memberInfoDTO = new MemberInfoDTO();
					memberInfoDTO.setMemberNo(selectedReport.getMemberNo());
					memberInfoDTO.setRemainingHoliday(modifiedHoliday);
					int result6 = holidayService.updateHavingHoliday(memberInfoDTO);
					System.out.println("사용휴가일수 : " + useHoliday);
					System.out.println("기존보유휴가일수 : " + havingHoliday);
					System.out.println("새 보유휴가일수 : " + modifiedHoliday);
					System.out.println("휴가 차감 성공 여부 : "+ result6);
				}
				
				if(selectedReport.getDocumentNo() == 7) {																	//야간교통비 신청서
					
					
					
				}
				
				if(selectedReport.getDocumentNo() == 8) {																	//경조사 신청서
					
					
					
				}
				
				if(selectedReport.getDocumentNo() == 9) {																	//자기개발 신청서
					
					
					
				}
				if(selectedReport.getDocumentNo() == 10) {																	//DOMITORY_WAITLIST로 간다.
					
					DomitoryWaitListDTO domitoryWaitListDTO = new DomitoryWaitListDTO();									//정보값을 담아줄 DTO선언
					
					memberNo = selectedReport.getMemberNo();																//입주신청자 번호
					String memberName = selectedReport.getMemberName();														//입주신청자
					java.sql.Date hopeDate = java.sql.Date.valueOf(itemList.get(2).getItemContent());						//입주희망일을 받아온다.
					
					domitoryWaitListDTO.setMemberNo(memberNo);
					domitoryWaitListDTO.setMemberName(memberName);
					domitoryWaitListDTO.setHopeDate(hopeDate);
				
					int result = welfareService.insertDomitoryWaitList(domitoryWaitListDTO);
				}
				
				if(selectedReport.getDocumentNo() == 11) {}																	//회의실은 결재가 없다.
				
				if(selectedReport.getDocumentNo() == 12) {																	//복지물품 대여신청
					
					ItemDTO itemDTO = new ItemDTO();																		//복지물품DTO 선언
					
					memberNo = selectedReport.getMemberNo();																//memberNo						신청자번호
					java.sql.Date returnDueDate = java.sql.Date.valueOf(itemList.get(1).getItemContent());					//returndueDate(returnDueDate)	반납일
					int itemNo = Integer.parseInt(itemList.get(2).getItemContent());										//itemNO						아이템번호
					
					itemDTO.setMemberNo(memberNo);																			//DTO에 신청자 사번 삽입
					itemDTO.setReturnDueDate(returnDueDate);																//DTO에 반납예정일 삽입
					itemDTO.setItemNo(itemNo);																				//DTO에 신청 아이템 번호삽입
					
					int insertItemLog = welfareService.insertItemLog(itemDTO);												//서비스 실행
					int updateItemStatus = welfareService.updateItemStatus(itemNo);
					
				}
				
				
				
				
				
				
			}
					
				

				
			
			System.out.println("성공당함 :"+ApproverStatus);
			
		}else {
			// 4.b 반려했으면 상신번호에 대한상신테이블의 상태를 '반려'로 변경
			result3 = approvalService.finishAppReport(thisAPR);
			System.out.println("반려당함 :"+ApproverStatus);
		}
		
		
		
		
		String path = "";
		if(result1 > 0 && result2 > 0 && result3 > 0) {
            path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "approvalProcess");
			
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "approvalProcess");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
		
	}
}
