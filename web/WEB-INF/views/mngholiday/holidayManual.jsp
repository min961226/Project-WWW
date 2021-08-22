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


		<div class="page-wrapper">
			<div class="content container-fluid">
				<div class="row">
					<div class="col-xs-12">
						<h4 class="page-title">직원 별 휴가정보 </h4>
					</div>
				</div>
			</div>

			<!-- 검색 시작 -->
			<div class="search-area" align="right">
				<form id="loginForm"
					action="${ pageContext.servletContext.contextPath }/mng/holiday/manual/select"
					method="get" style="display: inline-block">
					<input type="hidden" name="currentPage" value="1"> <select
						id="searchCondition" name="searchCondition">
						<option value="name"
							${ requestScope.selectCriteria.searchCondition eq "name"? "selected": "" }>사원명</option>
					</select> <input type="search" id="searchValue" name="searchValue"
						value="<c:out value="${ requestScope.selectCriteria.searchValue }"/>">
					<button class="btn-success" type="submit">검색하기</button>
					<!-- <button type="button" id="writeFree">작성하기</button> -->
				</form>
			</div>

			<div class="row">
				<div class="col-lg-12">
					<div class="card-box">
						<div class="card-block">
							<p class="content-group"></p>
							<table class="display datatable table table-stripped">

								<thead>
									<tr bgcolor="FFBC35">
										<th>사원번호</th>
										<th>사원명</th>
										<th>입사일</th>
										<th>부여일수</th>
										<th>자동발생일</th>
										<th>수동발생일</th>
										<th>사용일수</th>
										<th>잔여휴가일</th>
										<th>상세보기</th>
									</tr>

								</thead>
								<c:forEach var="board" items="${ MemberHolidayInfoIList }">
									<tr>
										<td><c:out value="${ board.memberNo }" /></td>
										<td><c:out value="${ board.name}" /></td>
										<td><c:out value="${ board.enrollDate }" /></td>
										<td><c:out value="${ board.autoDayNumber + board.passivedayNumber }" /></td>
										<td><c:out value="${ board.autoDayNumber }" /></td>
										<td><c:out value="${ board.passivedayNumber }" /></td>
										<td><c:out value="${ board.autoDayNumber + board.passivedayNumber - board.remainingHoliday }" /></td>
										<td><c:out value="${ board.remainingHoliday }" /></td>
										<td><button  class="btn btn-success btn-xs"
												type="submit">상세보기</button></td>
									</tr>
								</c:forEach>

							</table>
							<%-- 페이지 처리 --%>
							<jsp:include page="../common/navbar.jsp" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<script>

		if(document.getElementsByTagName("td")) {
			const $tds = document.getElementsByTagName("td");
			for(let i = 0; i < $tds.length; i++) {
				
				$tds[i].onmouseenter = function() {
					this.parentNode.style.backgroundColor = "LightGoldenRodYellow";
					this.parentNode.style.cursor = "pointer";
				}
				
				$tds[i].onmouseout = function() {
					this.parentNode.style.backgroundColor = "white";
				}
				
				$tds[i].onclick = function() {
					const no = this.parentNode.children[0].innerText;
					location.href = "${ pageContext.servletContext.contextPath }/mng/holiday/manual/selectOne?no=" + no;
				}
				
			}
			
		}
		
		
	</script>
</body>

</html>