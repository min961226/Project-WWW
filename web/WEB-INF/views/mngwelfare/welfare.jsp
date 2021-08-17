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
					<div class="col-sm-8">
						<h4 class="page-title">시행 복지 목록 관리</h4>
					</div>
				</div>
				<div class="card-box" style="width: 700px">
					<div class="card-block">
						<div class="row">
						<form name="insertSelfDev"	action="${ pageContext.servletContext.contextPath }/mng/welfare/list/update"	method="POST">
							<div class="col-sm-12">
								<h6 class="panel-title m-b-20"><b>시행 복지 목록</b></h6>
								<div class="panel">
									<ul class="list-group">
										<li class="list-group-item">야간교통비 신청
											<c:if test="${ welfareYn[0] eq 'Y' }">
												<div class="material-switch pull-right" align="left">
													<input name="nightTrans" id="nightTrans" type="checkbox" checked="checked"> <label for="nightTrans" class="label-success"></label>
												</div>
											</c:if>
											<c:if test="${ welfareYn[0] eq 'N' }">
												<div class="material-switch pull-right" align="left">
													<input name="nightTrans" id="nightTrans" type="checkbox"> <label for="nightTrans" class="label-success"></label>
												</div>
											</c:if>
										</li>
										<li class="list-group-item">경조사 신청
											<c:if test="${ welfareYn[1] eq 'Y' }">
												<div class="material-switch pull-right" align="left">
													<input name="familyEvent" id="familyEvent" type="checkbox" checked="checked"> <label for="familyEvent" class="label-success"></label>
												</div>
											</c:if>
											<c:if test="${ welfareYn[1] eq 'N' }">
												<div class="material-switch pull-right" align="left">
													<input name="familyEvent" id="familyEvent" type="checkbox"> <label for="familyEvent" class="label-success"></label>
												</div>
											</c:if>
										</li>
										<li class="list-group-item">자기개발비 신청
											<c:if test="${ welfareYn[2] eq 'Y' }">
												<div class="material-switch pull-right" align="left">
													<input name="selfDev" id="selfDev" type="checkbox" checked="checked"> <label	for="selfDev" class="label-success"></label>
												</div>
											</c:if>
											<c:if test="${ welfareYn[2] eq 'N' }">
												<div class="material-switch pull-right" align="left">
													<input name="selfDev" id="selfDev" type="checkbox"> <label	for="selfDev" class="label-success"></label>
												</div>
											</c:if>
										</li>
										<li class="list-group-item">기숙사 입주 신청
											<c:if test="${ welfareYn[3] eq 'Y' }">
												<div class="material-switch pull-right" align="left">
													<input name="domitory" id="domitory" type="checkbox" checked="checked"> <label for="domitory" class="label-success"></label>
												</div>
											</c:if>
											<c:if test="${ welfareYn[3] eq 'N' }">
												<div class="material-switch pull-right" align="left">
													<input name="domitory" id="domitory" type="checkbox" > <label for="domitory" class="label-success"></label>
												</div>
											</c:if>
										</li>
										<li class="list-group-item">회의실 대여 신청
											<c:if test="${ welfareYn[4] eq 'Y' }">
												<div class="material-switch pull-right" align="left">
													<input name="seminarRoom" id="seminarRoom" type="checkbox" checked="checked"> <label	for="seminarRoom" class="label-success"></label>
												</div>
											</c:if>
											
											<c:if test="${ welfareYn[4] eq 'N' }">
												<div class="material-switch pull-right" align="left">
													<input name="seminarRoom" id="seminarRoom" type="checkbox"> <label	for="seminarRoom" class="label-success"></label>
												</div>
											</c:if>
										</li>
										<li class="list-group-item">노트북 대여신청
											<c:if test="${ welfareYn[5] eq 'Y' }">
												<div class="material-switch pull-right" align="left">
													<input name="rentalLaptop" id="rentalLaptop" type="checkbox" checked="checked"> <label for="rentalLaptop" class="label-success"></label>
												</div>
											</c:if>
											<c:if test="${ welfareYn[5] eq 'N' }">
												<div class="material-switch pull-right" align="left">
													<input name="rentalLaptop" id="rentalLaptop" type="checkbox"> <label for="rentalLaptop" class="label-success"></label>
												</div>
											</c:if>
										</li>
									</ul>
								</div>
							</div>
							<div align="right" style="margin-right: 20px;padding:5px">
							<button class="btn btn-success" style="margin-right: 10px" type="submit">저장</button>
							<button class="btn btn-primary" type="reset" id="goBack">취소</button>
							</div> 
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		const $goBack = document.getElementById("goBack");

		$goBack.onclick = function() {
			location.href = "${ pageContext.servletContext.contextPath }/mng/welfare/list/select"
		}
	</script>
</body>

</html>