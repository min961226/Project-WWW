<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
	<link href="https://fonts.googleapis.com/css?family=Fira+Sans:400,500,600,700" rel="stylesheet">
	<link rel="shortcut icon" type="image/x-icon" href="${ pageContext.servletContext.contextPath }/assets/img/favicon.png">
    <link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/assets/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/assets/css/style.css">
	<title>Wonderful Welfare Workspace</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>

<body>
    <div class="main-wrapper">
        <div class="account-page">
            <div class="container">
                <h3 class="account-title">Login</h3>
                <div class="account-box">
                    <div class="account-wrapper">
                        <div class="account-logo">
                            <a href="${ pageContext.servletContext.contextPath }"><img src="assets/img/www_icon.png" alt="WWW"></a>
                        </div>
                        <form id="loginForm" action="${ pageContext.servletContext.contextPath }/member/login" method="post">
                            <div class="form-group form-focus">
                                <label class="control-label">ID</label>
                                <input class="form-control floating" type="text" name="memberId">
                            </div>
                            <div class="form-group form-focus">
                                <label class="control-label">Password</label>
                                <input class="form-control floating" type="password" name="memberPwd">
                            </div>
                            <div class="form-group text-center">
                                <button class="btn btn-primary btn-block account-btn" type="submit">Login</button>
                            </div>
                            <div class="text-center">
                                <span><a href="${ pageContext.servletContext.contextPath }/member/id/check">아이디 찾기</a></span>
                                <span> | </span>
                                <span><a href="${ pageContext.servletContext.contextPath }/member/pwd/check">비밀번호 찾기</a></span>
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