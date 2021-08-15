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
                    <div class="col-xs-12">
                        <h4 class="page-title">근무일정 생성 신청</h4>
                    </div>
                </div>
                
                <div class="row">
                    <div class="col-md-6">
                        <div class="card-box">
                            <h4 class="card-title">내용 작성</h4>
                            <form class="form-horizontal" action="${ pageContext.servletContext.contextPath }/schedule/workingSystem/insert" method="post">
                            	
                            	<div class="form-group">
                                	<label class="col-md-3 control-label">신청근무제도</label>
                                    <div class="col-md-9">
                                    	<!-- 일단은 선택가능한 거에 넣어두기 -->
                                        <select class="select" name="workNo" required>
                                            <option>선택</option>
                                            <option value="1">기본근태</option>
                                            <option value="2">시차출퇴근A</option>
                                            <option value="3">시차출퇴근B</option>
                                            <option value="4">시차출퇴근C</option>
                                            <option value="5">시차출퇴근D</option>
                                            <option value="6">선택근무제</option>
                                        </select>
                                    </div>
                                </div>
                            	
                                <div class="form-group">
                                    <label class="col-md-3 control-label">신청자</label>
                                    <div class="col-md-9">
                                        <input type="text" value="${ sessionScope.memberInfo.name }" class="form-control" disabled>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                	<label class="col-md-3 control-label">결재라인</label>
                                    <div class="col-md-9">
                                        <select class="select" name="line" required>
                                            <option>--결재라인 선택--</option>
                                            <c:forEach var="line" items="${ requestScope.lineList }">
												<option value= ${ line.lineNo }>${ line.lineName }</option>
											</c:forEach>
                                        </select>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-md-3 control-label">시작일</label>
                                    <div class="col-md-9">
                                        <input type="date" class="form-control" name="startDay" required>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-md-3 control-label">종료일</label>
                                    <div class="col-md-9">
                                        <input type="date" class="form-control" name="endDay" required>
                                    </div>
                                </div>
                                
                                <!-- 자바스크립트로 기간을... 바로 계산해주는 걸 해보자... -->
                                <div class="form-group">
                                    <label class="col-md-3 control-label">기간</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control">
                                    </div>
                                </div>
                                <script>
                                
                                </script>
                                
                                <div class="form-group">
                                    <label class="col-md-3 control-label">사유</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="changeReason" required>
                                    </div>
                                </div>
                               
                                <div align="center">
	                				<button type="submit" class="btn btn-success">신청하기</button>
	                				<button type="reset" class="btn btn-default" id="goBack">돌아가기</button>
	                			</div>
                            </form>
                        </div>
                    </div>
                </div>
                
                    
            </div>
        </div>
        
    </div>
    
    <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script>
    	const $goBack = document.getElementById("goBack");
    	
    	$goBack.onclick = function() {
    		location.href = "${ pageContext.servletContext.contextPath }/schedule/workingSystem/select"
    	}
    </script>
</body>

</html>