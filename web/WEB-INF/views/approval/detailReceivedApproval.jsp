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
					<div class="col-xs-6">
						<h4 class="page-title">결재대기함 상세보기</h4>
					</div>
					<div class="col-xs-3">
						<label>첨부서류</label>
						<div class="col-md-12">
							<c:if test="${ !empty requestScope.attachmentDTO.savedName}">
								<div class="form-group">
									<label>다운로드 :</label>&nbsp;&nbsp;<a
										href="${ pageContext.servletContext.contextPath }/FileDown?fileName=${requestScope.attachmentDTO.savedName }"><u>${requestScope.attachmentDTO.originalName}</u></a>
								</div>
							</c:if>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="card-box col-lg-6" style="height: 1000px;">
							<div class="form-group">
								<div class="col-sm-12">
									<h1 align="center">
										WWW
										<c:if test="${requestScope.selectedReport.documentNo eq 1}">
											<c:out value="일반기안문" />
										</c:if>
										<c:if test="${requestScope.selectedReport.documentNo eq 2}">
											<c:out value="일반품의서" />
										</c:if>
										<c:if test="${requestScope.selectedReport.documentNo eq 3}">
											<c:out value="일반결의서" />
										</c:if>
										<c:if test="${requestScope.selectedReport.documentNo eq 4}">
											<c:out value="근무신청서" />
										</c:if>
										<c:if test="${requestScope.selectedReport.documentNo eq 5}">
											<c:out value="초과근무신청서" />
										</c:if>
										<c:if test="${requestScope.selectedReport.documentNo eq 6}">
											<c:out value="휴가신청서" />
										</c:if>
										<c:if test="${requestScope.selectedReport.documentNo eq 7}">
											<c:out value="야간교통비신청서" />
										</c:if>
										<c:if test="${requestScope.selectedReport.documentNo eq 8}">
											<c:out value="경조사신청서" />
										</c:if>
										<c:if test="${requestScope.selectedReport.documentNo eq 9}">
											<c:out value="자기개발비신청서" />
										</c:if>
										<c:if test="${requestScope.selectedReport.documentNo eq 10}">
											<c:out value="기숙사입주신청서" />
										</c:if>
										<c:if test="${requestScope.selectedReport.documentNo eq 11}">
											<c:out value="회의실예약신청서" />
										</c:if>
										<c:if test="${requestScope.selectedReport.documentNo eq 12}">
											<c:out value="노트북대여신청서" />
										</c:if>
										<c:if test="${requestScope.selectedReport.documentNo eq 13}">
											<c:out value="야간교통비지출결의서" />
										</c:if>
										<c:if test="${requestScope.selectedReport.documentNo eq 14}">
											<c:out value="경조사지출결의서" />
										</c:if>
										<c:if test="${requestScope.selectedReport.documentNo eq 15}">
											<c:out value="자기개발비지출결의서" />
										</c:if>
									</h1>
								</div>

								<br>
								<br>
								<br>

							</div>
							<div class="form-group">
								<div class="col-sm-6">
									<label>문서번호 : </label>
									<div class="col-md-12">
										<input class="form-control"
											value="${ requestScope.selectedReport.reportNo }" disabled />
									</div>
								</div>
								<div class="col-sm-6">
									<label>결재 분류 : </label>
									<div class="col-md-12">
										<input class="form-control"
											value=<c:if test="${requestScope.selectedReport.documentNo eq 1}"> <c:out  value="일반기안문"/></c:if>
											<c:if test="${requestScope.selectedReport.documentNo eq 2}"> <c:out  value="일반품의서"/></c:if>
											<c:if test="${requestScope.selectedReport.documentNo eq 3}"> <c:out  value="일반결의서"/></c:if>
											<c:if test="${requestScope.selectedReport.documentNo eq 4}"> <c:out  value="근무신청서"/></c:if>
											<c:if test="${requestScope.selectedReport.documentNo eq 5}"> <c:out  value="초과근무신청서"/></c:if>
											<c:if test="${requestScope.selectedReport.documentNo eq 6}"> <c:out  value="휴가신청서"/></c:if>
											<c:if test="${requestScope.selectedReport.documentNo eq 7}"> <c:out  value="야간교통비신청서"/></c:if>
											<c:if test="${requestScope.selectedReport.documentNo eq 8}"> <c:out  value="경조사신청서"/></c:if>
											<c:if test="${requestScope.selectedReport.documentNo eq 9}"> <c:out  value="자기개발비신청서"/></c:if>
											<c:if test="${requestScope.selectedReport.documentNo eq 10}"> <c:out  value="기숙사입주신청서"/></c:if>
											<c:if test="${requestScope.selectedReport.documentNo eq 11}"> <c:out  value="회의실예약신청서"/></c:if>
											<c:if test="${requestScope.selectedReport.documentNo eq 12}"> <c:out  value="노트북대여신청서"/></c:if>
											<c:if test="${requestScope.selectedReport.documentNo eq 13}"> <c:out  value="야간교통비지출결의서"/></c:if>
											<c:if test="${requestScope.selectedReport.documentNo eq 14}"> <c:out  value="경조사지출결의서"/></c:if>
											<c:if test="${requestScope.selectedReport.documentNo eq 15}"> <c:out  value="자기개발비지출결의서"/></c:if>
											disabled />
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-6">
									<label>기안자 : </label>
									<div class="col-md-12">
										<input class="form-control"
											value="${ requestScope.selectedReport.memberName }" disabled />
									</div>
								</div>

								<div class="col-sm-6">
									<label>결재라인 : </label>
									<div class="col-md-12">
										<input class="form-control"
											value="${ requestScope.selectedReport.lineName }" disabled />
									</div>
								</div>
							</div>

							<div class="form-group">
								<div class="col-sm-6">
									<label>기안일자 : </label>
									<div class="col-md-12">
										<input class="form-control"
											value="~${ requestScope.selectedReport.reportDate }" disabled />
									</div>
								</div>
								<div class="col-sm-6">
									<label>보존기간 : </label>
									<div class="col-md-12">
										<input class="form-control" value="~${ requestScope.endDate }"
											disabled /> <br>
										<br>
										<br>
									</div>
								</div>
							</div>
							<c:if test="${requestScope.selectedReport.documentNo < 4}">
								<div class="col-sm-12">
									<label>결재 제목 : </label>
									<div class="col-md-12">
										<input class="form-control"
											value="${ requestScope.selectedReport.reportTitle }" disabled />
										<br>
										<br>
									</div>
								</div>
								<c:set var="no"
									value="${ requestScope.selectedReport.documentNo }" />
								<c:if test="${  no eq 3 }">
									<div id="area3" class="form-group">

										<div class="col-sm-3">
											<label>계약일 : </label>
											<div class="col-md-12">
												<input class="form-control"
													value="${ requestScope.contractDate }" disabled />
											</div>

										</div>

										<div class="col-sm-3">
											<label>지출예정일 : </label>
											<div class="col-md-12">
												<input class="form-control"
													value="${ requestScope.payDate }" disabled />
											</div>
										</div>



										<div class="col-sm-6">
											<label>물품번호 : </label>
											<div class="col-md-12">
												<input class="form-control"
													value="${ requestScope.productNo }" disabled />
											</div>
										</div>
									</div>
								</c:if>

								<div class="form-group">
									<div class="col-sm-12">
										<label>내용 : </label>
										<div class="col-lg-12">
											<textarea name="body" rows="10" cols="5" class="form-control"
												disabled>${ requestScope.body }</textarea>
										</div>
									</div>
								</div>
							</c:if>
							<c:if test="${requestScope.selectedReport.documentNo >= 4}">
								<div class="form-group col-xs-12">
									<c:forEach var="item" items="${ requestScope.itemList }">
										<div>
											<div class="col-sm-6">
												<label>${ item.itemName } : </label> <input
													class="form-control" value="${ item.itemContent }" disabled />
											</div>
										</div>
									</c:forEach>
								</div>
							</c:if>
							<div class="form-group">
								<div class="col-sm-12">
									<c:if test="${requestScope.selectedReport.documentNo >= 4}">
										<br>
										<br>
										<br>
									</c:if>
									<label>비고 : </label>
									<div class="col-lg-12">
										<textarea name="note" rows="5" cols="5" class="form-control"
											disabled>${ requestScope.selectedReport.reportNote }</textarea>
									</div>
								</div>
							</div>
						</div>
						<div class="card-box col-lg-6">
							<div class="col-xs-12">
								<h4 align="center">결재내역 목록</h4>
							</div>
						</div>
						<c:forEach var="line" items="${ requestScope.ALPRList }">
							<div class="card-box col-lg-3">
								<h4 class="card-title">${ line.memberName }님의결재내용 조회</h4>
								<div class="form-group">

									<div class="col-xs-6">
										<label>결재날짜</label> <input name="appStatus"
											value=${ line.appDate } disabled />
									</div>
									<div class="col-xs-6">
										<label>결재처리</label> <input name="appStatus"
											value=${ line.appStatus } disabled />
									</div>
									<div class="col-md-12">
										<label>의견</label> <br>
										<div class="col-sm-12">
											<textarea name="opinion" rows="4" cols="6"
												class="form-control" disabled>${ line.appNote }</textarea>

										</div>
									</div>
								</div>
							</div>

						</c:forEach>
						<div class="row">
							<div class="col-sm-12 text-center m-t-20">
								<button type="reset" class="btn btn-primary btn-lg" id="goBack">돌아가기</button>
							</div>
						</div>





					</div>
				</div>
			</div>

		</div>

	</div>
	<div class="sidebar-overlay" data-reff=""></div>

	<script>
    	
    	const $goBack = document.getElementById("goBack");
    	$goBack.onclick = function() {
    		location.href = "${ pageContext.servletContext.contextPath }/approval/received/select"
    	}
    </script>
</body>

</html>