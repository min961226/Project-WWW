<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css?family=Fira+Sans:400,500,600,700" rel="stylesheet">
   	<link rel="shortcut icon" type="image/x-icon" href="${ pageContext.servletContext.contextPath }/assets/img/favicon.png">
    <link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/assets/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/assets/css/style.css">
	<title>Wonderful Welfare Workspace</title>
</head>
<body>
	<div class="main-wrapper">
        <div class="account-page">
            <div class="container">
                <h3 class="account-title">비밀번호 찾기</h3>
                <div class="account-box">
                    <div class="account-wrapper">
                        <div class="account-logo">
                            <a href="${ pageContext.servletContext.contextPath }">
                            	<img src="${ pageContext.servletContext.contextPath }/assets/img/www_icon.png" alt="">
                            </a>
                        </div>
                        
                        <!-- 비밀번호 찾기 form -->
                        <form action="${ pageContext.servletContext.contextPath }/member/pwd/check" method="post">
                            <div class="form-group form-focus">
                                <label class="control-label">아이디</label>
                                <input class="form-control floating" type="text" name="id" required>
                            </div>
                            <div class="form-group form-focus">
                                <label class="control-label">이름</label>
                                <input class="form-control floating" type="text" name="name" required>
                            </div>
                            <div class="form-group form-focus">
                                <label class="control-label">E-mail</label>
                                <input class="form-control floating" type="email" name="email" required>
                            </div>
                            <div class="form-group form-focus">
                               	<select name="question" class="form-control">
                                <option class="control-option" value="">== 본인 확인 질문 ==</option>
								<c:forEach items="${ requestScope.questionList }" var="question">
									<option value="${ question.questionCode }"><c:out value="${ question.questionCode } ${ question.questionBody }" /></option>
								</c:forEach>
								</select>
                            </div>
                            <div class="form-group form-focus">
                                <label class="control-label">본인 확인 답변</label>
                                <input class="form-control floating" type="text" name="answer" required>
                            </div>
                            <div class="form-group text-center">
                                <button class="btn btn-primary btn-block account-btn" type="submit">비밀번호 찾기</button>
                            </div>
                            <div class="text-center">
                                <a href="${ pageContext.servletContext.contextPath }">로그인 화면으로 돌아가기</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="${ pageContext.servletContext.contextPath }/assets/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${ pageContext.servletContext.contextPath }/assets/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ pageContext.servletContext.contextPath }/assets/js/app.js"></script>
</body>
</html>