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
                <h3 class="account-title">비밀번호 변경</h3>
                <div class="account-box">
                    <div class="account-wrapper">
                        <div class="account-logo">
                            <a href="${ pageContext.servletContext.contextPath }">
                            	<img src="${ pageContext.servletContext.contextPath }/assets/img/www_icon.png" alt="">
                            </a>
                        </div>
                        <form action="${ pageContext.servletContext.contextPath }/member/pwd/update" method="post">
                            <input type="text" name="memberId" value="${ memberId }" hidden="hidden">
                            <div class="form-group form-focus">
                                <label class="control-label">새 비밀번호 입력</label>
                                <input class="form-control floating" type="password" name="changePwd" id="changePwd" required>
                            </div>
                            <div class="form-group form-focus">
                                <label class="control-label">새 비밀번호 입력 확인</label>
                                <input class="form-control floating" type="password" name="changePwd2" id="changePwd2" required>
                            </div>
                            <div class="form-group text-center">
	                            <div class="alert alert-success" id="alert-success"><b>비밀번호가 일치합니다.</b></div>
	                            <div class="alert alert-danger" id="alert-danger"><b>비밀번호가 일치하지 않습니다.</b></div>
                                <button class="btn btn-primary btn-block account-btn" type="submit" id="button">비밀번호 변경</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="${ pageContext.servletContext.contextPath }/assets/js/jquery-3.2.1.min.js"></script>
    <script>
    	$(function() {
    		$("#alert-success").hide();
    		$("#alert-danger").hide();
    		$("input").keyup(function() {
	    		const pwd1 = $("#changePwd").val();
	    		const pwd2 = $("#changePwd2").val();
	    		
	    		if(pwd1 == pwd2 && pwd2 != null) {
	    			$("#alert-success").show();
	    			$("#alert-danger").hide();
	    		} else if(pwd1 != pwd2) {
	    			$("#alert-success").hide();
	    			$("#alert-danger").show();
	    		}
    		});
    	});
    </script>
    <script type="text/javascript" src="${ pageContext.servletContext.contextPath }/assets/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ pageContext.servletContext.contextPath }/assets/js/app.js"></script>
</body>
</html>