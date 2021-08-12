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
			
			switch(successCode) {
				case "updateInfo" :
					successMessage = "프로필이 수정되었습니다."
					movePath =  "${ pageContext.servletContext.contextPath }/mypage/info/select";
					break;
					
				case "insertApproval" :
					successMessage = "결재가 신청되었습니다."
					movePath =  "${ pageContext.servletContext.contextPath }/approval/applied/select";
					break;
					

				case "insertWork" :
					successMessage = "근무신청이 상신되었습니다."
					movePath =  "${ pageContext.servletContext.contextPath }/schedule/workingSystem/insert";
					break;
					
				case "inserHoliday" :
					successMessage = "휴가신청이 상신되었습니다."
					movePath =  "${ pageContext.servletContext.contextPath }/schedule/holiday/insert";
					break;
					

				case "callbackApproval" :
					successMessage = "결재가 회수되었습니다."
					movePath =  "${ pageContext.servletContext.contextPath }/approval/applied/select";
					break;

			}
			
			alert(successMessage);
			
			location.replace(movePath);
		})();
	</script>
</body>
</html>