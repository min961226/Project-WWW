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
						<h4 class="page-title">기숙사 입주 현황</h4>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="card-box">
						<div class="card-block">
							<form name="insertDomitory"
								action="${ pageContext.servletContext.contextPath }/welfare/domitory/select"
								method="POST">
								<table class="display datatable table table-stripped">
									<thead>
										<tr bgcolor="FFBC35">
											<th>번호</th>
											<th>종류</th>
											<th>동</th>
											<th>호</th>
											<th>현재 이용중인 인원</th>
											<th>최대 인원</th>
										</tr>
									</thead>
									<c:forEach var="domitory"
										items="${ requestScope.domitoryList }">
										<tr>
											<td><c:out value="${ domitory.domitoryManageNo }" /></td>
											<td>기숙사</td>
											<td><c:out value="${ domitory.domitoryBlockNo }" /> 동</td>
											<td><c:out value="${ domitory.roomNo }" /> 호</td>
											<td><c:out value="${ domitory.currCapacity }" /> 명</td>
											<td><c:out value="${ domitory.maxCapacity }" /> 명</td>
										</tr>
									</c:forEach>
								</table>
								<div align="right" style="margin-right: 20px; padding: 5px">
									<button class="btn btn-primary pull-right">
										<i class="fa fa-plus"></i> 기숙사 신청
									</button>
								</div>
								<jsp:include page="../common/navbar.jsp" />
								<h5>
									***기숙사 신청 시 참고 사항 : <br>
									1. 대기 순번에 따라서 신청이 반려되거나 미뤄질 수 있습니다.<br>
									2. 입주자의 성별, 현황에 따라서 배정받는 기숙사의 동이나 호수가 다를 수 있습니다.<br>
									3. 기숙사 입주 선정의 우선순위는 본인 현주소로 부터 거리 순이며 개인 사정에 따라 변동될 수 있습니다.<br>
									4. 지금 주소를 조작하거나 확인 후 기존의 주소와 다를경우 기숙사 신청 명단에서 제외될 수 있습니다.
								</h5>
							</form>
						</div>
					</div>
				</div>
				<div class="m-t-20 text-center">
					<button type="reset" class="btn btn-primary btn-lg" id="goBack">돌아가기</button>
				</div>
			</div>
		</div>

	</div>
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
			}

		}
		const $goBack = document.getElementById("goBack");

		$goBack.onclick = function() {
			location.href = "${ pageContext.servletContext.contextPath }/welfare/list/select"
		}

		/* 제이쿼리 이용하는 경우 */
		/* $(function() {
			$("#listArea td").hover(function() {
				$(this).parent().css({"background":"orangered", "cursor":"pointer"});
			}, function() {
				$(this).parent().css({"background":"black"});
			}).click(function() {
				const no = $(this).parent().children(":eq(0)").text();
				location.href = "${ pageContext.servletContext.contextPath }/notice/detail?no=" + no;
			});
		}); */
	</script>
</body>

</html>