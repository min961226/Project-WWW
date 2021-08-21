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
						<h4 class="page-title">기숙사 입주 대기자 명단</h4>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="card-box">
						<div class="card-block">
							<form name="insertDomitory" method="POST">
								<table class="display datatable table table-stripped">
									<thead align="center">
										<tr bgcolor="FFBC35">
											<th>대기번호</th>
											<th>대기자 사번</th>
											<th>대기자명</th>
											<th>현재 대기 상태</th>
											<th>입주희망일</th>
											<th>입주 처리</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="domitoryWaitList"
											items="${ requestScope.domitoryWaitList }">
											<tr>
												<td><c:out value="${ domitoryWaitList.waitingNo }" /></td>
												<td><c:out value="${ domitoryWaitList.memberNo }" /></td>
												<td><c:out value="${ domitoryWaitList.memberName }" /></td>
												<td><c:out value="${ domitoryWaitList.waitingStatus }" /></td>
												<td><c:out value="${ domitoryWaitList.hopeDate }" /></td>
												<td><button type="button"
														class="btn btn-primary btn-sm">
														<i class="fa fa-plus"></i> 해당 대기자 입주처리
													</button></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<jsp:include page="../common/navbar.jsp" />
							</form>
						</div>
					</div>
				</div>
				<div class="m-t-20 text-center">
					<button type="reset" class="btn btn-primary" id="goBack">돌아가기</button>
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
					var yn;
					yn = confirm('해당 인원 입주 처리를 완료하시겠습니까?\n처리 후에는 수정이 불가합니다');

					if (yn == true) {
					const no = this.parentNode.children[0].innerText;
					location.href = "${ pageContext.servletContext.contextPath }/mng/welfare/domitory/selectOne?domitoryNo=" + ${ domitoryNo } + "&waitingNo=" + no;
					
					} else {
						location.href = "${ pageContext.servletContext.contextPath }/mng/welfare/domitory/select";
					}
				}

			}
		}
		const $goBack = document.getElementById("goBack");

		$goBack.onclick = function() {
			location.href = "${ pageContext.servletContext.contextPath }/mng/welfare/domitory/select"
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