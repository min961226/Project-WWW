<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Wonderful Welfare Workspace</title>
</head>

<body>
	<!-- 비로그인 상태 -->
	<c:if test="${ empty sessionScope.memberInfo }">
		<jsp:forward page="/WEB-INF/views/member/login.jsp"/>
	</c:if>
	
	<!-- 로그인 상태 -->
	<c:if test="${ !empty sessionScope.memberInfo }">
		<c:redirect url="/main"/>
	</c:if>
</body>
</html>