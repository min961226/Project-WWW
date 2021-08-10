<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="${ pageContext.servletContext.contextPath }/assets/img/favicon.png">
<title>Wonderful Welfare Workspace</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>

<body>
    <div class="main-wrapper">
		<jsp:include page="../common/navbar.jsp"/>
		
		<div class="page-wrapper">
			<div class="content container-fluid">
                <div class="row">
                    <div class="col-xs-6">
                        <h4 class="page-title">My Profile</h4>
                    </div>
                    <form class="col-xs-6 text-right m-b-30">
                        <a href="${ pageContext.servletContext.contextPath }/mypage/info/update" class="btn btn-primary rounded">프로필 수정</a>
                    </form>
                </div>
                <div class="row">
                	<div class="col-md-2">
                		<div class="author-img-wrap" align="center">
                			<img class="img-responsive img-circle" src="${ pageContext.servletContext.contextPath }/assets/img/user.jpg" alt="">
                        </div>
                	</div>
                	
                	<div class="col-md-5">
                        <div class="card-box">
                    		<div class="profile-view">
                    			<div class="form-horizontal">
	                    			<div class="form-group" style="back">
	                                    <label class="control-label col-lg-3">ID</label>
	                                    <div class="col-md-9">
	                                        <input type="text" class="form-control" value="${ memberInfo.memberId }" readonly="readonly">
	                                    </div>
	                                </div>
	                                <div class="form-group">
	                                    <label class="control-label col-lg-3">사번</label>
	                                    <div class="col-md-9">
	                                        <input type="text" class="form-control" value="${ memberInfo.memberNo }" readonly="readonly">
	                                    </div>
	                                </div>
	                                <div class="form-group">
	                                    <label class="control-label col-lg-3">이름</label>
	                                    <div class="col-md-9">
	                                        <input type="text" class="form-control" value="${ memberInfo.name }" readonly="readonly">
	                                    </div>
	                                </div>
	                                <div class="form-group">
	                                    <label class="control-label col-lg-3">부서</label>
	                                    <div class="col-md-9">
	                                        <input type="text" class="form-control" value="${ memberInfo.department.deptName }" readonly="readonly">
	                                    </div>
	                                </div>
	                                <div class="form-group">
	                                    <label class="control-label col-lg-3">직급</label>
	                                    <div class="col-md-9">
	                                        <input type="text" class="form-control" value="${ memberInfo.job.jobName }" readonly="readonly">
	                                    </div>
	                                </div>
	                                <div class="form-group">
	                                    <label class="control-label col-lg-3">E-mail</label>
	                                    <div class="col-md-9">
	                                        <input type="text" class="form-control" value="${ memberInfo.email }" readonly="readonly">
	                                    </div>
	                                </div>
	                                <div class="form-group">
	                                    <label class="control-label col-lg-3">사내전화</label>
	                                    <div class="col-md-9">
	                                        <input type="text" class="form-control" value="${ memberInfo.department.deptCallNumber }" readonly="readonly">
	                                    </div>
	                                </div>
	                                <div class="form-group">
	                                    <label class="control-label col-lg-3">입사일</label>
	                                    <div class="col-md-9">
	                                        <input type="text" class="form-control" value="${ memberInfo.enrollDate }" readonly="readonly">
	                                    </div>
	                                </div>
	                                <div class="form-group">
	                                    <label class="control-label col-lg-3">재직 상태</label>
	                                    <div class="col-md-9">
	                                        <input type="text" class="form-control" value="재직" readonly="readonly">
	                                    </div>
	                                </div>
	                                <div class="form-group">
	                                    <label class="control-label col-lg-3">권한</label>
	                                    <div class="col-md-9">
	                                        <input type="text" class="form-control" value="${ memberInfo.role.roleName }" readonly="readonly">
	                                    </div>
	                                </div>
		                        </div>
		                    </div>
                        </div>
                    </div>
                    
                    <div class="col-md-5">
                        <div class="card-box">
                    		<div class="profile-view">
                    			<div class="form-horizontal">
                    				<div class="form-group">
	                                    <label class="control-label col-lg-3">성별</label>
	                                    <div class="col-md-9">
	                                        <input type="text" class="form-control" value="${ memberInfo.gender }" readonly="readonly">
	                                    </div>
	                                </div>
	                                <div class="form-group">
	                                    <label class="control-label col-lg-3">생년월일</label>
	                                    <div class="col-md-9">
	                                        <input type="text" class="form-control" value="${ memberInfo.birthday }" readonly="readonly">
	                                    </div>
	                                </div>
	                                <div class="form-group">
	                                    <label class="control-label col-lg-3">휴대전화</label>
	                                    <div class="col-md-9">
	                                        <input type="text" class="form-control" value="${ memberInfo.phone }" readonly="readonly">
	                                    </div>
	                                </div>
	                                <div class="form-group">
	                                    <label class="control-label col-lg-3">자택주소</label>
	                                    <div class="col-md-9">
	                                        <input type="text" class="form-control" value="${ memberInfo.address }" readonly="readonly">
	                                    </div>
	                                </div>
		                        </div>
		                    </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>