<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
		<div class="page-wrapper">
			<div class="content container-fluid">
				<div class="row">
					<div class="col-xs-8">
						<h4 class="page-title">휴가 신청 현황</h4>
					</div>
					<div class="col-xs-4 text-right m-b-30">
						<a href="${ pageContext.servletContext.contextPath }/schedule/holiday/insert" class="btn btn-primary rounded pull-right">
							<i class="fa fa-plus"></i> 휴가신청하기</a>
					</div>
				</div>

				<!-- 검색조건 -->
				<div class="search-area" align="right">
					<form id="loginForm" action="${ pageContext.servletContext.contextPath }/schedule/holiday/select"
						method="get" style="display: inline-block">
						<input type="hidden" name="currentPage" value="1"> 
						<select id="searchCondition" name="searchCondition">
							<option value="title"
								${ requestScope.selectCriteria.searchCondition eq "title"? "selected": "" }>제목</option>
							<option value="status"
								${ requestScope.selectCriteria.searchCondition eq "status"? "selected": "" }>결재상태</option>
						</select> <input type="search" id="searchValue" name="searchValue"
							value="<c:out value="${ requestScope.selectCriteria.searchValue }"/>">
						<button type="submit" class="btn btn-success btn-sm">검색하기</button>
					</form>
				</div>

				<!-- 휴가신청내용 -->
				<div class="row">
					<div class="col-md-12">
						<div class="table-responsive">
							<table class="table table-striped custom-table m-b-0 datatable">
								<tbody>
								<thead>
									<tr>
										<th>결재번호</th>
										<th>결재분류</th>
										<th>결재제목</th>
										<th>결재라인</th>
										<th>상신일</th>
										<th>진행상태</th>
										<th>신청사유</th>
									</tr>
								</thead>

								<c:forEach var="holiday" items="${ requestScope.holidayReportList }">
									<c:set var="isApproved"	value="${ fn:contains(holiday.reportStatus, \"승인\") }" />
									<c:set var="isRejected"	value="${ fn:contains(holiday.reportStatus, \"반려\") }" />
									<c:set var="isAwaiting"	value="${ fn:contains(holiday.reportStatus, \"대기\") }" />
									<tr>
										<td><c:out value="${ holiday.reportNo }" /></td>
										<td><c:if test="${ holiday.documentNo eq 6 }">
												<c:out value="휴가신청서" />
											</c:if></td>
										<td><c:out value="${ holiday.reportTitle}" /></td>
										<td><c:out value="${ holiday.lineName }" /></td>
										<td><c:out value="${ holiday.reportDate }" /></td>
										<td><c:choose>
												<c:when test="${ isApproved }">
													<i class="fa fa-dot-circle-o text-info"></i>
													<c:out value=" ${ holiday.reportStatus }" />
												</c:when>
												<c:when test="${ isRejected }">
													<i class="fa fa-dot-circle-o text-purple"></i>
													<c:out value=" ${ holiday.reportStatus }" />
												</c:when>
												<c:when test="${ isAwaiting }">
													<i class="fa fa-dot-circle-o text-success"></i>
													<c:out value=" ${ holiday.reportStatus }" />
												</c:when>
												<c:otherwise>
													<i class="fa fa-dot-circle-o text-danger"></i>
													<c:out value=" ${ holiday.reportStatus }" />
												</c:otherwise>
											</c:choose></td>
										<td><c:out value="${ holiday.reportNote }" /></td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
							<!-- 근무신청내용 end -->

							<!-- 페이징 부분 -->
							<jsp:include page="../common/navbar.jsp" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- main-wrapper end -->

	<script>
		if (document.getElementsByTagName("td")) {
			const $tds = document.getElementsByTagName("td");
			for (let i = 0; i < $tds.length; i++) {

				$tds[i].onmouseenter = function() {
					this.parentNode.style.backgroundColor = "LightGoldenRodYellow";
					this.parentNode.style.cursor = "pointer";
				}

				$tds[i].onmouseout = function() {
					this.parentNode.style.backgroundColor = "white";
				}

				$tds[i].onclick = function() {
					const no = this.parentNode.children[0].innerText; 
					location.href = "${ pageContext.servletContext.contextPath }/schedule/holiday/selectOne?no="
							+ no;
				}

			}

		}
	</script>
</body>
</body>

</html>