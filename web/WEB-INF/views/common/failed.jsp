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
					
				case "matchPwd" :
					failedMessage = "비밀번호가 일치하지 않습니다.";
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

				case "updateAppLine" :
					failedMessage = "결재라인수정에 실패했습니다."
					movePath = "${ pageContext.servletContext.contextPath }/approval/line/selectOne?no=${ requestScope.lineNo  }";
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
					failedMessage = "야간교통비 신청에 실패하였습니다.";
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
					failedMessage = "신청 복지 회수에 실패했습니다."
					movePath = "${ pageContext.servletContext.contextPath }/welfare/applied/list/select";
					break;
					
				case "insertAppLine" :
					failedMessage = "결재라인 생성에 실패했습니다.";
					movePath = "${ pageContext.servletContext.contextPath }/approval/line/select";
					break;
					
				case "deleteLine" :
					failedMessage = "결재라인 삭제에 실패헀습니다.";
					movePath = "${ pageContext.servletContext.contextPath }/approval/line/select";
					break;
					
				case "deleteSeminarRoom" :
					failedMessage = "회의실 예약 삭제를 실패하였습니다."
					movePath = "${ pageContext.servletContext.contextPath }/welfare/applied/list/seminarRoom/select";
					break;
			}

			alert(failedMessage);

			location.replace(movePath);
		})();
	</script>
</body>

</html>