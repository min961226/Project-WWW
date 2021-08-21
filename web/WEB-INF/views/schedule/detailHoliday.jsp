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
						<h4 class="page-title">휴가신청함 상세보기</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">

						<form class="form-horizontal"
							action="${ pageContext.servletContext.contextPath }/approval/applied/selectOne?no=${ requestScope.selectedReport.reportNo }"
							method="post">

							<div class="form-group">
								<div class="col-sm-6">
									<label>결재 분류</label>
									<div class="col-md-12">
										<input class="form-control"
											value=<c:if test="${ requestScope.selectedReport.documentNo eq 6 }">"휴가신청서"</c:if>
											disabled />
									</div>
								</div>

								<div class="col-sm-6">
									<label>결재 제목</label>
									<div class="col-md-12">
										<input class="form-control"
											value="${ requestScope.selectedReport.reportTitle }" disabled />
									</div>
								</div>
							</div>


							<div class="form-group">
								<div class="col-sm-6">
									<label>기안자</label>
									<div class="col-md-12">
										<input class="form-control"
											value="${ sessionScope.memberInfo.name }" disabled />
									</div>
								</div>

								<div class="col-sm-6">
									<label>결재라인</label>
									<div class="col-md-12">
										<input class="form-control"
											value="${ requestScope.selectedReport.lineName }" disabled />
									</div>
								</div>
							</div>

							<div class="form-group">
								
								<div class="col-sm-6">
									<label>보존기간</label>
									<div class="col-md-12">
										<input class="form-control"
											value="~${ requestScope.preservedDate }" disabled />
									</div>
								</div>
							</div>

							<c:set var="no"
								value="${ requestScope.selectedReport.documentNo }" />
							<c:if test="${  no eq 6 }">
								<div id="area3" class="form-group">

									<div class="form-group">
										<!-- 유형코드도 c:if로 해서 한글로 표시되도록 하고싶다 -->
										<div class="col-sm-6">
											<label>휴가코드</label>
											<div class="col-md-12">
												<input class="form-control"
													value="${ requestScope.holidayType }" disabled />
											</div>
										</div>

										<div class="col-sm-6">
											<label>기간일수</label>
											<div class="col-md-12">
												<input class="form-control"
													value="${ requestScope.holidayDuring }" disabled />
											</div>
										</div>
									</div>

									<div class="form-group">
										<div class="col-sm-6">
											<label>시작일</label>
											<div class="col-md-12">
												<input class="form-control"
													value="${ requestScope.startDate }" disabled />
											</div>
										</div>

										<div class="col-sm-6">
											<label>시작일 종일여부</label>
											<div class="col-md-12">
												<input class="form-control"
													value="${ requestScope.startDateAllDay }" disabled />
											</div>
										</div>
									</div>

									<div class="form-group">
										<div class="col-sm-6">
											<label>종료일</label>
											<div class="col-md-12">
												<input class="form-control"
													value="${ requestScope.endDate }" disabled />
											</div>
										</div>

										<div class="col-sm-6">
											<label>종료일 종일여부</label>
											<div class="col-md-12">
												<input class="form-control"
													value="${ requestScope.endDateAllDay }" disabled />
											</div>
										</div>
									</div>

								</div>
							</c:if>



							<div class="form-group">
								<div class="col-sm-12">
									<label>신청사유</label>
									<div class="col-lg-12">
										<textarea name="note" rows="5" cols="5" class="form-control"
											disabled>${ requestScope.reason }</textarea>
									</div>
								</div>
							</div>

							<%-- 첨부파일 다운로드 추가 --%>
							<c:if test="${ !empty requestScope.attachmentDTO.savedName}">
								<div class="form-group">
									<div class="col-sm-6">
										<div class="col-md-12">
											<label>첨부 파일 :</label>&nbsp;&nbsp;<a
												href="${ pageContext.servletContext.contextPath }/FileDown?fileName=${requestScope.attachmentDTO.savedName }"><u>${requestScope.attachmentDTO.originalName}</u></a>
										</div>
									</div>
								</div>
							</c:if>


							<div class="row">
								<div class="col-sm-12 text-center m-t-20">
									<c:set var="reportStatus"
										value="${ requestScope.selectedReport.reportStatus }" />
									<c:if test="${  reportStatus eq '대기' }">
										<button type="submit" class="btn btn-primary btn-lg">
											회수하기</button>
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
    		location.href = "${ pageContext.servletContext.contextPath }/schedule/holiday/select"
    	}
    </script>
</body>

</html>