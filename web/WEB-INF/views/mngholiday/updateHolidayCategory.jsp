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
						<h4 class="page-title">휴가유형 상세보기/수정하기</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-md-8 col-md-offset-2">
						<form name="insertSelfDev"
							action="${ pageContext.servletContext.contextPath }/mng/holiday/category/update"
							
							method="POST" onsubmit="return askAgain();">
							<div class="form-group col-xs-6">
								<label>유형번호</label> <input name="holidayCode"
									class="form-control" type="text"
									value="${ requestScope.holidayType.holidayCode }"
									readonly="readonly" />
							</div>
							<div class="form-group  col-xs-6">
								<label>유형명</label> <input name="holidayName"
									class="form-control" type="text"
									value="${ requestScope.holidayType.holidayName }" />
							</div>
							<div class="form-group  col-xs-6">
								<label>분류</label> <select class="form-control"
									name="holidayType">
									<option value='연가'
										<c:if test="${requestScope.holidayType.holidayType eq '연가'}"> selected</c:if>>
										연가</option>
									<option value='공가'
										<c:if test="${requestScope.holidayType.holidayType eq '공가'}"> selected</c:if>>공가</option>
									<option value='반차'
										<c:if test="${requestScope.holidayType.holidayType eq '반차'}"> selected</c:if>>반차</option>
									<option value='포상'
										<c:if test="${requestScope.holidayType.holidayType eq '포상'}"> selected</c:if>>포상</option>
									<option value='기타'
										<c:if test="${requestScope.holidayType.holidayType eq '기타'}"> selected</c:if>>기타</option>
								</select>

							</div>
							<div class="form-group  col-xs-6">
								<label>활성화여부</label> <br><input type="radio" name="useYn" value="Y"
									<c:if test="${requestScope.holidayType.useYn eq 'Y'}"> checked</c:if>>활성
								<input type="radio" name="useYn" value="N"
									<c:if test="${requestScope.holidayType.useYn eq 'N'}"> checked</c:if>>비활성
							</div>
							<div class="form-group col-xs-12">

								<label>비고</label>
								<div class="col-lg-12">
									<textarea name="holidayNote" rows="5" cols="5" class="form-control"
										placeholder="비고를 입력해주세요">${ requestScope.holidayType.holidayNote }</textarea>
								</div>
							</div>

							<div class="m-t-20 text-center">
								<button class="btn btn-primary btn-lg" type="submit">유형수정</button>
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
				location.href = "${ pageContext.servletContext.contextPath }/mng/holiday/category/select"
			}
		}

		function askAgain() {

			var yn;
			yn = confirm('작성한 내용으로 휴가유형을 수정하시겠습니까?');

			if (yn == true) {
				return true;
			} else if (yn == false) {
				return false;
			}
		}
	</script>
</body>

</html>