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
						<h4 class="page-title">휴가수동발생</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-md-8 col-md-offset-2">
						<form name="insertSelfDev"
							action="${ pageContext.servletContext.contextPath }/mng/holiday/manual/insert""
							method="POST" onsubmit="return askAgain();">
							<div class="form-group col-xs-6">
								<label>부서</label> <input name="jobName" class="form-control"
									type="text"
									value="${ requestScope.memberInfo.department.deptName }"
									readonly="readonly"/>
							</div>
							<div class="form-group  col-xs-6">
								<label>직위</label> <input name="name" class="form-control"
									type="text" value="${ requestScope.memberInfo.job.jobName}"
									readonly="readonly"/>
							</div>
							<div class="form-group  col-xs-6">
								<label>사번</label> <input name="memberNo" class="form-control"
									type="text" value="${ requestScope.memberInfo.memberNo }"
									readonly="readonly" />
							</div>
							<div class="form-group  col-xs-6">
								<label>사원명</label> <input name="deptName" class="form-control"
									type="text" value="${ requestScope.memberInfo.name }"
									readonly="readonly"/>
							</div>
							<div class="form-group  col-xs-6">
								<label>종류</label>  <input name="giveType" class="form-control"
									type="text" value="수동지급"
									readonly="readonly"/>
							</div>
							<div class="form-group  col-xs-6">
								<label>발생일수</label> <input name="dayNumber" class="form-control"
									type="number"  required="required"
									/>
							</div>
							<div class="form-group col-xs-12">
							
									<label>사유</label>
									<div class="col-lg-12">
										<textarea name="note" rows="5" cols="5" class="form-control"
											placeholder="사유를 입력해주세요"  required="required"></textarea>
									</div>
								</div>
							
							<div class="m-t-20 text-center">
								<button class="btn btn-primary btn-lg" type="submit">휴가발생</button>
								<button type="reset" class="btn btn-primary btn-lg" id="goBack">돌아가기</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		function testChange(obj) {
			var result = $('#document option:selected').val();
			if (result == 1) {
				$('#marrige').show();
				$('#childBirth').hide();
				$('#sixtyBirth').hide();
				$('#death').hide();
			} else if (result == 2) {
				$('#marrige').hide();
				$('#childBirth').show();
				$('#sixtyBirth').hide();
				$('#death').hide();
			} else if (result == 3) {
				$('#marrige').hide();
				$('#childBirth').hide();
				$('#sixtyBirth').show();
				$('#death').hide();
			} else if (result == 4) {
				$('#marrige').hide();
				$('#childBirth').hide();
				$('#sixtyBirth').hide();
				$('#death').show();
			}
		}

		const $goBack = document.getElementById("goBack");

		$goBack.onclick = function() {
			if (!confirm("돌아가시겠습니까?\n작성중이던 모든 내용이 삭제됩니다.")) {

			} else {
				location.href = "${ pageContext.servletContext.contextPath }/mng/holiday/manual/select"
			}
		}

		function askAgain() {

			var yn;
			yn = confirm('작성한 내용으로 휴가를 수동발생시키겠습니까?');

			if (yn == true) {
				return true;
			} else if (yn == false) {
				return false;
			}
		}
	</script>
</body>

</html>