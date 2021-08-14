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
					failedMessage = "등록되지 않은 아이디이거나, 잘못된 비밀번호 입니다."
					movePath = "${ pageContext.servletContext.contextPath }"
					break;
					
				case "insertCommute" :
					failedMessage = "출근 시간 기록이 실패하였습니다."
					movePath = "${ pageContext.servletContext.contextPath }"
					break;
					
				case "updateInfo" :
					failedMessage = "프로필 수정에 실패했습니다."
					movePath = "${ pageContext.servletContext.contextPath }"
					break;
					
				case "insertApproval" :
					failedMessage = "결재 신청이 실패헀습니다."
					movePath = "${ pageContext.servletContext.contextPath }/approval/applied/select";
					break;
					
				case "approvalProcess" :
					failedMessage = "결재처리가 실패헀습니다."
					movePath = "${ pageContext.servletContext.contextPath }/approval/waiting/select";
					break;
					
				case "callbackApproval" :
					failedMessage = "결재회수가 실패했습니다."
					movePath = "${ pageContext.servletContext.contextPath }/approval/applied/select";
					break;
					
				case "insertWork" :
					failedMessage = "근무 신청이 실패했습니다."
					movePath = "${ pageContext.servletContext.contextPath }";
					break;
					
				case "insertHoliday" :
					failedMessage = "휴가 신청이 실패했습니다."
					movePath = "${ pageContext.servletContext.contextPath }";
					break;
					
				case "insertSelfDev" :
					failedMessage = "자기개발비 신청에 실패하였습니다."
					movePath = "${ pageContext.servletContext.contextPath }/welfare/list/select";
					break;
					
				case "insertNightTrans" :
					failedMessage = "야간교통비 신청에 실패하였습니다."
					movePath = "${ pageContext.servletContext.contextPath }/welfare/list/select";
					break;
					
				case "insertDomitory" :
					successMessage = "기숙사 입주 신청이 완료되었습니다."
					movePath = "${ pageContext.servletContext.contextPath }/welfare/list/select";
					break;
			}

			alert(failedMessage);

			location.replace(movePath);
		})();
	</script>
</body>

</html>