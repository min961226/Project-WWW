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
						<h4 class="page-title">물품 관리 현황</h4>
					</div>

					<!-- 복지신청으로 이동하는 버튼 -->
					<div class="col-xs-4 text-right m-b-30">
						<a
							href="${ pageContext.servletContext.contextPath }/mng/welfare/laptopRental/insert"
							class="btn btn-primary rounded pull-right"><i
							class="fa fa-plus"></i> 복지 물품 추가하기</a>
					</div>
				</div>
			</div>
			<div class="search-area" align="right">
                   <form id="loginForm" action="${ pageContext.servletContext.contextPath }/mng/welfare/laptopRental/select" method="get" style="display:inline-block">      
                   <input type="hidden" name="currentPage" value="1">
                  <select id="searchCondition" name="searchCondition">
                      <option value="category" ${ requestScope.selectCriteria.searchCondition eq "category"? "selected": "" }>품목 종류</option>
                      <option value="name" ${ requestScope.selectCriteria.searchCondition eq "name"? "selected": "" }>품명</option>
                     <option value="status" ${ requestScope.selectCriteria.searchCondition eq "status"? "selected": "" }>대여 상태</option>
                  </select>
                  <input type="search" id="searchValue" name="searchValue" value="<c:out value="${ requestScope.searchValue }"/>">
               <button type="submit" class="btn-primary btn-sm" >검색하기</button>
               <!-- <button type="button" id="writeFree">작성하기</button> -->
               </form>
            </div>
			
			<div class="row">
				<div class="col-lg-12">
					<div class="card-box">
						<div class="card-block">
							<table class="display datatable table table-stripped">
								<thead>
									<tr bgcolor="FFBC35">
										<th>번호</th>
										<th>종류</th>
										<th>품명</th>
										<th>대여 상태</th>
										<th>상세보기</th>
									</tr>

								</thead>
								<c:forEach var="laptopList" items="${ requestScope.laptopList }">
									<tr>
										<td><c:out value="${ laptopList.itemNo }" /></td>
										<td><c:out value="${ laptopList.itemCateGory }" /></td>
										<td><c:out value="${ laptopList.itemName }" /></td>
										<td><c:out value="${ laptopList.reservationStatus }" /></td>
										<td><button id="delete" class="btn btn-success btn-xs"
												type="submit">상세보기/회수하기</button></td>
									</tr>
								</c:forEach>
							</table>
								<div align="right" style="margin-right: 20px; padding: 5px">
								<a
							href="${ pageContext.servletContext.contextPath }/mng/welfare/laptopRental/delete"
							class="btn btn-danger pull-right"><i
							class="fa fa-minus"></i> 물품 삭제</a>
								</div>
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

				$tds[i].onclick = function() {
					/* 게시물 번호까지 알아왔으니 이제 상세보기는 할 수 있겠지? */
					const no = this.parentNode.children[0].innerText;
					location.href = "${ pageContext.servletContext.contextPath }/mng/welfare/laptopRental/selectOne?no="
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