<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css?family=Fira+Sans:400,500,600,700" rel="stylesheet">
   	<link rel="shortcut icon" type="image/x-icon" href="${ pageContext.servletContext.contextPath }/assets/img/favicon.png">
    <link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/assets/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/assets/css/style.css">
<title>Insert title here</title>
</head>
<body>
	<div class="main-wrapper">
        <div class="account-page">
            <div class="container">
                <h3 class="account-title">아이디 찾기</h3>
                <div class="account-box">
                    <div class="account-wrapper">
                        <div class="account-logo">
                            <a href="${ pageContext.servletContext.contextPath }">
                            	<img src="${ pageContext.servletContext.contextPath }/assets/img/www_icon.png" alt="">
                            </a>
                        </div>
                        <div class="form-group text-center">
	                        <div>
	                            <label class="control-label">${ requestScope.checkName } 님의 아이디 입니다.</label>
	                        </div>
	                        <div class="name">
	                            <label class="card-box">${ requestScope.checkId }</label>
	                        </div>
	                        <div class="text-center">
	                            <button type="button" class="btn btn-primary btn-block account-btn" id="goLogin">로그인 화면으로</button>
	                        </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="${ pageContext.servletContext.contextPath }/assets/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${ pageContext.servletContext.contextPath }/assets/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ pageContext.servletContext.contextPath }/assets/js/app.js"></script>
    <script>
	    const $goLogin = document.getElementById("goLogin");
		
		$goLogin.onclick = function() {
			location.href = "${ pageContext.servletContext.contextPath }";
		}
    </script>
</body>
</html>