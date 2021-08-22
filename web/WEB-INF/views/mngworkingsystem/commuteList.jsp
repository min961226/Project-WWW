<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

		<div class="page-wrapper" style="min-height: 722px;">
			<div class="content container-fluid">
				<div class="row">
					<div class="col-xs-12">
						<h4 class="page-title">직원 근무시간 조회</h4>
						<input value="${ requestScope.thisYear }년 ${ requestScope.thisMonth }월">
					</div>
				</div>
				
				<!-- 검색조건 -->
				<div class="search-area" align="right">
					<form id="loginForm"
						action="${ pageContext.servletContext.contextPath }/mng/workingSystem/commute/select"
						method="get" style="display: inline-block">
						<input type="hidden" name="currentPage" value="1"> 
						<select	id="searchCondition" name="searchCondition">
							<option value="name"
								${ requestScope.selectCriteria.searchCondition eq "name"? "selected": "" }>사원명</option>
							<option value="deptName"
								${ requestScope.selectCriteria.searchCondition eq "deptName"? "selected": "" }>부서코드</option>
							<option value="jobName"
								${ requestScope.selectCriteria.searchCondition eq "jobName"? "selected": "" }>직위코드</option>
							<option value="yearMonth"
								${ requestScope.selectCriteria.searchCondition eq "yearMonth"? "selected": "" }>연,월(yyyyMM)</option>
						</select> <input type="search" id="searchValue" name="searchValue"
							value="<c:out value="${ requestScope.selectCriteria.searchValue }"/>">
						<button type="submit" class="btn btn-success btn-sm">검색하기</button>
						<!-- <button type="button" id="writeFree">작성하기</button> -->
					</form>
				</div>
				<!-- 검색조건 end -->
				

				<div class="row">
					<div class="col-lg-12">
						<div class="card-box">
							<div class="card-block">
								<c:forEach var="memberList" items="${ requestScope.memberList}">
									<div>
										<table class="table table-striped">
											<thead>
												<!-- 요일출력부분 -->
												<tr>
													<th><c:out value="이름"></c:out></th>
													<th><c:out value="부서"></c:out></th>
													<th><c:out value="직급"></c:out></th>
													<c:forEach var="days" items="${ memberList.dailyCommuteList }">
														<th><c:out value="${ days.dateNum }" /></th>
													</c:forEach>
												</tr>
											</thead>
											
											<tbody>
												<!-- 요일 출력부분 -->
												<tr style="font-size: 8px">
													<th><c:out value="${ memberList.name }"></c:out></th>
													<th><c:out value="${ memberList.deptName }"></c:out></th>
													<th><c:out value="${ memberList.jobName }"></c:out></th>
													<c:forEach var="days" items="${ memberList.dailyCommuteList }">
														<c:set var="isSaterDay" value="${ fn:contains(days.dayOfWeek, \"토\") }" />
														<c:set var="isSundayDay" value="${ fn:contains(days.dayOfWeek, \"일\") }" />
														<c:choose>
															<c:when test="${ isSaterDay }">
																<td><div style="color: #1C7EBB"><c:out value="${ days.dayOfWeek }" />
																	</div></td>
															</c:when>
															<c:when test="${ isSundayDay }">
																<td><div style="color: #E94B3B"><c:out value="${ days.dayOfWeek }" />
																	</div></td>
															</c:when>
															<c:otherwise>
																<td><div><c:out value="${ days.dayOfWeek }" />
																	</div></td>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</tr>
												
												<!-- 출근시간 출력부분 -->
												<tr style="font-size: 5px">
													<td></td>
													<td></td>
													<td></td>
													<c:forEach var="days" items="${ memberList.dailyCommuteList }">
														<c:set var="isSaterDay"	value="${ fn:contains(days.dayOfWeek, \"토\") }" />
														<c:set var="isSundayDay" value="${ fn:contains(days.dayOfWeek, \"일\") }" />
														<c:set var="isLate"	value="${ fn:contains(days.lateYn, \"Y\")}" />
														<c:choose>
															<c:when test="${ isSaterDay || isSundayDay }">
																<td><div style="color: #E94B3B">휴</div></td>
															</c:when>
															<c:when test="${ !empty days.checkInTime && isLate}">
																<th><i class="fa fa-dot-circle-o text-danger"></i><br>
																<c:out value="${ days.checkInTime }" /></th>
															</c:when>
															<c:otherwise>
																<th><c:out value="${ days.checkInTime }" /></th>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</tr>
												
												<!-- 퇴근시간 출력부분 -->
												<tr style="font-size: 5px">
													<td></td>
													<td></td>
													<td></td>
													<c:forEach var="days" items="${ memberList.dailyCommuteList }">
														<c:set var="isSaterDay" value="${ fn:contains(days.dayOfWeek, \"토\") }" />
														<c:set var="isSundayDay" value="${ fn:contains(days.dayOfWeek, \"일\") }" />
														<c:set var="isLeaveEarly" value="${ fn:contains(days.leaveEarlyYn, \"Y\")}" />
														<c:choose>
															<c:when test="${ isSaterDay || isSundayDay }">
																<td><div style="color: #E94B3B">휴</div></td>
															</c:when>
															<c:when test="${ !empty days.checkOutTime && isLeaveEarly}">
																<th><i class="fa fa-dot-circle-o text-danger"></i><br>
																<c:out value="${ days.checkOutTime }" /></th>
															</c:when>
															<c:otherwise>
																<th><c:out value="${ days.checkOutTime }" /></th>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</tr>
											</tbody>
										</table>
									</div>
								</c:forEach>
																	
								</div>
								<jsp:include page="../common/navbar.jsp" />

							</div>
						</div>
					</div>
				</div>
			<!-- content END -->

			</div>
		<!-- page-wrapper END -->
		</div>
	<!-- main-wrapper END -->
</body>

</html>