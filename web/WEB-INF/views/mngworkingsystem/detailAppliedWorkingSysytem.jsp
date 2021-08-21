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
		<jsp:include page="../common/navbar.jsp" />
		<div class="page-wrapper">
			<div class="content container-fluid">
				<div class="row">
					<div class="col-xs-6">
						<h4 class="page-title">근무신청목록 상세보기</h4>
					</div>
					<div class="col-xs-6">
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
					<div class="col-xs-12">
						<div class="card-box col-xs-6" style="height: 1000px;">
							<div class="form-group">
								<div class="col-sm-12">
									<br>
									<h1 align="center">WWW 근무신청서</h1>
								</div>

								<br> <br> <br>

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
											value=<c:if test="${ requestScope.selectedReport.documentNo eq 4 }">"근무신청서"</c:if>
											<c:if test="${ requestScope.selectedReport.documentNo eq 5 }">"초과근무신청서"</c:if>
											disabled/>
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
								<div class="col-xs-6">
									<label>기안일자 : </label>
									<div class="col-md-12">
										<input class="form-control"
											value="~${ requestScope.selectedReport.reportDate }" disabled />
									</div>
								</div>
								<div class="col-xs-6">
									<label>보존기간 : </label>
									<div class="col-xs-12">
										<input class="form-control" value="~${ requestScope.endDate }"
											disabled /> <br> <br> <br>
									</div>
								</div>
							</div>
							
						
						<c:if test="${requestScope.selectedReport.documentNo == 4 and requestScope.itemList.get(5).itemContent eq '커스텀'}">
							<div class="form-group col-xs-12">
								<div>
									<div class="col-sm-6">
										<label>${ requestScope.itemList.get(0).itemName } : </label> <input
											class="form-control"
											value="${ requestScope.itemList.get(0).itemContent }"
											disabled />
									</div>
									<div class="col-sm-6">
										<label>${ requestScope.itemList.get(1).itemName } : </label> <input
											class="form-control"
											value="${ requestScope.itemList.get(1).itemContent }"
											disabled />
									</div>
								</div>
								<div>
									<div class="col-sm-6">
										<label>${ requestScope.itemList.get(2).itemName } : </label> <input
											class="form-control"
											value="${ requestScope.itemList.get(2).itemContent }"
											disabled />
									</div>
									<div class="col-sm-6">
										<label>${ requestScope.itemList.get(3).itemName } : </label> <input
											class="form-control"
											value="${ requestScope.itemList.get(3).itemContent }"
											disabled />
									</div>
								</div>
								<div>
									<div class="col-sm-6">
										<label>${ requestScope.itemList.get(4).itemName } : </label> <input
											class="form-control"
											value="${ requestScope.itemList.get(4).itemContent }"
											disabled />
									</div>
									<div class="col-sm-6">
										<label>${ requestScope.itemList.get(5).itemName } : </label> <input
											class="form-control"
											value="${ requestScope.itemList.get(5).itemContent }"
											disabled /> <br>
									</div>
								</div>
								<div>
									<div class="col-sm-6">
										<label>${ requestScope.itemList.get(6).itemName } : </label> <input
											class="form-control"
											value="${ requestScope.itemList.get(6).itemContent } ~ ${ requestScope.itemList.get(7).itemContent }"
											disabled />
									</div>

									<div class="col-sm-6">
										<label>${ requestScope.itemList.get(8).itemName } : </label> <input
											class="form-control"
											value="${ requestScope.itemList.get(8).itemContent } ~ ${ requestScope.itemList.get(9).itemContent }"
											disabled />
									</div>
								</div>
								<div>
									<div class="col-sm-6">
										<label>${ requestScope.itemList.get(10).itemName } : </label>
										<input class="form-control"
											value="${ requestScope.itemList.get(10).itemContent } ~ ${ requestScope.itemList.get(11).itemContent }"
											disabled />
									</div>
									<div class="col-sm-6">
										<label>${ requestScope.itemList.get(12).itemName } : </label>
										<input class="form-control"
											value="${ requestScope.itemList.get(12).itemContent } ~ ${ requestScope.itemList.get(13).itemContent }"
											disabled />
									</div>
								</div>
								<div>
									<div class="col-sm-6">
										<label>${ requestScope.itemList.get(14).itemName } : </label>
										<input class="form-control"
											value="${ requestScope.itemList.get(14).itemContent } ~ ${ requestScope.itemList.get(14).itemContent }"
											disabled />
									</div>
								</div>
							</div>
						</c:if>

						<c:if test="${requestScope.selectedReport.documentNo == 4 and requestScope.itemList.get(5).itemContent ne '커스텀'}">
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
									<br> <br> <br> <label>사유 : </label>
									<div class="col-lg-12">
										<textarea name="note" rows="5" cols="5" class="form-control"
											disabled>${ requestScope.selectedReport.reportNote }</textarea>
									</div>
								</div>
							</div>
						</div>




						<div class="card-box col-xs-6">
							<div class="col-xs-12">
								<h4 align="center">결재내역 목록</h4>
							</div>
						</div>
						<c:forEach var="line" items="${ requestScope.ALPRList }">
							<div class="card-box col-xs-3">
								<h4 class="card-title">${ line.memberName }님의결재내용조회</h4>
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
						<form class="form-horizontal"
							action="${ pageContext.servletContext.contextPath }/mng/workingSystem/applied/delete?no=${ requestScope.selectedReport.reportNo }"
							method="post" onsubmit="return askAgain();">
							<div class="row">
								<!-- DeleteMngAppliedWorkingSystemServlet 서블릿에서 표준/커스텀/초과근무 등을 판단하기 위해 넘겨줄 값 -->
								<div hidden=hidden>
									<input name="status" value="${ requestScope.selectedReport.reportStatus }" /> 
									<input name="memberNo" value="${ requestScope.selectedReport.memberNo }" />
									<input name="workType" value="${ requestScope.itemList.get(5).itemContent }">
									<input name="documentNo" value="${ requestScope.selectedReport.documentNo }">
								</div>
								<div class="col-sm-12 text-center m-t-20">
									<c:if test="${ requestScope.selectedReport.reportStatus ne '취소' || requestScope.selectedReport.reportStatus ne '회수'}">
										<button type="submit" class="btn btn-primary btn-lg">
											취소하기</button>
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
			location.href = "${ pageContext.servletContext.contextPath }/mng/workingSystem/applied/select"
		}

		function askAgain() {

			var yn;
			yn = confirm('해당 근무를 취소하시겠습니까?');

			if (yn == true) {
				return true;
			} else if (yn == false) {
				return false;
			}
		}
	</script>
</body>

</html>