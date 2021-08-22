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
						<h4 class="page-title">기숙사 입주/퇴거 관리</h4>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="card-box">
						<div class="card-block">
							<form name="insertDomitory"method="POST">
								<table class="display datatable table table-stripped" >
									<thead align="center">
										<tr bgcolor="FFBC35">
											<th>번호</th>
											<th>종류</th>
											<th>동</th>
											<th>호</th>
											<th>현재 이용중인 인원</th>
											<th>최대 인원</th>
											<th>대기자 입거</th>
											<th>입주자 퇴거</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach var="domitory"
										items="${ requestScope.domitoryList }">
										<tr>
											<td><c:out value="${ domitory.domitoryManageNo }" /></td>
											<td>기숙사</td>
											<td><c:out value="${ domitory.domitoryBlockNo }" /> 동</td>
											<td><c:out value="${ domitory.roomNo }" /> 호</td>
											<td><c:out value="${ domitory.currCapacity }" /> 명</td>
											<td><c:out value="${ domitory.maxCapacity }" /> 명</td>
											<td><button type="button" id="I${ domitory.domitoryManageNo }" class="btn btn-primary btn-sm" value="${ domitory.domitoryManageNo }"><i class="fa fa-plus"></i> 대기자 입거</button></td>
											<td><button type="button" id="D${ domitory.domitoryManageNo }" class="btn btn-danger btn-sm" value="${ domitory.domitoryManageNo }"><i class="fa fa-minus"></i> 입주자 퇴거</button></td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
							</form>
						</div>
					</div>
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
		/*------------------------------입거 버튼--------------------------------------------------*/

		const $insertDomitory1 = document.getElementById("I1");

		$insertDomitory1.onclick = function() {
			location.href = "${ pageContext.servletContext.contextPath }/mng/welfare/domitory/update?no=1"
		}
		
		const $insertDomitory2 = document.getElementById("I2");

		$insertDomitory2.onclick = function() {
			location.href = "${ pageContext.servletContext.contextPath }/mng/welfare/domitory/update?no=2"
		}
		
		const $insertDomitory3 = document.getElementById("I3");

		$insertDomitory3.onclick = function() {
			location.href = "${ pageContext.servletContext.contextPath }/mng/welfare/domitory/update?no=3"
		}
		const $insertDomitory4 = document.getElementById("I4");

		$insertDomitory4.onclick = function() {
			location.href = "${ pageContext.servletContext.contextPath }/mng/welfare/domitory/update?no=4"
		}
		
		/*------------------------------퇴거 버튼--------------------------------------------------*/
		const $deleteDomitory1 = document.getElementById("D1");

		$deleteDomitory1.onclick = function() {
			location.href = "${ pageContext.servletContext.contextPath }/mng/welfare/domitory/delete?no=1"
		}
		
		const $deleteDomitory2 = document.getElementById("D2");

		$deleteDomitory2.onclick = function() {
			location.href = "${ pageContext.servletContext.contextPath }/mng/welfare/domitory/delete?no=2"
		}
		
		const $deleteDomitory3 = document.getElementById("D3");

		$deleteDomitory3.onclick = function() {
			location.href = "${ pageContext.servletContext.contextPath }/mng/welfare/domitory/delete?no=3"
		}
		const $deleteDomitory4 = document.getElementById("D4");

		$deleteDomitory4.onclick = function() {
			location.href = "${ pageContext.servletContext.contextPath }/mng/welfare/domitory/delete?no=4"
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