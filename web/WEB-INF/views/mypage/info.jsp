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
                    				<ul class="personal-info">
		                    			<li>
		                    				<span class="title">ID</span>
		                    				<span class="text">${ memberInfo.memberId }</span>
		                    			</li>
		                    			<li>
		                    				<span class="title">사번</span>
		                    				<span class="text">${ memberInfo.memberNo }</span>
		                    			</li>
		                          		<li>
		                          			<span class="title">이름</span>
		                          			<span class="text">${ memberInfo.name }</span>
		                          		</li>
		                          		<li>
		                          			<span class="title">부서</span>
		                          			<span class="text">${ memberInfo.department.deptName }</span>                            				
		                          		</li>
		                          		<li>
		                          			<span class="title">직급</span>
		                          			<span class="text">${ memberInfo.job.jobName }</span>
		                          		</li>
		                          		<li>
		                          			<span class="title">E-mail</span>
		                          			<span class="text">${ memberInfo.email }</span>
		                          		</li>
		                          		<li>
		                          			<span class="title">사내전화</span>
		                          			<span class="text">내선 ${ memberInfo.department.deptCallNumber }</span>
		                          		</li>
		                          		<li>
		                          			<span class="title">입사일</span>
		                          			<span class="text">${ memberInfo.enrollDate }</span>
		                          		</li>
		                          		<li>
		                          			<span class="title">재직 상태</span>
		                          			<span class="text">재직</span>
		                          		</li>
		                          		<li>
		                          			<span class="title">권한</span>
		                          			<span class="text">${ memberInfo.role.roleName }</span>
		                          		</li>
		                        	</ul>
		                        </div>
		                    </div>
                        </div>
                    </div>
                    
                    <div class="col-md-5">
                        <div class="card-box">
                    		<div class="profile-view">
                    			<div class="form-horizontal">
                    				<ul class="personal-info">
		                    			<li>
		                    				<span class="title">성별</span>
		                    				<span class="text">${ memberInfo.gender }</span>
		                    			</li>
		                    			<li>
		                    				<span class="title">생년월일</span>
		                    				<span class="text">${ memberInfo.birthday }</span>
		                    			</li>
		                          		<li>
		                          			<span class="title">휴대전화</span>
		                          			<span class="text">${ memberInfo.phone }</span>
		                          		</li>
		                          		<li>
		                          			<span class="title">자택주소</span>
		                          			<span class="text">${ memberInfo.address }</span>                            				
		                          		</li>
		                        	</ul>
		                        </div>
		                    </div>
                        </div>
                    </div>
                </div>
                	
                <div class="row">
                	<div class="col-md-2">
                		<div class="edit-profile-img" align="center">
                			<img class="call-avatar" src="${ pageContext.servletContext.contextPath }/assets/img/user.jpg" alt="">
                        </div>
                	</div>
                	
                    <div class="col-md-5">
                        <div class="card-box">
                        	<form action="#" class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-3 control-label">ID</label>
                                    <label class="col-md-9 control-label">${ memberInfo.memberId }</label>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">사번</label>
                                    <label class="col-md-9 control-label">${ memberInfo.memberNo }</label>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">이름</label>
                                    <label class="col-md-9 control-label">${ memberInfo.name }</label>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">부서</label>
                                    <label class="col-md-9 control-label">${ memberInfo.department.deptName }</label>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">직급</label>
                                    <label class="col-md-9 control-label">${ memberInfo.job.jobName }</label>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">E-mail</label>
                                    <label class="col-md-9 control-label">${ memberInfo.email }</label>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">사내전화</label>
                                    <label class="col-md-9 control-label">내선 ${ memberInfo.department.deptCallNumber }</label>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">입사일</label>
                                    <label class="col-md-9 control-label">${ memberInfo.enrollDate }</label>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">재직 상태</label>
                                    <label class="col-md-9 control-label">재직</label>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">권한</label>
                                    <label class="col-md-9 control-label">${ memberInfo.role.roleName }</label>
                                </div>
                            </form>
                    	</div>
                    </div>
                    
                    <div class="col-md-5">
                        <div class="card-box">
                            <form action="#" class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-3 control-label">성별</label>
                                    <label class="col-md-9 control-label">${ memberInfo.gender }</label>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">생년월일</label>
                                    <label class="col-md-9 control-label">${ memberInfo.birthday }</label>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">휴대전화</label>
                                    <label class="col-md-9 control-label">${ memberInfo.phone }</label>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">자택주소</label>
                                    <label class="col-md-9 control-label">${ memberInfo.address }</label>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>