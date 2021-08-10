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
						<h4 class="page-title"><b>복지 신청</b></h4>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="card-box">
							<h4 class="card-title">복지 신청 가능 목록</h4>
							<form class="form-horizontal" action="/welfare/list/select">
								<div class="form-group">
									<label class="control-label col-lg-2">신청 복지 선택</label>
									<div class="col-lg-10" align="center">
										<select class="form-control">
											<option>-- 복지 신청 가능 목록 --</option>
											<c:forEach items="${ requestScope.welfareList }"
												var="welfare">
												<option><c:out value="${ welfare }" /></option>
											</c:forEach>
										</select>
										<br>
						<button class="btn btn-primary" type="submit">신청하기1</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>

</html>