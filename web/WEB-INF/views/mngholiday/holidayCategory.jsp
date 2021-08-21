<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="${ pageContext.servletContext.contextPath }/assets/img/favicon.png">
<title>Wonderful Welfare Workspace</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>

<body>
	<div class="main-wrapper">
	    <jsp:include page="../common/navbar.jsp" />
		<div class="page-wrapper">
			<div class="content container-fluid">
				<div class="row">
					<div class="col-xs-8">
						<h4 class="page-title">휴가유형 관리</h4>
					</div>

					<!-- 복지신청으로 이동하는 버튼 -->
					<div class="col-xs-4 text-right m-b-30">
						<a
							href="${ pageContext.servletContext.contextPath }/mng/holiday/category/insert"
							class="btn btn-primary rounded pull-right"><i
							class="fa fa-plus"></i> 휴가유형 추가하기</a>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="card-box">
						<div class="card-block">
							<table class="display datatable table table-stripped">
								<thead>
									<tr bgcolor="FFBC35">
										<th>번호</th>
										<th>유형명</th>
										<th>분류</th>
										<th>비고</th>
										<th>활성화여부</th>
										<th>상세보기</th>
									</tr>

								</thead>
								<c:forEach var="holidayType" items="${ requestScope.holidayTypeList }">
									<tr>
										<td><c:out value="${ holidayType.holidayCode }" /></td>
										<td><c:out value="${ holidayType.holidayName }" /></td>
										<td><c:out value="${ holidayType.holidayType }" /></td>
										<td><c:if test="${ holidayType.holidayNote != null}">
										<c:out value="${ holidayType.holidayNote }" /></c:if>
										<c:if test="${ holidayType.holidayNote == null}">
										<c:out value="비고없음" /></c:if>
										</td>
										<td><c:if test="${ holidayType.useYn == 'Y'}"><button id="delete" class="btn btn-success btn-xs"
												type="submit">활성</button></c:if>
										<c:if test="${ holidayType.useYn == 'N'}"><button id="delete" class="btn btn-danger btn-xs"
												type="submit">비활성</button></c:if>
										</td>
										<td><button id="delete" class="btn btn-success btn-xs"
												type="submit">상세보기/수정하기</button></td>
									</tr>
								</c:forEach>
							</table>
								<div align="right" style="margin-right: 20px; padding: 5px">
								<a
							href="${ pageContext.servletContext.contextPath }/mng/holiday/category/delete"
							class="btn btn-danger pull-right"><i
							class="fa fa-minus"></i>휴가유형 삭제</a>
								</div>
								<br><br>
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
					location.href = "${ pageContext.servletContext.contextPath }/mng/holiday/category/update?no="
							+ no;
				}
			}
		}
		const $goBack = document.getElementById("goBack");

		$goBack.onclick = function() {
			location.href = "${ pageContext.servletContext.contextPath }"
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