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
						<a
							href="${ pageContext.servletContext.contextPath }/schedule/holiday/insert"
							class="btn btn-primary rounded pull-right"><i
							class="fa fa-plus"></i> 휴가신청하기</a>
					</div>
				</div>

				<!-- 검색조건 -->
				<div class="row filter-row">
					<div class="col-sm-3 col-md-3 col-xs-6">
						<div class="form-group form-focus select-focus">
							<label class="control-label">휴가 유형</label> <select
								class="select floating">
								<option>-- Select 수정해야됨 --</option>
								<option>연차</option>
								<option>반차</option>
								<option>교육</option>
							</select>
						</div>
					</div>
					<div class="col-sm-3 col-md-3 col-xs-6">
						<div class="form-group form-focus select-focus">
							<label class="control-label">휴가신청 승인상태</label> <select
								class="select floating">
								<option>-- Select --</option>
								<option>승인</option>
								<option>반려</option>
								<option>대기</option>
								<option>미처리</option>
							</select>
						</div>
					</div>
					<div class="col-sm-3 col-md-2 col-xs-6">
						<div class="form-group form-focus">
							<label class="control-label">시작일</label>
							<div class="cal-icon">
								<input class="form-control floating datetimepicker" type="text"
									name="startDate">
							</div>
						</div>
					</div>
					<div class="col-sm-3 col-md-2 col-xs-6">
						<div class="form-group form-focus">
							<label class="control-label">종료일</label>
							<div class="cal-icon">
								<input class="form-control floating datetimepicker" type="text"
									name="endDate">
							</div>
						</div>
					</div>
					<div class="col-sm-3 col-md-2 col-xs-6">
						<a href="#" class="btn btn-success btn-block"> Search </a>
					</div>
				</div>
				<!-- 검색조건 end -->

				<!-- 근무신청내용 -->
				<div class="row">
					<div class="col-md-12">
						<div class="table-responsive">
							<table class="table table-striped custom-table m-b-0 datatable">

								<tbody>
								<thead>
									<tr>
										<!-- 시작일과 종료일도 뜨게 해주고 싶네 -->
										<th>결재번호</th>
										<th>결재분류</th>
										<th>결재제목</th>
										<th>결재라인</th>
										<th>상신일</th>
										<th>진행상태</th>
										<th>신청사유</th>
									</tr>
								</thead>


								<c:forEach var="holiday"
									items="${ requestScope.holidayReportList }">

									<!-- 승인인지 여부만 확인 -->
									<c:set var="isApproved"
										value="${ fn:contains(holiday.reportStatus, \"승인\") }" />

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
					const no = this.parentNode.children[0].innerText; //결재번호가 0번째 td이므로, [0]의 innerText를 가져오기
					location.href = "${ pageContext.servletContext.contextPath }/schedule/holiday/selectOne?no="
							+ no;
				}

			}

		}
	</script>
</body>
</body>

</html>