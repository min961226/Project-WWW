<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image/x-icon"
	href="${ pageContext.servletContext.contextPath }/assets/img/favicon.png">
<title>Wonderful Welfare Workspace</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$goBack.onclick = function() {
		location.href = "${ pageContext.servletContext.contextPath }";
	}
</script>
</head>

<body>
	<div class="main-wrapper">
		<jsp:include page="../common/navbar.jsp" />
		<div class="page-wrapper">
			<div class="content container-fluid">
				<div class="row">
					<div class="col-md-8 col-md-offset-2">
						<h4 class="page-title">경조금 신청서</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-md-8 col-md-offset-2">
						<form name="insertSelfDev"
							action="${ pageContext.servletContext.contextPath }/welfare/selfDevelopment/insert"
							method="POST">
							<div class="form-group">
								<label>직원 ID</label> <input name="memberNo" class="form-control"
									type="text" value="${memberNo}" readonly="readonly" />
							</div>
							<div class="form-group">
								<label>부서</label> <input name="deptName" class="form-control"
									type="text"
									value="${ sessionScope.memberInfo.department.deptName }">
							</div>
							<div class="form-group">
								<label>직위</label> <input name="jobName" class="form-control"
									type="text" value="${ sessionScope.memberInfo.job.jobName }">
							</div>
							<div class="form-group">
								<label>신청자</label> <input name="name" class="form-control"
									type="text" value="${ sessionScope.memberInfo.name }"
									readonly="readonly">
							</div>
							<select id="selectBox" name="selectBox">
								<option value="" selected="selected">전체</option>
								<option value="option1">옵션1</option>
								<option value="option2">옵션2</option>
								<option value="option3">옵션3</option>
							</select>
							<div class="div1">
							<select id="selectBox" name="selectBox">
								<option value="" selected="selected">전체</option>
								<option value="option1">옵션1</option>
								<option value="option2">옵션2</option>
								<option value="option3">옵션3</option>
							</select>
							</div>
							<div class="form-group">
								<label class="control-label col-lg-2">경조사 유형</label>
								<div class="col-lg-10">
									<div class="radio">
										<label> <input type="radio" name="radio"> 결혼
										</label>
									</div>
									<div class="radio">
										<label> <input type="radio" name="radio"> 출산
										</label>
									</div>
									<div class="radio">
										<label> <input type="radio" name="radio"> 사망
										</label>
									</div>
									<div class="radio">
										<label> <input type="radio" name="radio"> 회갑
										</label>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label>경조사명</label> <input name="eventName" class="form-control"
									type="text">
							</div>
							<div class="form-group">
								<label>장소</label> <input name="eventPlace" class="form-control"
									type="text">
							</div>
							<div class="form-group">
								<label>경조사 일자</label> <input name="date" class="form-control"
									type="date">
							</div>
							<div class="form-group">
								<label>결재선 지정</label> <select name="lineList"
									class="form-control">
									<option>-- 결재선 지정 목록 --</option>
									<c:forEach items="${ requestScope.lineList }" var="lineList">
										<option value=${ lineList.lineNo }>${ lineList.lineName }</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label>신청 내용</label>
								<textarea name="eventInfo" cols="30" rows="6"
									class="form-control"></textarea>
							</div>

							<div class="form-group">
								<label>증빙 자료 첨부</label>
								<div>
									<input class="form-control" type="file"> <small
										class="help-block">파일 최대 사이즈: 50 MB. 허용된 확장자: jpg,
										gif, png. </small>
								</div>

							</div>
							<div class="m-t-20 text-center">
								<button class="btn btn-primary btn-lg" type="submit">신청
									완료</button>
								<button type="reset" class="btn btn-primary btn-lg" id="goBack">돌아가기</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		const $goBack = document.getElementById("goBack");

		$goBack.onclick = function() {
			location.href = "${ pageContext.servletContext.contextPath }/welfare/list/select"
		}
	</script>
</body>

</html>