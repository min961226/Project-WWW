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
                    <div class="col-md-8 col-md-offset-2">
                        <h4 class="page-title">야간교통비 신청서</h4>
                    </div>
                </div>
                <div class="row">
                   <div class="col-md-8 col-md-offset-2">
						<form name = "insertNightTran" action="${ pageContext.servletContext.contextPath }/welfare/nightTransportation/insert" method="POST">
							<div class="form-group">
								<label>직원 ID</label> <input class="form-control" type="text"
									value="${memberNo}" readonly="readonly" />
							</div>
							<div class="form-group">
								<label>부서</label> <input name ="deptName" class="form-control" type="text" value=" ${ sessionScope.memberInfo.department.deptName }">
							</div>
							<div class="form-group">
								<label>직위</label> <input name ="jobName"  class="form-control" type="text" value="${ sessionScope.memberInfo.job.jobName }">
							</div>
							<div class="form-group">
								<label>신청자</label> <input name ="name" class="form-control" type="text"	value="${ sessionScope.memberInfo.name }" readonly="readonly">
							</div>
							<div class="form-group" >
								<label>결재선 지정</label>
								<select name="lineList" class="form-control">
									<option>-- 결재선 지정 목록 --</option>
									<c:forEach items="${ requestScope.lineList }" var="lineList">
										<option><c:out value="${ lineList.lineName }" /></option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label>야근 종료시간</label> <select name="selfDevList"
									class="form-control">
									<option>-- 결재 완료 야근 목록 --</option>
									<c:forEach items="${ requestScope.selfDevList }" var="selfDevList">
										<option><c:out value="${ selfDevList }" /></option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label>청구 예상 교통비</label> <br><input name="TransBill" class="" type="number" width="100px">￦
							</div>

							<div class="form-group">
								<label>신청 내용</label>
								<textarea name="selfDevInfo" cols="30" rows="6" class="form-control"></textarea>
							</div>

							<div class="form-group">
								<label>영수증 첨부</label>
								<div>
									<input class="form-control" type="file"> <small
										class="help-block">파일 최대 사이즈: 50 MB. 허용된 확장자: jpg,
										gif, png. </small>
								</div>

							</div>
							<div class="m-t-20 text-center">
								<button class="btn btn-primary btn-lg" type="submit">신청
									완료</button>
								<input id="goBack" type="reset" value="돌아가기"
									class="btn btn-primary btn-lg">
							</div>
						</form>
					</div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>