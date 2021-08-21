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
		<div class="page-wrapper" align="center">
			<div class="content container-fluid">
				<div class="row">
					<div class="col-xs-6">
						<h4 class="page-title">휴가수동발생 상세내역</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">

						<div class="row">
							<div>
								<div class="col-xs-6">
									<h5>${ memberHolidayInfo.name }(${ memberHolidayInfo.memberNo })</h5>
								</div>
								<div>
									<h5>잔여휴가일수 : ${ memberHolidayInfo.remainingHoliday }</h5>
								</div>
								<div class="card-box "
									style="width: 1000px; background: lightyellow; border: 1px solid black;">
									<div class="card-block">
										<p class="content-group"></p>
										<table class="display datatable table table-stripped">

											<thead>
												<tr bgcolor="FFBC35">
													<th>내역번호</th>
													<th>종류</th>
													<th>일자</th>
													<th>발생일자</th>
													<th>비고</th>
												</tr>

											</thead>
											<c:forEach var="board" items="${ holidayLogList }">
												<tr>
													<td><c:out value="${ board.holidayLogNo }" /></td>
													<c:choose>
														<c:when test="${board.isProducedByRule eq 'Y'}">
															<td><c:out value="정기연차" /></td>
														</c:when>
														<c:when test="${board.isProducedByRule eq 'N'}">
															<td><c:out value="수동" /></td>
														</c:when>

													</c:choose>


													<td><c:out value="${ board.logOccurDate}" /></td>
													<td><c:out value="${ board.holidayDuringDate }" /></td>
													<td><c:out value="${ board.logNote}" /></td>
												</tr>
											</c:forEach>

										</table>
										<%-- 페이지 처리 --%>
										<jsp:include page="../common/paging.jsp" />
									</div>
								</div>
							</div>
						</div>

						<form class="form-horizontal"
							action="${ pageContext.servletContext.contextPath }/mng/holiday/manual/insert"
							method="get" onsubmit="return askAgain();">
							<div class="row">
								<div class="col-sm-12 text-center m-t-20">
									<div hidden>
										<input type="text" name="memberNo" class="form-control"
											value="${ memberHolidayInfo.memberNo }">
									</div>
									<button type="submit" class="btn btn-primary btn-lg">
										수동지급</button>

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
			location.href = "${ pageContext.servletContext.contextPath }/mng/holiday/manual/select"
		}

		function askAgain() {

			var yn;
			yn = confirm('휴가를 수동발생하시겠습니까?');

			if (yn == true) {
				return true;
			} else if (yn == false) {
				return false;
			}
		}
	</script>
</body>

</html>