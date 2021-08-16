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
                        <button type="button" class="btn btn-success" id="updateInfo">프로필 수정</button>
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
	                                    <label class="control-label col-lg-4">ID</label>
	                                    <div class="col-md-8">
	                                        <input type="text" class="form-control" value="${ memberInfo.memberId }" readonly>
	                                    </div>
	                                </div>
	                                <div class="form-group">
	                                    <label class="control-label col-lg-4">사번</label>
	                                    <div class="col-md-8">
	                                        <input type="text" class="form-control" value="${ memberInfo.memberNo }" readonly>
	                                    </div>
	                                </div>
	                                <div class="form-group">
	                                    <label class="control-label col-lg-4">이름</label>
	                                    <div class="col-md-3">
	                                        <input type="text" class="form-control" value="${ memberInfo.name }" readonly>
	                                    </div>
	                                </div>
	                                <div class="form-group">
	                                    <label class="control-label col-lg-4">부서</label>
	                                    <div class="col-md-8">
	                                        <input type="text" class="form-control" value="${ memberInfo.department.deptName }" readonly>
	                                    </div>
	                                </div>
	                                <div class="form-group">
	                                    <label class="control-label col-lg-4">직급</label>
	                                    <div class="col-md-8">
	                                        <input type="text" class="form-control" value="${ memberInfo.job.jobName }" readonly>
	                                    </div>
	                                </div>
	                                <div class="form-group">
	                                    <label class="control-label col-lg-4">E-mail</label>
	                                    <div class="col-md-8">
	                                        <input type="text" class="form-control" value="${ memberInfo.email }" readonly>
	                                    </div>
	                                </div>
	                                <div class="form-group">
	                                    <label class="control-label col-lg-4">사내전화</label>
	                                    <div class="col-md-8">
	                                        <input type="text" class="form-control" value="${ memberInfo.department.deptCallNumber }" readonly>
	                                    </div>
	                                </div>
	                                <div class="form-group">
	                                    <label class="control-label col-lg-4">입사일</label>
	                                    <div class="col-md-8">
	                                        <input type="text" class="form-control" value="${ memberInfo.enrollDate }" readonly>
	                                    </div>
	                                </div>
	                                <div class="form-group">
	                                    <label class="control-label col-lg-4">재직 상태</label>
	                                    <div class="col-md-8">
	                                        <input type="text" class="form-control" value="재직" readonly>
	                                    </div>
	                                </div>
	                                <div class="form-group">
	                                    <label class="control-label col-lg-4">권한</label>
	                                    <div class="col-md-8">
	                                        <input type="text" class="form-control" value="${ memberInfo.role.roleName }" readonly>
	                                    </div>
	                                </div>
		                        </div>
		                    </div>
                        </div>
                    </div>
                    
                    <div class="col-md-5">
                        <div class="card-box">
                    		<div class="profile-view">
                    			<form class="form-horizontal">
                    				<div class="form-group">
	                                    <label class="control-label col-lg-4">성별</label>
	                                    <div class="col-md-8">
	                                        <input type="text" class="form-control" value="${ memberInfo.gender }" readonly>
	                                    </div>
	                                </div>
	                                <div class="form-group">
	                                    <label class="control-label col-lg-4">생년월일</label>
	                                    <div class="col-md-8">
	                                        <input type="text" class="form-control" value="${ memberInfo.birthday }" readonly>
	                                    </div>
	                                </div>
	                                <div class="form-group">
	                                    <label class="control-label col-lg-4">주민등록번호</label>
	                                    <div class="col-md-3">
	                                        <input type="text" class="form-control" value="${ firstRrn }" readonly>
	                                    </div>
	                                    <label class="control-label col-lg-1"> - </label>
	                                    <div class="col-md-4">
	                                        <input type="password" class="form-control" value="${ lastRrn }" readonly>
	                                    </div>
	                                </div>
	                                <div class="form-group">
	                                    <label class="control-label col-lg-4">휴대전화</label>
	                                    <div class="col-md-8">
	                                        <input type="text" class="form-control" value="${ memberInfo.phone }" readonly>
	                                    </div>
	                                </div>
	                                <div class="form-group">
	                                    <label class="control-label col-lg-4">자택주소</label>
	                                    <div class="col-md-8">
	                                    	<div class="input-group">
	                                    		<span><input type="text" class="form-control" name="zipCode" id="zipCode" value="${ zipCode }" readonly></span>
                                            	<span class="input-group-btn">
													<button type="button" class="btn btn-primary" id="searcHZipCode" disabled>검색</button>
												</span>
											</div>
										</div>												
	                                </div>
	                                <div class="form-group">
	                                    <label class="control-label col-lg-4"></label>
	                                    <div class="col-md-8">
	                                        <input type="text" class="form-control" name="address1" id="address1" value="${ address1 }" readonly>
	                                    </div>
	                                </div>
	                                <div class="form-group">
	                                    <label class="control-label col-lg-4"></label>
	                                    <div class="col-md-8">
	                                        <input type="text" class="form-control" name="address2" id="address2" value="${ address2 }" readonly>
	                                    </div>
	                                </div>
	                                <div class="form-group">
	                                    <label class="control-label col-lg-4">본인 확인 질문</label>
	                                    <div class="col-md-8">
	                                        <input type="text" class="form-control" name="question" value="${ memberInfo.checkQuestion.questionBody }" readonly>
	                                    </div>
		                            </div>
		                            <div class="form-group">
		                                <label class="control-label col-lg-4">답변</label>
		                                <div class="col-md-8">
		                            		<input type="text" class="form-control" name="answer" value="${ memberInfo.questionAnswer }" readonly>
		                            	</div>
		                        	</div>
		                        </form>
		                    </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script>
    	const $updateInfo = document.getElementById("updateInfo");
    	
    	$updateInfo.onclick = function() {
    		location.href = "${ pageContext.servletContext.contextPath }/mypage/info/update";
    	}
    </script>
</body>

</html>