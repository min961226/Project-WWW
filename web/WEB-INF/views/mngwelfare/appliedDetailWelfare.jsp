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
						<h4 class="page-title">복지 신청 내역 상세 조회</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<form class="form-horizontal" action="${ pageContext.servletContext.contextPath }/mng/welfare/applied/list/delete?no=${ requestScope.selectedReport.reportNo }"	method="post" onsubmit="return askAgain();">
							<div class="form-group">
								<div class="col-sm-6">
									<label>복지 분류</label>
									<div class="col-md-12">
										<input class="form-control"
											value=<c:if test="${ requestScope.selectedReport.documentNo eq 7 }">"야간교통비 신청"</c:if>
											<c:if test="${ requestScope.selectedReport.documentNo eq 8 }">"경조사 신청"</c:if>
											<c:if test="${ requestScope.selectedReport.documentNo eq 9 }">"자기개발비 신청"</c:if>
											<c:if test="${ requestScope.selectedReport.documentNo eq 10 }">"기숙사 입주 신청"</c:if>
											<c:if test="${ requestScope.selectedReport.documentNo eq 12 }">"노트북 대여 신청"</c:if>
											disabled />
									</div>
								</div>
								<div class="col-sm-6">
									<label>결재 상태</label>
									<div class="col-md-12">
										<input class="form-control"
											value="${ requestScope.selectedReport.reportStatus }"
											disabled />
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-6">
									<label>상신자</label>
									<div class="col-md-12">
										<input class="form-control"
											value="${ requestScope.selectedReport.memberName }" disabled />
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
									<label>결재 신청 제목</label>
									<div class="col-md-12">
										<input class="form-control"
											value="${ requestScope.selectedReport.reportTitle }" disabled />
									</div>
								</div>
								<div class="col-sm-6">
									<label>보존기간</label>
									<div class="col-md-12">
										<input class="form-control"
											value="~${ requestScope.preservedDate }" disabled />
									</div>
								</div>
							</div>
							<c:set var="no"	value="${ requestScope.selectedReport.documentNo }" />
							<c:if test="${ no eq 7 }">
								<div class="form-group">
									<!-- 유형코드도 c:if로 해서 한글로 표시되도록 하고싶다 -->
									<div class="col-sm-6">
										<label>초과 근무 업무 시간</label>
										<div class="col-md-12">
											<input class="form-control"
												value="${ requestScope.overTimeWorkTime }" disabled />
										</div>
									</div>
									<div class="col-sm-6">
										<label>청구 교통비</label>
										<div class="col-md-12">
											<input class="form-control"
												value="${ requestScope.overTimeTransBill }원" disabled />
										</div>
									</div>
								</div>
							</c:if>

							<c:if test="${ no eq 8 }">
								<div class="form-group">

									<!-- 유형코드도 c:if로 해서 한글로 표시되도록 하고싶다 -->
									<div class="col-sm-6">
										<label>경조사 분류</label>
										<div class="col-md-12">
											<input class="form-control"
												value="${ requestScope.eventCodeName }" disabled />
										</div>
									</div>

									<div class="col-sm-6">
										<label>경조사명</label>
										<div class="col-md-12">
											<input class="form-control"
												value="${ requestScope.eventName }" disabled />
										</div>
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-6">
										<label>일자</label>
										<div class="col-md-12">
											<input class="form-control"
												value="${ requestScope.eventDate }" disabled />
										</div>
									</div>

									<div class="col-sm-6">
										<label>지급 계획 경조금</label>
										<div class="col-md-12">
											<input class="form-control"
												value="${ requestScope.eventBill }만원" disabled />
										</div>
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-6">
										<label>장소</label>
										<div class="col-lg-12">
											<input class="form-control"
												value="${ requestScope.eventPlace }" disabled />
										</div>
									</div>
								</div>
								<!-- 흠 이건 id가 달린 div인데, 뭐가 다른지 궁금하네 -->
								<div id="area3" class="form-group"></div>
							</c:if>
							<c:if test="${ no eq 9 }">
								<%--자기개발비 신청 내역 --%>
								<div class="form-group">
									<!-- 유형코드도 c:if로 해서 한글로 표시되도록 하고싶다 -->
									<div class="col-sm-6">
										<label>자기개발비 분류</label>
										<div class="col-md-12">
											<input class="form-control"
												value="${ requestScope.selfDevCodeName }" disabled />
										</div>
									</div>
									<div class="col-sm-6">
										<label>자기개발비 사용일자</label>
										<div class="col-md-12">
											<input class="form-control"
												value="${ requestScope.selfDevUseDate }" disabled />
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-6">
										<label>청구 금액</label>
										<div class="col-md-12">
											<input class="form-control"
												value="${ requestScope.selfDevBill }원" disabled />
										</div>
									</div>
								</div>
							</c:if>

							<c:if test="${ no eq 10 }">
								<%--기숙사 신청 내역 --%>
								<div class="form-group">

									<div class="col-sm-6">
										<label>현 거주지</label>
										<div class="col-md-12">
											<input class="form-control"
												value="${ requestScope.domitoryAddress }" disabled />
										</div>
									</div>

									<div class="col-sm-6">
										<label>입주 희망일</label>
										<div class="col-md-12">
											<input class="form-control"
												value="${ requestScope.domitoryDate }" disabled />
										</div>
									</div>
								</div>
							</c:if>

							<c:if test="${ no eq 12 }">
								<%--노트북 대여 신청 내역 --%>
								<div class="form-group">
									<div class="col-sm-6">
										<label>대여 품목 번호</label>
										<div class="col-md-12">
											<input class="form-control" value="${ requestScope.itemNo }" disabled />
										</div>
									</div>
									<div class="col-sm-6">
										<label>반납일</label>
										<div class="col-md-12">
											<input class="form-control" value="${ requestScope.returnDate }" disabled />
										</div>
									</div>
								</div>
							</c:if>
							<div class="form-group">
								<div class="col-sm-12">
									<label>신청사유</label>
									<div class="col-lg-12">
										<textarea name="note" rows="5" cols="5" class="form-control" disabled>${ requestScope.reason }</textarea>
									</div>
								</div>
							</div>
							<%-- 첨부파일 다운로드 추가 --%>
							<c:if test="${ !empty requestScope.attachmentDTO.savedName}">
								<div class="form-group">
									<div class="col-sm-6">
										<div class="col-md-12">
											<label>첨부 파일 :</label>&nbsp;&nbsp;<a href="${ pageContext.servletContext.contextPath }/FileDown?fileName=${requestScope.attachmentDTO.savedName }"><u>${requestScope.attachmentDTO.originalName}</u></a>
										</div>
									</div>
								</div>
							</c:if>
							<div class="row">
								<div class="col-sm-12 text-center m-t-20">
									<c:set var="reportStatus"
										value="${ requestScope.selectedReport.reportStatus }" />
									<c:if test="${  reportStatus eq '대기' }">
										<button type="submit" class="btn btn-primary btn-lg"
											id="revoke">회수하기</button>
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
			location.href = "${ pageContext.servletContext.contextPath }/mng/welfare/applied/select"
		}

		function askAgain() {
			
			var yn;
			yn = confirm('신청된 결재를 회수하시겠습니까?');
			
			if (yn == true) {
				return true;
			} else {
				return false;
			}
		}
	</script>
</body>

</html>