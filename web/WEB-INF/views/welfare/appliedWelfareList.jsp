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

		<div class="page-wrapper">
			<div class="content container-fluid">

				<div class="row">
					<div class="col-xs-8">
						<h4 class="page-title">복지 신청 내역</h4>
					</div>

					<!-- 복지신청으로 이동하는 버튼 -->
					<div class="col-xs-4 text-right m-b-30">
						<a
							href="${ pageContext.servletContext.contextPath }/welfare/list/select"
							class="btn btn-primary rounded pull-right"><i
							class="fa fa-plus"></i> 복지신청하기</a>
					</div>
				</div>

				<!-- 검색조건 -->
				<!-- 검색 시작 -->
                <div class="search-area" align="right">
                   <form id="loginForm" action="${ pageContext.servletContext.contextPath }/welfare/applied/list/select" method="get" style="display:inline-block">      
                   <input type="hidden" name="currentPage" value="1">
                  <select id="searchCondition" name="searchCondition">
                      <option value="title" ${ requestScope.selectCriteria.searchCondition eq "title"? "selected": "" }>제목</option>
                     <option value="status" ${ requestScope.selectCriteria.searchCondition eq "status"? "selected": "" }>결재 상태</option>
                  </select>
                  <input type="search" id="searchValue" name="searchValue" value="<c:out value="${ requestScope.selectCriteria.searchValue }"/>">
               <button type="submit" class="btn-primary btn-sm" >검색하기</button>
               <!-- <button type="button" id="writeFree">작성하기</button> -->
               </form>
            </div>
				<!-- 검색조건 end -->
				<!-- 근무신청내용 -->
				<div class="row">
					<div class="col-lg-12">
						<div class="card-box">
							<div class="card-block">
								<div class="table-responsive">
									<table class="table table-striped custom-table m-b-0 datatable">

										<thead>
											<tr style="background-color: #ffbc34">
												<th rowspan="1"><h4>
														<b>복지 신청 내역</b>
													</h4></th>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
											</tr>
											<tr>
												<!-- 시작일과 종료일도 뜨게 해주고 싶네 -->
												<th style="width: 150px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;결재
													번호</th>
												<th>복지 분류</th>
												<th>신청 복지 제목</th>
												<th>결재라인</th>
												<th>상신일</th>
												<th>진행상태</th>
												<th>신청사유</th>
											</tr>
										</thead>
										<c:forEach var="welfareList"
											items="${ requestScope.appliedWelfareList }">

											<!-- 승인인지 여부만 확인. 승인 이외에는 빨간색으로 -->
											<!-- 
                                	<c:set var="isApproved" value="${ fn:contains(report.reportStatus, \"승인\") }"/>
                                	-->
											<tr>
												<td align="center"><c:out
														value="${ welfareList.reportNo }" /></td>
												<td><c:if test="${ welfareList.documentNo eq 7 }">
														<c:out value="야간교통비 신청" />
													</c:if> <c:if test="${ welfareList.documentNo eq 8 }">
														<c:out value="경조사 신청" />
													</c:if> <c:if test="${ welfareList.documentNo eq 9 }">
														<c:out value="자기개발비 신청" />
													</c:if> <c:if test="${ welfareList.documentNo eq 10 }">
														<c:out value="기숙사 입주 신청" />
													</c:if> <c:if test="${ welfareList.documentNo eq 11 }">
														<c:out value="세미나실 예약 신청" />
													</c:if> <c:if test="${ welfareList.documentNo eq 12 }">
														<c:out value="노트북 대여 신청" />
													</c:if></td>
												<td><c:out value="${ welfareList.reportTitle }" /></td>
												<td><c:out value="${ welfareList.lineName }" /></td>
												<td><c:out value="${ welfareList.reportDate }" /></td>
												<td><c:choose>
														<c:when test="${ welfareList.reportStatus eq '승인' }">
															<i class="fa fa-dot-circle-o text-success"></i>
															<c:out value=" ${ welfareList.reportStatus }" />
														</c:when>
														<c:otherwise>
															<i class="fa fa-dot-circle-o text-danger"></i>
															<c:out value=" ${ welfareList.reportStatus }" />
														</c:otherwise>
													</c:choose></td>
												<td><c:out value="${ welfareList.reportNote }" /></td>
											</tr>
										</c:forEach>

									</table>
									<!-- 근무신청내용 end -->
									<br>
									<!-- 페이징 부분 -->
											<jsp:include page="../common/navbar.jsp" />
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- main-wrapper end -->
	<script>
		if (document.getElementsByTagName("td")) {
			const $tds = document.getElementsByTagName("td");
			for (let i = 0; i < $tds.length; i++) {

				$tds[i].onmouseenter = function() {
					this.parentNode.style.backgroundColor = "LightGoldenRodYellow";
					this.parentNode.style.cursor = "pointer";
				}

				$tds[i].onmouseout = function() {
					this.parentNode.style.backgroundColor = "white";
				}
				$tds[i].onclick = function() {
					const no = this.parentNode.children[0].innerText;
					location.href = "${ pageContext.servletContext.contextPath }/welfare/applied/selectOne?no="
							+ no;

				}

			}

		}
	</script>
</body>

</html>