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
			
			switch(failedCode) {
				case "login" :
					failedMessage = "등록되지 않은 아이디이거나, 잘못된 비밀번호 입니다."
					movePath = "${ pageContext.servletContext.contextPath }"
			}
			
			alert(failedMessage);
			
			location.replace(movePath);
		})();
	</script>
</body>
</html>