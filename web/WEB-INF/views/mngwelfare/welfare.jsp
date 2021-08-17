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
			<div class="content container-fluid" >
				<div class="row">
					<div class="col-sm-8">
						<h4 class="page-title">시행 복지 목록 관리</h4>
					</div>
				</div>
				<div class="card-box" style="width: 700px; background-color:#FEEB99; border: 1px solid black;">
					<div class="card-block">
						<div class="row">
							<form name="insertSelfDev"
								action="${ pageContext.servletContext.contextPath }/mng/welfare/list/update" method="POST">
								<div class="col-sm-12">
									<h6 class="panel-title m-b-20"style="font-size: 20px">
										<b>시행 복지 목록</b>
									</h6>
									<div class="panel">
											<ul class="list-group" style="border: 1px solid black;">
												<li class="list-group-item">야간교통비 신청 
										<c:forEach var="welfareYn" items="${ requestScope.welfareYn }">
												<c:if test="${ welfareYn.documentNo eq 7 }">
												<input name="nightTransNo" value="${ welfareYn.documentNo }" hidden="hidden"> 
														<c:if test="${ welfareYn.useYn eq 'Y' }">
															<div class="material-switch pull-right" align="left">
																<input name="nightTransYn" id="nightTransYn" type="checkbox" checked="checked"  value="Y">
																<label for="nightTransYn" class="label-success"></label>
															</div>
														</c:if>
														<c:if test="${ welfareYn.useYn eq 'N' }">
															<div class="material-switch pull-right" align="left">
																<input name="nightTransYn" id="nightTransYn" type="checkbox">
																<label for="nightTransYn" class="label-success"></label>
															</div>
														</c:if>
													</c:if>
												</c:forEach>
												</li>
												<li class="list-group-item">경조사 신청 
												<c:forEach var="welfareYn" items="${ requestScope.welfareYn }">
														<c:if test="${ welfareYn.documentNo eq 8 }">
														<input name="eventNo" value="${ welfareYn.documentNo }" hidden="hidden"> 
														<c:if test="${ welfareYn.useYn eq 'Y' }">
															<div class="material-switch pull-right" align="left">
																<input name="eventYn" id="eventYn" type="checkbox" checked="checked" value="Y">
																<label for="eventYn" class="label-success"></label>
															</div>
														</c:if>
														<c:if test="${ welfareYn.useYn eq 'N' }">
															<div class="material-switch pull-right" align="left">
																<input name="eventYn" id="eventYn" type="checkbox"> 
																<label for="eventYn" class="label-success"></label>
															</div>
														</c:if>
													</c:if>
												</c:forEach>
												</li>
												<li class="list-group-item">자기개발비 신청 
												<c:forEach var="welfareYn" items="${ requestScope.welfareYn }">
														<c:if test="${ welfareYn.documentNo eq 9 }">
																<input name="selfDevNo" value="${ welfareYn.documentNo }" hidden="hidden"> 
														<c:if test="${ welfareYn.useYn eq 'Y' }">
															<div class="material-switch pull-right" align="left">
																<input name="selfDevYn"	id="selfDevYn" type="checkbox" checked="checked" value="Y"> 
																<label for="selfDevYn" class="label-success"></label>
															</div>
														</c:if>
														<c:if test="${ welfareYn.useYn eq 'N' }">
															<div class="material-switch pull-right" align="left">
																<input name="selfDevYn" id="selfDevYn" type="checkbox">
																<label	for="selfDevYn" class="label-success"></label>
															</div>
														</c:if>
													</c:if>
												</c:forEach>
												</li>
												<li class="list-group-item">기숙사 입주 신청 
												<c:forEach var="welfareYn" items="${ requestScope.welfareYn }">
														<c:if test="${ welfareYn.documentNo eq 10 }">
														<input name="domitoryNo" value="${ welfareYn.documentNo }" hidden="hidden"> 
														<c:if test="${ welfareYn.useYn eq 'Y' }">
															<div class="material-switch pull-right" align="left">
																<input name="domitoryYn" id="domitoryYn" type="checkbox" checked="checked" value="Y"> 
																<label for="domitoryYn" class="label-success"></label>
															</div>
														</c:if>
														<c:if test="${ welfareYn.useYn eq 'N' }">
															<div class="material-switch pull-right" align="left">
																<input name="domitoryYn" id="domitoryYn" type="checkbox">
																<label for="domitoryYn" class="label-success"></label>
															</div>
														</c:if>
													</c:if>
												</c:forEach>
												</li>
												<li class="list-group-item">회의실 대여 신청 
												<c:forEach var="welfareYn" items="${ requestScope.welfareYn }">
														<c:if test="${ welfareYn.documentNo eq 11 }">
														<input name="seminarNo" value="${ welfareYn.documentNo }" hidden="hidden"> 
														<c:if test="${ welfareYn.useYn eq 'Y' }">
															<div class="material-switch pull-right" align="left">
																	<input name="seminarYn" id="seminarYn" type="checkbox" checked="checked" value="Y"> 
																	<label for="seminarYn" class="label-success"></label>
															</div>
														</c:if>

														<c:if test="${ welfareYn.useYn eq 'N' }">
															<div class="material-switch pull-right" align="left">
																<input name="seminarYn" id="seminarYn" type="checkbox"> 
																<label for="seminarYn" class="label-success"></label>
															</div>
														</c:if>
													</c:if>
												</c:forEach>
												</li>
												<li class="list-group-item">노트북 대여신청
												<c:forEach var="welfareYn" items="${ requestScope.welfareYn }">
													 <c:if test="${ welfareYn.documentNo eq 12 }">
													 <input name="laptopNo" value="${ welfareYn.documentNo }" hidden="hidden"> 
														<c:if test="${ welfareYn.useYn eq 'Y' }">
															<div class="material-switch pull-right" align="left">
																<input name="laptopYn" id="laptopYn" type="checkbox" checked="checked" value="Y"> 
																<label for="laptopYn" class="label-success"></label>
															</div>
														</c:if>
														<c:if test="${ welfareYn.useYn eq 'N' }">
															<div class="material-switch pull-right" align="left">
																<input name="laptopYn" id="laptopYn" type="checkbox"> 
																<label for="laptopYn"	class="label-success"></label>
															</div>
														</c:if>
													</c:if>
												</c:forEach>
												</li>
											</ul>
										
									</div>
								</div>
								<div align="right" style="margin-right: 20px; padding: 5px">
									<button class="btn btn-success" style="margin-right: 10px"
										type="submit">저장</button>
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