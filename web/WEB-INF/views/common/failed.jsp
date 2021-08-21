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
			const failedCode = "${ requestScope.failedCode }"

			let failedMessage = "";
			let movePath = "";

			switch (failedCode) {


			case "login" :
				failedMessage = "등록되지 않은 아이디이거나, 잘못된 비밀번호 입니다.";
				movePath = "${ pageContext.servletContext.contextPath }";
				break;

			case "checkId" :
				failedMessage = "일치하는 아이디가 없습니다.";
				movePath = "${ pageContext.servletContext.contextPath }/member/id/check";
				break;

			case "checkPwd" :
				failedMessage = "일치하는 계정을 찾을 수 없습니다.";
				movePath = "${ pageContext.servletContext.contextPath }/member/pwd/check";
				break;

			case "updatePwd" :
				failedMessage = "비밀번호 변경에 실패하였습니다.";
				movePath = "${ pageContext.servletContext.contextPath }/member/pwd/check";
				break;

			case "insertCommute" :
				failedMessage = "출근 시간 기록이 실패하였습니다.";
				movePath = "${ pageContext.servletContext.contextPath }";
				break;

			case "updateInfo" :
				failedMessage = "프로필 수정에 실패했습니다.";
				movePath = "${ pageContext.servletContext.contextPath }";
				break;

			case "insertApproval" :
				failedMessage = "결재 신청이 실패헀습니다.";
				movePath = "${ pageContext.servletContext.contextPath }/approval/applied/select";
				break;

			case "approvalProcess" :
				failedMessage = "결재처리가 실패헀습니다.";
				movePath = "${ pageContext.servletContext.contextPath }/approval/waiting/select";
				break;

			case "callbackApproval" :
				failedMessage = "결재회수가 실패했습니다.";
				movePath = "${ pageContext.servletContext.contextPath }/approval/applied/select";
				break;

			case "insertAppLine" :
				failedMessage = "결재라인 생성에 실패했습니다.";
				movePath = "${ pageContext.servletContext.contextPath }/approval/line/select";
				break;

			case "updateAppLine" :
				failedMessage = "결재라인수정에 실패했습니다.";
				movePath = "${ pageContext.servletContext.contextPath }/approval/line/selectOne?no=${ requestScope.lineNo  }";
				break;

			case "deleteLine" :
				failedMessage = "결재라인 삭제에 실패헀습니다.";
				movePath = "${ pageContext.servletContext.contextPath }/approval/line/select";
				break;

			case "insertWork" :
				failedMessage = "근무 신청이 실패했습니다.";
				movePath = "${ pageContext.servletContext.contextPath }";
				break;

			case "insertHoliday" :
				failedMessage = "휴가 신청이 실패했습니다.";
				movePath = "${ pageContext.servletContext.contextPath }";
				break;

			case "insertSelfDev" : 
				failedMessage = "자기개발비 신청에 실패하였습니다.";
				movePath = "${ pageContext.servletContext.contextPath }/welfare/list/select";
				break;

			case "insertNightTrans" :
				failedMessage = "야간교통비 신청에 실패하였습니다." ;
				movePath = "${ pageContext.servletContext.contextPath }/welfare/list/select";
				break;

			case "insertDomitory" :
				failedMessage = "기숙사 입주 신청에 실패하였습니다.";
				movePath = "${ pageContext.servletContext.contextPath }/welfare/list/select";
				break;

			case "insertSeminarRoom" :
				failedMessage = "해당시간의 세미나실은 대여중입니다.";
				movePath = "${ pageContext.servletContext.contextPath }/welfare/list/select";
				break;

			case "alreadyInsertedLaptop" :
				failedMessage = "해당 품목은 이미 대여중입니다.";
				movePath = "${ pageContext.servletContext.contextPath }/welfare/list/selected?selectedWelfare=노트북대여신청";
				break;

			case "insertLaptopDateError" :
				failedMessage = "대여는 2주 이상 할 수 없습니다";
				movePath = "${ pageContext.servletContext.contextPath }/welfare/list/selected?selectedWelfare=노트북대여신청";
				break;

			case "insertLaptop" :
				failedMessage = "노트북 대여 신청에 실패하였습니다";
				movePath = "${ pageContext.servletContext.contextPath }/welfare/list/select";
				break;

			case "callbackWelfare" :
				failedMessage = "신청 복지 회수에 실패했습니다.";
				movePath = "${ pageContext.servletContext.contextPath }/welfare/applied/list/select";
				break ;

			case "deleteSeminarRoom" :
				failedMessage = "회의실 예약 삭제를 실패하였습니다.";
				movePath = "${ pageContext.servletContext.contextPath }/welfare/applied/list/seminarRoom/select";
				break;
				
			case "updateMngEmployee" :
				failedMessate = "직원 정보 수정에 실패하였습니다."
				movePath = "${ pageContext.servletContext.contextPath }/mng/employee/list/update?no=${ requestScope.MemberNo }";
				break;

			case "inserWorkType" :
				successMessage = "근무제 추가가 실패하였습니다.";
				movePath = "${ pageContext.servletContext.contextPath }/mng/workingSystem/select";
				break;

			case "deleteHoliday" :
				failedMessage = "휴가취소에 실패하였습니다.";
				movePath = "${ pageContext.servletContext.contextPath }/mng/holiday/applied/select";
				break;

			case "updateholidayRule" :
				failedMessage = "휴가일수 발생규칙 수정에 실패하였습니다.";
				movePath = "${ pageContext.servletContext.contextPath }/mng/holiday/rule/select";
				break;
				
			case "updateWelfare" :
				failedMessage = "시행복지 수정에 실패하였습니다.";
				movePath = "${ pageContext.servletContext.contextPath }/mng/welfare/list/select";
				break;
				
			case "mngCallbackWelfare" :
				failedMessage = "신청 복지 회수에 실패했습니다.";
				movePath = "${ pageContext.servletContext.contextPath }/mng/welfare/applied/select";
				break;
				
			case "insertItem" :
				failedMessage = "복지 품목 추가에 실패하였습니다.";
				movePath = "${ pageContext.servletContext.contextPath }/mng/welfare/laptopRental/select";
				break;

			case "returnItem" :
				failedMessage = "품목 반납처리에 실패하였습니다.";
				movePath = "${ pageContext.servletContext.contextPath }/mng/welfare/laptopRental/select";
				break;
				
			case "deleteItem" :
				failedMessage = "복지 품목 삭제에 실패하였습니다.";
				movePath = "${ pageContext.servletContext.contextPath }/mng/welfare/laptopRental/select";
				break;

			case "insertManualHolidayLog":
				failedMessage = "휴가수동지급에 실패하였습니다.";
				movePath = "${ pageContext.servletContext.contextPath }/mng/holiday/manual/select";
				break;

			case "insertDomitoryLog" :
				failedMessage = "대기자 입주 처리를 실패하였습니다.";
				movePath = "${ pageContext.servletContext.contextPath }/mng/welfare/domitory/select";
				break;
			case "maxCapacity" :
				failedMessage = "해당 기숙사 객실은 현재 만원입니다.";
				movePath = "${ pageContext.servletContext.contextPath }/mng/welfare/domitory/select";
				break;
			case "updateHolidayType" :
				failedMessage = "휴가유형 수정에 실패하였습니다";
				movePath = "${ pageContext.servletContext.contextPath }/mng/holiday/category/select";
				break;
			case "insertHolidayType" :
				failedMessage = "휴가유형 생성에 실패하였습니다";
				movePath = "${ pageContext.servletContext.contextPath }/mng/holiday/category/select";
				break;
				

			}

			alert(failedMessage);

			location.replace(movePath);
		})();
	</script>
</body>

</html>