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
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js">
	
</script>
</head>

<body>
	<div class="main-wrapper">
		<jsp:include page="../common/navbar.jsp" />
		<div class="page-wrapper">
			<div class="content container-fluid">
				<div class="row">
					<div class="col-xs-12">
						<h4 class="page-title">복지 물품 상세 조회/반납</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<form class="form-horizontal"
							action="${ pageContext.servletContext.contextPath }/mng/welfare/laptopRental/update?logNo=${ requestScope.item.logNo}&itemNo=${ requestScope.item.itemNo }"
							method="post" onsubmit="return askAgain();">
							<div class="card-box"
								style="background: lightyellow; border: 1px solid black;">
								<div class="card-block">
									<table class="display datatable table table-stripped">
										<thead>
											<tr>
												<td rowspan="1">
													<h3>
														<b>물품 대여 기록</b>
													</h3>
												</td>
											</tr>
											<tr bgcolor="FFBC35">

												<th style="width: 200px">대여 기록 번호</th>
												<th>대여자 사번</th>
												<th>복지 물품 번호</th>
												<th>품목 종류</th>
												<th>품목명</th>
												<th>대여상태</th>
												<th>대여일</th>
												<th>반납일</th>
											</tr>
										</thead>
										<c:forEach var="itemLog" items="${ requestScope.itemLog }">
											<tr>
												<td><c:out value="${ itemLog.logNo }" /></td>
												<td><c:out value="${ itemLog.memberNo }" /></td>
												<td><c:out value="${ itemLog.itemNo }" /></td>
												<td><c:out value="${ itemLog.itemCategory }" /></td>
												<td><c:out value="${ itemLog.itemName }" /></td>
												<td><c:out value="반납완료" /></td>
												<td><c:out value="${ itemLog.reservationDate }" /></td>
												<td><c:out value="${ itemLog.returnDate }" /></td>
											</tr>
										</c:forEach>
									</table>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-6">
									<label>대여 기록 번호</label>
									<div class="col-md-12">
										<input class="form-control"
											value="${ requestScope.item.logNo }" disabled />
									</div>
								</div>
								<div class="col-sm-6">
									<label>복지 물품 번호</label>
									<div class="col-md-12">
										<input class="form-control" name="itemNo"
											value="${ requestScope.item.itemNo }" disabled />
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-6">
									<label>품목 종류</label>
									<div class="col-md-12">
										<input class="form-control"
											value="${ requestScope.item.itemCategory }" disabled />
									</div>
								</div>
								<div class="col-sm-6">
									<label>품목명</label>
									<div class="col-md-12">
										<input class="form-control"
											value="${ requestScope.item.itemName }" disabled />
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-6">
									<label>물품 대여자 사번</label>
									<div class="col-md-12">
										<input class="form-control"
											value="${ requestScope.item.memberNo }" disabled />
									</div>
								</div>
								<c:if test="${  empty requestScope.item.reservationStatus  }">
									<div class="col-sm-6">
										<label>대여상태</label>
										<div class="col-md-12">
											<input class="form-control" value="대여가능" disabled />
										</div>
									</div>
								</c:if>
								<c:if test="${  !empty requestScope.item.reservationStatus  }">
									<div class="col-sm-6">
										<label>대여상태</label>
										<div class="col-md-12">
											<input class="form-control"
												value="${ requestScope.item.reservationStatus }" disabled />
										</div>
									</div>
								</c:if>
							</div>
							<div class="form-group">
								<div class="col-sm-6">
									<label>물품 대여일</label>
									<div class="col-md-12">
										<input class="form-control"
											value="${ requestScope.item.reservationDate}" disabled />
									</div>
								</div>
								<div class="col-sm-6">
									<label>물품 반납 예정일</label>
									<div class="col-md-12">
										<input class="form-control"
											value="${ requestScope.item.returnDueDate }" disabled />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12 text-center m-t-20">
									<c:if test="${  requestScope.item.reservationStatus eq '대여중' }">
										<button type="submit" class="btn btn-primary btn-lg">반납
											완료</button>
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
		function askAgain() {

			var yn;
			yn = confirm('대여중인 물품을 반납처리하시겠습니까?\n반납일은 오늘 날짜로 기록됩니다.');

			if (yn == true) {
				return true;
			} else {
				return false;
			}
		}

		const $goBack = document.getElementById("goBack");
		$goBack.onclick = function() {
			location.href = "${ pageContext.servletContext.contextPath }/mng/welfare/laptopRental/select"
		}
	</script>
</body>

</html>