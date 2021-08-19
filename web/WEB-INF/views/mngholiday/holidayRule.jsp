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
					<div class="col-xs-12"></div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<form class="form-horizontal"
							action="${ pageContext.servletContext.contextPath }/approval/line/insert"
							method="post">
							<div class="card-box">
								<div class="card-block">
									<p class="content-group">
									<h4 class="page-title">휴가일수 발생규칙</h4>
									연차에 따른 휴가발생 일수, 발생일을 조회/수정할 수 있습니다. <br> <br>


									<div class="col-xs-12" align="center">
										<c:set var="startHourNum" value="1" />
										<table class="col-xs-1 " style="background-color: #eeeeee;">
											<tr>
												<td>00년차 :</td>
												<td><input style="width: 50px;" value="0" />일</td>
											</tr>
										</table>
										<c:forEach var="hours" begin="1" end="40" step="1">
											<table class="col-xs-1 " style="background-color: #eeeeee;">
												<tr>
													<td><c:if test="${ hours < 10}">0</c:if><c:out
															value="${ hours }" />년차 :</td>
													<td><input style="width: 50px;" value="${ hours }" />일</td>
												</tr>
											</table>
										</c:forEach>


									</div>
									<br>
									<div class="col-xs-12">
									<br>
										<table class="col-xs-2 "  style="background-color: #eeeeee;">
											<tr>
												<td>발생일 : </td>
												<td>매년&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select style="width: 50px;" > <c:forEach
														var="hours" begin="1" end="12" step="1">
														<option value="${ hours }">${ hours }월</option>
													</c:forEach></select> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1일</td>
											</tr>
										</table>
									</div>
									<table class="table table-bordered ">
										<tbody>

											<c:forEach var="workinghour"
												items="${ requestScope.teamWorkingHourList }">
												<tr>
													<td><c:out value="${ workinghour.name }" /></td>
													<td><c:out
															value="${ workinghour.standardWorkDTO.checkInTime }" /></td>
													<td>1</td>
													<td style="background: blue">2</td>
													<td>1</td>
													<td>1</td>
													<td>2</td>

												</tr>
											</c:forEach>


										</tbody>
									</table>
									<div class="row">
										<div class="col-sm-12 text-center m-t-20">
											<button type="submit" class="btn btn-primary btn-lg">
												수정</button>

										</div>
									</div>
								</div>
								</div>
						</form>
					</div>
				</div>
			</div>
		</div>

	</div>
</body>

</html>