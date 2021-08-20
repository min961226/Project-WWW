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
		<jsp:include page="../common/navbar.jsp" />
		<div class="page-wrapper">
			<div class="content container-fluid">
				<div class="row">
					<div class="col-xs-6">
						<h4 class="page-title">휴가수동발생 상세내역</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12" >
						
						<div class="row" >
				<div class="col-xs-6">
				<div class="col-xs-6">
				<h5>${ memberHolidayInfo.name }(${ memberHolidayInfo.memberNo })</h5>
				</div>
				<div >
				<h5>잔여휴가일수 : ${ memberHolidayInfo.remainingHoliday }</h5>
				</div>
					<div class="card-box " style="background: lightyellow; border: 1px solid black;" align="center">
						<div class="card-block" >
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
										<td><c:out value="${ board.isProducedByRule }" /></td>
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
							action="${ pageContext.servletContext.contextPath }/mng/holiday/applied/delete?no=${ requestScope.selectedReport.reportNo }"
							method="post" onsubmit="return askAgain();">
							<div class="row">
								<div hidden>
									<input name="status"
										value="${ requestScope.selectedReport.reportStatus }" /> <input
										name="memberNo"
										value="${ requestScope.selectedReport.memberNo }" />
								</div>
								<div class="col-sm-12 text-center m-t-20">
									<c:if
										test="${ requestScope.selectedReport.reportStatus ne '취소'}">
										<button type="submit" class="btn btn-primary btn-lg">
											수동지급</button>
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
			location.href = "${ pageContext.servletContext.contextPath }/mng/holiday/applied/select"
		}

		function askAgain() {

			var yn;
			yn = confirm('해당 휴가를 취소하시겠습니까?');

			if (yn == true) {
				return true;
			} else if (yn == false) {
				return false;
			}
		}
	</script>
</body>

</html>