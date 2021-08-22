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
							action="${ pageContext.servletContext.contextPath }/mng/holiday/rule/select"
							method="post" onsubmit="return askAgain();">
							<div class="card-box">
								<div class="card-block">
									<p class="content-group">
									<h4 class="page-title">휴가일수 발생규칙</h4>
									연차에 따른 휴가발생 일수, 발생일을 조회/수정할 수 있습니다. <br> <br>


									<div class="col-xs-12" align="center">
										<c:set var="startHourNum" value="1" />
										<table class="col-xs-1 " style="background-color:LightGoldenRodYellow;">
											<tr>
												<td>01년차 :</td>
												<td><input style="width: 50px;" value="15" disabled />일</td>
											</tr>
										</table>
										<c:forEach var="year" items="${ requestScope.holidayRule }">
											<table class="col-xs-1 " style="background-color: LightGoldenRodYellow;">
												<tr>
													<td>
														<c:if test="${ year.workPeriod < 10}">0</c:if>${ year.workPeriod }년차 :</td>
													<td><input name="${ year.ruleCode }" type="number"
														min="1" style="width: 50px;" value="${ year.dayNumber }" />일</td>
												</tr>
											</table>
										</c:forEach>
									</div>
									<br>
									<div class="col-xs-12">
										<br>
										<table class="col-xs-2 " style="background-color: LightGoldenRodYellow;">
											<tr>
												<td>발생일 :</td>
												
												<td>매년&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<input name="giveDate" type="number" min="1" max="12" value="${ requestScope.giveDate }"/>월
												<%-- <select
													name="fiscalDate"style="width: 50px;">
														<c:forEach var="hours" begin="1" end="12" step="1">
															<option value="${ hours }">${ hours }월</option>
														</c:forEach>
												</select>  --%>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1일
												</td>
											</tr>
										</table>
									</div>
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
	<script>

		function askAgain() {

			var yn;
			yn = confirm('휴가일수 발생규칙을 수정하시겠습니까?');

			if (yn == true) {
				return true;
			} else if (yn == false) {
				return false;
			}
		}
	</script>
</body>

</html>