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
					<div class="col-xs-8">
						<h4 class="page-title">관리 물품 삭제</h4>
					</div>

					<!-- 복지신청으로 이동하는 버튼 -->
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="card-box">
						<div class="card-block">
							<form name="insertLaptopRental"
								action="${ pageContext.servletContext.contextPath }/mng/welfare/laptopRental/deleteComplete"
								method="post" onsubmit="return askAgain();">
								<table class="display datatable table table-stripped">
									<thead>
										<tr bgcolor="FFBC35">
											<th>번호</th>
											<th>종류</th>
											<th>품명</th>
											<th>대여 상태</th>
											<th>삭제할 품목</th>
										</tr>

									</thead>
									<c:forEach var="laptopList"
										items="${ requestScope.laptopList }">
										<tr>
											<td><c:out value="${ laptopList.itemNo }" /></td>
											<td><c:out value="${ laptopList.itemCateGory }" /></td>
											<td><c:out value="${ laptopList.itemName }" /></td>
											<td><c:out value="${ laptopList.reservationStatus }" /></td>
										<td><input type="checkbox" name="deleteItemCheck"
											value="${ laptopList.itemNo }"></td>
										</tr>
									</c:forEach>
								</table>
								<div align="right" style="margin-right: 20px; padding: 5px">
									<button class="btn btn-danger pull-right" type="submit">삭제
										완료</button>
								</div>
							</form>
							<%-- 페이징 처리 부분, 네비바 --%>
							<jsp:include page="../common/navbar.jsp" />
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
			location.href = "${ pageContext.servletContext.contextPath }/mng/welfare/laptopRental/select"
		}

		function askAgain() {

			var yn;
			yn = confirm('물품을 정말로 삭제하시겠습니까?');

			if (yn == true) {
				return true;
			} else if (yn == false) {
				return false;
			}
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