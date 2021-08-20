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
					<div class="col-md-8 col-md-offset-2">
						<h4 class="page-title">복지 물품 추가</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-md-8 col-md-offset-2">
						<form name="insertLaptopRental"
							action="${ pageContext.servletContext.contextPath }/mng/welfare/laptopRental/insertComplete" method="post" onsubmit="return askAgain();">
							<div class="form-group">
								<label>추가 품목 번호</label> <br> <input name="itemNo"
									class="form-control" type="text" width="100px"
									readonly="readonly" value="${ requestScope.nextItemNo }">
							</div>
							<div class="form-group">
								<label>품목 종류</label> <br> <input name="itemCategory"
									class="form-control" type="text" width="100px" required="required">
							</div>
							<div class="form-group">
								<label>대여 품목명</label> <br> <input name="itemName"
									class="form-control" type="text" width="100px" required="required">
							</div>
							<div class="m-t-20 text-center">
								<button class="btn btn-primary btn-lg" type="submit">추가
									완료</button>
								<button type="reset" class="btn btn-primary btn-lg" id="goBack">돌아가기</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		const $goBack = document.getElementById("goBack");
		$goBack.onclick = function() {
			if (!confirm("돌아가시겠습니까?\n작성중이던 모든 내용이 삭제됩니다.")) {

			} else {
				location.href = "${ pageContext.servletContext.contextPath }/mng/welfare/laptopRental/select"
			}

		}
		function askAgain() {

			var yn;
			yn = confirm('복지 물품추가를 완료하시겠습니까?\n신청 후에는 수정이 불가합니다');

			if (yn == true) {
				return true;
			} else if (yn == false) {
				return false;
			}
		}
	</script>
</body>

</html>