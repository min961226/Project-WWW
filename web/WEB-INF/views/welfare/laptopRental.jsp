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
						<h4 class="page-title">복지 물품 대여 현황</h4>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="card-box">
						<div class="card-block">
							<p class="content-group"></p>
							<table class="display datatable table table-stripped">
								<thead>
									<tr bgcolor="FFBC35">
										<th>번호</th>
										<th>종류</th>
										<th>품명</th>
										<th>대여 상태</th>
										<th>신청하기</th>
									</tr>
								</thead>
								<c:forEach var="laptopList" items="${ requestScope.laptopList }">
									<tr>
										<td><c:out value="${ laptopList.itemNo }" /></td>
										<td><c:out value="${ laptopList.itemCateGory }" /></td>
										<td><c:out value="${ laptopList.itemName }" /></td>
										<td><c:out value="${ laptopList.reservationStatus }" /></td>
										<td><button class="btn btn-primary btn-xs" type="submit">
												신청하기</button></td>
									</tr>
								</c:forEach>
							</table>
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

				$tds[i].onclick = function() {
					/* 게시물 번호까지 알아왔으니 이제 상세보기는 할 수 있겠지? */
					const no = this.parentNode.children[0].innerText;
					location.href = "${ pageContext.servletContext.contextPath }/welfare/LaptopRental/select?no="
							+ no;
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