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
</head>

<body>
	<div class="main-wrapper">
		<jsp:include page="../common/navbar.jsp" />
		<div class="page-wrapper">
			<div class="content container-fluid">
				<div class="row">
					<div class="col-md-8 col-md-offset-2">
						<h4 class="page-title">기숙사 입주 신청서</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-md-8 col-md-offset-2">
						<form name="insertDomitory"
							action="${ pageContext.servletContext.contextPath }/welfare/domitory/insert"
							method="POST" onsubmit="return askAgain();">
							<div class="form-group">
								<label>직원 ID</label> <input name="memberNo" class="form-control"
									type="text" value="${memberNo}" readonly="readonly" />
							</div>
							<div class="form-group">
								<label>부서</label> <input name="deptName" class="form-control"
									type="text"
									value="${ sessionScope.memberInfo.department.deptName }"
									required="required">
							</div>
							<div class="form-group">
								<label>직위</label> <input name="jobName" class="form-control"
									type="text" value="${ sessionScope.memberInfo.job.jobName }"
									required="required">
							</div>
							<div class="form-group">
								<label>신청 기숙사 : </label>
								<c:if test="${requestScope.manageNo eq 1}">
									<c:out value="1동 101호" />
								</c:if>
								<c:if test="${requestScope.manageNo eq 2}">
									<c:out value="1동 102호" />
								</c:if>
								<c:if test="${requestScope.manageNo eq 3}">
									<c:out value="2동 201호" />
								</c:if>
								<c:if test="${requestScope.manageNo eq 4}">
									<c:out value="2동 202호" />
								</c:if>
							</div>
							<div class="form-group">
								<label>신청자</label> <input name="name" class="form-control"
									type="text" value="${ sessionScope.memberInfo.name }"
									readonly="readonly">
							</div>
							<br>
							<div class="form-group">
								<label>현 거주지</label> <input name="address" class="form-control"
									type="text" value="${ requestScope.address }"
									required="required">
							</div>
							<br>
							<div class="form-group">
								<label>입주 희망일</label> <input name="hopeDate"
									class="form-control" type="date" required="required">
							</div>
							<br>
							<div class="form-group">
								<label>결재선 지정</label> <select name="lineList"
									class="form-control" required="required">
									<option value="">-- 결재선 지정 목록 --</option>
									<c:forEach items="${ requestScope.lineList }" var="lineList">
										<option value=${ lineList.lineNo }>${ lineList.lineName }</option>
									</c:forEach>
								</select>
							</div>

							<div class="form-group">
								<label>입주 신청 사유</label>
								<textarea name="domitoryInfo" cols="30" rows="6"
									class="form-control" required="required"></textarea>
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
			if (!confirm("돌아가시겠습니까?\n작성중이던 모든 내용이 삭제됩니다.")) {

			} else {
				location.href = "${ pageContext.servletContext.contextPath }/welfare/list/selected?selectedWelfare=기숙사입주신청"
			}
		}
		function askAgain() {

			var yn;
			yn = confirm('기숙사 신청을 완료하시겠습니까?\n신청 후에는 수정이 불가합니다');

			if (yn == true) {
				return true;
			} else {
				return false;
			}
		}
	</script>
</body>

</html>