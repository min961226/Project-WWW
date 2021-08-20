<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script>
		(function() {
			const successCode = "${ requestScope.successCode }";

			let successMessage = "";
			let movePath = "";

			switch (successCode) {
			
				case "updateInfo" :
					successMessage = "프로필이 수정되었습니다.";
					movePath = "${ pageContext.servletContext.contextPath }/mypage/info/select";
					break;
					
				case "updatePwd" :
					successMessage = "비밀번호가 변경되었습니다.";
					movePath = "${ pageContext.servletContext.contextPath }";
					break;
					
				case "insertApproval" :
					successMessage = "결재가 신청되었습니다.";
					movePath = "${ pageContext.servletContext.contextPath }/approval/applied/select";
					break;
					
				case "approvalProcess" :
					successMessage = "결재처리가 완료되었습니다.";
					movePath = "${ pageContext.servletContext.contextPath }/approval/waiting/select";
					break;
					
				case "callbackApproval" :
					successMessage = "결재가 회수되었습니다.";
					movePath = "${ pageContext.servletContext.contextPath }/approval/applied/select";
					break;
					
				case "insertAppLine" :
					successMessage = "새로운 결재라인이 생성되었습니다.";
					movePath = "${ pageContext.servletContext.contextPath }/approval/line/select";
					break;
					
				case "updateAppLine" :
					successMessage = "결재라인을 수정하였습니다.";
					movePath = "${ pageContext.servletContext.contextPath }/approval/line/selectOne?no=${ requestScope.lineNo  }";					
					break;
					
				case "deleteLine" :
					successMessage = "결재라인을 삭제하였습니다.";
					movePath = "${ pageContext.servletContext.contextPath }/approval/line/select";
					break;
					
				case "insertWork" :
					successMessage = "근무신청이 상신되었습니다.";
					movePath = "${ pageContext.servletContext.contextPath }/schedule/workingSystem/insert";
					break;
					
				case "insertHoliday" :
					successMessage = "휴가신청이 상신되었습니다.";
					movePath = "${ pageContext.servletContext.contextPath }/schedule/holiday/insert";
					break;
					
				case "insertFree" :
					successMessage = "자유게시글 작성이 완료되었습니다.";
					movePath = "${ pageContext.servletContext.contextPath }/board/free/select";
					break;
					
				case "updateFree" :
					successMessage = "자유게시글 업데이트 완료되었습니다.";
					movePath = "${ pageContext.servletContext.contextPath }/board/free/select";
					break;
					
				case "deleteFree" :
					successMessage = "게시글을 삭제하였습니다.";
					movePath = "${ pageContext.servletContext.contextPath }/board/free/select";
					break;
					
				case "insertSelfDev" :
					successMessage = "자기개발비 신청이 완료되었습니다.";
					movePath = "${ pageContext.servletContext.contextPath }/welfare/list/select";
					break;
					
				case "insertFamilyEvent" :
					successMessage = "경조금 신청이 완료되었습니다.";
					movePath = "${ pageContext.servletContext.contextPath }/welfare/list/select";
					break;
					
				case "insertNightTrans" :
					successMessage = "야간 교통비 신청이 완료되었습니다.";
					movePath = "${ pageContext.servletContext.contextPath }/welfare/list/select";
					break;
					
				case "insertDomitory" :
					successMessage = "기숙사 입주 신청이 완료되었습니다.";
					movePath = "${ pageContext.servletContext.contextPath }/welfare/list/select";
					break;
					
				case "insertSeminarRoom" :
					successMessage = "세미나실 대여신청이 완료되었습니다.";
					movePath = "${ pageContext.servletContext.contextPath }/welfare/list/select";
					break;
					
				case "insertLaptop" :
					successMessage = "노트북 대여신청이 완료되었습니다.";
					movePath = "${ pageContext.servletContext.contextPath }/welfare/list/select";
					break;
					
				case "deleteSeminarRoom" :
					successMessage = "회의실 예약을 삭제하였습니다.";
					movePath = "${ pageContext.servletContext.contextPath }/welfare/applied/list/seminarRoom/select";
					break;
					
				case "callbackWelfare" :
					successMessage = "신청 복지 회수가 완료되었습니다.";
					movePath = "${ pageContext.servletContext.contextPath }/welfare/applied/list/select";
					break;
					
				case "insertMngEmployee" :
					successMessage = "신규 계정이 생성되었습니다.";
					movePath = "${ pageContext.servletContext.contextPath }/mng/employee/list/insert";
					break;
					
				case "inserWorkType" : 
					successMessage = "근무제 추가가 완료되었습니다.";
					movePath = "${ pageContext.servletContext.contextPath }/mng/workingSystem/select";
					break;

				case "deleteHoliday" :
					successMessage = "휴가 취소가 완료되었습니다.";
					movePath = "${ pageContext.servletContext.contextPath }/mng/holiday/applied/select";
					break;
					
				case "updateholidayRule" :
					successMessage = "휴가일수 발생규칙 수정이 완료되었습니다.";
					movePath = "${ pageContext.servletContext.contextPath }/mng/holiday/rule/select";
          			break;
					
				case "insertMngNotice" :
					successMessage = "공지사항 작성이 완료되었습니다.";
					movePath = "${ pageContext.servletContext.contextPath }/mng/board/notice/select";
					break;
					
				case "updateMngNotice" :
					successMessage = "공지사항 수정이 완료되었습니다.";
					movePath = "${ pageContext.servletContext.contextPath }/mng/board/notice/select";
					break;
										
				case "deleteMngNotice" :
					successMessage = "공지사항 삭제가 완료되었습니다.";
					movePath = "${ pageContext.servletContext.contextPath }/mng/board/notice/select";
					break;
					
				case "insertMngForm" :
					successMessage = "문서서식 게시판 작성이 완료되었습니다.";
					movePath = "${ pageContext.servletContext.contextPath }/mng/board/form/select";
					break;
					
				case "updateMngForm" :
					successMessage = "문서서식 게시판 수정이 완료되었습니다.";
					movePath = "${ pageContext.servletContext.contextPath }/mng/board/form/select";
					break;
					
				case "deleteMngForm" :
					successMessage = "문서서식 게시판 삭제가 완료되었습니다.";
					movePath = "${ pageContext.servletContext.contextPath }/mng/board/form/select";
					break;

				case "updateWelfare" :
					successMessage = "시행복지 수정이 완료되었습니다.";
					movePath = "${ pageContext.servletContext.contextPath }/mng/welfare/list/select";
					break;
					
				case "mngCallbackWelfare" :
					successMessage = "신청 복지 회수가 완료되었습니다.";
					movePath = "${ pageContext.servletContext.contextPath }/mng/welfare/applied/select";
					break;
          			
				case "insertItem" :
					successMessage = "복지 품목 추가에 성공하였습니다.";
					movePath = "${ pageContext.servletContext.contextPath }/mng/welfare/laptopRental/select";
					break;
					
				case "deleteItem" :
					successMessage = "복지 품목 삭제에 성공하였습니다.";
					movePath = "${ pageContext.servletContext.contextPath }/mng/welfare/laptopRental/select";
					break;
					
				case "deleteWorkType" : 
					successMessage = "근무제삭제가 완료되었습니다.";
					movePath = "${ pageContext.servletContext.contextPath }/mng/workingSystem/select";
					break;
				
			}

			alert(successMessage);

			location.replace(movePath);
		})();
	</script>
</body>

</html>