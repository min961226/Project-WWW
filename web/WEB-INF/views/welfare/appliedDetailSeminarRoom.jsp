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
					<div class="col-xs-12">
						<h4 class="page-title">복지 신청 내역 상세 조회</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">


						<form class="form-horizontal"
							action="${ pageContext.servletContext.contextPath }/welfare/applied/list/seminarRoom/delete?roomNo=${ requestScope.roomNo }&useDate=${ requestScope.useDate }&reservNo=${requestScope.reservNo}" method="post">



							<div class="form-group">
								<div class="col-sm-6">
									<div class="col-md-12">
									<label>복지 분류</label>
										<input class="form-control" value="회의실 예약 신청"
											readonly="readonly" />
									</div>
								</div>

								<div class="col-sm-6">
									<div class="col-md-12">
									<label>회의실 번호</label>
										<input class="form-control" value="${ requestScope.roomNo }"
											disabled />
									</div>
								</div>

							</div>


							<div class="form-group">
								<div class="col-sm-6">
									<div class="col-md-12">
									<label>신청자</label>
										<input class="form-control"
											value="${ sessionScope.memberInfo.name }" disabled />
									</div>
								</div>

								<div class="col-sm-6">
									<div class="col-md-12">
									<label>신청자 번호</label>
										<input class="form-control" value="${ requestScope.memberNo }"
											disabled />
									</div>
								</div>
							</div>

							<div class="form-group">
								<div class="col-sm-12">
									<div class="col-md-12">
									<label>회의실 예약일자</label>
										<input class="form-control" value="${ requestScope.useDate }"
											disabled />
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-12">
									<div class="col-sm-6">
										<label>파트별 시간 번호</label>
											<input class="form-control"
												value="${ requestScope.reservNo }" disabled />
									</div>

									<div class="col-md-6">
									<label>이용 시간</label>
										<input class="form-control"
											value=<c:if test="${ requestScope.reservNo eq 1 }">"08:00 - 10:00"</c:if>
											<c:if test="${ requestScope.reservNo eq 2 }">"10:00 - 12:00"</c:if>
											<c:if test="${ requestScope.reservNo eq 3 }">"12:00 - 14:00"</c:if>
											<c:if test="${ requestScope.reservNo eq 4 }">"14:00 - 16:00"</c:if>
											<c:if test="${ requestScope.reservNo eq 5 }">"16:00 - 18:00"</c:if>
											disabled />
									</div>
								</div>
							</div>

							<div class="form-group">
								<div class="col-sm-12">
									<div class="col-lg-12">
									<label>신청사유</label>
										<textarea name="note" rows="5" cols="5" class="form-control"
											disabled>${ requestScope.seminarInfo }</textarea>
									</div>
								</div>
							</div>


							<div class="row">
								<div class="col-sm-12 text-center m-t-20">
									<c:set var="isDatePassed" value="${ requestScope.isDatePassed }" />
									<c:if test="${  isDatePassed }">
										<button type="submit" class="btn btn-primary btn-lg">예약 취소하기</button>
									</c:if>
									<button type="reset" class="btn btn-primary btn-lg" id="goBack">돌아가기</button>
								</div>
							</div>

						</form>



					</div>
				</div>
			</div>

		</div>

	</div>
	<div class="sidebar-overlay" data-reff=""></div>

	<script>
		const $goBack = document.getElementById("goBack");
		$goBack.onclick = function() {
			location.href = "${ pageContext.servletContext.contextPath }/welfare/applied/list/seminarRoom/select"
		}
	</script>
</body>

</html>