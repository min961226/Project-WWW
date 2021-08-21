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
                        <h4 class="page-title">계정 생성</h4>
                    </div>
                </div>
            
           		<form action="${ pageContext.servletContext.contextPath }/mng/employee/list/insert" method="POST">
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
		                                    	<div class="input-group has-feedback" id="inputId">
		                                    		<span>
		                                    			<input type="text" class="form-control" name="memberId" id="memberId" value="" required>
		                                    		</span>
	                                            	<span class="input-group-btn">
														<button type="button" class="btn btn-primary" id="checkId">중복확인</button>
													</span>
												</div>
											</div>												
		                                </div>
		                    			<div class="form-group has-feedback" id="checkPwd1">
		                                    <label class="control-label col-lg-4">비밀번호</label>
		                                    <div class="col-md-8">
		                                        <input type="password" class="form-control" name="pwd1" id="pwd1" value="" required>
		                                    </div>
		                                </div>
		                    			<div class="form-group has-feedback" id="checkPwd2">
		                                    <label class="control-label col-lg-4">비밀번호 확인</label>
		                                    <div class="col-md-8">
		                                        <input type="password" class="form-control" name="pwd2" id="pwd2" value="" required>
		                                        <span class="help-block" id="message"></span>
		                                    </div>
		                                </div>
	                                </div>
	                            </div>
	                        </div>
	                        
	                        <div class="card-box">
	                    		<div class="profile-view">
		                            <div class="form-horizontal">
		                                <div class="form-group">
		                                    <label class="control-label col-lg-4">사번</label>
		                                    <div class="col-md-8">
		                                        <input type="text" class="form-control" name="memberNo" value="${ memberNo }" readonly>
		                                    </div>
		                                </div>
		                                <div class="form-group">
		                                    <label class="control-label col-lg-4">이름</label>
		                                    <div class="col-md-8">
		                                        <input type="text" class="form-control" name="name" value="" required>
		                                    </div>
		                                </div>
		                                <div class="form-group">
		                                    <label class="control-label col-lg-4" >부서</label>
		                                    <div class="col-md-8">
		                                        <select class="form-control" name="department" onchange="changeSelection(this.value)" required>
													<option value=""></option>
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
													<option value=""></option>
													<c:forEach var="jobList" items="${ jobList }">
														<option><c:out value="${ jobList.jobCode } ${ jobList.jobName }" /></option>
													</c:forEach>
												</select>
		                                    </div>
		                                </div>
		                                <div class="form-group">
		                                    <label class="control-label col-lg-4">E-mail</label>
		                                    <div class="col-md-8">
		                                        <input type="email" class="form-control" name="email" value="" required>
		                                    </div>
		                                </div>
		                                <div class="form-group">
		                                    <label class="control-label col-lg-4">사내전화</label>
		                                    <div class="col-md-8">
		                                    	
		                                        <input type="text" class="form-control" name="deptCallNumber" id="deptCallNumber" value="" readonly>
		                                    </div>
		                                </div>
		                                <div class="form-group">
		                                    <label class="control-label col-lg-4">입사일</label>
		                                    <div class="col-md-8">
			                                    <div class="cal-icon">
	                                               	<input type="text" class="form-control floating datetimepicker" name="enrollDate" value="" required>
	                                            </div>
                                            </div>
		                                </div>
		                                <div class="form-group">
		                                    <label class="control-label col-lg-4">재직 상태</label>
		                                    <div class="col-md-8">
		                                        <select class="form-control" name="entYn" disabled>
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
		                                        <input type="text" class="form-control" name="gender" value="" readonly>
		                                    </div>
		                                </div>
		                                <div class="form-group">
		                                    <label class="control-label col-lg-4">생년월일</label>
		                                    <div class="col-md-8">
		                                    	<div class="cal-icon">
		                                        	<input type="text" class="form-control floating datetimepicker" name="birthday" value="" readonly>
		                                        </div>
		                                    </div>
		                                </div>
		                                <div class="form-group">
		                                    <label class="control-label col-lg-4">주민등록번호</label>
		                                    <div class="col-md-3">
		                                        <input type="text" class="form-control" name="firstRrn" value="">
		                                    </div>
		                                    <label class="control-label col-lg-1"> - </label>
		                                    <div class="col-md-4">
		                                        <input type="password" class="form-control" name="lastRrn" value="">
		                                    </div>
		                                </div>
		                                <div class="form-group">
		                                    <label class="control-label col-lg-4">휴대전화</label>
		                                    <div class="col-md-8">
		                                        <input type="tel" class="form-control" name="phone" value="" readonly>
		                                    </div>
		                                </div>
		                                <div class="form-group">
		                                    <label class="control-label col-lg-4">자택주소</label>
		                                    <div class="col-md-8">
		                                    	<div class="input-group">
		                                    		<span><input type="text" class="form-control" name="zipCode" id="zipCode" value="" readonly></span>
	                                            	<span class="input-group-btn">
														<button type="button" class="btn btn-primary" id="searcHZipCode" disabled>검색</button>
													</span>
												</div>
											</div>												
		                                </div>
		                                <div class="form-group">
		                                    <label class="control-label col-lg-4"></label>
		                                    <div class="col-md-8">
		                                        <input type="text" class="form-control" name="address1" id="address1" value="" readonly>
		                                    </div>
		                                </div>
		                                <div class="form-group">
		                                    <label class="control-label col-lg-4"></label>
		                                    <div class="col-md-8">
		                                        <input type="text" class="form-control" name="address2" id="address2" value="" readonly>
		                                    </div>
		                                </div>
		                                <div class="form-group">
		                                    <label class="control-label col-lg-4">본인 확인 질문</label>
		                                    <div class="col-md-8">
		                                        <select name="question" class="form-control" required disabled>
													<option><c:out value="" /></option>
											</select>
		                                    </div>
			                            </div>
			                            <div class="form-group">
			                                <label class="control-label col-lg-4">답변</label>
			                                <div class="col-md-8">
			                            		<input type="text" class="form-control" name="answer" value="" readonly>
			                            	</div>
			                        	</div>
			                        </div>
			                    </div>
	                        </div>
	                    </div>
	                </div>
	                
	                <div align="center">
	                	<button type="submit" class="btn btn-success">계정 생성</button>
	                	<button type="reset" class="btn btn-default">취소</button>
	                </div>
                </form>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="${ pageContext.servletContext.contextPath }/assets/js/jquery-3.2.1.min.js"></script>
    <script>
    	$(function() {
    		$("#checkId").click(function() {
    			const memberId = $("#memberId").val();
    			
    			$.ajax({
    				url: "${ pageContext.servletContext.contextPath }/mng/employee/checkId/select",
    				type: "get",
    				data: { memberId : memberId },
    				success: function(data, textStatus, xhr) {
    					console.log(data);
    					if(data > 0) {
    						alert("이미 사용 중인 ID입니다.");
    						$("#inputId").removeClass("has-success");
    						$("#inputId").addClass("has-error");
    					} else if(data < 0) {
    						alert("ID를 입력해주세요.");
    						$("#inputId").removeClass("has-success");
    						$("#inputId").removeClass("has-error");
    					} else {
    						alert("사용 가능한 ID입니다.");
    						$("#inputId").removeClass("has-error");
    						$("#inputId").addClass("has-success");
    					}
    				},
    				error: function(xhr, status, error) {
    					alert("Error : " + error);
    				}
    			});
    		});
    	});
    
    	$(function() {
    		$("#pwd1, #pwd2").keyup(function() {
	    		const pwd1 = $("#pwd1").val();
	    		const pwd2 = $("#pwd2").val();
    			
	    		if(pwd1 == pwd2 && pwd2 != null) {
	    			$("#checkPwd1, #checkPwd2, #checkResult").removeClass("has-error");
	    			$("#checkPwd1, #checkPwd2, #checkResult").addClass("has-success");
	    			$("#message").html("<b>비밀번호가 일치합니다.</b>");
	    		} else if(pwd1 != pwd2) {
	    			$("#checkPwd1, #checkPwd2, #checkResult").removeClass("has-success");
	    			$("#checkPwd1, #checkPwd2, #checkResult").addClass("has-error");
	    			$("#message").html("<b>비밀번호가 일치하지 않습니다.</b>");
    			}
    		});
    	});
    	
    	function changeSelection(selectedDept) {
    		
    		const deptList = ${ deptListJson };
    		const selectedDeptCode = selectedDept.split(" ")[0];
    		
    		for(let i = 0; i < deptList.length; i++) {
    			if(selectedDeptCode == deptList[i].deptCode) {
    				$("#deptCallNumber").val(deptList[i].deptCallNumber);
    			}
    		}
    	}
    </script>
</body>

</html>