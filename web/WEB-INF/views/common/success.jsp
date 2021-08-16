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

			
				case "updateInfo":
					successMessage = "프로필이 수정되었습니다."
					movePath = "${ pageContext.servletContext.contextPath }/mypage/info/select";
					break;
					
				case "insertApproval" :
					successMessage = "결재가 신청되었습니다."
					movePath = "${ pageContext.servletContext.contextPath }/approval/applied/select";
					break;
					
				case "approvalProcess" :
					successMessage = "결재처리가 완료되었습니다."
					movePath = "${ pageContext.servletContext.contextPath }/approval/waiting/select";
					break;
					
				case "callbackApproval" :
					successMessage = "결재가 회수되었습니다."
					movePath = "${ pageContext.servletContext.contextPath }/approval/applied/select";
					break;
					
				case "insertWork" :
					successMessage = "근무신청이 상신되었습니다."
					movePath = "${ pageContext.servletContext.contextPath }/schedule/workingSystem/insert";
					break;
					
				case "inserHoliday" :
					successMessage = "휴가신청이 상신되었습니다."
					movePath = "${ pageContext.servletContext.contextPath }/schedule/holiday/insert";
					break;
					
				case "insertFree" :
					successMessage = "자유게시글 작성이 완료되었습니다."
					movePath = "${ pageContext.servletContext.contextPath }/board/free/select";
					break;
					
				case "updateFree" :
					successMessage = "자유게시글 업데이트 완료되었습니다."
					movePath = "${ pageContext.servletContext.contextPath }/board/free/select";
					break;
					
				case "insertSelfDev" :
					successMessage = "자기개발비 신청이 완료되었습니다."
					movePath = "${ pageContext.servletContext.contextPath }/welfare/list/select";
					break;
					
				case "insertFamilyEvent" :
					successMessage = "경조금 신청이 완료되었습니다."
					movePath = "${ pageContext.servletContext.contextPath }/welfare/list/select";
					break;
					
				case "insertNightTrans" :
					successMessage = "야간 교통비 신청이 완료되었습니다."
					movePath = "${ pageContext.servletContext.contextPath }/welfare/list/select";
					break;
					
				case "insertDomitory" :
					successMessage = "기숙사 입주 신청이 완료되었습니다."
					movePath = "${ pageContext.servletContext.contextPath }/welfare/list/select";
					break;
				case "insertSeminarRoom" :
					successMessage = "세미나실 대여신청이 완료되었습니다."
					movePath = "${ pageContext.servletContext.contextPath }/welfare/list/select";
					break;

			}

			alert(successMessage);
			
			location.replace(movePath);
		})();
	</script>
</body>

</html>