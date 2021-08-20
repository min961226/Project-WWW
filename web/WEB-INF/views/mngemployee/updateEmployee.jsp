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
                        <h4 class="page-title">직원 정보 수정</h4>
                    </div>
                </div>
            
	            <form action="${ pageContext.servletContext.contextPath }/mng/employee/list/update" method="POST">
	                <div class="row">
	                	<div class="col-md-2">
	                		<div class="author-img-wrap" align="center">
	                			<img class="img-responsive img-circle" src="${ pageContext.servletContext.contextPath }/assets/img/user.jpg" alt="">
	                        </div>
	                        <br>
	                        <div align="center">
	                        	<button type="button" class="btn btn-primary">사진 변경</button>
	                        </div>
	                	</div>
	                	
	                	<div class="col-md-5">
	                        <div class="card-box">
	                    		<div class="profile-view">
	                    			<div class="form-horizontal">
	                    				<div class="form-group">
		                                    <label class="control-label col-lg-4">ID</label>
		                                    <div class="col-md-8">
		                                        <input type="text" class="form-control" name="memberId" value="${ memberInfo.memberId }" readonly>
		                                    </div>
		                                </div>
		                                <div class="form-group">
		                                    <label class="control-label col-lg-4">사번</label>
		                                    <div class="col-md-8">
		                                        <input type="text" class="form-control" name="memberNo" value="${ memberInfo.memberNo }" readonly>
		                                    </div>
		                                </div>
		                                <div class="form-group">
		                                    <label class="control-label col-lg-4">이름</label>
		                                    <div class="col-md-8">
		                                        <input type="text" class="form-control" name="name" value="${ memberInfo.name }">
		                                    </div>
		                                </div>
		                                <div class="form-group">
		                                    <label class="control-label col-lg-4">부서</label>
		                                    <div class="col-md-8">
		                                        <select class="form-control" name="department" onchange="changeSelection(this.value)" required>
													<c:forEach var="deptList" items="${ deptList }">
														<option><c:out value="${ deptList.deptCode } ${ deptList.deptName }" /></option>
													</c:forEach>
												</select>
		                                    </div>
		                                </div>
		                                <div class="form-group">
		                                    <label class="control-label col-lg-4">직급</label>
		                                    <div class="col-md-8">
		                                        <select class="form-control" name="job" required>
													<c:forEach var="jobList" items="${ jobList }">
														<option><c:out value="${ jobList.jobCode } ${ jobList.jobName }" /></option>
													</c:forEach>
												</select>
		                                    </div>
		                                </div>
		                                <div class="form-group">
		                                    <label class="control-label col-lg-4">E-mail</label>
		                                    <div class="col-md-8">
		                                        <input type="email" class="form-control" name="email" value="${ memberInfo.email }">
		                                    </div>
		                                </div>
		                                <div class="form-group">
		                                    <label class="control-label col-lg-4">사내전화</label>
		                                    <div class="col-md-8">
		                                        <input type="text" class="form-control" name="deptCallNumber" value="${ memberInfo.department.deptCallNumber }" readonly>
		                                    </div>
		                                </div>
		                                <div class="form-group">
		                                    <label class="control-label col-lg-4">입사일</label>
		                                    <div class="col-md-8">
		                                    	<div class="cal-icon">
		                                        	<input type="text" class="form-control floating datetimepicker" name="enrollDate" value="${ memberInfo.enrollDate }" readonly>
		                                        </div>
		                                    </div>
		                                </div>
		                                <div class="form-group">
		                                    <label class="control-label col-lg-4">재직 상태</label>
		                                    <div class="col-md-8">
		                                        <select class="form-control" name="entYn">
													<option value="N">재직</option>
													<option value="Y">퇴직</option>
												</select>
		                                    </div>
		                                </div>
		                                <div class="form-group">
		                                    <label class="control-label col-lg-4">권한</label>
		                                    <div class="col-md-8">
		                                        <select class="form-control" name="role">
													<option><c:out value="" /></option>
													<c:forEach var="roleList" items="${ roleList }">
														<option><c:out value="${ roleList.roleCode } ${ roleList.roleName }" /></option>
													</c:forEach>
												</select>
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
		                                    <label class="control-label col-lg-4">성별</label>
		                                    <div class="col-md-8">
		                                    	<input type="text" class="form-control" name="gender" value="${ memberInfo.gender }" readonly>
		                                    </div>
		                                </div>
	                                    <div class="form-group">
	                                    	<label class="control-label col-lg-4">생년월일</label>
	                                    	<div class="col-md-8">
	                                            <div class="cal-icon">
	                                               	<input type="text" class="form-control floating datetimepicker" name="birthday" value="${ memberInfo.birthday }" required readonly>
	                                            </div>
	                                        </div>
	                                	</div>
	                                	<div class="form-group">
		                                    <label class="control-label col-lg-4">주민등록번호</label>
		                                    <div class="col-md-3">
		                                        <input type="text" class="form-control" name="firstRrn" value="${ firstRrn }" required readonly>
		                                    </div>
		                                    <label class="control-label col-lg-1"> - </label>
		                                    <div class="col-md-4">
		                                        <input type="password" class="form-control" name="lastRrn" value="${ lastRrn }" required readonly>
		                                    </div>
	                                	</div>
	                                	<div class="form-group">
		                                    <label class="control-label col-lg-4">휴대전화</label>
		                                    <div class="col-md-8">
		                                        <input type="tel" class="form-control" name="phone" value="${ memberInfo.phone }" required readonly>
		                                    </div>
		                                </div>
		                                <div class="form-group">
		                                    <label class="control-label col-lg-4">자택주소</label>
		                                    <div class="col-md-8">
		                                    	<div class="input-group">
		                                    		<span>
		                                    			<input type="text" class="form-control" name="zipCode" id="zipCode" value="${ zipCode }" readonly required>
		                                    		</span>
	                                            	<span class="input-group-btn">
														<button type="button" class="btn btn-primary" id="searchZipCode">검색</button>
													</span>
												</div>
											</div>
		                                </div>
		                                <div class="form-group">
		                                    <label class="control-label col-lg-4"></label>
		                                    <div class="col-md-8">
		                                        <input type="text" class="form-control" name="address1" id="address1" value="${ address1 }" readonly required>
		                                    </div>
		                                </div>
		                                <div class="form-group">
		                                    <label class="control-label col-lg-4"></label>
		                                    <div class="col-md-8">
		                                        <input type="text" class="form-control" name="address2" id="address2" value="${ address2 }" required readonly>
		                                    </div>
		                                </div>
		                                <div class="form-group">
		                                    <label class="control-label col-lg-4">본인 확인 질문</label>
		                                    <div class="col-md-8">
				                               	<select name="question" class="form-control" required disabled>
													<option><c:out value="${ memberInfo.checkQuestion.questionCode } ${ memberInfo.checkQuestion.questionBody }" /></option>
											</select>
		                                    </div>
		                                </div>
		                                <div class="form-group">
		                                    <label class="control-label col-lg-4">답변</label>
		                                    <div class="col-md-8">
		                                        <input type="text" class="form-control" name="answer" value="${ memberInfo.questionAnswer }" required readonly>
		                                    </div>
		                                </div>
			                        </div>
			                    </div>
	                        </div>
	                    </div>
	                </div>
	                
	                <div align="center">
	                	<button type="submit" class="btn btn-success">프로필 수정</button>
	                	<button type="reset" class="btn btn-default" id="goBack">돌아가기</button>
	                </div>
	            </form>
            </div>
        </div>
    </div>
    
    <script>
	    $goBack.onclick = function() {
			location.href = "${ pageContext.servletContext.contextPath }/mypage/info/select"
		}
    </script>
</body>

</html>